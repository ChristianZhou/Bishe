/*
var userClick = function(){
	alert(1111)
};

$("#smt").on("click" , userClick);
*/

/*
var init = function(){
	$("#smt").on("click" , function(){
		alert(1111);
	});
}

$(init);
*/

$(function(){
	var k=2;
	var str="";
	$("#tijiao").on("click" , function(){
		for (var i = $("#material").children("input").length - 1; i >= 0; i--) {
			var ms=$("#material").children("input");
			var m=ms[i];
			str+=m.value+",";
			$("#str").val(str);
		}

	});
	
	
	$("#more").on("click" , function(){
		document.getElementById("material").innerHTML+="<input name='name'"+(++k)+"type='text' ><br><br>";
	});
	
	
	
	$("#smt").on("click" , function(){
		alert(1111);
	});
	
	//
	$("#ronly").on("click" , function(){
		$("#sometext").attr("readonly" , "readonly");
	})
	
	$("#conly").on("click" , function(){
		$("#sometext").removeAttr("readonly");
	})
	
	$("#show").on("click" , function(){
		alert($("#sometext").attr("id") + ":" + $("#sometext").val());
	})
	
	$("#schoose").on("click" , function(){
		alert($("#choose").val());
	})
	
	$("#cchoose").on("click" , function(){
		alert($("#choose").val(2));
	})
});



$(function(){
	// do something....
});