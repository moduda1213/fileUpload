<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		//데이터 5개씩 페이징 작업 할 것
		$("#updateBtn0").click(function(){
			$("#comment0").hide();
			$("#commentUpdate0").show();
		})
		$("#updateBtn1").click(function(){
			$("#comment1").hide();
			$("#commentUpdate1").show();
		})
		$("#updateBtn2").click(function(){
			$("#comment2").hide();
			$("#commentUpdate2").show();
		})
		$("#updateBtn3").click(function(){
			$("#comment3").hide();
			$("#commentUpdate3").show();
		})
		$("#updateBtn4").click(function(){
			$("#comment4").hide();
			$("#commentUpdate4").show();
		})
		
	})
</script>
</head>
<body>
	<h1>리스트 상세보기</h1>
	<a href="${pageContext.request.contextPath}/">index</a>
	<a href="${pageContext.request.contextPath }/updateBoard/${boardListOne.boardId }" style="float:auto" type="button">수정하기</a>
	
		<table border="1">
			<tr>
				<th>board_id</th>
				<td><input type="text" name="boardId" value="${boardListOne.boardId }"readonly>
			<tr>
				<th>board_title</th>
				<td><input type="text" value="${boardListOne.boardTitle }" name="boardTitle" readonly></td>
			</tr>
			<tr>
				<th>board_content</th>
				<td><textarea cols="50" rows="3" name="boardContent" readonly>${boardListOne.boardContent}</textarea></td>
			</tr>
		</table>
		
		<br>
		
		<table border="1">
			<tr>
				<th>첨부 파일</th>
				<td>
					<c:forEach var="bf" items="${boardListOne.boardfile}">
						<div>
							<a href="${pageContext.request.contextPath}/upload/${bf.boardfileName}">${bf.boardfileName } </a>
						</div>
					</c:forEach>
				</td>
			</tr>
		</table>
		
		<br>
		
		<table border="1">
			<tr>
				<th>댓글</th>
			</tr>
			<c:forEach var="c" items="${boardListOne.commentList}" varStatus="status">
				<c:if test="${c.commentContent != null}">
					<tr>
						<td>
							<div id="comment${status.index}">
								<input type="text" value="${c.commentContent }" name="commentContent" readonly>
								<a href="${pageContext.request.contextPath }/removeComment?commentId=${c.commentId }&boardId=${boardListOne.boardId}">삭제</a>
								
								<button type="button" id="updateBtn${status.index}">수정</button>
							</div>
							
							<form style="display:none" id ="commentUpdate${status.index}" method="post" action="${pageContext.request.contextPath }/updateComment">
								<div>
									<input type="hidden" name="commentId" value="${c.commentId }">
									<input type="hidden" name="boardId" value="${boardListOne.boardId }">
									<textarea  rows="3" cols="50" name="commentContent">${c.commentContent }</textarea>
								</div>
								<button type="submit">수정</button>
							</form>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${c.commentContent == null}">
					<!-- 다 삭제했을 때 빈칸이 남아있는 것을 없애기 위해 -->
				</c:if>
			</c:forEach>
		</table>
		<c:if test="${currentPage==1 && lastPage != 1}">
				<a href="">처음으로</a>
				<a href="">이전</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${currentPage+1}">다음</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${lastPage}">마지막으로</a>
			</c:if>
			<c:if test="${currentPage == lastPage && lastPage != 1}">
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/1">처음으로</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${currentPage-1}">이전</a>
				<a href="">다음</a>
				<a href="">마지막으로</a>
			</c:if>
			<c:if test="${currentPage > 1 && currentPage < lastPage }">
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/1">처음으로</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${currentPage-1}">이전</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${currentPage+1}">다음</a>
				<a href="${pageContext.request.contextPath }/boardOne/${boardListOne.boardId}/${lastPage}">마지막으로</a>
			</c:if>
			<c:if test="${currentPage == 1 && lastPage == 1 }">
				<a href="">처음으로</a>
				<a href="">이전</a>
				<a href="">다음</a>
				<a href="">마지막으로</a>
			</c:if>
		<br>
		
		<form method="post" action="${pageContext.request.contextPath}/addComment">
			<table border="1">
				<tr>
					<th>댓글입력</th>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="boardId" value="${boardListOne.boardId }">
						<textarea rows="3" cols="50" name="commentContent"></textarea>
					</td>
				</tr>
				<tr>
					<td><button type="submit" style="float:right">댓글 입력</button></td>
				</tr>
			</table>
		</form>
</body>
</html>