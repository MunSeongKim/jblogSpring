<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
/* pager */
div.pager {
	width:100%;
	text-align:center;
}
div.pager  ul {
	height:20px;
	margin:10px auto;
}
div.pager  ul li 		  { display:inline-block; margin:5px 0; width:20px ; font-weight:bold; color:#ddd }
div.pager  ul li.selected { text-decoration: underline; color:#f40808 }
div.pager  ul li a,
div.pager  ul li a:visited,
div.pager  ul li a:link,
div.pager  ul li a:active { text-decoration: none; color:#555 }
div.pager  ul li a:hover { text-decoration: none; color:#f00 }
</style>
<div class="pager">
	<ul>
		<c:if test='${ pager.leftNavigator }'>
			<li><c:choose>
					<c:when test="${not empty categoryNo }">
						<a
							href="${pageContext.servletContext.contextPath }/${blog.userId }/${categoryNo }/${pager.startPageNumber-1 }/${post.no}">◀</a>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.servletContext.contextPath }/${blog.userId }/${pager.startPageNumber-1 }/${post.no}">◀</a>
					</c:otherwise>
				</c:choose></li>
		</c:if>
		<c:forEach begin='0' end='${ pager.pageCount -1 }' var='i' step='1'>
			<c:choose>
				<c:when test="${not empty categoryNo }">
					<c:choose>
						<c:when
							test='${ (pager.startPageNumber + i) gt pager.totalPageCount }'>
							<li>${ pager.startPageNumber + i }</li>
						</c:when>
						<c:when
							test='${ (pager.startPageNumber + i) eq pager.currentPageNumber }'>
							<li class="selected"><a
								href="${pageContext.servletContext.contextPath }/${blog.userId }/${categoryNo }/${pager.startPageNumber+i }/${post.no}">${ pager.startPageNumber + i }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/${blog.userId }/${categoryNo }/${pager.startPageNumber+i }/${post.no}">${ pager.startPageNumber + i }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when
							test='${ (pager.startPageNumber + i) gt pager.totalPageCount }'>
							<li>${ pager.startPageNumber + i }</li>
						</c:when>
						<c:when
							test='${ (pager.startPageNumber + i) eq pager.currentPageNumber }'>
							<li class="selected"><a
								href="${pageContext.servletContext.contextPath }/${blog.userId }/${pager.startPageNumber+i }/${post.no}">${ pager.startPageNumber + i }</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.servletContext.contextPath }/${blog.userId }/${pager.startPageNumber+i }/${post.no}">${ pager.startPageNumber + i }</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test='${ pager.rightNavigator }'>
			<li><c:choose>
					<c:when test="${not empty categoryNo }">
						<a
							href="${pageContext.servletContext.contextPath }/${blog.userId }/${categoryNo }/${pager.endPageNumber-1 }/${post.no}">▶</a>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.servletContext.contextPath }/${blog.userId }/${pager.endPageNumber-1 }/${post.no}">▶</a>
					</c:otherwise>
				</c:choose></li>
		</c:if>
	</ul>
</div>