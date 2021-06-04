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
<h2>게시글 수정</h2>
<form action="Gw_Dataupdate" method="post" enctype="multipart/form-data" id="forms">
<table class="table table-dark table-striped" >
	<tr>	
		
		<td>
			<div class="input-group mb-3">
			<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${dataroom_update.data_title}" name="data_title" style="background-color: transparent;color: white; ">
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<div class="mb-3">
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="data_content" style="background-color: transparent; min-height: 300px; color: white;" >${dataroom_update.data_content}</textarea>
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<div class="mb-3">
			  <input class="form-control form-control-sm" id="formFileSm" type="file" name="data_up" style="background-color: transparent;color: white;">
				<input type="text" readonly value="${dataroom_update.data_up}" name="existing_data" id="existing_data" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" style="background-color: transparent;color: white; "> 	
			</div>
			</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="data_idx" value="${dataroom_update.data_idx}">
			<input type="submit" value="작성" class="btn btn-outline-white" style="background-color: transparent;color: white;">
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
			
			if($('input[name=data_up]').val() == null || $('input[name=data_up]').val() == ""){
	    		var ck = confirm("변경된 파일이 없습니다 기존 파일 그대로 등록하시겠습니까?");
	    		if(!ck){
					return false;
				}
				
	        }else if ($('input[name=data_up]').val() != null || $('input[name=data_up]').val() != "" || $('#existing_data').val() != null ||$('#existing_data').val() != "") {
	            var ck2 = confirm("변경된 파일로 등록하시겠습니까?");
	            if(!ck2){
					return false;
				}
	        }
	    }); // end submit()
	}); // end ready()
	

	
	
	
</script>
</body>
</html>