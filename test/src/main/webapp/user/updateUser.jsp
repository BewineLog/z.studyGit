<%@ page contentType="text/html; charset=utf-8" %>

	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<title>회원정보수정</title>
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

function fncUpdateUser() {

	var name=$('input:text[name="userName"]').val();
	
	var phone = '';
	var phone1 = $('.ct_input_g[name="phone1"]').val();
	var phone2 = $('input:text[name="phone2"]').val();
	var phone3 = $('input:text[name="phone3"]').val();
	
	if(name == null || name.length <1){
		alert("이름은  반드시 입력하셔야 합니다.");
		return;
	}
		
	if(phone2 != "" && phone3 != "") {
		phone = phone1 + "-" + phone2 + "-" + phone3;
		$('input:hidden[name="phone"]').val(phone);
	}
	$('form').attr("method","POST").attr("action","/user/updateUser").submit();
	
}

$(function(){

// 	var email=document.detailForm.email.value;
	alert( $('input:text[name="userName"]').val() );
	$('input:text[name="email"]').on('change', function(){
    	if(email != "" && (email.indexOf('@') < 1 || email.indexOf('.') == -1)){
    		alert("이메일 형식이 아닙니다.");
   		}
	});
	
	$('select.ct_input_g[name="phone1"]').on('change', function(){
		$('#phone2').focus();
	});
	
	$(".ct_btn01:contains('수정')").on("click", function(){
		fncUpdateUser();
	});
	
	
	$('td:contains("취소")').on("click", function(){
		history.go(-1);
	});
	
});

// function resetData() {
// 	$('form')[0].reset();
// }


//
// 도로명 주소
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
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm">
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
			
       	
<input type="hidden" name="userId" value="${user.userId}">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원정보수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<input type="hidden" id="regDate" name="regDate" value="${user.regDate}"/>
	<input type="hidden" id="role" name="role" value="${user.role}"/>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${user.userId}</td>
					<td>	</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			이름 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="userName" value="${user.userName}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" > 
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="addr" value="${user.addr}" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100"> <i id="searchRoadAddress" class="glyphicon glyphicon-search"></i>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">휴대전화번호</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="phone1" class="ct_input_g" style="width:50px">
				<option value="010" >010</option>
				<option value="011" >011</option>
				<option value="016" >016</option>
				<option value="018" >018</option>
				<option value="019" >019</option>
			</select>
			<input type="text" name="phone2"
<%-- 						<%if (vo.getPhone() != null) {%>  --%>
<%-- 							value="<%=vo.getPhone().split("-")[1] %>" --%>
<%-- 						<%} %>  --%>
						
						value="${! empty user.phone ? (fn:split(user.phone,'-')[1]):''}"
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			- 
			<input type="text" name="phone3" 
<%-- 						<%if (vo.getPhone() != null) {%>  --%>
<%-- 							value="<%=vo.getPhone().split("-")[2] %>" --%>
<%-- 						<%} %>  --%>
						value="${! empty user.phone ? (fn:split(user.phone,'-')[2]):''}"
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
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
						<input 	type="text" name="email" value="${user.email}" class="ct_input_g" 
										style="width:100px; height:19px">
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
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
<!-- 						<a href="javascript:fncUpdateUser();">수정</a> -->
							수정
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
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
