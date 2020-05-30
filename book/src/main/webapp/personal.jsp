<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8" />
<title>书友</title>
<link rel="stylesheet" type="text/css" href="bookcom/public.css" />
<link rel="stylesheet" type="text/css" href="css/personal.css" />
<link rel="stylesheet" href="css/lobibox.css">
</head>

<body>
	<!------------------------------head------------------------------>
	<div class="head">
		<div class="wrapper clearfix">
			<%@ include file="head1.jsp"%>
			<%@ include file="head3.jsp"%>
		</div>

	</div>

	<div class="main_page">
		<div class="part_left">
			<div class="main_left">
				<h1>个人信息内容</h1>
				<div class="person_inf">
					<a><img class="icon" src="img/flo1.jpg"></a>
					<div class="name_inf">
						<a>昵称</a> <a id="person_inf_userName">马赞均</a><a id="info_change">修改信息</a><a id="zhuxiao">注销</a>

					</div>
					<div class="jianjie">

						<span>简介</span> <a>&nbsp&nbsp&nbsp反射弧超长的码农</a>
					</div>

				</div>
				<div class="person_total">
					<div class="person_total_part">
						<a>评论次数</a> <br></br> <a>&nbsp&nbsp&nbsp&nbsp46</a>
					</div>
					<div class="person_total_part">
						<a>关注人数</a> <br></br> &nbsp&nbsp&nbsp&nbsp<a id="info_following_num">18</a>
					</div>
					<div class="person_total_part">
						<a>被关注数</a> <br></br> &nbsp&nbsp&nbsp&nbsp<a id="info_num">20</a>
					</div>



				</div>
				<div class="comment_inf">
					<div class="comment_head">
						<a id="post_control">帖子回复</a><a>\</a><a id="comment_control">评论回复</a>

					</div>
					<div class="comment_body">
						<div class="comment_res_mask">
							<div class="comment_res_head">
								<a>我的被回复评论</a><span>时间</span><span>他的评论内容</span><span>点击查看评论</span>
							</div>

							<div class="comment_res_body"></div>
						</div>
						<div class="post_res_mask">

							<div class="post_res_head">
								<a>发布的帖子</a><span>时间</span><span>内容</span><span>讨论数</span>
							</div>

							<div class="post_res_body" style="">
								

							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="part_right">
			<h1>关注列表</h1>
			<div class="main_right"></div>
		</div>
	</div>
	<div class="mask"></div>
	<div class="main">
		<img class="off" src="img/temp/off.jpg" /> <a><img
			class="icon_bot" src="img/flo2.jpg" /></a> <input type="file" value="上传" class="file "
			id="articleImgBtn" /> <input type="button"  class="newbutton ant-btn-red" value="上传头像"
			id="icon_button" />
		<p>
			<span>账号</span><input type="text" id="userAccount" name="userAccount"
				value="" disabled="disabled">
		</p>
		<p>
			<span>昵称</span><input type="text" id="userName" name="userName"
				value="">
		</p>
		<p>
			<span>邮箱</span><input type="text" id="email" name="email"
				disabled="disabled" value="">
		</p>
		<p>
			<input type="button" value="保存信息内容" class="newbutton ant-btn-red" id="info_button"
				style="float: left;" />
		</p>
		<p>
			<span>输入原密码</span><input type="password" id="password"
				name="password" value="">
		</p>
		<p>
			<span>新密码</span><input type="password" id="password_new"
				name="password" value="">
		</p>
		<p>
			<input type="button" value="保存密码内容" class="newbutton ant-btn-red" id="password_button"
				style="float: left;" />
		</p>
	</div>
	<script src="js/jquery-1.12.4.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.flexslider-min.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="js/jquery.cookie.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/ajaxfileupload.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/jquery-migrate-1.4.1.min.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/jquery.base64.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/Lobibox.js"></script>  
	<script type="text/javascript">
		$(function(){
		$('.icon_bot').attr("src",storage.getItem("icon"));
		})
		$(function(){
			var url="GetUserFollowNum";
			var args={
				"userID":$.cookie("userID")
			}
			$.post(url,args,function(data){
				$("#info_num").text(data.followersNum);
				$("#info_following_num").text(data.followingNum);
			},"json")
			
		})
		$(function() {
			$("#post_control").click(function() {
				$("#post_control").css("color","#3C3C3C");
				$("#comment_control").css("color","#BEBEBE");
				$(".comment_res_mask").css("display", "none");
				$(".post_res_mask").css("display", "block");
			})
		})
		$(function() {
			$("#comment_control").click(function() {
				$("#post_control").css("color","#BEBEBE");
				$("#comment_control").css("color","#3C3C3C");
				$(".post_res_mask").css("display", "none");
				$(".comment_res_mask").css("display", "block");
			})
		})
		$(function() {

			$("#person_inf_userName").text(storage.getItem("username"));
			var userID = $.cookie("userID");
			$(".icon").attr("src", storage.getItem("icon"));
			/* var url = "OtherUserMessage";  */ //这里是 主页最复杂的功能  得到主页主体信息 包括了个人中心各种 
			var url = "ShowUserComments";//这里是帖子信息
			var args = {
				"userID" : userID
			}
			$
					.post(
							url,
							args,
							function(data) {

							
								var comment_num = Number(data.num);
								var following_num = Number(data.following_num);
								for (i = 0; i < comment_num; i++) {
									var commentParentContent = data.comments[i].commentParentContent;
									var time = data.comments[i].time;
									var content = data.comments[i].content;
									var postID = data.comments[i].postID;
									var href = "detail.jsp?postID=" + postID;
									content=content.replace(/\</g, "");
									$(".comment_res_body")
											.append(
													'<div class="comment_res_comment">'
															+ '<a href='+href+'>'
															+ commentParentContent
															+ '</a><span>'
															+ time
															+ '</span><span>'
															+ content
															+ '</span><span >点击最左该帖子</span>'
															+ '</div>')
								}
								
								

							}, "json")
			var url="ShowUserFollowing";//这里是关注
			/* var url="js/info_text.js" */
			var args={
					"userID":$.cookie("userID")
			}
			$.post(url,args,function(data){
				var following_num = Number(data.num);
				for (i = 0; i < following_num; i++) {

					var icon = data.followings[i].icon;

					var userID = data.followings[i].userID;
					var userName = data.followings[i].userName;
					var href = "properson.jsp?userID=" + userID;
					$(".main_right")
							.append(
									"<div class='r_pic'>"
											+ "<a href="+href+"><img src="+icon+" /></a>"
											+ "<div>"
											+ "<a href="+href+" class='r_user_name'>"
											+ userName + "</a>"
											+ "</div>"
											+ "</div>")
				}
				
			},"json")
				var url="UserPost";//这里是得到帖子
				/* var url="js/text.js"; */
			var args={
					"userID":$.cookie("userID")
			}
			$.post(url,args,function(data){
				var posts_num = Number(data.num);
				for (i = 0; i < posts_num; i++) {

					var postTitle = data.posts[i].postTitle;
					var postID = data.posts[i].postID;
					var userID = data.posts[i].userID;
					var time = data.posts[i].time;
					var commentNum = data.posts[i].commentNum;
					var content= data.posts[i].content;
					var href = "detail.jsp?postID=" + postID;
					content=content.replace(/\</g, "");
					postTitle=postTitle.replace(/\</g, "");

					$(".post_res_body").append(
									'<div class="post_res_comment">'+
									'<a href='+href+'>'+postTitle+'</a><span>12121212\</span><span>'+content+'</span><span>'+commentNum+'</span>'+
								'</div>')
				
				}
				
			},"json")
		})
		$(function(){
			$("#zhuxiao").click(function(){
				 storage.removeItem('icon');
				 storage.removeItem('account');
				 storage.removeItem('email');
				 storage.removeItem('username');
				 $.removeCookie('userID',{ path: '/'});
				 $.removeCookie('account',{ path: '/'});
				 $.removeCookie('identity',{ path: '/'});
				 window.location.href="index.jsp";
			})
		})
		$(function() {
			$('#info_change').click(function() {
				$(".mask").css("display", "block");
				$(".main").css("display", "block");
			})
		})
		$(function() {
			$("#post_control").click(function() {
				$(".comment_res_mask").css("display", "none");
				$(".post_res_mask").css("display", "block");
			})
		})
		$(function() {
			$("#comment_control").click(function() {
				$(".post_res_mask").css("display", "none");
				$(".comment_res_mask").css("display", "block");
			})
		})
		$(function() {
			$("#userAccount").val(storage.getItem("account"));
			$("#userName").val(storage.getItem("username"));
			$("#email").val(storage.getItem("email"));
		})
		$(function() {
			$('.off').click(function() {
				$(".mask").css("display", "none");
				$(".main").css("display", "none");
				window.location.href = "personal.jsp";
			})
		})
		$(function() {
			$('.off').click(function() {
				$(".mask").css("display", "none");
				$(".main").css("display", "none");
				window.location.href = "personal.jsp";
			})
		})
		$(function() {
			$("#info_button").click(function() {
				var user_name = $("#userName").val();
				if (user_name == null) {
					alert('请输入更改的用户名');
					return;
				}
				var userID = $.cookie('userID');
				var url = "ChangeUsername";//个人信息更改  只能更改用户名
				var args = {
					"userID" : userID,
					"userName" : user_name

				}
				$.post(url, args, function(data) {
					if (data.success == "true") {
						alert('修改成功');
						storage['username'] = user_name;
					}
				}, "json")
			})
		})
		$(function() {
			var reg_test = /^\w+$/;
			$("#password_button").click(function() {
				var password = $("#password").val();
				var password_new = $("#password_new").val();
				if (!reg_test.test(password) || !reg_test.test(password_new)) {
					alert("错误");
					return;
				}
				if (!password.length > 5 && password.length < 17) {
					alert('长度限定在6到16之间');
					return;

				}
				if (!password_new.length > 5 && password.length < 17) {
					alert('长度限定在6到16之间')
					return;
				}
				if (password == null) {
					alert('原密码不为空');
					return;
				}
				if (password_new == null) {
					alert('新密码不为空');
					return;
				}
				var userID = $.cookie('userID');
				var url = "ChangePassword";//新密码功能
				var args = {
					"userID" : userID,
					"oldPassword" : password,
					"newPassword" : password_new

				}
				$.post(url, args, function(data) {
					if (data.success == "true") {
						alert('修改成功');
					} else {
						alert('修改失败请检查密码是否错误');
					}
				}, "json")
			})
		})

		$('#icon_button').click(function() {

			run(function(data) {
				uploadImage(data);
			});
		});
	
		function run(get_data) {
			/*input_file：文件按钮对象*/
			/*get_data: 转换成功后执行的方法*/
			if (typeof (FileReader) === 'undefined') {
				alert("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
			} else {
				try {
					/*图片转Base64 核心代码*/
					var filemaxsize = 1024 * 2;
					var file = $("#articleImgBtn")[0].files[0];
					var fileSize = file.size;
					var size = fileSize / 1024;
					if (size > filemaxsize) {
						alert("附件大小不能大于" + filemaxsize / 1024 + "M！");

						return false;
					}
					if (size <= 0) {
						alert("附件大小不能为0M！");

						return false;
					}
					//这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件  
					if (!/image\/\w+/.test(file.type)) {
						alert("请确保文件为图像类型");
						return false;
					}
					var reader = new FileReader();
					reader.onload = function() {
						get_data(this.result);
					}
					reader.readAsDataURL(file);
				} catch (e) {
					alert('请不要传递空文件或非图片文件' + e.toString())
				}
			}
		}

		function uploadImage(img) {
			//判断是否有选择上传文件
			var imgPath = $("#articleImgBtn").val();
			if (imgPath == "") {
				alert("请选择上传图片！");
				return;
			}
			//判断上传文件的后缀名
			var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
			if (strExtension != 'jpg' && strExtension != 'gif'
					&& strExtension != 'png' && strExtension != 'bmp') {
				alert("请选择图片文件");
				return;
			}
			if ($.cookie("userID") == null) {
				alert("请登录");
				return;
			}

			var url = "ChangeIcon";//上传头像图片功能
			var args = {
				"userID" : $.cookie("userID"),
				"icon" : img.substr(img.indexOf(',') + 1),
			}
			$.post(url, args, function(data) {
				alert("上传成功");

				$(".icon_bot").attr("src", data.path);
				storage['icon'] = data.path;
			}, "json")

		}
		
	</script>
</body>
</html>