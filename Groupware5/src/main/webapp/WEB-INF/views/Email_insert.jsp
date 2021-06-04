<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="email_submit" method="post">



<div class="test">
<table class="table table-dark table-striped" >
	<tr>
		<td>보내는사람&nbsp;&nbsp;&nbsp;
			${sessionScope.user_name }
		</td>
	</tr>
	<tr>
		<td>보내는 사원 번호&nbsp;&nbsp;&nbsp;
			${sessionScope.user_idx }
			<input type="hidden" value="${sessionScope.user_idx }" name="user_idx"> 
		
		</td>
	
		
	</tr>
	<tr>
		<td>받는사람(사원번호)
		<div class="input-group input-group-sm mb-3">
	  		
	  		<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="email_re" style="background-color: transparent;color: white;">
		</div>
		
		</td>
		
	</tr>
	<tr>	
		
		<td>제목
			<div class="input-group mb-3">
			  	
			  	<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="email_title" style="background-color: transparent;color: white; ">
			</div>
			
		</td>
	</tr>
	<tr>
		
		<td>
			<div class="mb-3">
			  <label for="formFileSm" class="form-label">파일 첨부</label>
			  <input class="form-control form-control-sm" id="formFileSm" type="file" name="email_imgname" style="background-color: transparent;color: white;">
			</div>
			
			
		</td>
	</tr>
	<tr>

		<td>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">글쓰기</label>
			  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="email_content" style="background-color: transparent; min-height: 300px; color: white;" ></textarea>
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="보내기" class="btn btn-outline-white" style="background-color: transparent;color: white;">
			<input type="hidden" value="${sessionScope.user_idx }" name="user_idx">
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