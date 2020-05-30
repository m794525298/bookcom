<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8" />
<title>书友</title>
<link rel="stylesheet" type="text/css" href="bookcom/public.css" />
<link rel="stylesheet" type="text/css" href="bookcom/index.css" />
	<link rel="stylesheet" href="css/lobibox.css">

</head>

<body>

	<div class="head">
		<div class="wrapper clearfix">
			<%@ include file="head1.jsp"%>
			<%@ include file="head3.jsp"%>
		</div>

	</div>
	<div class="all">
		<div>
			<a class="title">帖子管理</a>
		</div>

		<div class="mainpage">
			<div class="all_channel"></div>



		</div>
	</div>
	<div class="list-page">
		<div id="total_page" style="margin-bottom: 20px;"></div>
		<a id="first_page1">首页</a> <a id="previous_page1">上一页</a> <a
			id="next_page1">下一页</a> <a id="last_page1">尾页</a>

	</div>



	<!-------------------login-------------------------->
	<!--footer-->

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
		} //返回参数值

		/* $(function(){
		
		 var url ="js/text.js?";
		 var page=getUrlParam("page");
		 var args ={"time":new Date(),
		 "page":page};
		
		
		 $.post(url,args,function(data){
		 $(".all_channel").empty();
		 var num=data.num;
		 for( i=0;i<num;i++){
		 var totalPage=data.totalPage;
		 var postID=data.posts[i].postID;
		 var title=data.posts[i].title+'这是第1个';
		 var content=data.posts[i].content;
		 var cover=data.posts[i].cover;
		 var commentNum=data.posts[i].commentNum;
		 var time=data.posts[i].time;
		 var publisher=data.posts[i].publisher;
		 var publisherName=data.posts[i].publisherName;
		 var properson_href="./detail.jsp"+"?userID="+publisher;
		 var post_href="./detail.jsp"+"?postID="+postID;
		 storage["totalPage"]=totalPage;
		
		 $(".all_channel").append(
		 '<div class="channel">'+
		 '<div class="likes"><a class="like_num">'+commentNum+'</a><br><a>喜欢</a></div>'+
		 '<div class="channel-item">'+
		 '<div class="bd">'+
		 '<a class="s_title" href='+post_href+'>'+title+'</a>'+
		 '<div class="block">'+
		 '<div class="pic">'+
		 '<div class="pic-wrap">'+
		 '<a href='+post_href+'><img src="'+cover+'"></a>'+
		 '</div>'+
		 '</div>'+
		 '<div class="text_content">'+
		 '<a class="content">'+content+'</a>'+
		 '</div>'+
		 '</div>'+
		 '<div class="source">'+
		 '<span class="from">来自<a class="publisher" href='+properson_href+ '>'+publisherName+'</a></span>'+
		 '<span class="pubtime">'+time+'</span>'+
		 '</div>'+
		 '</div>'+
		 '</div>'+
		 '</div>');
		
		
		 }
		
		 },"json")
		
		 }) */

		$(function() {

			var url = "UserPost"; //得到个人发布的贴
			/* var url = "js/text.js"; */
			var page = getUrlParam("page");
			var args = {
				"userID":$.cookie("userID"),
				"time" : new Date(),
				"page" : page
			};

			$
					.post(
							url,
							args,
							function(data) {
								$(".all_channel").empty();
								var num = data.num;
								for (i = 0; i < num; i++) {
									var totalPage = data.totalPage;
									var postID = data.posts[i].postID;
									var title = data.posts[i].postTitle
											+ '这是第1个';
									var content = data.posts[i].content;
									var cover = data.posts[i].cover;
									var commentNum = data.posts[i].commentNum;
									var time = data.posts[i].time;
									var author = data.posts[i].author;
									var bookTitle = data.posts[i].bookTitle;
									var publisher = data.posts[i].publisher;
									var publisherName = data.posts[i].publisherName;
									var post_href = "./detail.jsp" + "?postID="
											+ postID;
									storage["totalPage"] = totalPage;
									var publisher_href = "properson.jsp?userID="
											+ publisher;
									var post_change_href="post_change.jsp?postID="+postID;
									$(".all_channel")
											.append(
													'<div class="channel">'
															+ '<div class="likes"><a class="like_num">'
															+ commentNum
															+ '</a><br><a>喜欢</a></div>'
															+ '<div class="channel-item">'
															+ '<div class="bd">'
															+ "<a class='s_title'href="+post_href+">"
															+ title
															+ "</a>"
															+ '<div class="block">'
															+ '<div class="pic">'
															+ '<div class="pic-wrap">'
															+ '<a href='+post_href+'><img src="'+cover+'"></a>'
															+ '</div>'
															+ '</div>'
															+ '<div class="text_content">'
															+ '<a class="content" href='+post_href+'>'
															+ content
															+ '</a>'
															+ '</div>'
															+ '</div>'
															+ '<div class="source">'
															+ '<span class="from">来自&nbsp<a class="publisher" href='+publisher_href+ '>'
															+ publisherName
															+ '</a></span>&nbsp&nbsp'
															+ '<span class="pubtime">'
															+ time
															+ '</span>'
															+ '<span >&nbsp&nbsp&nbsp书名:&nbsp'
															+ bookTitle
															+ '</span>'
															+ '<span >&nbsp&nbsp&nbsp作者:&nbsp'
															+ author
															+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>'
															+ '<a href='+post_change_href+' value='+postID+'>编辑&nbsp&nbsp&nbsp</a>'
															+ '<a class="delete" id="delete" postID='+postID+'>删除</a>'
															+ '</div>'
															+ '</div>'
															+ '</div>'
															+ '</div>');

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
			$('#home_slider').flexslider({
				animation : 'slide',
				controlNav : true,
				directionNav : true,
				animationLoop : true,
				slideshow : true,
				slideshowSpeed : 2000,
				useCSS : false
			});
		});
		$(function() {
			$(".all_channel")
					.on(
							'click',
							'[class="delete"]',
							function(event) {	
								
								var r=Lobibox.confirm({
									msg:"确定要删除吗？",
										callback: function ($this, type, yes) {
											if(type=='yes'){
												var postID = $(event.target).attr("postID");
												var userID=$.cookie("userID");
												url="DeletePost"; //删除内容
												args={
														"postID":postID,
														"userID":userID
												}
												$.post(url,args,function(data){
													if(data.success=="true"){
														Lobibox.alert('success',{msg:"删除成功"});
													}else{
														Lobibox.alert('error',{msg:"删除失败"});
													}
												},"json")
											}else{return;}
											
									    }
								});
								
								

							})
		})
	</script>
	<script src="js/index_page.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

