'use strict';
function showform(){
	    var request = new XMLHttpRequest();
    request.onreadystatechange = function(){
        
        if(this.readyState === 4 && this.status === 200){
          var page = document.getElementById("content_index");
        page.innerHTML = this.responseText;
        }
    };
    request.open('GET','register');
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    request.send();
};
	