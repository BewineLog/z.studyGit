<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>로그인</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
	<!-- 참조 : http://getbootstrap.com/css/   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script>
<script type="text/javascript">
// 	function fncLogin() {
// 		var id=$('input:text[name="userId"]').val();
// 		var pw=$('input:password[name="password"]').val();
// 		if(id == null || id.length <1) {
// 			alert('ID 를 입력하지 않으셨습니다.');
// 			$('#userId').focus();
// 		}
		
// 		if(pw == null || pw.length <1) {
// 			alert('패스워드를 입력하지 않으셨습니다.');
// 			$('#password').focus();
// 		}
// 	    $('form').attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
// 	}
	
	$(function(){
		$('button[id="loginButton"]').on("click", function(){
// 			fncLogin();

			var id=$('input:text[name="userId"]').val();
			var pw=$('input:password[name="password"]').val();
		
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
					alert("JSONData:" + JSONData);
					alert("status:" + status);
					
					if(JSONData != null){
						$(window.parent.document.location).attr("href","/index.jsp");
						
// 						window.parent.document.location.reload();
						
// 						$(window.parent.frames["topFrame"].document.location).attr("href","/layout/top.jsp");
// 						$(window.parent.frames["leftFrame"].document.location).attr("href","/layout/left.jsp");
// 						$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId="+JSONData.userId);
						
					}
				}
				
			});
		});
		
		$('button[id="addUserButton"]').on("click", function(){
			 $('form').attr("method","GET").attr("action","/user/addUser").attr("target","_parent").submit();
		});
		
	});
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000" >

<form name="loginForm" class="form-inline">

<div align="center">

<TABLE WITH="100%" HEIGHT="100%" BORDER="0" CELLPADDING="0" CELLSPACING="0">
<TR>
<TD ALIGN="CENTER" VALIGN="MIDDLE">

<table width="650" height="390" border="5" cellpadding="0" cellspacing="0" bordercolor="#D6CDB7">
  <tr> 
    <td width="10" height="5" align="left" valign="top" bordercolor="#D6CDB7">
    	<table width="650" height="390" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="305">
            <img src="/images/logo-spring.png" width="305" height="390">
          </td>
          <td width="345" align="left" valign="top" background="/images/login02.gif">
          	<table width="100%" height="220" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="30" height="100">&nbsp;</td>
                <td width="100" height="100">&nbsp;</td>
                <td height="100">&nbsp;</td>
                <td width="20" height="100">&nbsp;</td>
              </tr>
              <tr> 
                <td width="30" height="50">&nbsp;</td>
<!--                 <td width="100" height="50"> -->
<!--                 	<img src="/images/text_login.gif" width="91" height="32"> -->
<!--                 </td> -->
                <td height="50">&nbsp;</td>
                <td width="20" height="50">&nbsp;</td>
              </tr>
              <tr> 
                <td width="200" height="50" colspan="4">
                </td>
              </tr>              
              <tr> 
                <td width="30" height="30">&nbsp;</td>
                <td width="100" height="30">
                	<img src="/images/text_id.gif" width="100" height="30">
                </td>
                <td height="30">
<!--                   <input 	type="text" name="userId"  class="ct_input_g"  -->
<!--                   				style="width:180px; height:19px"  maxLength='50'/>           -->
						<div class="form-group">
<!--     						<label class="sr-only" for="exampleInputEmail3">Email address</label> -->
							<div class="col-sm-12">
   								<input type="text" name="userId" class="form-control" id="userId" placeholder="ID">
   							</div>
  						</div>
          		</td>
                <td width="20" height="30">&nbsp;</td>
              </tr>
              <tr> 
                <td width="30" height="30">&nbsp;</td>
                <td width="100" height="30">
                	<img src="/images/text_pas.gif" width="100" height="30">
                </td>
                <td height="30">                    
<!--                     <input 	type="password" name="password" class="ct_input_g"  -->
<!--                     				style="width:180px; height:19px"  maxLength="50" > -->

						<div class="form-group">
<!--     						<label for="inputPassword3" class="col-sm-2 control-label">Password</label> -->
    						<div class="col-sm-12">
      							<input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password">
    						</div>
  						</div>
                </td>
                <td width="20" height="30">&nbsp;</td>
              </tr>
              <tr height="20"></tr>
              <tr> 
                <td width="30" height="20">&nbsp;</td>
                <td width="100" height="20">&nbsp;</td>
                <td height="30" align="center">
      				<table width="136" height="30" border="0" cellpadding="0" cellspacing="0">
                          <tr> 
                            <td width="70">
<!--                             	<a href="javascript:fncLogin();"> -->
<!--                             		<img src="/images/btn_login.gif" width="56" height="20" border="0">s
<!--                             	</a> -->

								<p> <button id="loginButton" type="button" class="btn btn-primary btn-sm">로 그 인</button></p>
                            </td>
                            <td width="10">&nbsp;</td>
                            <td width="56">
<!--                             	<a href="addUserView.jsp;"> -->
<!--                             		<img src="/images/btn_add.gif" width="70" height="20" border="0"> -->
<!--                             	</a> -->
								<p><button id="addUserButton" type="button" class="btn btn-default btn-sm">회원가입</button></p>
                            </td>
                          </tr>
                    </table>
                  </td>
                  <td width="20" height="20">&nbsp;</td>
                </tr>
            </table>
         </td>
       </tr>                            
      </table>
      </td>
  </tr>
</table>
</TD>
</TR>
</TABLE>
</div>

</form>

</body>
</html>

