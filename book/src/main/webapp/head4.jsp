<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8"/>

    
</head>
<body><!------------------------------head------------------------------>

        <ul class="clearfix" id="bott">
            <li><a href="index.jsp">首页</a></li>
            
            
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


</script>
</body>
</html>