<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<h1 id="head">boardList</h1>
	
	<div class="menu">
		<a class="menuEl" href="${pageContext.request.contextPath}/">index</a>
		<a class="menuEl" href="${pageContext.request.contextPath}/addBoard">addBoard</a>
	</div>
	
	<table border="1">
		<tr>
			<th>board_id</th>
			<th>board_title</th>
		<tr>
		<c:forEach var="b" items="${boardList }">
			<tr>
				<td>${b.boardId }</td>
				<td>
					<a href="${pageContext.request.contextPath }/boardOne/${b.boardId}/1">${b.boardTitle}</a>
				</td>
			</tr>
		</c:forEach>
		
	<!-- 페이지에 따라 기능하는 페이지 넘기기 -->
	</table>
	<c:if test="${currentPage==1 && lastPage != 1}">
		<a href="">처음으로</a>
		<a href="">이전</a>
		<a href="${pageContext.request.contextPath}/boardList/${currentPage+1}">다음</a>
		<a href="${pageContext.request.contextPath}/boardList/${lastPage}">마지막으로</a>
	</c:if>
	<c:if test="${currentPage == lastPage && lastPage != 1}">
		<a href="${pageContext.request.contextPath}/boardList/1">처음으로</a>
		<a href="${pageContext.request.contextPath}/boardList/${currentPage-1}">이전</a>
		<a href="">다음</a>
		<a href="">마지막으로</a>
	</c:if>
	<c:if test="${currentPage > 1 && currentPage < lastPage }">
		<a href="${pageContext.request.contextPath}/boardList/1">처음으로</a>
		<a href="${pageContext.request.contextPath}/boardList/${currentPage-1}">이전</a>
		<a href="${pageContext.request.contextPath}/boardList/${currentPage+1}">다음</a>
		<a href="${pageContext.request.contextPath}/boardList/${lastPage}">마지막으로</a>
	</c:if>
	<c:if test="${currentPage == 1 && lastPage == 1 }">
		<a href="">처음으로</a>
		<a href="">이전</a>
		<a href="">다음</a>
		<a href="">마지막으로</a>
	</c:if>
</body>
</html>