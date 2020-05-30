<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8" />
<title>书友</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/detail.css" />

</head>
<body>
	<!------------------------------head------------------------------>
	<div class="head">
		<div class="wrapper clearfix">
			<%@ include file="head1.jsp"%>
			<%@ include file="head2.jsp"%>
		</div>
	</div>
	<div class="title">
		<a>鲁滨逊漂流记读书有感</a>
	</div>
	<div class="clear"></div>
	<div class="mainpage">
		<div class="page_left">


			<div class="poster">
				<div class="post_pic">
					<div class="pic">
						<img src="img/flo1.jpg" />
					</div>

				</div>

				<div class="poster_info">

					<div class="poster_name">
						<a class="user_id">你的好哥哥</a> <a class="poster_time">2020/5/2
							14:12:13</a>
					</div>






				</div>
			</div>

			<div class="zhengwen">
				<table cellSpacing="0" cellpadding="1" width="100%" class="ctl">

					<tBody>
						<tr>
							<td></td>
						</tr>

					</tBody>
				</table>
				<img src="img/flo1.jpg" />
			</div>
			<h2 style="margin-top: 200px">有关评论</h2>
			<div class="comment_all"></div>

			<div>
				<div class="list-page">
					<div id="total_page" style="margin-bottom: 20px;"></div>
					<a id="first_page1">首页</a> <a id="previous_page1">上一页</a> <a
						id="next_page1">下一页</a> <a id="last_page1">尾页</a>

				</div>
			</div>

			<form id="myForm" onsubmit="return false";>
				<div class="response">
					<div class="response_to_area">
						<a>response to:</a><a class="response_to"></a>
					</div>
					<div>
						<textarea id="textarea" maxlength="120"
							style="width: 100%; height: 200px; white-space: normal; word-wrap: break-word; font-size: 15px;"
							value="这是可以回复评论"></textarea>
					</div>
					<div>
						<input type="submit" id="submit"
							style="width: 60px; height: 30px; float: right;" />
					</div>
				</div>
			</form>
		</div>
		<div class="page_right">
			<h1 class="r_title">推荐用户</h1>
			<div class="r_pic_all"></div>

		</div>
	</div>
	<script src="js/jquery-1.12.4.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.flexslider-min.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="js/jquery.cookie.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/jquery.base64.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/Lobibox.js"></script>  
	<script type="text/javascript">
		var storage = window.localStorage;
		function getUrlParam(name) {//封装方法
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg); //匹配目标参数
			if (r != null)
				return unescape(r[2]);
			return null;
		}

		$(function() {

			var url = "HotUser";//推荐用户  对应文档功能4

			var args = {
				"time" : new Date()
			};

			$
					.post(
							url,
							args,
							function(data) {
								$(".r_pic_all").empty();
								var num = data.num;
								for (i = 0; i < num; i++) {
									var userID = data.list[i].userID;
									var icon = data.list[i].icon;
									var username = data.list[i].username;

									var href = "properson.jsp?userID=" + userID;
									$(".r_pic_all")
											.append(
													"<div class='r_pic' stlye='{margin-top:60px;}'>"
															+ "<a href="+href+" ><img  src="+icon+" /></a>"
															+ "<div ><a href="+href+" class='r_user_name'>"
															+ username
															+ "</a></div>"
															+ "</div>");

									/* $(".s_title").eq(i).empty().append(title);
									$(".s_title").eq(i).attr("href",post_href);
									$(".content").eq(i).empty().append(content);
									$('.pic-wrap img').eq(i).attr("src",cover);
									$(".like").eq(i).empty().append(commentNum);
									$(".pubtime").eq(i).empty().append(time);
									$(".publisher").eq(i).empty().append(publisher);
									/* /* $(".s_title")[0].empty().append(name);
									$(".channel-item .bd .content")[0].empty().append(age); */
								}

							}, "json")

		})
		$(function() {
			var url = "PostDetail";//对应功能1 得到帖子的具体内容
			var postID = getUrlParam("postID");
			var args = {
				"time" : new Date(),
				"postID" : postID
			};
		
			$.post(url, args, function(data) {
			
				var publisher = data.publisher;
				var publisherName = data.publisherName;
				var postTitle = data.postTitle;
				var cover = data.cover;
				var time = data.time;
				var icon = data.icon;
				var content = data.content;
				var publisher_href = "properson.jsp?userID=" + publisher;
				storage['postPublisher_ALL'] = publisher;
				$(".title a").text(postTitle);
				$(".post_pic img").attr("src", icon);
				$(".user_id").text(publisherName);
				$(".user_id").attr("href", publisher_href);
				$(".poster_time").text(time);
				$(".zhengwen td").empty().append(content);
				$(".zhengwen img").attr("src", cover);

			}, "json")
		})
		$(function() {
			var url = "ShowPostComments";//对应帖子功能2 数据给你们 你们自己搞定
			var postID = getUrlParam("postID");
			var page = getUrlParam("page");
			var args = {
				"time" : new Date(),
				"postID" : postID,
				"page" : page
			};

			$
					.post(
							url,
							args,
							function(data) {

								var num = data.num;

								$(".comment_all").empty();
								for (i = 0; i < num; i++) {
									var commentID = data.comments[i].commentID;
									var publisher = data.comments[i].publisher;
									var time = data.comments[i].time;
									var icon = data.comments[i].icon;
									var publisherName = data.comments[i].publisherName;
									var content = data.comments[i].content;
									var commentParentContent = data.comments[i].commentParentContent;
									commentParentContent = commentParentContent
											.substring(0, 8);
									var commentParentPublisher = data.comments[i].commentParentPublisher;
									var commentParentPublisherName = data.comments[i].commentParentPublisherName;
									var publisher_href = "properson.jsp?userID="
											+ publisher;
									var comment_publisher_href = "properson.jsp?userID="
											+ commentParentPublisher;

									var totalPage = data.totalPage;
									storage['totalPage'] = totalPage;
									$(".comment_all")
											.append(
													"<div class='comment'>"
															+ "<div class='com_pic'>"
															+ "<img src="+icon+" value='头像'/>"
															+ "</div>"
															+ "<div class='com_content'>"
															+ "<div class='com_user'>"
															+ "<a>from:&nbsp&nbsp</a>"
															+ "<a class='the_comment_publisher' id='the_comment_publisher' value="+publisher+" href="+publisher_href+">"
															+ publisherName
															+ "</a> <a>&nbsp&nbsp&nbsp&nbsp发表时间:&nbsp&nbsp"
															+ time
															+ "&nbsp&nbsp&nbsp&nbsp</a><a>回复评论："
															+ commentParentContent
															+ "&nbsp&nbsp&nbsp&nbsp</a><a href="+comment_publisher_href+">该评论发布者"
															+ commentParentPublisherName
															+ "&nbsp&nbsp</a>"
															+ "</div>"
															+ "<div class='com_conment'>"
															+ "<a>"
															+ content
															+ "</a>"
															+

															"</div>"
															+ "<a style='float:right' class='response_but' publisherName="+publisherName+" publisher="+publisher+" commentID="+commentID+">回复</a>"
															+

															"</div>" +

															"</div>");
								}

							}, "json");

		})
		$(function() {
			$(".comment_all")
					.on(
							'click',
							'[class="response_but"]',
							function(event) {
								var parent = $(event.target).attr("commentID");
								var parentPublisher = $(event.target).attr(
										"publisher");

								var parentPublisherName = $(event.target).attr(
										"publisherName");

								$(".response_to").empty().append(
										parentPublisherName);
								$(".response_to").attr("parent", parent);
								$(".response_to").attr("parentPublisher",
										parentPublisher);
								$('html,body').animate({
									scrollTop : $('#textarea').offset().top
								}, 800);
							})
		})
		$(function() {
			$("#submit").click(
					function() {

						var content = $("#textarea").val();

						var parent = $(".response_to").attr('parent');
						var parentPublisher = $(".response_to").attr(
								'parentPublisher');
						
						content=content.replace(/\ /g, "&nbsp");
						content=content.replace(new RegExp("\n", "gm"), '<br/>');
						var postID = getUrlParam("postID")
						if ($.cookie('userID')) {

							var commentPublisher = $.cookie('userID');
						} else {
							alert('请登录');
							return;
						}
						var url = "PublishComment"; //发表评论功能 对应功能3
						var args = {
							"postID" : postID,
							"content" : content,
							"parent" : parent,
							"commentPublisher" : commentPublisher,
							"parentPublisher" : parentPublisher

						};
						$.post(url, args, function(data) {
							if (data.success == "true") {
								alert("发表成功");
								window.location.reload();
							} else {
								alert('发表失败');
							}

						}, "json")

					})
		})
	</script>
	<script src="js/detail_page.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>