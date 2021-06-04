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
.test{
	margin: 0px auto;
	width: 90%;
	}
.form-control{

	

}
	
</style>
</head>
<body>


<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" style="height: 100%">
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
<h2>자료실 게시판</h2>
<form action="dataroom_insert" method="post" enctype="multipart/form-data" id="forms">
<table class="table table-dark table-striped" >
	<tr>	
		
		<td>
			<div class="input-group mb-3">
			<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="제목을 입력하세요" name="data_title" style="background-color: transparent;color: white; ">
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<div class="mb-3">
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="내용을 입력하세요" name="data_content" style="background-color: transparent; min-height: 300px; color: white;" ></textarea>
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<div class="mb-3">
			  <input class="form-control form-control-sm" id="formFileSm" type="file" name="data_up" style="background-color: transparent;color: white;">
			<div class = "uploadResult"></div>			
			</div></td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="user_idx" value="${sessionScope.user_idx}">
			<input type="submit" value="작성" id="submit" class="btn btn-outline-white" style="background-color: transparent;color: white;">
			<input type="button" value="취소" onclick = "location.href = 'Gw_Datapage'" class="btn btn-outline-white" style="background-color: transparent;color: white;">
		</td>
	</tr>
</table>
</form>
</main>
</div>
</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	
	$(document).ready(function() {
    $('#forms').submit(function() {
        if($('input[name=data_title]').val() == null || $('input[name=data_title]').val() == ""){
        	alert("제목이 입력되지 않았습니다 제목을 입력해 주세요");
        	$('input[name=data_title]').focus();
            return false;
        }else if($('textarea[name=data_content]').val() == null || $('textarea[name=data_content]').val() == ""){
        	alert("내용이 입력되지 않았습니다. 내용을 입력해 주세요.");
            $('textarea[name=data_content]').focus();
            return false;
        }if ($('input[name=data_up]').val() == null || $('input[name=data_up]').val() == "") {
            alert("등록된 파일이 없습니다. 파일을 첨부해 주세요");
            return false;
        }
    }); // end submit()
	}); // end ready()
	

	
</script>
</body>
</html>