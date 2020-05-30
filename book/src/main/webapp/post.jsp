<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8" />
<title>书友</title>
<link rel="stylesheet" type="text/css" href="bookcom/public.css" />
<link rel="stylesheet" type="text/css" href="bookcom/post.css" />
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
			<a class="title">帖子发布</a>
		</div>
		<div class="book_info">
			<div class='input'>
				<span>发布标题</span><input id="postTitle" type="text" value="" />
			</div>
			<div class='input'>
				<span>发布书籍</span><input id="bookTitle" type="text" value="" />
			</div>
			<div class='input'>
				<span>书籍作者</span><input id="author" type="text" value="" />
			</div>
			<div class='input'>
				<span>书籍类型</span> <select id="bookType" value="1">
					<option value="1">国外著作</option>
					<option value="2">国内著作</option>
					<option value="3">国内网文</option>
					<option value="4">耽美文学</option>
					<option value="5">日轻小说</option>
					<option value="6">其他</option>
				</select>
			</div>
			<a class="small_title">添加封面</a>
			<div class="cover">
				<a id="show_cover"><img	id="img" src="img/temp.jpg" /></a>
				<a class="file">选择文件<input value="上传图片" type="file" id="file"  name="file"  /></a>
			</div>
		</div>
		<a class="h1">写下你的内容吧</a>
		<div class="post_content">
			<textarea id="textarea" maxlength="1500"></textarea>
		</div>

		<div class="post_button">
			<a>发布</a>
		</div>
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

		 $('#file').change(function(e) {
		        var _URL = window.URL || window.webkitURL;
		        var file, img;
		        if ((file = this.files[0])) {
		            img = new Image();
		            img.onload = function() {
		                $('#img').attr('src', this.src);
		            };
		            img.src = _URL.createObjectURL(file);
		        }
		    })
		$('.post_button').click(function() {

			run(function(data) {
				if($("#bookTitle")==null){
					alert('请填写书籍');
					return;
				}
				if($("#content")==null){
					alert('请填写发布内容');
					return;
				}
				if($("#author")==null){
					alert('请填写书籍作者');
					return;
				}
				uploadpost(data);
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
						var file = $("#file")[0].files[0];
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
		 function uploadpost(img) {
				//判断是否有选择上传文件
				var imgPath = $("#file").val();
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

			/*	var url = "js/reg_userAccount.js";//发布功能*/
			var url = "PublishPost";
				var postTitle=$("#postTitle").val();
				var bookTitle=$("#bookTitle").val();
				var bookType=$("#bookType").val();
				var content=$("#textarea").val();
				var author=$("#author").val();
				content_check(bookTitle);
				content_check(author);
				var content=content.replace(/\ /g, "&nbsp");
				content=content.replace(new RegExp("\n", "gm"), '<br/>');
				var args = {
					"publisher" : $.cookie("userID"),
					"cover" : img.substr(img.indexOf(',') + 1),
					"postTitle": postTitle,
					"bookTitle":bookTitle,
					"bookType":bookType,
					"author":author,
					"content"	:content
				}
				$.post(url, args, function(data) {
					if(data.success=="true"){
						Lobibox.alert("success",{msg:"发布成功",callback: function ($this, type, yes) {
					if(type=='ok'){
						
						
								
								window.location.href="detail.jsp?postID="+data.postID;
							
					}else{window.location.href="login.jsp";}
					
			    }});}
					else{
						Lobibox.alert("error",{msg:"发布失败"});
					}
				}, "json")

			}
		 function content_check(content){
			 content=content.replace(/[^u4e00-u9fa5w]/g, '');
				content = content.replace(/\</g, '');
				content = content.replace(/\>/g, '');
		 }
	</script>
	<script src="js/index_page.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

