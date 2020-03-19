var moduleJSON = [];
var funMap = new Map();

$(document).ready(function(){ 
	bindFunTest(moduleJSON);
});

var requestFun;

function submitRequst(){
	var url = JSON.parse($("#requestUrl").val());
	var data = JSON.parse($("#requestText").val());
	console.log("请求对象:");
	console.log(data);
	var requestData = data;
	var requestMethod = "post";
	if("RequestBody"==requestFun.requestPattern){
		requestData = JSON.stringify(requestData);
	} else if("PathVariable"==requestFun.requestPattern){
		for(var key in requestData) {
			url = url.replace("{"+key+"}", requestData[key]);
		}
		requestData = null;
	}
	if(requestFun.requestMethod!=null){
		requestMethod = requestFun.requestMethod;
	}
	$.ajax({
        async : true,    //表示请求是否异步处理
        type : requestMethod,    //请求类型
        url : url,
        contentType:"application/json",
        dataType : "json",//返回的数据类型
        //数据，json字符串
        data : requestData,
        //请求成功
        success : function(result) {
        	$("#responseText").val(JSON.stringify(result));
        	console.log("返回对象:");
            console.log(result);
        },
        //请求失败，包含具体的错误信息
        error : function(e){
        	$("#responseText").val(e.responseText);
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
		var fRequestPattern = funMap.get(this.id).requestPattern;
		$("#functionName").html($(this).html());
		$("#requestUrl").val(JSON.stringify(url));
		$("#requestText").val(JSON.stringify(data));
		$("#responseText").val("");
		requestFun = funMap.get(this.id);
	});
	
	$("#responseSubmit").bind("click", function(event){
		submitRequst();
	});
};

function Security(){};

