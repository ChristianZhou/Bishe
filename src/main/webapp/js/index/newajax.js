$(function(){
	$.ajax({  
        url: 'JSONServlet',  
        type: 'GET',  
        contentType:"application/json" ,
        cache: false,
        success: function(person){
			var msg = "person name=" + person.name + ";age=" + person.age + ";address=" + person.address;
			document.getElementById("content").innerHTML= msg;
        }
    });
});