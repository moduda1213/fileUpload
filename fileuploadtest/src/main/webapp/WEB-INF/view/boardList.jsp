<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
</head>
<body>
	<h1>boardList</h1>
	<a href="${pageContext.request.contextPath}/">index</a>
	<table border="1">
		<tr>
			<th>board_no</th>
			<th>board_title</th>
			<th>board_file</th>
			<th>삭제</th>
			<th>수정</th>
		<tr>
		<c:forEach var="b" items="${boardList }">
			<tr>
				<td>${b.boardId }</td>
				<td>${b.boardTitle }</td>
				<td>
					<c:forEach var="bf" items="${b.boardfile }">
						<div><a href="${pageContext.request.contextPath}/upload/${bf.boardfileName}">${bf.boardfileName }</a></div>
					</c:forEach>
				</td>
				<td><a href="${pageContext.request.contextPath}/removeBoard?boardId=${b.boardId}&currentPage=${currentPage}">삭제</a>
				<td><a href="${pageContext.request.contextPath}/updateBoard/${b.boardId}">수정</a>
			</tr>
		</c:forEach>
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