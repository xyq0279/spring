var fromObj = {
	"flag" : true,
	"localhost":window.location.host,
	"contextpath":window.location.pathname.split("/")[0],
	"clickForm" : function(thisObj) {
		
		this.flag = true;
		$(" form input").blur();
		return this.flag;
	},
	"checkNull" : function(thisObj) {
		if (!$(thisObj).val().trim()) {
		//	debugger;
//			alert(1);
			var str = $(thisObj).parent().prev().text();
			str = str.substring(0, str.length - 1) + "不能为空!";
			this.setMsg(thisObj, str);
			this.flag = false;
			return false;
		} else {
			this.setMsg(thisObj, "");
			return true;
		}
	},
	"checkPassword" : function() {
			var $p1 = $("table tr td input[name='password']");
			var $p2 = $("table tr td input[name='password2']");
			
			if($.trim($p1.val()) && $.trim($p2.val())){
				if($.trim($p1.val()) != $.trim($p2.val())){
					this.setMsg($p2.get(0), "密码不一致");
					this.flag = false;
					return false;
				}else{
					this.setMsg($p2.get(0), "");
				}
			}
			return true;
	},
	"checkEmail" : function(thisObj) {
		var email = $(thisObj).val().trim();
		var str = /\w+@\w+(\.\w+)+/;
		
		if(email){
			if(!str.test(email)){
				this.setMsg(thisObj, "邮箱格式不正确！");
				this.flag = false;
				return false;
			}else{
				this.setMsg(thisObj, "");
			}
		}
		return true;
	},
	"changeImage" : function(thisObj) {
		var time = new Date().toLocaleString();
		thisObj.src = this.contextpath+"/VerifyCodeServlet?time="+time;
	},
	"AjaxCheckName":function(){
		var username = $("input[name='username']").val().trim();
		if(username){
			$.ajax({
				"url":this.contextpath+"/AjaxCheckNameServlet",
				"data" : {"username": username},
				"type" : "POST",
				"success" : function(result){
					$("#username_msg").html(result).css("color","red");}
			});
			this.flag = false;
		}
		
	},
	"AjaxCheckCode":function(){
		var valistr = $("input[name='valistr']").val().trim();
		if(valistr){
			$.ajax({
				"url":this.contextpath+"/AjaxCheckCodeServlet",
				"data" : {"valistr": valistr},
				"type" : "POST",
				"success" : function(result){
					$("#valistr_msg").html(result).css("color","red");}
			});
			this.flag = false;
		}
		
	},
	"setMsg" : function(thisObj, msg) {
		$(thisObj).nextAll("span").html(msg).css("color", "red");
	}
};
$(function() {
	$(".tds~td input").bind('blur',function(){
		return fromObj.checkNull(this);
	});
	$("input[type='password']").bind('blur',function(){
		return fromObj.checkPassword();
	});
	$("input[name='email']").bind('blur',function(){
		return fromObj.checkEmail(this);
	});
	$("from").bind('submit',function(){
		return fromObj.clickForm(this);
	});
	$("img").bind('click',function(){
		return fromObj.changeImage(this);
	});
	$("input[name='username']").bind('blur',function(){
		fromObj.AjaxCheckName();
	});
	$("input[name='valistr']").bind('blur',function(){
		fromObj.AjaxCheckCode();
	});
/*	window.onblur = function(){
		
		var oInp = document.getElementsByTagNames('input');
		for(oInp.has){
			
		}
	};*/
	
});
