<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font">
				</i><a href="/book/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name"
				href="/book/manage/admin_dofollowselect">关注管理</a>
				<span class="crumb-step">&gt;</span><span>修改关注</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/book/manage/admin_dofollowupdate" method="post" id="myform" name="myform">
				<input type="hidden" name="FOLLOWID" value="${follow.id }">
				<input type="hidden" name="cpage" value="${cpage}">
				<input type="hidden" name="keywords" value="${keywords }">
				<table class="insert-tab" width="100%">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>用户ID：</th>
							<td><input class="common-text required" id="FOLLOW_FOLLOWERID" name="FOLLOW_FOLLOWERID" size="50" value="${follow.followerId }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>被关注人ID：</th>
							<td><input class="common-text required" id="FOLLOW_FOLLOWINGID" name="FOLLOW_FOLLOWINGID" size="50" value="${follow.followingId }" type="text"></td>
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