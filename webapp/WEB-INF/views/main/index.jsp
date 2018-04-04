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
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/main/header.jsp" />
		<form class="search-form">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="title" checked> <label>블로그 제목</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="user"> <label>블로거</label>
			</fieldset>
		</form>
		<div id="list">
			<c:if test="${not empty blogs }">
			<hr />
			</c:if>
			<ul id="list-guestbook">
				<c:forEach items="${ blogs }" var="blog" varStatus="status">
				<a href="${pageContext.request.contextPath}/${blog.userId }">
				<li style="background-image: url('${pageContext.request.contextPath}/${blog.imagePath }');">
					<div class="list-header">
						<strong>${ blog.userId }</strong> <p class="date">RegDate</p>
					</div>
					<p class="content">
						${blog.title }
					</p>
				</li>
				</a>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>