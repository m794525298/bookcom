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
			<%@ include file="head4.jsp"%>
		</div>

	</div>

	<div class="main_page">
		<div class="part_left">
			<div class="main_left">
				<h1>他的个人主页</h1>
				<div class="person_inf">
					<a><img class="icon" src="img/flo1.jpg"></a>
					<div class="name_inf">
						<a>昵称</a> <a id="person_inf_userName">马赞均</a><a class="doguanzhu">关注</a>

					</div>
					<div class="jianjie">

						<span>简介</span> <a>&nbsp&nbsp&nbsp无财作力&nbsp少有斗智</a>
					</div>

				</div>
				<div class="person_total">
					<div class="person_total_part">
						<a>评论次数</a> <br></br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a>20</a>
					</div>
					<div class="person_total_part">
						<a>关注人数</a> <br></br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a id = "info_following_num"></a>
					</div>
					<div class="person_total_part">
						<a>被关注数</a> <br></br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a class="beguanzhunum" id = "info_num">20</a>
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
			class="icon_bot" src="img/flo2.jpg" /></a> <input type="file" value="上传"
			id="articleImgBtn" /> <input type="button" value="上传"
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
			<input type="button" value="保存信息内容" id="info_button"
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
			<input type="button" value="保存密码内容" id="password_button"
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
	function getUrlParam(name) {//封装方法
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if (r != null)
			return unescape(r[2]);
		return null;
	} //返回参数值
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
	$(function(){
			var url="GetUserFollowNum";
			var userID=getUrlParam("userID");
			var args={
				"userID":userID
			}
			$.post(url,args,function(data){
				$("#info_num").text(data.followersNum);
				$("#info_following_num").text(data.followingNum);
			},"json")
			
		})
		$(function() {
			
			var url="OtherUserMessage";  //得到被看的人的个人信息
			var userID = getUrlParam("userID");
			args={
					"userID":userID
			}
			$.post(url,args,function(data){
				$("#person_inf_userName").text(data.username);
				
				$(".icon").attr("src", data.icon);
			},"json")

		
			
			
			
			
			/*	var url = "js/info_text.js";*///这里是帖子信息
			var url = "UserPageComments";
			var args = {
				"userID" : userID
			}
			$
					.post(
							url,
							args,
							function(data) {

							
								var comment_num = Number(data.num);
								var following_num = Number(data.num);
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
			/*var url="js/info_text.js";//这里是关注*/
				var url="ShowUserFollowing";//这里是关注
			var args={
					"userID" : userID
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
				/* var url="js/info_text.js";//这里是得到帖子 */
				var url="UserPost";
			var args={
					"userID":userID
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
									'<a href='+href+'>'+postTitle+'</a><span>'+time+'</span><span>'+content+'</span><span>'+commentNum+'</span>'+
								'</div>')
				
				}
				
			},"json")

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
		
		$(function(){
		var url="IsFollow";
		var followingUserID=getUrlParam("userID");
		var args={
		"userID":$.cookie("userID"),
		"followingUser":followingUserID}
		$.post(url,args,function(data){
		
			if(data.following=="false"){
		$(".doguanzhu").text("关注");
			
			}else{$(".doguanzhu").text("已关注");}
		},"json")
		
		})

		
		
		$(function(){
			$(".name_inf").on('click','[class="doguanzhu"]',function(event){
				var userID=getUrlParam("userID");
				var thefollowstate=$(event.target).text();
				if(thefollowstate=="关注"){url="Follow";}//这里对应关注功能 我这边只给你该用户和他关注的谁 至于是关注还是取关 我真不知道。你们只有一个接口 我也只有一个效果}
				else{url="DisFollow";}//这里对应关注功能 我这边只给你该用户和他关注的谁 至于是关注还是取关 我真不知道。你们只有一个接口 我也只有一个效果}
				
				if($.cookie('userID')==null){
					alert("请先登录");
					window.location.href="login.jsp";
				}
				arg={
						"userID":$.cookie('userID'),
						"followingUser":userID
				};
				$.post(url,arg,function(data){
					
					
					var success =data.success;
					
					if(success=="true"){
					
						
						if($(event.target).text()=="关注"){
							
							
							$(event.target).text('已关注');
							$(event.target).css("color","red");
							$(".beguanzhunum").text(Number($(".beguanzhunum").text())+1);
						
						}else{
						
							$(event.target).text('关注');
							$(event.target).css("color","blue");
							$(".beguanzhunum").text(Number($(".beguanzhunum").text())-1);
							
						}
					}else{
						
					}
					
				
					
					
				},"json")
				
			})
		})
	</script>
</body>
</html>