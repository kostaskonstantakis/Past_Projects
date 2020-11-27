/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var img = document.createElement("img");
var image64;

var activeUsers = new Array();
var activeUsername ="" ;
function showMyAccount(){
      
       var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          
             var Data=document.getElementById("logindex");
            Data.innerHTML=request.responseText;  
             
        }
    };
    request.open("GET", "showMyAccount",true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(); 
  refreshCookie();
  
    
};

function editAccount(){
       var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          
             var Data=document.getElementById("logindex");
            Data.innerHTML=request.responseText;  
             
        }
    };
    request.open("GET", "editAccount",true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(); 
      refreshCookie();
}

function updateData(){
    var username = document.getElementById("username").value;
     username = xss(username);
    console.log(username);
    var email = document.getElementById("email").value;
   
     email = xss(email);
    console.log(email);
    var password = document.getElementById("password").value;
    
    password = xss(password);
    console.log(password);
    var fname  = document.getElementById("fname").value;
    
     fname = xss(fname);
    console.log(fname);
    var lname  = document.getElementById("lname").value;
    var gender; 
     lname = xss(lname);
    console.log(lname);
    var genderarray=document.getElementsByName("gender");
    for (var i = 0; i <genderarray.length; i++)
    {
     if (genderarray[i].checked)
     {
      gender = genderarray[i].value;

      break;
     }
    }
     console.log(gender);
     var addr  = document.getElementById("Address").value;
   
    addr = xss(addr);
    console.log(addr);
    var date  = document.getElementById("date").value;
    console.log(date);
     var country  = document.getElementById("countries").value;
   
    country = xss(country);
    console.log(country);
    var city  = document.getElementById("City").value;
    
    city = xss(city);
    console.log(city);
    var work  = document.getElementById("Work").value;
    work = xss(work);
     console.log(work);
    var intrs  = document.getElementById("Interests").value;
    intrs = xss(intrs);
    console.log(intrs);
    var info  = document.getElementById("Info").value;
    info =  xss(info);   
    console.log(info);
    var myData = '&username=' + username + '&email='+email+'&password='+password+ '&confirm=' + confirm +'&fname='+fname+ '&addr='+addr + '&date='+date+ '&country='+country +'&info='+info
    + '&lname='+lname+ '&gender='+gender+ '&work='+work+ '&city='+city + '&intrs='+intrs;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(request.readyState === 4 && request.status === 200){
    
            var page = document.getElementById("logindex");
        page.innerHTML = request.responseText;
        }
    };
    request.open('POST','uptadeData');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(myData);
   }

function showAllUsers(){
   // var myJsonString = JSON.stringify(activeUsername);
   // console.log(myJsonString);
    var params = "&users="+activeUsername; console.log(params);
        var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          
            var Data=document.getElementById("logindex");
            Data.innerHTML=request.responseText;  
             
        }
    };
    request.open('POST', "AllUsers", true);
   request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(params); 
    
}

function xss( string){
 console.log("******************************************");
var flagt = string.includes("<");
var flags = string.includes(">");
 var ret = string;
 console.log(ret);
if( flagt == true || flags == true )
{    
    console.log("hack attempt");
    var i = 0;
    for(i=0; i< string.length; i++)
    {
        ret = ret.replace(">","");
      //     console.log(ret);
        ret = ret.replace("<","");
        //  console.log(ret);
    }
    console.log(flagt);
    console.log("potato")
    console.log(ret);
    console.log("******************************************");
    return ret;
    }
 console.log("******************************************");
   refreshCookie();
return ret;
}

function checkCookies(){
    
            var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          console.log("hey");
            var Data=document.getElementById("content_index");
            Data.innerHTML=request.responseText;  
             
        }
    };
    request.open("GET", "cookieCheck", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(); 
}

function logout(){
        
       var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("content_index");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','logout');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    
}

function showPost(){
        
    
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("content_index");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','showPost');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    
}


function showPostUrl(){
        
    
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("postbox");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','showPostUrl');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    
}

function showPostFile(){
        
    
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("postbox");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','showPostFile');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
      refreshCookie();
}


function displayUrl(){
  
   var url = document.getElementById("urllini").value;
   var can =    document.getElementById("canvas");
   can.innerHTML = "";
  
    var img = new Image();
        img.crossOrigin="anonymous" ;
    img.src = url;

    $('<canvas></canvas>').attr({
        'id' : 'url-image-canvas',
        'width' : '500px',
        'height' : '340px'
    }).appendTo('#canvas');

    var canvas = document.getElementById('url-image-canvas');
    
    img.onload = function(){
        if(canvas !== null)
        {
            var context = canvas.getContext('2d');
            context.drawImage(img,0,0,500,340);
            image64 = getImageAsBase64FromCanvas(canvas);
        }
    };
/*
if(url != ""){
 
      
    img = document.createElement("img");
  
     img.src = url;
     img.crossOrigin = "Anonymous";
    
     can.style.width = "200px";
     can.style.height  = "300px";
     img.setAttribute("height", "200");
    img.setAttribute("width", "300");
    
  can.appendChild(img);
   
   }
*/
}


function displayFileImage(){
  
var url = document.getElementById("fileImage").value;
 var can =    document.getElementById("canvas");
    can.innerHTML = "";

      var input = document.getElementById('fileImage');
   
    var file_image = input.files[0];
   
    var image_on_canvas = new Image();
   
    var binaryData = [];

    if(file_image !== null)
    {
      binaryData.push(file_image);
      image_on_canvas.src=URL.createObjectURL(new Blob(binaryData, {type: "application/zip"}));
     
       
        $('<canvas></canvas>').attr({
            'id' : 'image_from_file_canvas',
            'width' : '500px',
            'height' : '340px'
        }).appendTo('#canvas');

        var canvas_show_element = document.getElementById('image_from_file_canvas');
       
        image_on_canvas.onload = function(){
            if(canvas_show_element !== null)
            {
     
                var context = canvas_show_element.getContext('2d');

                context.drawImage(image_on_canvas,0,0,500,340);

               image64 = getImageAsBase64FromCanvas(canvas_show_element);
            }
        };
  }
}

function getLocationpost() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPositionPost);
  } 
}
var long,lang;
function showPositionPost(position) {
  lang =  position.coords.latitude; 
  long = position.coords.longitude;
  
}
function getImageAsBase64FromCanvas(canvas) {
    
    return canvas.toDataURL('image/jpeg', 1.0).split(',')[1];
}


function addPost(){
    getLocationpost();
    var description = document.getElementById("textpost").value;
    if (description != "")
    { 
        var parameters = 'description=' + description+ '&long=' +long + "&lang=" + lang + "&image64="+image64;
           var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(request.readyState === 4 && request.status === 200){
           
            var page = document.getElementById("content_index");
        page.innerHTML = request.responseText;
        }
    };
    request.open('POST','addPost');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(parameters);
    }
    else
    {
       
       
    }
      refreshCookie();
}


  function createAndModifyDivs() {
      
 
  
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
      if(this.readyState === 4 && this.status === 200){
      var json = JSON.parse(this.responseText);
      console.log(json);
      var board = document.getElementById("logindex"),
              
      myDivs = [],
      i = 0,
      numOfDivs = 9;
       document.getElementById("logindex").innerHTML = "";
       
       
       var image = new Image();
    var divid ,name;
    for (i; i < numOfDivs; i += 1) {
        console.log("*********************");
          var boardDiv = document.createElement("div");
          console.log(json.posts[i]);
          name = "top10id" + i;
          boardDiv.id = name;
        boardDiv.className = "top10div";
       // boardDiv.innerText = "I am new DIV" ;
       image.src =json.posts[i].base64 ;
      console.log(image.src);
       boardDiv.innerHTML  = 
          
                        "<div>"+ "<h4>" + json.posts[i].username+"</h4> <br> \n"+"<h4>" +
       json.posts[i].date+"</h4>\n <br>"+"<h4>" +
       json.posts[i].id+"</h4><br>\n"+"<h4>" +
       json.posts[i].description+"</h4><br>\n"+
     "<div>"+
"<textarea id =\""+json.posts[i].id +"area\" rows=\"4\" cols=\"50\">Write a comment</textarea>"+
	   "<select>"+
  "<option value=\"1\">1</option>"+
  "<option value=\"2\">2</option>"+
  "<option value=\"3\">3</option>"+
 " <option value=\"4\">4</option>"+
  " <option value=\"5\">5</option>"+
"</select>"+
	   "<button id =\""+json.posts[i].id +"\"class=\"buttonpost\" onClick =\"addComment(this.id)\">Comments</button>\n"+
	   "</div>"+
       "<button id =\""+json.posts[i].id +"\"class=\"buttonpost\" onClick =\"detailPost(this.id)\">View Post!</button>\n"
	  
	 "</div>";
	
        image.style.height= "200px";
        image.style.width= "300px";
        
      myDivs.push(boardDiv);
      board.appendChild(myDivs[i]);
       divid = document.getElementById(name).innerHTML;
       if(image.src != "" ){
         document.getElementById(name).appendChild(image);
             
      /*var $img = $("<img/>");
    $img.attr("src", "data:image/png;base64," + json.posts[i].base64);
    $("#"+name).append($img);
      /*/
      
                }console.log("*********************");
    }
    
        }
    };
    request.open('GET','getTop10');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    refreshCookie();
    

   
  }

function showTopPost(){
    createAndModifyDivs();
}

function showPofile(id){
var parameters = "&username=" + id ;
var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
         var json = JSON.parse(this.responseText);
      console.log(json);
      var board = document.getElementById("logindex"),
              
      myDivs = [],
      i = 0,
      numOfDivs = json.length;
       document.getElementById("logindex").innerHTML = "";
       
       var user = json.user;
       
       if(user === "same")
       {
           showMyPofile();
       }
       else 
       {
       var image = new Image();
    var divid ,name;
    for (i; i < numOfDivs; i += 1) {
        console.log("*********************");
          var boardDiv = document.createElement("div");
          //console.log(json.posts[i]);
          name = "top10id" + i;
          boardDiv.id = name;
        boardDiv.className = "top10div";
       // boardDiv.innerText = "I am new DIV" ;
       image.src =json.posts[i].base64 ;
      //console.log(image.src);
      
        boardDiv.innerHTML  = 
               " <header>\n"+
                        "<div id = \"signup_box\" class=\"head\">\n" +
                        "	<input type=\"button\" class = \"hbutton \" value = \"Logout\" onClick = \"logout()\">\n" +
                        "	<input type=\"button\" class = \"hbutton \"  value = \"Show users\" onClick = \"showAllUsers()\">\n" +
                         "	<input type=\"button\" class = \"hbutton \"  value = \"My Posts\" onClick = \"showMyPofile()\">\n" +
                        "	</div>\n" +
                        " <div id = \"signup_box2\" class = \"head\" >"+
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Create Post\" onClick = \"showPost()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"My Account\" onClick = \"showMyAccount()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Top Posts\" onClick = \"showTopPost()\">\n" +
                        " </div>"+
                        "   <div id = \"title\" class=\"head\">\n" +
                        "	<h1>GLORIOUS WEBSITE</h1>\n" +
                        "   </div>\n" +
                        "	\n" +
                        " </header>\n" +
                    "<div>"+ "<h4>" + json.posts[i].username+"</h4> <br> \n"+"<h4>" +
       json.posts[i].date+"</h4>\n <br>"+"<h4>" +
       json.posts[i].id+"</h4><br>\n"+"<h4>" +
       json.posts[i].description+"</h4><br>\n"+
       "<div id = \"myPostsbut\" >"+
       
       "<button id=\""+json.posts[i].id +"\" class=\"buttonpost\"  onclick= \"detailPost(this.id)\"   >View post!</button>\n"+
       
         
       "</div>" 
                 +"</div>";
     
      myDivs.push(boardDiv);
      board.appendChild(myDivs[i]);
       divid = document.getElementById(name).innerHTML;
    console.log("*********************");
        }
    }
    };
    request.open('POST','printUserProfile');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(parameters);
      refreshCookie();
}




function showMyPofile(){

var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
         var json = JSON.parse(this.responseText);
      console.log(json);
      var board = document.getElementById("logindex"),
              
      myDivs = [],
      i = 0,
      numOfDivs = json.length;
       document.getElementById("logindex").innerHTML = "";
       
       
       var image = new Image();
    var divid ,name;
    for (i; i < numOfDivs; i += 1) {
        console.log("*********************");
          var boardDiv = document.createElement("div");
          console.log(json.posts[i]);
          name = "top10id" + i;
          boardDiv.id = name;
        boardDiv.className = "top10div";
       // boardDiv.innerText = "I am new DIV" ;
       image.src =json.posts[i].base64 ;
      console.log(image.src);
       boardDiv.innerHTML  = 
               " <header>\n"+
                        "<div id = \"signup_box\" class=\"head\">\n" +
                        "	<input type=\"button\" class = \"hbutton \" value = \"Logout\" onClick = \"logout()\">\n" +
                        "	<input type=\"button\" class = \"hbutton \"  value = \"Show users\" onClick = \"showAllUsers()\">\n" +
                         "	<input type=\"button\" class = \"hbutton \"  value = \"My Posts\" onClick = \"showMyPofile()\">\n" +
                        "	</div>\n" +
                        " <div id = \"signup_box2\" class = \"head\" >"+
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Create Post\" onClick = \"showPost()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"My Account\" onClick = \"showMyAccount()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Top Posts\" onClick = \"showTopPost()\">\n" +
                        " </div>"+
                        "   <div id = \"title\" class=\"head\">\n" +
                        "	<h1>GLORIOUS WEBSITE</h1>\n" +
                        "   </div>\n" +
                        "	\n" +
                        " </header>\n" +
                    "<div>"+ "<h4>" + json.posts[i].username+"</h4> <br> \n"+"<h4>" +
       json.posts[i].date+"</h4>\n <br>"+"<h4>" +
       json.posts[i].id+"</h4><br>\n"+"<h4>" +
       json.posts[i].description+"</h4><br>\n"+
       "<div id = \"myPostsbut\" >"+
       "<button id=\""+json.posts[i].id +"\" class=\"buttonpost\" onCLick = \"deletePost(this.id)\">Delete post!</button>\n"+
       "<button id=\""+json.posts[i].id +"\" class=\"buttonpost\"  onclick= \"detailPost(this.id)\"   >View post!</button>\n"+
       
         
       "</div>" 
                 +"</div>";
   
        
      myDivs.push(boardDiv);
      board.appendChild(myDivs[i]);
      
      /*var $img = $("<img/>");
    $img.attr("src", "data:image/png;base64," + json.posts[i].base64);
    $("#"+name).append($img);
      /*/
               console.log("*********************");
        }
    };
    request.open('GET','printMyprofile');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    refreshCookie();
}


function deletePost(id){
    
    var params = "&id=" + id;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){   
        if(this.readyState === 4 && this.status === 200){
     
        }
    };
    request.open('POST','deletePost');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(params);
    showMyPofile();
      refreshCookie();
}


function detailPost(id){
    window.open( 'mapwindow.html?'+id);
   
  
}

function a(){
    var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.replace("?","");
    console.log(queryString);
   
       var params = "&id=" + queryString;
       
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){ 
        if(request.readyState === 4 && request.status === 200){
        var json = JSON.parse(this.responseText);
        console.log(json);
        var map = document.getElementById("mapdiv");
        var coordinatesx,coordinatesy;
					coordinatesx = json.long;// get cordinates
					coordinatesy = json.lat;
					console.log(coordinatesx);
					console.log(coordinatesy);
					var map2 = new OpenLayers.Map("mapdiv2");
					map2.addLayer(new OpenLayers.Layer.OSM());
					var lonLat = new OpenLayers.LonLat( coordinatesx ,coordinatesy ).transform(new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
						map2.getProjectionObject() // to Spherical Mercator Projection
						);
					var zoom=16;
					var markers = new OpenLayers.Layer.Markers( "Markers" );
					map2.addLayer(markers);
					markers.addMarker(new OpenLayers.Marker(lonLat));
					map2.setCenter (lonLat, zoom);
          console.log(map2);
        map.innerHTML =   
                "<div><h1>"+json.username +"</h1> <br>" +
        "<h1>"+json.id +"</h1> <br>"         +
        "<h1>"+json.description +"</h1> <br>" +
        "<h1>"+json.lat +"</h1> <br>" +
        "<h1>"+json.long +"</h1> </div>" +
        "<img style=\"display:block; width:100px;height:100px;\" id=\"base64image\" src=" + json.base64 + " />" 
        ;
       
    
        }
    };
    request.open('POST','detailProfile');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(params);
  refreshCookie();  
}

function deleteAccount(){
    var txt;
    var r = confirm("Are you sure you want to delete your profile?");
     if (r == true) {
    txt = "You pressed OK!";
        var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          
         //    var Data=document.getElementById("logindex");
           // Data.innerHTML=request.responseText;  
             logout();
        }
    };
    request.open("GET", "deleteUser",true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(); 

  } else {
    txt = "You pressed Cancel!";
  }
  refreshCookie();
  console.log(txt);
}


function refreshCookie(){
          var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          
        console.log("refresh cookie");
        }
    };
    request.open("GET", "refreshCookie",true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(); 

}

function addActiveUser(name){
    var length =activeUsers.length ;
  var minutes = 1000 * 60;
 
 console.log("**********");
  var d = new Date();
  var t= d.getTime();
    var y = Math.round(t / minutes);
    console.log("time " +y);
  y = y + (60*3);
  console.log("time " +y);
  console.log("*************");
for (i =0; i < length; i++){
      console.log(activeUsers[i][0]);
       if(activeUsers[i][0] == name)
       {
           activeUsers[i][1] = y ;
           refreshUsers();
           return;
       }
   }
//changed to 3 min for testing purposes
  var status= 0;
activeUsers.push([name,y,status]);
console.log(activeUsers);
console.log("**********");
refreshUsers();
}


function refreshUsers(){activeUsername = "";
    var length =activeUsers.length ;
    console.log(length);
  var minutes = 1000 * 60;   
  var d = new Date();
  var t= d.getTime();

  var y = Math.round(t / minutes);
    var i=0;
    var restult;
    console.log("time " +y);
    var time = [y];
    for (i =0; i < length; i++){
       console.log(activeUsers[i][1]);
       time = activeUsers[i][1];
       console.log(time);
       if(time > y)
       {
           console.log("active");
           activeUsers[i][2] = 1; 

             activeUsername = activeUsername + "@" + activeUsers[i][0];
           
       }
       else
       {
           console.log("not active");
           activeUsers[i][2] = 0; 
       }
       console.log("active "+activeUsers[i][2]);
    }
}
function addComment(id){
       var textbox = id+"area";
       var text = document.getElementById(textbox).value;
       console.log(text);
       
       
       
           var params = "&user=" + id+"&com=" + text;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){   
        if(this.readyState === 4 && this.status === 200){
     
        }
    };
    request.open('POST','addComment');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(params)
       
       
       
       
       
       
       
       
       
       
       
       
       
       
 }