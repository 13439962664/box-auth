var moduleJSON = [];
var funMap = new Map();

$(document).ready(function(){ 
	bindFunTest(moduleJSON);
});

function submitRequst(){
	var url = JSON.parse($("#requestUrl").val());
	var data = JSON.parse($("#requestText").val());
	console.log("请求对象:");
	console.log(data);
	$.ajax({
        async : true,    //表示请求是否异步处理
        type : "post",    //请求类型
        url : url,
        dataType : "json",//返回的数据类型
        //数据，json字符串
        data : data,
        //请求成功
        success : function(result) {
        	$("#responseText").val(JSON.stringify(result));
        	console.log("返回对象:");
            console.log(result);
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function bindFunTest(moduleJSON){
	console.log(moduleJSON);
	$(moduleJSON).each(function (i) {
		var funGroup = this.funGroup;
		$(funGroup).each(function (i) {
			var ul = $("<ul></ul>");
			$("#left").append(ul);
			var h3 = $("<h3></h3>");
			ul.append(h3);
			h3.html(this.funGroupName);
			var fun = this.fun;
			$(fun).each(function (i) {
				var li = $("<li></li>");
				ul.append(li);
				var a = $("<a></a>");
				li.append(a);
				a.attr("id",this.funId);
				a.attr("class","funtest");
				a.html(this.funName);
				a.attr("href","javascript:void(0);");
				funMap.set(this.funId,this.funParam);
			});
		}) ;
    }) ;
	
	$(".funtest").bind("click", {"key":"value"}, function(event){
		var url = funMap.get(this.id).url;
		var data = funMap.get(this.id).data;
		$("#functionName").html($(this).html());
		$("#requestUrl").val(JSON.stringify(url));
		$("#requestText").val(JSON.stringify(data));
		$("#responseText").val("");
	});
	
	$("#responseSubmit").bind("click", function(event){
		submitRequst();
	});
};

function Security(){};

