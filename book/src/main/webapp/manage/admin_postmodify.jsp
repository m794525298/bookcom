<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font">
				</i><a href="/book/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name"
				href="/book/manage/admin_dopostselect">帖子管理</a>
				<span class="crumb-step">&gt;</span><span>修改帖子</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/book/manage/admin_dopostupdate" method="post" id="myform" name="myform">
				<input type="hidden" name="POSTID" value="${post.id }">
				<input type="hidden" name="cpage" value="${cpage}">
				<input type="hidden" name="keywords" value="${keywords }">
				<table class="insert-tab" width="100%">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>发布者ID:</th>
							<td><input class="common-text required" id="POST_PUBLISHERID" name="POST_PUBLISHERID" size="50" value="${post.publisherId }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>帖子标题：</th>
							<td><input class="common-text required" id="POST_TITLE" name="POST_POSTTITLE" size="50" value="${post.title }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>帖子封面：</th>
							<td><input class="common-text required" id="POST_COVER" name="POST_COVER" size="50" value="${post.cover }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>帖子内容：</th>
							<td><input class="common-text required" id="POST_CONTENT" name="POST_CONTENT" size="50" value="${post.content }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>书名：</th>
							<td><input class="common-text" name="POST_BOOKTITLE" size="50" value="${post.bookTitle }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>书本类型：</th>
							<td><input class="common-text" name="POST_BOOKTYPE" size="50" value="${post.bookType }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>书本作者：</th>
							<td><input class="common-text" name="POST_BOOKAUTHOR" size="50" value="${post.bookAuthor }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>状态：</th>
							<td><input class="common-text" name="POST_ISEXIST" size="50" value="${post.isExist }" type="text"></td>
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