function showTipsWindown(title,id,width,height){
	tipsWindown(title,"id:"+id,width,height,"true","","true",id);
}

function confirmTerm(s) {
	parent.closeWindown();
	if(s == 1) {
		parent.document.getElementById("isread").checked = true;
	}
}
//弹出层调用
function popTips(){
	showTipsWindown("意见反馈", 'simTestContent', 610, 455);
	$("#isread").attr("checked", false);
}

function popTipa(){
	showTipsWindown("选择类型", 'simTestSelect', 510, 410);
	$("#isread").attr("checked", false);
}

function popTipz(){
	showTipsWindown("更换手机号码", 'simTestPhone', 450, 320);
	$("#isread").attr("checked", false);
}

function popTipx(){
	showTipsWindown("绑定邮箱", 'simTestMailbox', 450, 300);
	$("#isread").attr("checked", false);
}

function popTipc(){
	showTipsWindown("修改有户名", 'simTestUsername', 480, 230);
	$("#isread").attr("checked", false);
}

function popTipq(){
	showTipsWindown("设置密码", 'simTestPassword', 450, 320);
	$("#isread").attr("checked", false);
}

//alert
function popAlert(text,min){
//	showTipsWindown("提示", 'simTestAlert', 300, 160);
	tipsWindown("提示","text:"+text,300,160,"true",min,"true",'simTestAlert');
	$("#isread").attr("checked", false);
}
//上传头像

function popTipq(){
	showTipsWindown("上传头像", 'upheader', 300,150);
}

/**发送到手机***/
function popTipSend(){
	if($("#useruid").val()==""){
		showTipsWindown("会员登录", 'simTestLogin',  510,260);
		$("#isread").attr("checked", false);
	}else{
		var phone = $("#textfieldphone").val();
		var codeVal = $("#codeVal").val();
		var code = $("#checkCode").val();
		if(!isNaN(parseInt(phone))){
			$("#textfieldphone").css("border","1px solid #ccc");
			if(codeVal=="输入验证码"||codeVal==""){
				$("#codeVal").css("border","1px solid red");
			}else{
				$("#codeVal").css("border","1px solid #ccc");
				if(codeVal.toUpperCase()==code){
					//发送
					$.post("mobile.do?act=sendingMobilephone",
							{id:$("#couponcid").val(),
							userId:$("#useruid").val(),
							mobile:phone
							},
							function(msg){
								if(msg.succeed=="000"){
									alert("验证码已发送！请注意查收！");
								}else if(msg.succeed=="510"){
									alert("您的验证码"+msg.code+"已经发送过.请勿重新发送");
								}else{
									alert("发送失败请检查手机号是否正确！");
								}
							},"json");
					
				}else{
					$("#codeVal").css("border","1px solid red");
				}
			}
		}else{
			$("#textfieldphone").css("border","1px solid red");
		}
	}
}
//发送2
function popTopSend2(){
	if($("#useruid").val()==""){
		showTipsWindown("会员登录", 'simTestLogin',  510,260);
		$("#isread").attr("checked", false);
	}else{
		showTipsWindown("免费短信", 'sendCode2',305,400);
	}
}

/**添加评论**/
function addcomm(){
	if($("#useruid").val()==""){
		showTipsWindown("会员登录", 'simTestLogin',  510,260);
		$("#isread").attr("checked", false);
	}else{
		showTipsWindown("评论", 'iscomm',670,608);
	}	
}

/**发送到手机***/
function popTipSendc(i){
	if($("#useruid").val()==""){
		showTipsWindown("会员登录", 'simTestLogin',  510,260);
		$("#isread").attr("checked", false);
	}else{
		var phone = $("#windown-content #textfieldphonec"+i).val();
		var codeVal = $("#windown-content #codeValc"+i).val();
		var code = $("#windown-content #checkCodec"+i).val();
		if(!isNaN(parseInt(phone))){
			$("#windown-content #textfieldphonec"+i).css("border","1px solid #ccc");
			if(codeVal=="输入验证码"||codeVal==""){
				$("#windown-content #codeValc"+i).css("border","1px solid red");
			}else{
				$("#windown-content #codeValc"+i).css("border","1px solid #ccc");
				if(codeVal.toUpperCase()==code){
					//发送
					var cid  = $("#windown-content #ccccouidc"+i).val();
					$.post("mobile.do?act=sendingMobilephone",
							{id:cid,
								userId:$("#useruid").val(),
								mobile:phone
							},
							function(msg){
								if(msg.succeed=="000"){
									alert("验证码已发送！请注意查收！");
								}else if(msg.succeed=="510"){
									alert("您的验证码"+msg.code+"已经发送过.请勿重新发送");
								}else{
									alert("发送失败请检查手机号是否正确！");
								}
							},"json");
				}else{
					$("#windown-content #codeVal1").css("border","1px solid red");
				}
			}
		}else{
			$("#windown-content #textfieldphone1").css("border","1px solid red");
		}
	}
}

/**发送到手机***/
function popTipSend2(){
	if($("#useruid").val()==""){
		showTipsWindown("会员登录", 'simTestLogin',  510,260);
		$("#isread").attr("checked", false);
	}else{
		var phone = $("#windown-content #textfieldphone1").val();
		var codeVal = $("#windown-content #codeVal1").val();
		var code = $("#windown-content #checkCode1").val();
		if(!isNaN(parseInt(phone))){
			$("#windown-content #textfieldphone1").css("border","1px solid #ccc");
			if(codeVal=="输入验证码"||codeVal==""){
				$("#windown-content #codeVal1").css("border","1px solid red");
			}else{
				$("#windown-content #codeVal1").css("border","1px solid #ccc");
				if(codeVal.toUpperCase()==code){
					//发送
					var cid  = $("#windown-content #ccccouid").val();
					$.post("mobile.do?act=sendingMobilephone",
							{id:cid,
								userId:$("#useruid").val(),
								mobile:phone
							},
							function(msg){
								if(msg.succeed=="000"){
									alert("验证码已发送！请注意查收！");
								}else if(msg.succeed=="510"){
									alert("您的验证码"+msg.code+"已经发送过.请勿重新发送");
								}else{
									alert("发送失败请检查手机号是否正确！");
								}
							},"json");
				}else{
					$("#windown-content #codeVal1").css("border","1px solid red");
				}
			}
		}else{
			$("#windown-content #textfieldphone1").css("border","1px solid red");
		}
	}
}

$(document).ready(function(){
	$("#isread").click(popTips);
	$("#isread-text").click(popTips);
	$("#isread-select").click(popTipa);
	$("#isread-phone").click(popTipz);
	$("#isread-mailbox").click(popTipx);
	$("#isread-username").click(popTipc);
	$("#isread-password").click(popTipq);
	//发送到手机
	$("#sendPhone2").click(popTipSend);
	$(".topSend").each(function(){
		var _this = this;
		$(_this).click(popTopSend2);
	});
	$("#sendPhone100").click(function(){
	});
	//添加评论
	$("#addcomment").click(addcomm);
	//上传头像
	$("#headder").click(popTipq);
	
});