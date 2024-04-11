<%@ page contentType="text/html; charset=utf-8" %>

<html>
<head>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
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
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
<!-- 	<style> -->

<!--    	</style> -->
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 <script>
		var userId = "${user.userId}";
	
	</script>
	<script src="/javascript/custom.js" charset='utf-8'>
	</script>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script>
<script type="text/javascript">

function fncAddUser() {
	// Form 유효성 검증
	var id=$('input:text[name="userId"]').val();
	var pw=$('input:password[name="password"]').val();
	var pw_confirm=$('input:password[name="password2"]').val();
	var name=$('input:text[name="userName"]').val();
	
	if(id == null || id.length <1){
		alert("아이디는 반드시 입력하셔야 합니다.");
		return;
	}
	if(pw == null || pw.length <1){
		alert("패스워드는  반드시 입력하셔야 합니다.");
		return;
	}
	if(pw_confirm == null || pw_confirm.length <1){
		alert("패스워드 확인은  반드시 입력하셔야 합니다.");
		return;
	}
	if(name == null || name.length <1){
		alert("이름은  반드시 입력하셔야 합니다.");
		return;
	}
	
	if(pw != pw_confirm) {
		alert("비밀번호 확인이 일치하지 않습니다.");
		$('input:text[name="password2"]').focus();
		return;
	}
		
	if($('input:text[name="phone2"]').val() != "" && $('input:text[name="phone3"]').val() != "") { //여기 조건이 이상한디? 
// 		document.detailForm.phone.value = document.detailForm.phone1.value + "-" + document.detailForm.phone2.value + "-" + document.detailForm.phone3.value;
		var value = $('option:selected').val() + "-" +$('input:text[name="phone2"]').val() + "-"+$('input:text[name="phone3"]').val();
		alert("phone::" + value);
	} 
	
	$('input:hidden[name="phone"]').val(value);
	
// 	document.detailForm.action='/user/addUser';
// 	document.detailForm.submit();
	$("form").attr("method","POST").attr("action","/user/addUser").submit()
}

$(function(){
		$('td.ct_btn01:contains("가입")').on("click", function(){
			fncAddUser();
		});
});

$(function(){
		$('td.ct_btn01:contains("취소")').on("click", function(){
			$("form")[0].reset();
		});
});

$(function(){
	$('select.ct_input_g[name="phone1"]').on("change", function(){
		$('input:text[name="phone2"]').focus();
	});
});

function check_email(frm) {

	$('input[name="email"]').on("change", function(){
		var email= $('input[name="email"]').val();
    	if(email != "" && (email.indexOf('@') < 1 || email.indexOf('.') == -1)){ 
    		alert("이메일 형식이 아닙니다.");

    	}

	});
}

function checkSsn() {
	var ssn1, ssn2; 
	var nByear, nTyear; 
	var today; 

	ssn = document.detailForm.ssn.value;
	// 유효한 주민번호 형식인 경우만 나이 계산 진행, PortalJuminCheck 함수는 CommonScript.js 의 공통 주민번호 체크 함수임 
	if(!PortalJuminCheck(ssn)) {
		alert("잘못된 주민번호입니다.");
		return false;
	}
}

function PortalJuminCheck(fieldValue){
    var pattern = /^([0-9]{6})-?([0-9]{7})$/; 
	var num = fieldValue;
    if (!pattern.test(num)) return false; 
    num = RegExp.$1 + RegExp.$2;

	var sum = 0;
	var last = num.charCodeAt(12) - 0x30;
	var bases = "234567892345";
	for (var i=0; i<12; i++) {
		if (isNaN(num.substring(i,i+1))) return false;
		sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
	}
	var mod = sum % 11;
	return ((11 - mod) % 10 == last) ? true : false;
}

$(function () {
	$('td.ct_btn:contains("ID중복확인")').on( "click", function() {
		popWin = window.open("/user/checkDuplication.jsp","popWin", "left=300,top=200,width=300,height=200,marginwidth=0,marginheight=0,scrollbars=no,scrolling=no,menubar=no,resizable=no");
	});
});


//
//도로명 주소
//


	
	$(function() {
		$('#searchRoadAddress')
				.on(
						"click",
						function() {

							// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
							var pop = window
									.open("./roadAddress.jsp", "pop",
											"width=570,height=420, scrollbars=yes, resizable=yes");

							// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
							//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 

						});
	});

	function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail,
			roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,
			detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn,
			buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
// 		document.form.roadAddrPart1.value = roadAddrPart1;
// 		document.form.roadAddrPart2.value = roadAddrPart2;
// 		document.form.addrDetail.value = addrDetail;
		console.log("addrDetail:" + roadFullAddr);
		$('input:text[name="addr"]').val('');
		$('input:text[name="addr"]').val(roadFullAddr);
// 		document.form.zipNo.value = zipNo;
	};
	
	
	//
	//email 인증
	//
	
	$(function(){
	$('#emailVerify').on("click", function(){
		
		console.log('why not work?');
		console.log('user email: ' + $('input:text[name="email"]').val());
		
		$.ajax({
			url: "/mail/sendAuthMail",
			method: "POST",
			dataType: "json",
			headers:{
				"Accept": "application/json",
				"Content-Type":"application/json"
			},
			data: JSON.stringify({
				receiverMail : $('input:text[name="email"]').val()
			}),
			success: function(JSONData,status){
				console.log("data:" + JSONData.result);
				console.log("status:" + status);
				if(JSONData.result == 'ok'){
					//"pop","width=570,height=420, scrollbars=yes, resizable=yes"
					popWin = window.open("emailVerify.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
					
				}
			}
		});
		
		
	});
	});
	
	
	function userAuthCode(authCode){
		console.log('userAuthCode code:' + authCode);
		
		$.ajax({
			url: "/mail/authCodeCheck",
			method: "POST",
			dataType: "json",
			headers:{
				"Accept": "application/json",
				"Content-Type":"application/json"
			},
			data: JSON.stringify({
				receiverMail : $('input:text[name="email"]').val(),
				authCode : authCode
			}),
			success: function(JSONData,status){
				console.log("data:" + JSONData.result);
				console.log("status:" + status);
				if(JSONData.result == 'true'){
					$('input:hidden[name="emailAuth"]').val('true');
					console.log('change after auth:' + $('input:hidden[name="emailAuth"]').val());
				}
			}
		});
		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form>


	<!-- ToolBar Start /////////////////////////////////////-->
<%-- 	<jsp:include page="/layout/toolbar.jsp" /> --%>
   	<!-- ToolBar End /////////////////////////////////////-->
   	<jsp:include page="/common/topBar.jsp"/>
   	<!-- ToolBar End /////////////////////////////////////-->
   	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		
		<!-- 다단레이아웃  Start /////////////////////////////////////-->
		<div class="row">
	
			<!--  Menu 구성 Start /////////////////////////////////////-->     	
			<div class="col-md-3">
		        
		       	<!--  회원관리 목록에 제목 -->
				<jsp:include page="/common/bodyMenu.jsp"/>
				
			</div>
		

	<!--  아래의 내용은 http://getbootstrap.com/getting-started/  참조 -->	
		<div class="col-md-9">

	<!-- 참조 : http://getbootstrap.com/css/   : container part..... -->
			<div class="container-md">
			<!--  -->
			<!--  -->
			<!--  -->
			
       	<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원가입</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
						<input type="text" name="userId" class="ct_input_bg" style="width:100px; height:19px"  maxLength="20" >
					</td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="4" height="21">
									<img src="/images/ct_btng01.gif" width="4" height="21"/>
								</td>
								<td 	align="center" background="/images/ct_btng02.gif" class="ct_btn" 
										style="padding-top:3px;">
<!-- 									<a href="javascript:fncCheckDuplication();" id="btnCmfID">ID중복확인</a> -->
										ID중복확인
								</td>
								<td width="4" height="21">
									<img src="/images/ct_btng03.gif" width="4" height="21">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			비밀번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="password" name="password" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			비밀번호 확인 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="password" name="password2" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			이름<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="userName" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">주민번호</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="ssn" class="ct_input_g" 
							style="width:100px; height:19px" onChange="javascript:checkSsn();"  maxLength="13" >
			-제외, 13자리 입력
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="addr" class="ct_input_g" style="width:370px; height:19px"  maxLength="100"/> <i id="searchRoadAddress" class="glyphicon glyphicon-search"></i>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">휴대전화번호</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="phone1" class="ct_input_g" style="width:50px" >
				<option value="010" >010</option>
				<option value="011" >011</option>
				<option value="016" >016</option>
				<option value="018" >018</option>
				<option value="019" >019</option>
			</select>
			<input type="text" name="phone2" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
			- 
			<input type="text" name="phone3" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
			<input type="hidden" name="phone" class="ct_input_g"  >
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">이메일 </td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="26">
						<input 	type="text" name="email" class="ct_input_g" style="width:100px; height:19px">
						<input	type="hidden" name="emailAuth" value="false"/>
						<button id="emailVerify" type="button" class="btn btn-default">이메일 인증하기</button>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
<!-- 						<a href="javascript:fncAddUser();">가입</a> -->
							가입
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
<!-- 						<a href="javascript:resetData();">취소</a> -->
							취소
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
       		
       		<!--  -->
       		<!--  -->
       		<!--  -->
       			
  	 		</div>
  	 	</div> <!-- col md -->
  	</div>
</div>


</form>
</body>
</html>
