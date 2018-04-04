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
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${not empty post }">
							<h4>${post.title }</h4>
							<p>
								${post.body }
							<p>
						</c:when>
						<c:otherwise>
							<h4>포스트가 없습니다.</h4>
							<p>
								작성 된 포스트가 없습니다.
							<p>
						</c:otherwise>
					</c:choose>
				</div>
				<hr />
				<ul class="blog-list">
					<c:forEach items="${posts }" var="post" varStatus="status" >
					<li>
					<c:choose>
					<c:when test="${not empty categoryNo }">
					<a href="${pageContext.servletContext.contextPath }/${blog.userId }/${categoryNo }/${post.no}?p=${pager.currentPageNumber }">${post.title }</a> <span>${post.regDate }</span>
					</c:when>
					<c:otherwise>
					<a href="${pageContext.servletContext.contextPath }/${blog.userId }/${post.no}?p=${pager.currentPageNumber }">${post.title }</a> <span>${post.regDate }</span>
					</c:otherwise>
					</c:choose>
					</li>
					</c:forEach>
				</ul>
				<c:import url="/WEB-INF/views/pager/pagination.jsp" />
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${blog.imagePath}">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>