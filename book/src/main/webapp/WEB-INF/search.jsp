<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书友</title>
    <link rel="stylesheet" type="text/css" href="bookcom/public.css"/>
    <link rel="stylesheet" type="text/css" href="bookcom/index.css"/>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
</head>
<body><!------------------------------head------------------------------>
<div class="head">
     <div class="wrapper clearfix">
		<%@ include file="head1.jsp" %>
		<%@ include file="head2.jsp" %>	
    </div>
</div><!-------------------------banner--------------------------->
<div class="all">
		
	<div class="mainpage">
	<div style="width:100%;height:30px;margin-left:30%;"><a class="search_change_button" id="search_by_username"  style="font-size:30px;border:1px  dashed #1e1e1e;padding:0px 20px;">显示按用户搜索</a>
	<a style="font-size:30px;padding:0px 20px;border:1px  dashed #1e1e1e;" id="search_by_bookname" >显示按书名搜索</a></div>
		<div class="all_channel">
			
		</div>
		
		
	</div>
</div>	
<div class="gotop"><a href="cart.html">
    
    <dl>
        <dt><img src="img/gt3.png"/></dt>
        <dd>个人<br/>中心</dd>
    </dl>
</a><a href="#" class="toptop" style="display: none">
    <dl>
        <dt><img src="img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
    </dl>
</a>
    <p>400-800-8200</p></div>
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

<script src="js/book_page.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

var storage=window.localStorage;
function getUrlParam(name) {//封装方法
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]);
	return null;} //返回参数值
	

	function search(searchtype,url){
		var url='Search';//搜索展示页面对应文档的唯一功能
		var keywords=getUrlParam("keywords");
		 var _t = encodeURI(encodeURI(keywords));
		 var page = getUrlParam("page");
		 var booktype = getUrlParam("bookType");
		 var searchType = getUrlParam("searchType");
		 
	
	

		 
			var args ={
					"keywords":_t,
					"page":page,
					"bookType":booktype,
					"searchType":searchType};
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
			var publisher=data.posts[i].publisher;
			var author=data.posts[i].author;
			var bookTitle=data.posts[i].bookTitle;
			var publisherName=data.posts[i].publisherName;
			var post_href="./detail.jsp"+"?postID="+postID;
			
			storage["totalPage"]=totalPage;
			var publisher_href="properson.jsp?userID="+publisher;
			$(".all_channel").append(
					'<div class="channel">'+
					'<div class="likes"><a class="like_num">'+commentNum+'</a><br><a>讨论</a></div>'+
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
								'<span class="from">来自&nbsp&nbsp&nbsp<a class="publisher" href='+publisher_href+ '>'+publisherName+'&nbsp&nbsp&nbsp</a></span>'+
								'<span class="pubtime">'+time+'</span>'+
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
		
	}
	

	
	$(function(){
	
		var url="js/text.js";//搜索展示页面对应文档的唯一功能  至于按照怎么样的方式搜索 我把值都给你了  我只接受内容
		var searchtype="0";
		search(searchtype,url);
		$('#search_by_username').click(function(){
		
			var _t = getUrlParam("keywords");
			_t=encodeURI(_t);
			 var page = getUrlParam("page");
			 var bookType = getUrlParam("bookType");
			 var searchType = "1";
			
	window.location.href="search.jsp?"+"keywords="+_t+"&bookType="+bookType+"&searchType="+searchType+"&page="+page;
	
		})
		 
		$('#search_by_bookname').click(function(){
			 
			 var _t = getUrlParam("keywords");
			 _t=encodeURI(_t);
			 var page = getUrlParam("page");
			 var bookType = getUrlParam("bookType");
			 var searchType = "0";
			
				window.location.href="search.jsp?"+"keywords="+_t+"&bookType="+bookType+"&searchType="+searchType+"&page="+page;
		
	})})
	
	/* $(function(){
		
		$("#search_change_button").click(function(){
			var url =this.href;
			var args ={"time":new Date()};

			$.getJSON(url,args,function(data){
				$(".all_channel").empty();
			for( i=0;i<5;i++){
				var postID=data.person[i].postID;
				var title=data.person[i].title;
				var content=data.person[i].content;
				var cover=data.person[i].cover;
				var commentNum=data.person[i].commentNum;
				var time=data.person[i].time;
				var publisher=data.person[i].publisher;
				var post_href="./detail.jsp"+"?postID="+postID;
				
				$(".all_channel").append(
						'<div class="channel">'+
						'<div class="likes"><a class="like_num">'+commentNum+'</a><br><a>喜欢</a></div>'+
							'<div class="channel-item">'+
								'<div class="bd">'+
									'<a class="s_title" href="#">'+title+'</a>'+
									'<div class="block">'+
										'<div class="pic">'+
											'<div class="pic-wrap">'+
												'<a href="#"><img src="'+cover+'"></a>'+
											'</div>'+
										'</div>'+
										'<div class="text_content">'+
											'<a class="content">'+content+'</a>'+
										'</div>'+
									'</div>'+
									'<div class="source">'+
									'<span class="from">来自<a class="publisher" href='+post_href+ '>'+publisher+'</a></span>'+
									'<span class="pubtime">'+time+'</span>'+
									'</div>'+
								'</div>'+
							'</div>'+
					'</div>');
			
			}
			
			})
			return false;
		})
		
	}) */
/* window.onload=function()
{
  var search_change=document.getElementById("search_change_button");
  search_change.onclick=function()
  {	
  
		//3 创建XMLHttpRequest 对象
		var request = new XMLHttpRequest();
		//4 准备发送请求的数据 url
		var url="http://localhost:8080/testshop/js/text.js" + "#time" + new Date();
		var method="GET";
		//5 调用XMLHttpRequest 对象
		request.open(method,url,true);
		//6调用XMLhttpRequest 对象的send方法
		request.send();
		//7 为XML···对象添加 onreadystatechage 响应函数
		request.onreadystatechange = function(){
		if(request.readyState == 4){
			if(request.status ==200||request.status == 304){
				
				var result =request.responseText;
				var object = eval("("+ result+")");
				var name =object.person.name;
				
				alert("name");
				
			}
		}}
					
		
		//8判断响应是否完成：XMLH····对象的reaystate属性值为4
		//9 在判断响应是否可由:XMLHttpRequest 对象status属性为200
		//10打印响应结果:responseText
			//2取消默认行为		
 
			return false;//防止点击了跳转页面
			
  }
}
	 */

	
</script>
</body>
</html>