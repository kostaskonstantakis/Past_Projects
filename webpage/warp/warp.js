"use strict";  
      
function validateForm()
{
var formObj = document.forms.Form;
if ((formObj.username.value === "") ||
(formObj.mail.value ==="") ||
(formObj.password.value ==="") ||
(formObj.verification.value==="") ||
   (formObj.name.value==="") ||
       (formObj.surname.value==="")  ||
       (formObj.date_of_birth.value==="") || 
       (formObj.city.value==="") || 
        (formObj.occupation.value==="") ||
         (formObj.interests.value==="") || 
         (formObj.info.value==="") ) 
		 document.getElementById("form").innerHTML="You must fill all the fields here!"; 
else document.getElementById("form").innerHTML="Thank you for filling out this form!";			
}

function validatePassword()
{
   var my_form = document.forms.Form;
   var my_password=document.getElementById("psswrd").value;
   var my_verification=document.getElementById("psswrd_vrfctn").value;
   if(my_password!==my_verification) document.getElementById("psswrd_vrfctn").innerHTML="Please,retype the password correctly in both password related field!"; 
}
 
function uncheckOthers(id) //gotta find a more efficient way to do this!
{
	
	if(id==="1")
    {
        document.getElementById("1").checked=true;
        document.getElementById("2").checked=false;
        document.getElementById("3").checked=false;
    }
    
    else if(id==="2")
    {   
        document.getElementById("2").checked=true;
        document.getElementById("1").checked=false;
        document.getElementById("3").checked=false;
    }
    
    else if(id==="3")
    {
        document.getElementById("3").checked=true;
        document.getElementById("1").checked=false;
        document.getElementById("2").checked=false;
    }
	/*var gender = document.forms[0].gender;
	for (i = 0; i < gender.length; i++)
	{
     if (gender[i].checked)
	 {
	  if(i-1<0) break;
      gender[i-1].checked=false;
	  if(i+1>2) break;
	  gender[i+1].checked=false;
      gender[i+2].checked=false;
     }
    } */
}