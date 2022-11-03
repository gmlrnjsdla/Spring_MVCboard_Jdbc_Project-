<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 수정</title>
</head>
<body>
	<h2>글 내용 수정</h2>
	<hr>
	<table width = "500" border = "1" cellspacing="0" cellpadding ="0">
		<form action="modify">
		<input type="hidden" name="bid" value="${content.bid}">
		<tr>
			<th width="100">글번호</th>
			<td>${content.bid}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${content.bhit}</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td><input type="text" name="bname" value="${content.bname}" size="60"></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input type="text" name="btitle" value="${content.btitle}" size="60"></td>
		</tr>
		<tr>
			<th valign ="top">글내용</th>
			<td valign ="top" height ="100">
				<textarea name="bcontent" rows="10" cols="45">${content.bcontent}</textarea> 
			</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${content.bdate}</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정완료" >
				<input type="reset" value="취소" >
				<input type="button" value="목록" onclick="jaavascript:window.location='list'">
			</td>
		</tr>
		</form>
	</table>
</body>
</html>