<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>

    
</head>
<body><!------------------------------head------------------------------>

        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="personal.jsp">个人主页</a></li>
            <li><a href="personal_post.jsp">帖子管理</a></li>
            <li><a href="personal_comment.jsp">评论回复</a></li>
            
            
        </ul>


<script type="text/javascript">
var storage = window.localStorage;
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
});
$(function(){
	if($.cookie('userID')==null||storage.getItem("icon")==null){
		
		var r=Lobibox.confirm({
			msg:"请先登录",
				callback: function ($this, type, yes) {
					if(type=='yes'){
						
						
								
								window.location.href="login.jsp";
							
					}else{window.location.href="login.jsp";}
					
			    }
		});
		
		
	}
	
})

</script>
</body>
</html>