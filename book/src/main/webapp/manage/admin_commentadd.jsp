<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="admin_index.jsp">首页</a><span
				class="crumb-step">&gt;</span><a class="crumb-name"
				href="/book/manage/admin_docommentselect">评论管理</a><span class="crumb-step">&gt;</span><span>新增评论</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/book/manage/admin_docommentadd" method="post"
				id="myform" name="myform">
				<table class="insert-tab" width="100%">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>用户ID：</th>
							<td><input class="common-text required" id="COMMENT_PUBLISHERID" name="COMMENT_PUBLISHERID" size="50" value="" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>帖子ID：</th>
							<td><input class="common-text required" id="COMMENT_POSTID" name="COMMENT_POSTID" size="50" value="" type="text"></td>
						</tr>
						<tr>
							<th>被回复评论ID：</th>
							<td><input class="common-text required" id="COMMENT_PARENT" name="COMMENT_PARENTID" size="50" value="" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>评论内容：</th>
							<td><input class="common-text required" id="COMMENT_CONTENT" name="COMMENT_CONTENT" size="50" value="" type="text"></td>
						</tr>
						
						
						<tr>
							<th></th>
							<td><input class="btn btn-primary btn6 mr10" value="提交"
								type="submit"> <input class="btn btn6"
								onClick="history.go(-1)" value="返回" type="button"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

</div>
<!--/main-->
</div>
</body>
</html>