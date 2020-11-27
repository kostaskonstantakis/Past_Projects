'use strict';
 function psw_check(){
	var psw = document.getElementById("password").value;
	var psw_confirm = document.getElementById("confirm").value;
	
	if (psw === psw_confirm)
	{
		
            document.getElementById("psw_msg").innerHTML = "";// no error message if passwords match
            return 1;
	}
	else
	{
		document.getElementById("psw_msg").innerHTML = "Password doesn't match";//error message if passwords dont match
	}
	
	return 0;
        
}
 function registerData(){
   var  code=psw_check();
   if (code == 1)
   {
  var username = document.getElementById("username").value;
  username = xss(username);
         var email = document.getElementById("email").value;console.log(username);
    email = xss(email);
         console.log(email);
    
    var password = document.getElementById("password").value;
    console.log(password);
  password = xss(password);
    var fname  = document.getElementById("fname").value;
    console.log(fname);
    fname = xss(fname);
    var lname  = document.getElementById("lname").value;
    lname = xss(lname);
         var gender; console.log(lname);
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
   city  = xss(city);
         console.log(city);
    var work  = document.getElementById("Work").value;
    console.log(work);
    work = xss(work);
    var intrs  = document.getElementById("Interests").value;
    console.log(intrs);
    intrs = xss(intrs);
    var info  = document.getElementById("Info").value;
    console.log(info);
    info = xss(info);
   
    var myData = '&username=' + username + '&email='+email+'&password='+password+ '&confirm=' + confirm +'&fname='+fname+ '&addr='+addr + '&date='+date+ '&country='+country +'&info='+info
    + '&lname='+lname+ '&gender='+gender+ '&work='+work+ '&city='+city + '&intrs='+intrs;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(request.readyState === 4 && request.status === 200){
    
            var page = document.getElementById("content_index");
        page.innerHTML = request.responseText;
        }
    };
    request.open('POST','myServlet');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send(myData);
   }
}