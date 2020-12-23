function translation(){
	$.ajax({
		type:"post",
		url:"translate.do",
		//Content-Type:application/x-www-form-urlencoded;
		data:{query:$("#input").val(),option:$("#text option:selected").val()},
		dataType:"json",
		success:function(response){
			//-------------------------------------------------------------------------------------------
		$("#show").text(response.answer);
		}
	});
}