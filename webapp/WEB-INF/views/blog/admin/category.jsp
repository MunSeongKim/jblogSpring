<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
	<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
	<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
var CategoryManager = {
	init: function() {
		$('#btn-addCategory').click(this.__onAddCategoryClicked);
		$('.btn-delete').click(this.__onDelCategoryClicked);
	},
	__onAddCategoryClicked: function() {
		var name = $('#category-name').val();
		var desc = $('#category-desc').val();
		var category = JSON.stringify({ 'name': name, 'description': desc, 'userId': "${blog.userId}" });
		
		$.ajax({
			url: '${ pageContext.servletContext.contextPath }/${blog.userId}/api/blog/category',
			type: 'POST',
			data: category,
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			success: function( response, status, xhr ) {
				if( response.result == "success" ){
					var category = response.data;
					var table = $('.admin-cat')
					var index = $('.admin-cat tr').size();
					table.append("<tr>"
							 + "<td>" + index + "</td>"
							 + "<td>" + category.name + "</td>"
							 + "<td>" + category.postCount + "</td>"
							 + "<td>" + category.description + "</td>"
							 + "<td><img class='btn-delete' id='cate-"+ category.no +"' data-no='" + category.no + "' src='${pageContext.request.contextPath}/assets/images/delete.jpg' /></td>"
							 + "</tr>" );
					$('.admin-cat').on('click', "cate-"+category.no, CategoryManager.__onDelCategoryClicked);
					$('#category-name').val("");
					$('#category-desc').val("");
					return ;
				}
				console.log("[" + response.result + "]: " + response.message);
			},
			error: function( e, status, xhr ) {
				console.error("[" + status + "] " + e);
			}
		});
	},
	__onDelCategoryClicked: function() {
		var categoryNo = $(event.target).data("no");
		var category = JSON.stringify({ 'no': categoryNo, 'userId': "${blog.userId}" });
		var removeTarget = $(event.target).closest("tr");

		$.ajax({
			url: '${ pageContext.servletContext.contextPath }/${blog.userId}/api/blog/category',
			type: 'DELETE',
			data: category,
			contentType: 'application/json; charset=utf-8',
			dataType: 'json',
			success: function( response, status, xhr ) {
				console.log(response);
				if( response.result == "success" ){
					// Remove in category table
					removeTarget.remove();
					var rows = $('.admin-cat tr')
					for(var i = 1; i < rows.size(); i++){
						$(rows[i]).children().first().text(i);		
					}
					return ;
				}
				
				console.log("[" + response.result + "]: " + response.message);
			},
			error: function( e, status, xhr ) {
				console.error("[" + status + "] " + e);
			}
		});
	}
};

$(function() {
	CategoryManager.init();	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog/admin/menu.jsp">
					<c:param name="menu" value="category" />
				</c:import>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${ categories }" var="category" varStatus="status">
						<tr>
							<td>${status.index +1 }</td>
							<td>${category.name }</td>
							<td>${category.postCount }</td>
							<td>${category.description }</td>
							<td>
							<c:if test="${status.index ne 0 }">
								<img class="btn-delete" id="cate-${category.no}" data-no="${category.no }" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table class="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="category-name" type="text" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="category-desc" type="text" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="btn-addCategory" type="button" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>