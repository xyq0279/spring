var ObjForm = function(){
	"flag":true,
	"checkForm":function(){
		this.flag = true;
		
	},
	"checkNull":function(obj){
//		alert(1);
		var val = $(obj).val().trim();
		this.setMsg(obj,"");
		if(val){
			var msg = $(obj).parent("tr").prev().text().trim()+"不能为空!!";
			this.setMsg(obj,msg);
			this.flag = false;
		}
	},
	"checkPassword":function(){
		var $p1 = $("input[name='password']");
		var $p2 = $("input[name='password2']");
		var p1 = $p1.val().trim();
		var p2 = $p2.val().trim();
		if(!p&&!p2){
			if(p1!==p2){
				this.setMsg($p2[0],"密码不一致");
				this.flag = false;
			}else{
				this.setMsg($p2[0],"");
			}
		}
	},
	"checkEmail":function(obj){
		var email = $(obj).val().trim();
		var reg = / ^\w+@\w+(\.\w+)+$ /;
		if(!email){
			if(reg.test(email)){
				this.setMsg(obj,"邮箱不一致");
				this.flag = false;
			}else{
				this.setMsg(obj,"");
			}
		}
	},
	"AjaxCheckName":function(){
		
	},
	"AjaxCheckCode":function(){
		
	},
	"setMsg":function(obj,msg){
		$(obj).nextAll("span").html(msg).css("color","red");
	}
	
};
$(function(){
//	alert(1);
	$(".tds~td input").bind("blur",function(){
//		alert(1);
		ObjForm.checkNull(this);
	});
	$("input[type = 'password']")..bind("blur",function(){
//		alert(1);
		ObjForm.checkPassword();
	});
	$("input[name = 'email']")..bind("blur",function(){
//		alert(1);
		ObjForm.checkEmail(this);
	});
	$()
});