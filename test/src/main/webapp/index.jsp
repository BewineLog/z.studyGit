<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="UTF-8"%>


<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ///////////////////////////// 로그인시 Forward  /////////////////////////////////////// -->
 <c:if test="${ ! empty user }">
 	<jsp:forward page="main.jsp"/>
 </c:if>
 <!-- //////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="UTF-8">
	
	<!-- KaKao Login -->
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.1/kakao.min.js" integrity="sha384-kDljxUXHaJ9xAb2AzRd59KxjrFjzHa5TAoFQ6GbYTCAG0bjM55XohjjDT7tDDC01" crossorigin="anonymous"></script>
	<script>
  		Kakao.init('34a1e558e66560b2828143fa8eac7f54'); // 사용하려는 앱의 JavaScript 키 입력
	</script>
	
	
	<!-- 참조 : http://getbootstrap.com/css/   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<!-- 	<link type="text/css" rel="stylesheet" href="../css/bootstrap.css" > -->
<!-- 	<link rel="stylesheet" href="../fonts/glyphicons-halflings-regular.woff2" > -->
<!-- 	<link rel="stylesheet" href="../css/bootstrap.css" > -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style type="text/css">
/* 		.navbar .navbar-default{ */
/* 			background-color:blue !important; */
/* 		} */

/* 		.navbar-default{ */
/* 			background-color:blue; */
/* 		} */

		.navbar{
        	background-color:#3174AE;
        }
        
        .navbar-brand{
        	color:white;
        }
        
        .nav{
        	color:white;
        }
/*         .btn:hover{ */
/*         	color:pink; */
/*         } */
        
       
	</style>
   	
   	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		
		//============= 회원원가입 화면이동 =============
		$( function() {
			//==> 추가된부분 : "addUser"  Event 연결
			$("a[href='#' ]:contains('회원가입')").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
		//============= 로그인 화면이동 =============
			
		$( function() {
			//==> 추가된부분 : "addUser"  Event 연결
			$("a[href='#' ]:contains('로 그 인')").on("click" , function() {
// 				self.location = "/user/login"

// 				alert($($('div[class="jumbotron"]')).html()); //ok
// 				alert($($('div[class="jumbotron"]').children()[0]).html());
// 				alert($($('div[class="jumbotron"]').children()[1]).html());
// 				alert($($('div[class="jumbotron"]').children()[2]).html());
// 				alert($($('div[class="jumbotron"]').children()[3]).html());
// 				alert($($('div[class="jumbotron"]').children()[4]).html());
// 				document.querySelector("#inputLogin")
// 				document.querySelector("#changeToLogin > div > table:nth-child(2) > ")
// 				document.querySelector("#changeToLogin > div > a")
				
// 				alert($($('#changeToLogin > div > a')[0]).attr("id"));//ok
// 				alert($('#inputLogin').text());//ok
// 				$('a[id="inputLogin"]:contains("로 그 인")') // 안되는거 위에가 되는거 

// 				$('div[class="jumbotron"]').remove('h1');
// 				$('div[class="jumbotron"]').remove('p');
				$('p').remove();
				
				if($($('#changeToLogin > div > a')[0]).attr("id") == 'inputLogin'){
					var id=$('#userId').val();
					var pw=$('#password').val();
					
// 					alert("value:" + id + " " + pw);
				
				
					if(id == null || id.length <1) {
						alert('ID 를 입력하지 않으셨습니다.');
						$('#userId').focus();
					}
				
					if(pw == null || pw.length <1) {
						alert('패스워드를 입력하지 않으셨습니다.');
						$('#password').focus();
					}
					
					$.ajax({
						url: "/user/json/login",
						method: "POST",
						dataType: "json",
						headers:{
							"Accept": "application/json",
							"Content-Type":"application/json"
						},
						data: JSON.stringify({
							userId:id,
							password:pw
						}),
						success: function(JSONData,status){
// 							alert("JSONData:" + JSONData);
// 							alert("status:" + status);
							
							if(JSONData != null){
								$(window.parent.document.location).attr("href","/index.jsp");
								
//		 						window.parent.document.location.reload();
								
//		 						$(window.parent.frames["topFrame"].document.location).attr("href","/layout/top.jsp");
//		 						$(window.parent.frames["leftFrame"].document.location).attr("href","/layout/left.jsp");
//		 						$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId="+JSONData.userId);
								
							}
						}
						
					});
				}else{
				
				
				
				$($('div.text-center')).prepend(
						
						'<table width="100%" height="150" border="0" cellpadding="0" cellspacing="0">'+
// 			              '<tr>'+ 
// 			                '<td width="30" height="100">'+'&nbsp;'+'</td>'+
// 			                '<td width="100" height="100">'+'&nbsp;'+'</td>'+
// 			                '<td height="100">'+'&nbsp;'+'</td>'+
// 			                '<td width="20" height="100">'+'&nbsp;'+'</td>'+
// 			              '</tr>'+
// 			              '<tr>'+ 
// 			                '<td width="30" height="0">'+'&nbsp;'+'</td>'+
// 			                '<td width="100" height="0">'+
// // 			                	'<img src="/images/text_login.gif" width="91" height="32">'+
// 			                '</td>'+
// 			                '<td height="50">'+'&nbsp;'+'</td>'+
// 			                '<td width="20" height="0">'+'&nbsp;'+'</td>'+
// 			              '</tr>'+
			              '<tr>'+ 
			                '<td width="200" height="0" colspan="4">'+
			                '</td>'+
			              '</tr>'+              
			              '<tr>'+ 
			                '<td width="30" height="30">'+'&nbsp;'+'</td>'+
			                '<td width="100" height="30">'+
			                	'<img src="/images/text_id.gif" width="100" height="30">'+
			                '</td>'+
			                '<td height="30">'+
									'<div class="form-group">'+
										'<div class="col-sm-12">'+
			   								'<input type="text" name="userId" class="form-control" id="userId" placeholder="ID">'+
			   							'</div>'+
			  						'</div>'+
			          		'</td>'+
			                '<td width="20" height="30">'+'&nbsp;'+'</td>'+
			              '</tr>'+
			              '<tr>'+ 
			                '<td width="30" height="30">'+'&nbsp;'+'</td>'+
			                '<td width="100" height="30">'+
			                	'<img src="/images/text_pas.gif" width="100" height="30">'+
			                '</td>'+
			                '<td height="30">'+                    
									'<div class="form-group">'+
			    						'<div class="col-sm-12">'+
			      							'<input type="password" name="password" class="form-control" id="password" placeholder="Password">'+
			    						'</div>'+
			  						'</div>'+
			                '</td>'+
			                '<td width="20" height="30">'+'&nbsp;'+'</td>'+
			              '</tr>'+
			              '<tr height="20">'+'</tr>'+
// 			              '<tr>'+ 
// 			                '<td width="30" height="20">'+'&nbsp;'+'</td>'+
// 			                '<td width="100" height="20">'+'&nbsp;'+'</td>'+
// 			                '<td height="30" align="center">'+
// 			                '<div class="text-center">'+
// 				  			'<a class="btn btn-info btn-lg" id="inputLogin" href="#" role="button">'+'로 그 인'+'</a>'+
// 				  			'<a class="btn btn-info btn-lg" href="#" role="button">'+'회원가입'+'</a>'+
// 				  		'</div>'+
// 			                  '</td>'+
// 			                  '<td width="20" height="20">'+'&nbsp;'+'</td>'+
// 			                '</tr>'+
			            '</table>'
						
				
				);
				
				$('a[href="#"]:contains("로 그 인")').attr("id", "inputLogin");
				
// 				$("#changeToLogin").load(window.location.href + " #changeToLogin");
			}
			
		});
			
			
	});
		
		
	$(function () {	
		$('a.navbar-brand:contains("Model2")').on("click", function(){
			alert("going home");
			$(window.parent.document.location).attr("href","/index.jsp");
		});
	});
	
	$(function(){
		$('li a[id="topButton"]').css('color','white');
	});
	
	//
	//KaKao Login js
	//
	
	function loginWithKakao() {
    	Kakao.Auth.authorize({
      		redirectUri: 'http://192.168.0.16:8080/user/kakaoLogin',
    	});
  	}

  // 아래는 데모를 위한 UI 코드입니다.
  	displayToken()
  	function displayToken() {
    	var token = getCookie('authorize-access-token');

    	if(token) {
     		Kakao.Auth.setAccessToken(token);	
      		Kakao.Auth.getStatusInfo().then(function(res) {
      		
          	if (res.status === 'connected') {
            
             	alert('login success, token: ' + Kakao.Auth.getAccessToken());
          	}
        })
        .catch(function(err) {
         	Kakao.Auth.setAccessToken(null);
        });
    }
  }

  	function getCookie(name) {
    	var parts = document.cookie.split(name + '=');
    	if (parts.length === 2) { return parts[1].split(';')[0]; }
  	}
		
	</script>	
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<div class="navbar">
		
        <div class="container">
        
        	<a class="navbar-brand" href="#">Model2 MVC Shop</a>
			
			<!-- toolBar Button Start //////////////////////// -->
			<div class="navbar-header">
			    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			    </button>
			</div>
			<!-- toolBar Button End //////////////////////// -->
			
			<div class="collapse navbar-collapse"  id="target">
	             <ul class="nav navbar-nav navbar-right">
	                 <li><a id="topButton" href="#">로 그 인</a></li>
	                 <li><a id="topButton" href="#">회원가입</a></li>
	           	</ul>
	       </div>
   		
   		</div>
   	</div>
   	<!-- ToolBar End /////////////////////////////////////-->
   	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		
		<!-- 다단레이아웃  Start /////////////////////////////////////-->
		<div class="row">
	
			<!--  Menu 구성 Start /////////////////////////////////////-->     	
			<div class="col-md-3">
		        
		       	<!--  회원관리 목록에 제목 -->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-heart"></i> 회원관리
         			</div>
         			<!--  회원관리 아이템 -->
					<ul class="list-group">
						 <li class="list-group-item">
						 	<a href="#">개인정보조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
						 </li>
<!-- 						 <li class="list-group-item"> -->
<!-- 						 	<a href="#">회원정보조회</a> -->
<!-- 						 </li> -->
					</ul>
		        </div>
               
               
<!-- 				<div class="panel panel-primary"> -->
<!-- 					<div class="panel-heading"> -->
<!-- 							<i class="glyphicon glyphicon-briefcase"></i> 판매상품관리 -->
<!--          			</div> -->
<!-- 					<ul class="list-group"> -->
<!-- 						 <li class="list-group-item"> -->
<!-- 						 	<a href="#">판매상품등록</a> -->
<!-- 						 </li> -->
<!-- 						 <li class="list-group-item"> -->
<!-- 						 	<a href="#">판매상품관리</a> -->
<!-- 						 </li> -->
<!-- 					</ul> -->
<!-- 		        </div> -->
               
               
				<div class="panel panel-primary">
					<div class="panel-heading">
							<i class="glyphicon glyphicon-shopping-cart"></i> 상품구매
	    			</div>
					<ul class="list-group">
						 <li class="list-group-item"><a href="#">상품검색</a></li>
						  <li class="list-group-item">
						  	<a href="#">구매이력조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
						  </li>
						 <li class="list-group-item">
						 	<a href="#">최근본상품</a>
						 </li>
					</ul>
				</div>
				
			</div>
			<!--  Menu 구성 end /////////////////////////////////////-->   

	 	 	<!--  Main start /////////////////////////////////////-->   		
	 	 	<div class="col-md-9">
				<div id="changeToLogin" class="jumbotron">
    				<a href="#" class="thumbnail">
      				<img src="images/ncampLogo.png" alt="noImage">
    				</a>
			  		<p>로그인 후 사용가능...</p>
			  		<p>로그인 전 검색만 가능합니다.</p>
			  		<p>회원가입 하세요.</p>
			  		
			  		<div class="text-center">
			  			<a class="btn btn-info btn-lg" href="#" role="button">로 그 인</a>
			  			<a class="btn btn-info btn-lg" href="#" role="button">회원가입</a>
			  		</div>
			  	
			  		<div class="text-center">
			  			<a id="kakao-login-btn" href="javascript:loginWithKakao()">
  						<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"  alt="카카오 로그인 버튼" />
						</a>
			  		</div>
			  	</div>
	        </div>
	   	 	<!--  Main end /////////////////////////////////////-->   		
	 	 	
		</div>
		<!-- 다단레이아웃  end /////////////////////////////////////-->
		
	</div>
	<!--  화면구성 div end /////////////////////////////////////-->

</body>

</html>