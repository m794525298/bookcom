<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8" />
<title>书友</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/detail.css" />
<link rel="stylesheet" href="css/lobibox.css">
</head>

<body>

	<div class="head">
		<div class="wrapper clearfix">
			<%@ include file="head1.jsp"%>
			<%@ include file="head3.jsp"%>
		</div>

	</div>
	<div class="clear"></div>
	<div class="mainpage">
		<div class="page_left">

			<a class="big_title">有关评论</a>
			<div class="comment_all"></div>

			<div>
				<div class="list-page">
					<div id="total_page" style="margin-bottom: 20px;"></div>
					<a id="first_page1">首页</a> <a id="previous_page1">上一页</a> <a
						id="next_page1">下一页</a> <a id="last_page1">尾页</a>

				</div>
			</div>


			<form id="myForm" onsubmit="return false">
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
	</div>
	<script src="js/jquery-1.12.4.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/jquery.flexslider-min.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="js/jquery.cookie.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/Lobibox.js"></script>  
		<script src="js/jquery.base64.js" type="text/javascript"
		charset="utf-8"></script>
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
			var url = "ShowUserComments";//对应评论列表的评论获取  记得给postID

			var page = getUrlParam("page");
			var userID = $.cookie("userID");
			var args = {
				"time" : new Date(),

				"page" : page,
				"userID" : userID
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
									var icon = data.comments[i].cover;
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
									var postID = data.comments[i].postID;
									var postHref="detail.jsp?postID="+postID;
									storage['totalPage'] = totalPage;
									$(".comment_all")
											.append(
													"<div class='comment'>"
															+ "<div class='com_pic'>"
															+"<a href="+postHref+">"
															+ "<img src="+icon+" value='头像'/>"
															+"</a>"
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
															+ "<a style='float:right' class='response_but' postID="+postID+" publisherName="+publisherName+" publisher="+publisher+" commentID="+commentID+">回复</a>"
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
								var postID = $(event.target).attr("postID");

								$(".response_to").empty().append(
										parentPublisherName);
								$(".response_to").attr("parent", parent);
								$(".response_to").attr("parentPublisher",
										parentPublisher);
								$(".response_to").attr("postID", postID);
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
						var postID = $(".response_to").attr('postID');
						if(postID==null||postID==NaN){
							alert('请选择被回复人');
							return;
						}
						content = content.replace(/[^u4e00-u9fa5w]/g, '');
						content = content.replace(/\</g, '');
						content = content.replace(/\>/g, '');
						/* var params=content.replace(/\ /g, "&nbsp");
						params=params.replace(new RegExp("\n", "gm"), '<br/>'); */

						if ($.cookie('userID')) {

							var commentPublisher = $.cookie('userID');
						} else {
							alert('请登录');
							return;
						}
						var url = "js/detail_comment_test.js"; //评论列表下的发表评论功能  这是这个页面第二个功能 发表评论
						var args = {
							"postID" : postID,
							"content" : content,
							"parent" : parent,
							"commentPublisher" : commentPublisher,
							"parentPublisher" : parentPublisher,

						};
						alert(postID + parent + content + commentPublisher
								+ parentPublisher + postID);
						/* alert(postID + parent + content + commentPublisher
								+ parentPublisher + postID);   这里取消注释可以看到发布评论我给你的所有内容*/
						$.post(url, args, function(data) {
							if (data.success == "true") {
								alert(data.success);
								window.location.reload();
							} else {
								alert('后台出现错误');
							}

						}, "json")

					})

		})
	</script>
	<script src="js/personal_comment_page.js" type="text/javascript"
		charset="utf-8"></script>
</body>

</html>