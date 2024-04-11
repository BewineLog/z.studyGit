<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <link href="/css/custom.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
<title>이메일 인증</title>

<script type="text/javascript">

	$(function(){
		$('.btn').on("click",function(){
			console.log("user auth code:" + $('input:text').val());
			opener.userAuthCode($('input:text').val());
			window.close();
		});
	});

</script>
</head>
<body>

	<div class="row">
	<div class="col-lg-6">
    	<div class="input-group">
     	 <input type="text" class="form-control" placeholder="인증번호를 입력해주세요...">
      		<span class="input-group-btn">
        		<button class="btn btn-default" type="button">확인</button>
      		</span>
   	 	</div><!-- /input-group -->
 	 </div>
 	 </div>


</body>
</html>