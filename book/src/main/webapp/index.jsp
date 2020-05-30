<%@ page language="java" contentType="text/html; charset=utf-8"%>
 
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书友</title>
    <link rel="stylesheet" type="text/css" href="bookcom/public.css"/>
    <link rel="stylesheet" type="text/css" href="bookcom/index.css"/>
    <link rel="stylesheet" href="css/lobibox.css">
</head>
<body><!------------------------------head------------------------------>
<div class="head">
 <div class="wrapper clearfix">
		<%@ include file="head1.jsp" %>
		<%@ include file="head2.jsp" %>	
    </div>
    
</div><!-------------------------banner--------------------------->
<div class="all">
		<div><a class="title">讨论精选</a></div>

	<div class="mainpage">
		<div class="all_channel_parrent">
			<div class="all_channel">
		
				</div>
		</div>
		<div class="user">
			<div class="user_title">
				<div><a class="user_head">推荐关注</a></div>
			</div>
			<div class="recommend_area">
				<div class="all_recommend">
					<div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div><div class="recommend">
						<div class="pic">
							<a href="#"><img src="img/flo1.jpg"></a>
						</div>	
							<div class="name_area"><a class="user_name" href="#">你的好哥哥</a></div>
						<div class="num_area">
							<a class="num" href="#">56522关注</a>
							<a class="join" href="#">+关注</a>
							<br><br/>
						</div>
					</div>
				
				</div>
				
			</div>
			
   
			
	
		</div>
		
	</div>
</div>	
<div class="list-page">
					<div id="total_page" style="margin-bottom:20px;"></div> <a id="first_page1">首页</a> <a id="previous_page1">上一页</a> <a id="next_page1">下一页</a>
					<a id="last_page1">尾页</a>

				</div>

	
	
	<!-------------------login--------------------------><!--footer-->

<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.flexslider-min.js" type="text/javascript" charset="utf-8"></script>

<script src="js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.base64.js" type="text/javascript"
		charset="utf-8"></script>
		<script src="js/Lobibox.js"></script>  
<script type="text/javascript">

var storage=window.localStorage;
function getUrlParam(name) {//封装方法
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]);
	return null;} //返回参数值

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
	
$(function(){
		
		var url ="HotUser";//这是对应主页用户推荐功能 对应功能2 请给我该用户没关注的人 谢谢
		
		var args ={"time":new Date()};
	
		
		$.post(url,args,function(data){
			$(".all_recommend").empty();
			var num=data.num;
		for( i=0;i<num;i++){
			var userID=data.list[i].userID;
			var icon=data.list[i].icon;
			var username=data.list[i].username;
			var followersNum=data.list[i].followersNum;
			
			var href="properson.jsp?userID="+userID;
			$(".all_recommend").append("<div class='recommend'>"+
			"<div class='pic'>"+
			"<a href="+href+"><img src='img/flo1.jpg'></a>"+
		"</div>	"+
			"<div class='name_area'><a class='user_name' href="+href+">"+username+"</a></div>"+
		"<div class='num_area'>"+
		"<a style='{padding-lef:10px; float:left}'>关注数  </a>"+
			"<a class='num' style='{padding-lef:10px; float:left}'>"+followersNum+"</a>"+
			"<a class='join' value="+userID+" style='{padding-lef:20px; float:left}'>关注</a>"+
			"<br><br/>"+
		"</div>"+
	"</div>");
			
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
		
		},"json")
		
	})
$(function(){
	$(".all_recommend").on('click','[class="join"]',function(event){
		var userID=$(event.target).attr("value");
		
		url="Follow";//这里对应关注功能 我这边只给你该用户和他关注的谁 至于是关注还是取关 我真不知道。你们只有一个接口 我也只有一个效果
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
					$(event.target).prev().text(Number($(event.target).prevAll(".num").text())+1);
				}else{
				
					$(event.target).text('关注');
					$(event.target).css("color","blue");
					$(event.target).prev().text(Number($(event.target).prevAll(".num").text())-1);
				}
			}else{
				
			}
			
		
			
			
		},"json")
		
	})
})
$(function(){
		
		var url ="HotPost";
		/*var url ="js/text.js";*///这是主页得到精品讨论帖子的功能 对应主页功能1
		var page=getUrlParam("page");
		var args ={"time":new Date(),
				"page":page};
	
		
		$.post(url,args,function(data){
			$(".all_channel").empty();
			var num=data.num;
		for( i=0;i<num;i++){
			var totalPage=data.totalPage;
			var postID=data.posts[i].postID;
			var title=data.posts[i].postTitle;
			var content=data.posts[i].content;
			var cover=data.posts[i].cover;
			var commentNum=data.posts[i].commentNum;
			var time=data.posts[i].time;
			var author=data.posts[i].author;
			var bookTitle=data.posts[i].bookTitle;
			var publisher=data.posts[i].publisher;
			var publisherName=data.posts[i].publisherName;
			var post_href="./detail.jsp"+"?postID="+postID;
			storage["totalPage"]=totalPage;
			var publisher_href="properson.jsp?userID="+publisher;
		
			$(".all_channel").append(
					'<div class="channel">'+
					'<div class="likes"><a class="like_num">'+commentNum+'</a><br><a>喜欢</a></div>'+
						'<div class="channel-item">'+
							'<div class="bd">'+
								"<a class='s_title'href="+post_href+">"+title+"</a>"+
								'<div class="block">'+
									'<div class="pic">'+
										'<div class="pic-wrap">'+
											'<a href='+post_href+'><img src="'+cover+'"></a>'+
										'</div>'+
									'</div>'+
									'<div class="text_content">'+
										'<a class="content" href='+post_href+'>'+content+'</a>'+
									'</div>'+
								'</div>'+
								'<div class="source">'+
								'<span class="from">来自&nbsp<a class="publisher" href='+publisher_href+ '>'+publisherName+'</a></span>'+
								'<span class="pubtime">&nbsp&nbsp'+time+'</span>'+
								'<span >&nbsp&nbsp&nbsp书名'+bookTitle+'</span>'+
								'<span >&nbsp&nbsp&nbsp作者'+author+'</span>'+
								'</div>'+
							'</div>'+
						'</div>'+
				'</div>');
			
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
		
		},"json")
		
	})
$(function () {
    $('#home_slider').flexslider({
        animation: 'slide',
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        slideshow: true,
        slideshowSpeed: 2000,
        useCSS: false
    });
});</script>
<script src="js/index_page.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

