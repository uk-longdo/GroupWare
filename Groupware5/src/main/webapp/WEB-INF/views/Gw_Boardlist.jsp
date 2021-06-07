<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>익명게시판</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
tr:hover{
	cursor: pointer;
	color: black;
}
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
<table class="table table-dark table-striped">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
		<td>날짜</td>
	</tr>

<c:forEach var="board" items="${board }">
	<tr onclick="location.href='Gw_board_read?board_idx=${board.board_idx}'">
		<td>${board.board_idx }</td>
		<td>${board.board_title } [${board.comm_cnt}]</td>
		<td>익명</td>
		<td>${board.board_count }</td>
		<td>${board.board_date }</td>
		
	
	</tr>
</c:forEach>

<tr>
	<td colspan="5" style="text-align: center;">
		<input type="button" onclick="location.href='Gw_board_insert'" value="글쓰기"> 
	</td>
<tr>

</table>

<div id="tests">
	<div>
		<c:if test="${prev == true }">
			<a href="Gw_Boardlist?page=${startPage-1 }">이전</a>
		</c:if>
		<c:forEach var="page" begin="${startPage }" end="${end_Page }">
			<c:choose>
				<c:when test="${page eq page_page }">
					<span style="font-weight: bold;">${page }</span>
				</c:when>
				<c:otherwise>
					<a href="Gw_Boardlist?page=${page}">${page }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${next == true and end_Page > 0 }">
			<a href="Gw_Boardlist?page=${end_Page+1 }">다음</a>		
		</c:if>
	</div>
</div>


</main>
</div>
</div>
</div>
</body>
</html>