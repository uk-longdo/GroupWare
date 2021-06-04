<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
html{
	margin: 0, auto;
}

#tests{
		
		display: flex;
		justify-content: space-around;
		padding : 0.3rem;
	}


</style>
</head>
<body>



<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" style="height: 100%">
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
<h1>${sessionScope.user_name} 님 출근버튼을 눌러주세요</h1>
<div id="tests">
<form action="work_in" method="post" id="inform">
	
	<input type="hidden" name="user_idx" value="${sessionScope.user_idx}">
	
	<input type="button" id="in_ck" value="출근" class="btn btn-outline-primary">
</form>


<form action="work_out" method="post" id="offform">
	<input type="hidden" name="user_idx" value="${sessionScope.user_idx}">
	
	<input type="button" id="off_ck" name="work_off" value="퇴근" class="btn btn-outline-danger">
</form>
</div>
<table class="table table-dark table-striped" style="text-align: center; width: 95%; margin: auto;">
	<tr>
		<td>출근시간</td>
		<td>퇴근시간</td>
	</tr>

<c:forEach var="user_work" items="${user_work}">
	<tr>
		<td>${user_work.work_on}</td>
		<td>${user_work.work_off }</td>
	</tr>

</c:forEach>
</table>
	
	<div style="width: 100%; text-align: center; padding: 1rem;">
	<c:if test="${prev == true}">
		<a href="work_user?page=${startPage-1}&user_idx=${sessionScope.user_idx}">이전</a>
	</c:if>
	<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page eq pageck}">
				<span style="font-weight: bold;">${page}</span>
			</c:when>
			<c:otherwise>
				<a href="work_user?page=${page}&user_idx=${sessionScope.user_idx}">${page}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${next == true and endPage > 0}">
			<a href="work_user?page=${endPage+1}&user_idx=${sessionScope.user_idx}">다음</a>
	</c:if>
	</div>
	

			</main>
		</div>
	</div>
</div>


<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	$(function() {
            $('#in_ck').click(function() {
            
                submitinCheck();
            });
            
            $('#off_ck').click(function() {
            
                submitoffCheck();
            });
            
        });
        
        
        function submitinCheck() {
        	var user_idx = "${sessionScope.user_idx}";
        	
            $.ajax({
                type : "POST",
                url: "work_ck",
                data: {user_idx : user_idx},
                dataType: "json",
                success: function(data) {
                		if(data.result == "출근"){
                			$('#inform').submit();
                			alert("출근 처리 완료되었습니다.");
                		}else{
                			alert(data.result);
                			 
                		}
                        
                }
            }) ;
        } 
		
		function submitoffCheck() {
        	var user_idx = "${sessionScope.user_idx}";
        	
            $.ajax({
                type : "POST",
                url: "work_offck",
                data: {user_idx : user_idx},
                dataType: "json",
                success: function(data) {
                		if(data.result == "퇴근"){
                			$('#offform').submit();
                			alert("퇴근 처리 완료되었습니다.");
                		}else{
                			alert(data.result);
                			 
                		}
                        
                }
            }) ;
        } 
	
</script>
</body>
</html>