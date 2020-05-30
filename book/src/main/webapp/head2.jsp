<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>

    
</head>
<body><!------------------------------head------------------------------>

        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            <li><a href="search.jsp?bookType=1">国外著作</a></li>
            <li><a href="search.jsp?bookType=2">国内著作</a></li>
            <li><a href="search.jsp?bookType=3">国内网文</a></li>
            <li><a href="search.jsp?bookType=4">耽美文学</a></li>
            <li><a href="search.jsp?bookType=5">日轻小说</a></li>
            <li><a href="search.jsp?bookType=6">其它 </a></li>
        </ul>


<script type="text/javascript">

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
</body>
</html>