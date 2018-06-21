$(function(){
	$("#trigger").on("click" , function(){
		alert($("#username").textbox("getValue"));
	});
});