'use strict';
var user;
function showlogin(){
    
    
    console.log("sup");
    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("content_index");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','loginpage');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
    
    
};

function login(){
    var uname = document.getElementById('usernamelog').value;
    
    var psw = document.getElementById('passwordlog').value;
  user = uname;
   var request = new XMLHttpRequest();
  var myData="ulog=" + uname + "&plog=" + psw;
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) 
        {
           var page = document.getElementById("content_index");
        page.innerHTML = this.responseText;
       console.log("user = " +user);
            addActiveUser(user);
        }
    };
    request.open("POST", "login");
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send(myData);
    
}
        
     
	