<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoard</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#addbtn').click(function(){
			let html = `
				<tr>
					<td></td>
					<td><input type="file" class="boardfile" name="boardfile" multiple="multiple"></td>
				</tr>
				`;
			$('#appendfile').append(html);
	})
	
	$('#delbtn').click(function(){
		$('#appendfile').children().last().remove();
	})

	$("#submitbtn").click(function(){
		let ck = true;
		$('.boardfile').each(function(index,item){
				if($(item).val()==''){
				ck = false;
			}
		})
		if(ck == true){
			$('#fileuploadForm').submit();
		}else{
			alert('선택하지 않은 파일이 있습니다.');
		}
	})
})
</script>
</head>
<body>
	<h1>자료실 입력</h1>
	<a href="${pageContext.request.contextPath}/">index</a>
	<form id="fileuploadForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/addBoard">
		<table border="1">
			<tr>
				<th>board_title</th>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<th>board_content</th>
				<td><textarea cols="50" rows="3" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<th style="float:auto;">
					board_file
					<br>
					<button type="button" id="addbtn" style="float:auto;">[+]</button>
					<button type="button" id="delbtn" style="float:auto;">[-]</button>
				</th>
				<td>
					<div id="appendfile"></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<button type="button" id="submitbtn">추가하기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>