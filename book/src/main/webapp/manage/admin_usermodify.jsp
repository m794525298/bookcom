<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">

	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font">
				</i><a href="/book/manage/admin_index.jsp">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name"
				href="/book/manage/admin_douserselect">用户管理</a>
				<span class="crumb-step">&gt;</span><span>修改用户</span>
		</div>
	</div>
	<div class="result-wrap">
		<div class="result-content">
			<form action="/book/manage/admin_douserupdate" method="post" id="myform" name="myform">
				<input type="hidden" name="USERID" value="${user.id }">
				<input type="hidden" name="cpage" value="${cpage}">
				<input type="hidden" name="keywords" value="${keywords }">
				<table class="insert-tab" width="100%">
					<tbody>
						<tr>
							<th><i class="require-red">*</i>用户昵称：</th>
							<td><input class="common-text required" id="USER_NAME" name="USER_NAME" size="50" value="${user.nickname }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>用户账号：</th>
							<td><input class="common-text required" id="USER_ACCOUNT" name="USER_ACCOUNT" size="50" value="${user.account }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>用户密码：</th>
							<td><input class="common-text required" id="USER_PASSWORD" name="USER_PASSWORD" size="50" value="${user.password }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>用户头像：</th>
							<td><input class="common-text" name="USER_ICON" size="50" value="${user.icon }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>电子邮箱：</th>
							<td><input class="common-text" name="USER_EMAIL" size="50" value="${user.email }" type="text"></td>
						</tr>
						<tr>
							<th><i class="require-red">*</i>用户身份：</th>
							<td><input class="common-text required" id="USER_IDENTITY" name="USER_IDENTITY" size="50" value="${user.identity }" type="text"></td>
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