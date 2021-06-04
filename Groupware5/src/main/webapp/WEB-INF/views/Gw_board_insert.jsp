<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" >
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>



<form action="board_insert" method="post">
<div class="test">
<table class="table table-dark table-striped">
	<tr>
		<td>제목
			<div class="input-group mb-3">
			  	
			  	<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="board_title" style="background-color: transparent;color: white; ">
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="board_content" style="background-color: transparent; min-height: 300px; color: white;" ></textarea>
			</div>
			
		</td>
		
			
	</tr>
	<tr>
		<td style="text-align: center;">
			<input type="hidden" value="${sessionScope.user_idx }" name="user_idx"> 
			<input type="submit" value="등록" class="btn btn-outline-white" style="background-color: transparent;color: white;">
		</td>
	</tr>
</table>
</div>
</form>
</main>
</div>
</div>
</div>
</body>
</html>