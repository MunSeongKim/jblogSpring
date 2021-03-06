<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog/admin/menu.jsp">
					<c:param name="menu" value="write" />
				</c:import>
				<form action="${pageContext.servletContext.contextPath }/${blog.userId}/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td class="b">
			      				<select name="categoryNo">
			      				<c:forEach items="${ categories }" var="category" varStatus="status">
				      				<option value="${category.no }">${category.name }</option>
								</c:forEach>
				      			</select>
			      				<input type="text" size="60" name="title">
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="body"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>