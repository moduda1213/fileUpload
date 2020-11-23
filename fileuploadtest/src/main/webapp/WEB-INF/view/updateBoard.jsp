<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateBoard</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#addbtn').click(function(){
			let html = `
					<div>
						<input type="file" class="boardfile" name="boardfile" multiple="multiple">
					</div>
				`;
			$('#appendfile').append(html);
	})
	
	$('#delbtn').click(function(){
		$('#appendfile').children().last().remove();
	})

	$("#updateBoard").click(function(){
		let ck = true;
		$('.boardfile').each(function(index,item){
				if($(item).val()==''){
				ck = false;
			}
		})
		if(ck == true){
			$('#fileUpdateForm').submit();
		}else{
			alert('선택하지 않은 파일이 있습니다.');
		}
	})
	$("#deleteFile").click(function(){
			let ck = document.getElementsByClassName("ck");
			console.log(ck.length+"<-- ck.length");

			$.ajax({
				url:'${pageContext.request.contextPath}/deleteFileOne',
				type:'post',
				dataType:'json',
				data: {boardId : ${board.boardId}}
				})
				
			for(let i=0;i<ck.length+1;i++){
				let check = $('input[name='+i+']').is(':checked');
				console.log(check+"<- check");
				if(check){
						$("#"+i).remove();
					}
				}
		})
	})
</script>
</head>
<body>
	<h1>리스트 수정</h1>
	<a href="${pageContext.request.contextPath}/">index</a>
	<form id="fileUpdateForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/updateBoard?boardId=${board.boardId}">
		<table border="1">
			<tr>
				<th>board_id</th>
				<td><input type="text" name="boardId" value="${board.boardId }"readonly>
			<tr>
				<th>board_title</th>
				<td><input type="text" value="${board.boardTitle }" name="boardTitle"></td>
			</tr>
			<tr>
				<th>board_content</th>
				<td><textarea cols="50" rows="3" name="boardContent">${board.boardContent}</textarea></td>
			</tr>
			<tr>
				<th style="float:auto;">
					파일 첨부
					<br>
					<button type="button" id="addbtn" style="float:auto;">[+]</button>
					<button type="button" id="delbtn" style="float:auto;">[-]</button>
				</th>
				<td>
					<div id="appendfile"></div>
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td>
					<c:forEach var="bf" items="${board.boardfile}" varStatus="status">
						<div id="${status.index }">
							<a href="${pageContext.request.contextPath}/upload/${bf.boardfileName}">${bf.boardfileName } </a>
							<span><input type="checkbox" class="ck" name="${status.index }"></span>
						</div>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<button type="button" id="updateBoard">수정하기</button>
					<button type="button" id="deleteFile" style="float:right">파일 삭제</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>