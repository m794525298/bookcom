<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <link rel="stylesheet" href="css/lobibox.css">
	<script src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script src="js/gVerify.js"></script>

	<style>
.reg .reg_title{
				width:80px;
				
				padding:0px 200px;
				font-size:20px;
				text-align:center;
				
			}
.reg p .error{
	display:inline-block;
	padding:0px 20px;
	height:25px;
	line-height:25px;
	color:#313131;
	
}

	</style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form  onsubmit="return false" action="#" method="post" id="reg_form"><h1><a href="index.jsp"><img src="img/logo2.png"></a></h1>
        <p class="reg_title">用户登录</p>
		<p><input type="text" name="userAccount" value="" placeholder="请输入账号" ><span class="error">请输入账号</span></p>
       
        <p><input type="password" name="userPassWord" value=""  placeholder="请输入密码"><span class="error">请输入密码</span></p>
     
        <p class="txtL txt"><input class="code" id="code_input" name="code" type="text" autocomplete="off"  placeholder="输入下侧验证码" ><span class="error">请输入验证码</span></p>
         <div id="v_container" style="width: 200px;height: 50px;margin-top:20px;"></div>
        
        <p><input type="submit" id="login" name="" value="登录" disabled="disabled" ></p>
        <p class="txtL txt">完成此登录，即表明您同意了我们的<a href="#">
            <使用条款和隐私策略>
        </a></p>
        <p class="txt"><a href="reg.jsp"><span></span>没有账号？去注册</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
 
	<script>
	 var verifyCode = new GVerify("v_container");
    
	 var storage = window.localStorage;
	 function checkbutton(){
		 var v1=$("input").eq(0).next().text();
		 var v2=$("input").eq(1).next().text();
		 var v3=$("input").eq(2).next().text();
		
	
		 if(v1==""&&v2==""&&v3=="ok"){
			 $("#login").removeAttr("disabled");
			 $("#login").css({background:"#C10000"});
			
			 
		 }else{
			 $("#login").attr("disabled","disabled"); 
			 $("#login").css({background:""});
		 }
	 }
	 $(function(){
		 $('#login').click(function(){
			
			 
			var account=$("input").eq(0).val(); 
			var password=$("input").eq(1).val();
			
			var url="Login";//这里是点击登录功能，对应接口文档功能二
		/*	var url="js/reg_userAccount.js";*/
			var args ={
					"account":account,
					"password":password};
			$.post(url,args,function(data){

				if(data.match=="true"){
					var userID=data.userID;
			
					 var account=data.account;
					 var identity=data.identity;
					 $.cookie('userID', userID);
					 $.cookie('account', account);
					 $.cookie('identity', identity);
					 if(!window.localStorage){
				            alert("浏览器支持localstorage");
				            return false;
				        }else{
				        	 var storage=window.localStorage;
				        	 storage["icon"]=data.icon;
				        	 storage["account"]=data.account;
				        	  storage["email"]=data.email;
				        	 storage["username"]=data.username;
				        	
				            //主逻辑业务
				        }
					
					window.location.href="index.jsp";
				}else{
					alert('error');
				}
			
		},"json")
		
		 })
		 
		 
	 })
	
	$(function(){
		$()
		
		$("input").focus(function(){
			var p_name=this.name;
			switch (p_name){
			case "userAccount" :
			
				$(this).next().empty();
				break;
				;
			case "userPassWord" :
				$(this).next().empty();
				break;
				;
			case "rePassWord" :
				$(this).next().empty();
				break;
				;
			case "email" :
				$(this).next().empty();
				break;
				;
			
			
			}
			
			
		})
		$("input").blur(function(){
			  var reg_test = /^\w+$/;
			  var reg_email_test = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;

			var p_name=this.name;
		
			switch (p_name){
			case "userAccount" :
				var p_value=this.value;
				if(p_value==""){
					
					$(this).next().empty().append("不能为空");
				}else{	if(!reg_test.test(p_value)){
					$("input").eq(0).next().empty().append("非法字符");
					
				}else{
					if(!(p_value.length>5&&p_value.length<17) ){
					 	$(this).next().empty().append("请限定长度在6—16"); }
					else(
						$(function(){
						var url="CheckAccount";//这一部分是判断账号是否存在 对接文档功能一
						var args ={"time":new Date(),
								"account":p_value};
						$.post(url,args,function(data){
						
								if(data.exist=="true"){
									
								 	$("input").eq(0).next().empty();
								}else{
									
									$("input").eq(0).next().empty().append("此账号不存在");
								 
								}
							
						},"json")
						
						})
						)}}
			
				checkbutton();
				break;
				;
			case "userPassWord" :
				var p_value=this.value;
				
				if(p_value==""){
					
					$(this).next().empty().append("不能为空");
				}else{	
					
					
					if(!reg_test.test(p_value)){
					$("input").eq(2).next().empty().append("非法字符");
				}else if(!(p_value.length>5&&p_value.length<17) ){
				 	$(this).next().empty().append("请限定长度在6—16"); }
			
				}
				checkbutton();
				break;
				;
			
				
			case "code" :
				
				var res = verifyCode.validate($("#code_input").val());
				
				if(res){		
					$(this).next().empty().append("ok")
						}else{
							$(this).next().empty().append("no")}
				checkbutton();
				break;
				;
			
			
			}
			
			
		})
	})
	

	</script>
</body>
</html>