<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css?=ver1" rel="stylesheet" />
<style>
	#tests{
		
		display: flex;
		justify-content: space-between;
		padding : 0.3rem;
	}

</style>
</head>
<body>
<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" >
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
	<table class="table table-dark">
			<tr>
				<td style=" padding-bottom: 0px; border-top:0px;"><span style="padding-left:0.7rem; font-size:1.7rem;">${board2.board_title}</span></td>
			</tr>
			<tr>
				<td style=" padding-top: 0px; border-top:0px; font-size:0.8rem;">
					<span style="padding-left:0.7rem;">조회수 ${board2.board_count}</span>
					<span style="padding-left:0.7rem;">${board2.board_date}</span>
					
				</td>
			</tr>
			<tr>
				<td style="border-bottom:1px;">
				<div style="min-height:300px; padding-left:0.7rem;">${board2.board_content}</div>
				</td>
			</tr>
			<tr>
				<td><span style="padding-left:0.7rem; font-size:1.3rem;">댓글 ${comm_cnt}</span></td>
			</tr>
			<c:forEach var="comm" items="${comm}">
			<tr>
				<td style=" padding-top: 0px; font-size:0.8rem;">
					<span style="padding-left:0.7rem; font-size:0.9rem;">익명</span>
					<span style="padding-left:0.7rem; font-size:0.9rem;">${comm.comm_cotent}</span>
					<span style="padding-left:0.7rem;">${comm.comm_date}</span>
					
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td>
					<form action="comm" method="post">
					<div id="tests">
					<textarea class="form-control" id="exampleFormControlTextarea1" placeholder="내용을 입력하세요" name="comm_cotent" style="background-color: transparent; min-height: 100px; color: white;" ></textarea> 
					<input type="hidden" value="${board2.board_idx}" name="board_idx">
					<input type="hidden" value="${sessionScope.user_idx}" name="user_idx">
					<input type="submit" class="btn btn-dark btn-outline-light" value="댓글쓰기">
					</div>
					</form>
				</td>
			</tr>
	</table>
	<div id="tests">
		<div>
			<input class="btn btn-dark" type="button" value="목록" onclick = "location.href = 'Gw_Boardlist'">
		</div>
		<div>
			<input class="btn btn-dark" type="button" value="글쓰기" onclick = "location.href = 'Gw_board_insert'">
			<c:choose>
				<c:when test="${sessionScope.user_idx eq board2.user_idx}">
					<input class="btn btn-dark" type="button" value="수정" onclick = "location.href = ''">
					<input class="btn btn-dark" type="button" value="삭제" onclick="location.href='board_delete?board_idx=${board2.board_idx}'">
				</c:when>
			</c:choose>
			
		</div>
	</div>
	
</main>
</div>
</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

</body>
</html>