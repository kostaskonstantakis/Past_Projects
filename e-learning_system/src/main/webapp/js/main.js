'use strict';



var list;
var flag_map =0; // this is used to check if a map exists and we need to delete it
function initialize(){ // hide objects on load of logbook
	$("#trackLoc").hide();
	$("#picbox").hide();
	$("#picbutton_box").hide();
	
}
function initialize2(){ //hide objects on load of log in
	
	
	$("#buttonspic").hide();	
	
}

/*function that checks if the password is correct*/
function psw_check(){
	var psw = document.getElementById("password").value;
	var psw_confirm = document.getElementById("password2").value;
	
	if (psw === psw_confirm)
	{
		document.getElementById("psw_msg").innerHTML = "";// no error message if passwords match
	}
	else
	{
		document.getElementById("psw_msg").innerHTML = "Password doesn't match";//error message if passwords dont match
	}
	
	
	
}
function userbutton(){
	var name = document.getElementById("usernamelog").value;
	if(name.length > 7)		// if the lenght is not long enough we shouldnt display the radio button
	{
		$("#picbox").show();
	}
	else
	{
	
		$("#radiopic").prop("checked",false);
		$("#buttonspic").hide();	
	}

}

function photo_button(){
	var name = document.getElementById("username").value;
	if(name.length > 7)
	{
		$("#picbox").show();
	}
	else
	{
		$("#picbox").hide(); //hide button and map
		$("#picbutton_box").hide();
		$("#radiopic").prop("checked",false);
		$("#picbutton_box").hide();	
	}

}
function radio_user(){
$("#buttonspic").show();
}

function radio_button(){
$("#picbutton_box").show();
}



// function that generates a map that pings the location formed by the date the user has inserted

function map_generator(){
	

	var Http = new XMLHttpRequest();
	var addr = document.getElementById("Address").value;// this is how we get the value of the address and city
	var housenumber;
	var city = document.getElementById("City").value;
	var country =countries.options[countries.selectedIndex].text;// because the value of country is a code we check with the text that has the full name of the city
	var url = "https://nominatim.openstreetmap.org/search?q="+addr+"%2C+"+ city+"%2C+"+country+"%2C&format=geocodejson";// combine them in a url
	//alert(url);
	//console.log(url);
	Http.open("GET",url);
	Http.send();
	
	Http.onreadystatechange=function(){
		if(this.readyState == 4 && this.status == 200)// if the request is succesful
		{
			list = JSON.parse(this.responseText);
			if(list.features.length > 0){
				if( flag_map == 0){
					document.getElementById('map_box').style.height = " 500px";
					document.getElementById('work').style.marginTop = "200px";
					var coordinatesx,coordinatesy;
					coordinatesx = list.features["0"].geometry.coordinates[0];// get cordinates
					coordinatesy = list.features["0"].geometry.coordinates[1];
					console.log(coordinatesx);
					console.log(coordinatesy);
					var map = new OpenLayers.Map("map_box");
					map.addLayer(new OpenLayers.Layer.OSM());
					var lonLat = new OpenLayers.LonLat( coordinatesx ,coordinatesy ).transform(new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
						map.getProjectionObject() // to Spherical Mercator Projection
						);
					var zoom=16;
					var markers = new OpenLayers.Layer.Markers( "Markers" );
					map.addLayer(markers);
					markers.addMarker(new OpenLayers.Marker(lonLat));
					map.setCenter (lonLat, zoom);
					flag_map = 1;
				}
				else if ( flag_map == 0)
				{
					document.getElementById("mapdiv").innerHTML=""; // delete inner elements
					document.getElementById("mapbox").innerHTML="";
					flag_map = 0;
					map_generator();// call function again to apply the new map if there was one displayed before so they dont overlap
				}
			}
		}
		else
		{
			document.getElementById('work').style.marginTop = "10px";// reset page to original properties
			document.getElementById('map_box').style.height = "auto";
		}
	};
}
function checkmap(){
	var city = document.getElementById("City").value;
	if(city.length == 0)
	{
		document.getElementById('work').style.marginTop = "10px";
		document.getElementById('map_box').style.height = "auto";
		$("#trackLoc").hide(); //hide button and map
	}
	else
	{
		$("#trackLoc").show();
	}
}
// function that fills the address if the user wants 
var latitude,longitude;
function autofill(){
		var city,addr,county;
	var Http = new XMLHttpRequest();
		var url = "https://nominatim.openstreetmap.org/search/"+latitude+"%2C"+longitude+"?format=json&polygon=1&addressdetails=1" ;
		Http.open("GET",url);
		Http.send();
		Http.onreadystatechange=function(){
			if(this.readyState == 4 && this.status == 200)// if the request is succesful
			{
				var list = JSON.parse(this.responseText);
				var city = list[0].address.city;
				var country = list[0].address.country_code;
				addr = list[0].address.road;
				country = country.toUpperCase();
				console.log(country);
				document.getElementById("City").value =city;// set data returned by the request to the fields we want
				document.getElementById("countries").value =country;
				document.getElementById("Address").value =addr;
			}
		};
}
function showPosition(position) {
		
	var pos = position;
	latitude = position.coords.latitude;
	longitude = position.coords.longitude;
	console.log(longitude);
	console.log(latitude);
	autofill();
}


function track(){

	var list;
	var Position;
	if (navigator.geolocation) 
	{  
		
		navigator.geolocation.getCurrentPosition(showPosition);
		$("#trackLoc").show();
	} 
	else 
	{ 
		document.getElementById("city_msg").innerHTML = "Geolocation is not supported by this browser";
	}
}

/*
choice of request GeocodeJSON the address behaves very weirdly i dont understand why basic cases should work fine
*/
function address_validation(){
	//alert("sadf");
	var i = 0;
	var flag_country = 0,flag_city = 0,flag_addr =0; 
	var Http = new XMLHttpRequest();
	var addr = document.getElementById("Address").value;// this is how we get the value of the address and city
	var housenumber;
	var city = document.getElementById("City").value;
	var country =countries.options[countries.selectedIndex].text;// because the value of country is a code we check with the text that has the full name of the city
	var url = "https://nominatim.openstreetmap.org/search?q="+addr+"%2C+"+ city+"%2C+"+country+"%2C&format=geocodejson";// combine them in a url
	//alert(url);
	//console.log(url);
	Http.open("GET",url);
	Http.send();
	
	Http.onreadystatechange=function()
	{
		if(this.readyState == 4 && this.status == 200)// if the request is succesful
		{
			list = JSON.parse(this.responseText);
			console.log(list);
			//alert(list.features.length)
			if (list.features.length > 0 && city.length>0)
			{
				var string_location = list.features["0"].properties.geocoding["label"];/*get the real location*/
			
				var location_array = string_location.split(",");// split string into array with sub strings that contain address details
				while (i != location_array.length)
				{
					console.log(location_array[i]);
					location_array[i] = location_array[i].trim();// white space removal because if we dont comparison fails
					location_array[i] = location_array[i].toUpperCase();// make all letters caps so comparison doesnt fail
					i++;
				}
				i=0;
				country = country.toUpperCase();
				city = city.toUpperCase();
				addr = addr.toUpperCase();
				if(addr.length == 0)
				{
					flag_addr = 1;
				}
				while (i != location_array.length)
				{
		
					console.log(location_array[i]);
					if(location_array[i] == city )
						flag_city =1;
					if(location_array[i] == country )
						flag_country = 1;
					if(addr.length > 0){
						console.log(addr.length);
						if(location_array[i] == addr )
							flag_addr = 1;
					}
					i++;
				}
				console.log(flag_city);
				console.log(flag_country);
				console.log(flag_addr);
				if( flag_city ==1 && flag_country == 1 && flag_addr == 1)
					document.getElementById("city_msg").innerHTML = "";
				else
					document.getElementById("city_msg").innerHTML = "City/Address doesnt exist";
			}
			else
			{
				if(city.length > 0)
					document.getElementById("city_msg").innerHTML = "City doesnt exist";
				else
					document.getElementById("city_msg").innerHTML = "Add a city";
			}
		}
	};
}

function check(){
	psw_check();
	address_validation();
}



/*  face recognition that is based on faceplusplus service */
var faceRec = (function () {

  // Object that holds anything related with the facetPlusPlus REST API Service
  var faceAPI = {
    apiKey: 'l2jNgKbk1HXSR4vMzNygHXx2g8c_xT9c',
    apiSecret: '2T6XdZt4EYw-I7OhmZ6g1wtECl81e_Ip',
    app: 'hy359',
    // Detect
    // https://console.faceplusplus.com/documents/5679127
    detect: 'https://api-us.faceplusplus.com/facepp/v3/detect',  // POST
    // Set User ID
    // https://console.faceplusplus.com/documents/6329500
    setuserId: 'https://api-us.faceplusplus.com/facepp/v3/face/setuserid', // POST
    // Get User ID
    // https://console.faceplusplus.com/documents/6329496
    getDetail: 'https://api-us.faceplusplus.com/facepp/v3/face/getdetail', // POST
    // addFace
    // https://console.faceplusplus.com/documents/6329371
    addFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/addface', // POST
    // Search
    // https://console.faceplusplus.com/documents/5681455
    search: 'https://api-us.faceplusplus.com/facepp/v3/search', // POST
    // Create set of faces
    // https://console.faceplusplus.com/documents/6329329
    create: 'https://api-us.faceplusplus.com/facepp/v3/faceset/create', // POST
    // update
    // https://console.faceplusplus.com/documents/6329383
    update: 'https://api-us.faceplusplus.com/facepp/v3/faceset/update', // POST
    // removeface
    // https://console.faceplusplus.com/documents/6329376
    removeFace: 'https://api-us.faceplusplus.com/facepp/v3/faceset/removeface', // POST
};

  // Object that holds anything related with the state of our app
  // Currently it only holds if the snap button has been pressed
  var state = {
    photoSnapped: false,
  };

  // function that returns a binary representation of the canvas
  function getImageAsBlobFromCanvas(canvas) {

    // function that converts the dataURL to a binary blob object
    function dataURLtoBlob(dataUrl) {
      // Decode the dataURL
      var binary = atob(dataUrl.split(',')[1]);

      // Create 8-bit unsigned array
      var array = [];
      for (var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
      }

      // Return our Blob object
      return new Blob([new Uint8Array(array)], {
        type: 'image/jpg',
      });
    }

    var fullQuality = canvas.toDataURL('image/jpeg', 1.0);
    return dataURLtoBlob(fullQuality);

  }

  // function that returns a base64 representation of the canvas
  function getImageAsBase64FromCanvas(canvas) {
    // return only the base64 image not the header as reported in issue #2
    return canvas.toDataURL('image/jpeg', 1.0).split(',')[1];

  }

  // Function called when we upload an image
  function uploadImage() {
    if (state.photoSnapped) {
      var canvas = document.getElementById('canvas');
      var image = getImageAsBlobFromCanvas(canvas);

      // ============================================

      // TODO!!! Well this is for you ... YES you!!!
      // Good luck!

      // Create Form Data. Here you should put all data
      // requested by the face plus plus services and
      // pass it to ajaxRequest
      var data = new FormData();
      data.append('api_key', faceAPI.apiKey);
      data.append('api_secret', faceAPI.apiSecret);
      // add also other query parameters based on the request
      // you have to send
	   data.append('image_base64',getImageAsBase64FromCanvas(canvas));
      // You have to implement the ajaxRequest. Here you can
      // see an example of how you should call this
      // First argument: the HTTP method
      // Second argument: the URI where we are sending our request
      // Third argument: the data (the parameters of the request)
      // ajaxRequest function should be general and support all your ajax requests...
      // Think also about the handler
	  if(document.getElementById('usernamelog')!=null) // if username log is null that means the user is in the form so we need to detect the face
	  {
        ajaxRequest('POST', faceAPI.search, data); //search the face to auto fill name
      }
	  else
	  {
		ajaxRequest('POST', faceAPI.detect, data);// detect the face of the form
	  }

    } else {
      alert('No image has been taken!');
    }
  }

  // Function for initializing things (event handlers, etc...)
  function init() {
    // Put event listeners into place
    // Notice that in this specific case handlers are loaded on the onload event
    window.addEventListener('DOMContentLoaded', function () {
      // Grab elements, create settings, etc.
      var canvas = document.getElementById('canvas');
      var context = canvas.getContext('2d');
      var video = document.getElementById('video');
      var mediaConfig = {
        video: true,
      };
      var errBack = function (e) {
        console.log('An error has occurred!', e);
      };

      // Put video listeners into place
      if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices.getUserMedia(mediaConfig).then(function (stream) {
          video.srcObject = stream;
          video.onloadedmetadata = function (e) {
            video.play();
          };
        });
      }

      // Trigger photo take
      document.getElementById('snap').addEventListener('click', function () {
        context.drawImage(video, 0, 0, 640, 480);
        state.photoSnapped = true; // photo has been taken
      });

      // Trigger when upload button is pressed
      document.getElementById('upload').addEventListener('click', uploadImage);

    }, false);

  }

  // ============================================

  // !!!!!!!!!!! ================ TODO  ADD YOUR CODE HERE  ====================
  // From here on there is code that should not be given....

  // You have to implement the ajaxRequest function!!!!
  function ajaxRequest(method,url,data){
    
        var http=new XMLHttpRequest();console.log("3");
		http.open(method, url, true);
        http.send(data);
		http.onreadystatechange = function()
		{
			if (this.readyState == 4 && this.status == 200) {
			if(faceAPI.detect==url)
			{	
				var response=JSON.parse(http.responseText);
				var faceToken=response.faces[0].face_token;
				data.append('face_token',faceToken);
				var username=document.getElementById('username');
				data.append('user_id',username.value);
				data.delete('image_base64');
				data.append('outer_id','hy359');
				data.append('face_tokens',faceToken);
				data.delete('user_id');
				alert("face added successfull")
			}
			else
			{
			data.append('outer_id','hy359');
			var username=document.getElementById('usernamelog');
			var response=JSON.parse(http.responseText);
			username.value=response.results[0].user_id;
        
			}
          }
        };
        
      }
    

  
  

  // !!!!!!!!!!! =========== END OF TODO  ===============================

  // Public API of function for facet recognition
  // You might need to add here other methods based on your implementation
  return {
    init: init,
  };
})();