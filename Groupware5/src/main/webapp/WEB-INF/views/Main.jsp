<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="./resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="./resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link href="./resources/css/util.css" rel="stylesheet" />
	<link href="./resources/css/main.css" rel="stylesheet" />
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="./resources/images/img-01.png" alt="IMG">
				</div>

				<form class="login100-form validate-form" action="logindo" method="post"> 
					<span class="login100-form-title">
						Member Login
					</span>
					
					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
						<input class="input100" type="text" name="user_idx" placeholder="사원번호" id="useridx">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="user_pw" placeholder="비밀번호" id="userpw">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<input type="submit" class="login100-form-btn" id="login_chk" value="Login">
							
						
					</div>

					

					<div class="text-center p-t-136">
						
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!-- 
	
===============================================================================================	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
===============================================================================================
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
===============================================================================================
	<script src="vendor/select2/select2.min.js"></script>
===============================================================================================
	<script src="vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
===============================================================================================
	<script src="js/main.js"></script>


 -->
<!--  
 
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>      
        

 -->

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$(document).ready(function (){
		$('#login_chk').click(function(){
			test();
		
		});
	
	});
	
	function test(){
		var user_idx = $('#useridx').val();
		var user_pw = $('#userpw').val();
		
		$.ajax({
			type : "post",
			url : "main_loginchk",
			data :{user_idx:user_idx, user_pw:user_pw},
			dataType : "json",
			success : function(data){
				if(data.result == "로그인 성공"){
					$('form').submit();
				}else{
					alert(data.result);
					 $('#useridx' ).val('');
					 $('#userpw').val('');
					 $('#useridx' ).focus();
				}
			}
		
		});
	}

</script>
</body>
</html>

