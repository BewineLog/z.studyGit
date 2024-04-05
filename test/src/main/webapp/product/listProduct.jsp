<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>상품 목록조회</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <link href="/css/custom.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- <script src="../javascript/list.js"></script> -->
<!-- <script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script> -->
	 <script>
		var userId = "${user.userId}";
	
	</script>
	<script src="/javascript/custom.js" charset='utf-8'>
	</script>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script>

var page = 2; // infinite scroll

function fncGetList(page) {
// 	document.getElementById("page").value = page;
// 	document.detailForm.submit();
// action="/product/listProduct?menu=${menu}" method="post"
	$("#page").val(page);
	
	alert("form ::" + $('form').html());
	
	$('form').attr("method","POST").attr("action","/product/listProduct?menu=${menu}").submit();
}

function rankingAsc(page){
// 	if(document.getElementById("rankingAscValue").value ==="" || document.getElementById("rankingAscValue").value ==="null"){
// 		document.getElementById("rankingAscValue").value = "asc";
// 		document.getElementById("rankingDescValue").value = null;
// 	}else{
// 		document.getElementById("rankingAscValue").value = null;
// 	}
	 
	var val = $("#rankingAscValue").val();

	if(val === "" || val ==="null"){
		$("#rankingAscValue").val("asc");
		$("#rankingDescValue").val(null);
	}else{
		$("#rankingAscValue").val(null);
	}
	
	fncGetList(page);
}


function rankingDesc(page){
// 	if(document.getElementById("rankingDescValue").value ==="" || document.getElementById("rankingDescValue").value ==="desc"){
// 		document.getElementById("rankingDescValue").value = "desc";
// 		document.getElementById("rankingAscValue").value = null;
// 	}else{
// 		document.getElementById("rankingDescValue").value = null;
// 	}

	var val = $("#rankingDescValue").val();
	
	if(val === "" || val === "desc"){
		$("#rankingDescValue").val("desc");
		$("#rankingAscValue").val(null);
	}else{
		$("#rankingDescValue").val(null);
	}
	
	fncGetList(page);
}


function showInventory(page){
// 	if(document.getElementById("inventoryValue").value === "" || document.getElementById("inventoryValue").value === "show"){
// 		document.getElementById("inventoryValue").value = "notShow";
// 	}else{
// 		document.getElementById("inventoryValue").value = "show";
// 	}
	var val = $("#inventoryValue").val();
	
	if(val === "" || val === "show"){
		$("#inventoryValue").val("notShow");
	}else{
		$("#inventoryValue").val("show");
	}
	
	
	fncGetList(page);
}

function setRange(element){

// 	alert("setRange: "+ element.html());
	if(element.val() == "false" || element.val() == null){
		element.val(true);
	}else{
		element.val(false);
	}
	
// 	fncGetList(1);
}


function removeUser(page,id){
// 	document.getElementById("removeUserId").value = id;
	$("#removeUserId").val(id);
	fncGetList(page);
}

function openDetailPage(){
	alert('openDetailpage');
//	window.open("/detailSearch.jsp");
}

$(function(){
	
	$('button.btn-default').on("click",function(){
		alert($(this).html().trim());
		fncGetList($(this).html().trim());
	});
});

$(function() {
	$('input[name="searchKeyword"]').on("keyup", function(){
// 		alert("keydown event");
// 		alert($(this).val());
		
		$.ajax({
			url:"/product/json/listProduct?menu=${menu}&isAutoComplete=true",			
			method:"POST",
			dataType:"json",
			data: JSON.stringify({
				searchKeyword:$(this).val()
			}),
			headers:{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function(JSONData,status){
				$('input[name="searchKeyword"]').autocomplete({
					source: JSONData.autoCompleteList
				});
// 				console.log(typeof JSONData.autoCompleteList);//object
// 				console.log(JSONData.autoCompleteList);//array
// 				console.log(JSONData.autoCompleteList.length);
				
				for(let i = 0 ; i < JSONData.autoCompleteList.length; i++){
					console.log(i+":"+JSONData.autoCompleteList[i]);
				}
				
// 				alert("JSONData:" + JSONData.autoCompleteList);
// 				alert("JSONData:" + JSONData.autoCompleteList.data);
// 				alert("status:" + status);
				
// 				let list = JSONData.autoCompleteList.split(",");
// 				for(const i = 0; i < list.length; i++){
// 					console.log(list[i]);
// 				}
// 				if(JSONData.autoCompleteList != null){
// 					let htmlCode = '<div class="btn-group">\n';
// 					htmlCode += '<ul class="dropdown-menu">\n';
				
// 					for(let i = 0; i < JSONData.autoCompleteList.length ; i ++){
// 						htmlCode += "<li>" + JSONData.autoCompleteList[i] + "</li>\n";
// 					}
				
// 				htmlCode +="</ul>\n";
// 				htmlCode +="</div>\n";
				
// 				console.log(htmlCode);
// 				$(this).html(htmlCode);
// 				}
			}
		});
	});
});

$(function() {
	$('select.ct_input_g[name="searchCondition"]').on("change", function(){
		$('input.ct_input_g[name="searchKeyword"]').focus();
	});
	
	
	$('button.btn:contains("검색")').on("click", function(){
		alert("검색 click");
		fncGetList(1);
	});
	
	$('#fixedSearchRangeOne').on("click", function(){
		alert("fixedSearchRangeOne:" + $(this).val());
		setRange($(this));
	});
	
	$('#fixedSearchRangeTwo').on("click", function(){
		alert("fixedSearchRangeTwo:" + $(this).next().attr("id"));
		setRange($(this));
	});
	
	$('#fixedSearchRangeThree').on("click", function(){
		alert("fixedSearchRangeThree:" + $(this).html());
		setRange($(this));
	});
	
	$('#orderAsc').on("click", function(){
		rankingAsc(1);
	});
	
	$('#orderDesc').on("click", function(){
		rankingDesc(1);
	});
	
	$('#inventory').on("click", function(){
		showInventory(1);
	});
	
	$('.ct_list_pop td:nth-child(3)').on("click", function(){
		
// 		alert($('.ct_list_pop td:nth-child(11)').html());
// 		alert($($(this).children()[0]).val());
// 		alert($($(this).children()[1]).val());
		
		if($($(this).children()[0]).val().trim() == '판매중'){
			self.location = "/product/getProduct?prodNo="+$($(this).children()[1]).val().trim()+"&menu=${menu}";
		}
		
		
		
		
	});
	
	$('.ct_list_pop td:nth-child(11):contains("배송하기")').on("click", function(){
// 		alert($($(this).parent().children()[2]).html());
// 		alert("/purchase/updateTranCode?tranNo="+$($(this).parent().children()[2]).find('input:hidden[id="getTranNo"]').val() +"&tranCode=2&menu=manage");
		self.location = "/purchase/updateTranCode?tranNo="+$($(this).parent().children()[2]).find('input:hidden[id="getTranNo"]').val() +"&tranCode=2&menu=manage";
	});
	
	$('.ct_list_pop td:nth-child(3)').css('color','blue');
	$('.ct_list_pop td:nth-child(9)').css('color','#4aa8d8');
	$('.ct_list_pop td:nth-child(11)').css('color','red');
	$('table[id="list"] tr:nth-child(4n)').css('background-color','whitesmoke');
	
	$('.ct_list_pop td:nth-child(9):contains("펼치기")').on("click", function(){
		var prodNo = $($(this).parent().children()[2]).find('input:hidden[id="getProdNo"]').val();
// 		alert( $($(this).parent().children()[2]).html() );
// 		alert( $($(this).parent().children()[2]).val() );



// 		alert("prodNo:"+$($(this).parent().children()[2]).find('input:hidden[id="getProdNo"]').val());
		
		
// 		alert("text:" +$(this).text());
		
		if($(this).text().trim() == '닫기'){
			$("h5").remove();
			$("#detailInfoSpace" + prodNo+"").css("display","none");
	 		$('td[id="detailInfo'+ prodNo+'"]').text("펼치기");
		}else if($(this).text().trim() == '펼치기'){
			console.log("/product/json/getProduct/" + prodNo + "/" + "${menu}");
			
			$.ajax({	
				url:"/product/json/getProduct/" + prodNo + "/" + "${menu}",
				method:"GET",
				dataType:"json",
				headers:{
					"Accept":"application/json",
					"Content-Type" : "application/json"
				},
				success: function(JSONData, status){
					var displayValue = "<h5>"
								 + "상품번호:" + JSONData.prodNo + "<br/>"
								 + "상세정보:" + JSONData.prodDetail + "<br/>"
								 + "제조일자:" + JSONData.manuDate + "<br/>"
								 + "</h5>";
								 
					$('td:contains("닫기")').text("펼치기");
					$('td[id="detailInfo'+ prodNo+'"]').text("닫기");
					$("h5").remove();
					$("#detailInfoSpace" + prodNo +"").css("display","");
					$('#' + prodNo+'').html(displayValue);				
				
					alert(displayValue);
				}
			});
		}
	});
	
	$(function() {
		$(window).scroll(function(){
			console.log('scroll');
			console.log('value: ' + $(window).scrollTop() + ' ' + $(document).height() + ' ' + $(window).height());
			if($(window).scrollTop() == $(document).height() - $(window).height()){
				console.log('scroll event 발생');
			}
			
		});
		
	});//infinite scroll //나중에 하자.. 귀찮다...
	
// 	$('td:contains("닫기")').on("click", function(){
// 		var prodNo = $($(this).parent().children()[2]).find('input:hidden[id="getProdNo"]').val();
// 		$("h3").remove();
// 		$('td[id="detailInfo'+ prodNo+'"]').text("펼치기");
// 	});
	
	
});

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

<!-- 		<form name="detailForm"> -->

<!-- 			<table width="100%" height="37" border="0" cellpadding="0" -->
<!-- 				cellspacing="0"> -->
<!-- 				<tr> -->
<!-- 					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif" -->
<!-- 						width="15" height="37" /></td> -->
<!-- 					<td background="/images/ct_ttl_img02.gif" width="100%" -->
<!-- 						style="padding-left: 10px;"> -->
<!-- 						<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 							<tr> -->
<!-- 								<td width="93%" class="ct_ttl01">상품 목록조회</td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</td> -->
<!-- 					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" -->
<!-- 						width="12" height="37" /></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->

	 <jsp:include page="/common/topBar.jsp"/>
		
	<div class="container">
		
		<!-- 다단레이아웃  Start /////////////////////////////////////-->
		<div class="row">
	
			<!--  Menu 구성 Start /////////////////////////////////////-->     	
			<div class="col-md-3">
		        
		       	<!--  회원관리 목록에 제목 -->
				<jsp:include page="/common/bodyMenu.jsp"/>
				
			</div>
		

	<!--  아래의 내용은 http://getbootstrap.com/getting-started/  참조 -->	
	<form class="form-inline" name="detailForm">
		<div class="col-md-9">
			<div class="container">

				<div class="page-header text-info">
	       			<h3>상품목록조회</h3>
	   			 </div>
				<div class="row">
					<div class="col-md-4 text-left">
						<p>전체  ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</p>
					</div>
					<div class="col-md-5 text-right">
						
							<div class="form-group">
								<select class="form-control" name="searchCondition">
									<c:if test="${! empty menu && menu == 'manage'}">
										<option value="0" ${! empty search.searchCondition && search.searchCondition.trim() == '0' ? 'selected' : '' }>상품번호</option>
									</c:if>
									<option value="1"${! empty search.searchCondition && search.searchCondition.trim() == '1' ? 'selected' : '' }>상품명</option>
							<%-- 					<option value="2" ${! empty search.searchCondition && search.searchCondition.trim() == '2' ? 'selected' : '' } >상품가격</option> --%>
								</select> 
							</div>
							<input type="hidden" id="optionValue" name="optionValue"
								value="${optionValue}" />
						
					
					<!-- 
				여기에 상세보기 버튼을 하나 생성
				상세보기 버튼을 누르면, 새로운 window open by jsp ??
				
				기본검색
				
				가격대: ~ 1만원 1~2만원 2~3만원 직접 입력  ㅁ-ㅁ 검색 버튼   
				
				값을 입력하는 칸 안에 max 가격은 천만원으로 회색 글씨??
				
				그럼 적어도 query에 between price A AND B option
				
				search에 price range variable 필요
				
				
				 -->
<%-- 					<c:if test="${empty search.searchKeyword }"> --%>
						<div class="form-group">
							<label class="sr-only" for="searchKeyword">검색어</label> 
							<input type="text" class="form-control" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword.trim() : ''}"/> 
							<!--  -->
						</div>
						<button type="button" class="btn" >
							검색
<!-- 							<input type="hidden" id="buttonNumber" val="1"> -->
						</button>
						<input type="hidden" id="currentPage" name="currentPage" value="" />
						
<%-- 					</c:if> --%>
				</div>
			</div>
			
			

			<div class="row">
	
					<!--  Menu 구성 Start /////////////////////////////////////-->     	
					<div class="col-md-4"> <!-- 영역 잡기 --></div>
					<div class="col-md-5 text-right">
<!-- 						<form class="form-inline" name="detailForm"> -->
<%-- 			<c:if test="${! empty search.searchKeyword}"> --%>
				
							<input type="checkbox" id="fixedSearchRangeOne" name="fixedSearchRangeOne" value="${! empty search.fixedSearchRangeOne ? search.fixedSearchRangeOne : ''}" ${search.fixedSearchRangeOne ? 'checked' : ''} />1만원~2만원
							<input type="checkbox" id="fixedSearchRangeTwo" name="fixedSearchRangeTwo" value="${! empty search.fixedSearchRangeTwo ? search.fixedSearchRangeTwo : ''}" ${search.fixedSearchRangeTwo ? 'checked' : ''} />2만원~3만원
							<input type="checkbox" id="fixedSearchRangeThree" name="fixedSearchRangeThree" value="${! empty search.fixedSearchRangeThree ? search.fixedSearchRangeThree : ''}" ${search.fixedSearchRangeThree ? 'checked' : ''} />3만원~4만원




							<input type="text" class="form-control" id="searchRangeLow" name="searchRangeLow" value="${! empty search.searchRangeLow ? search.searchRangeLow : ''}" />
							<input type="text" class="form-control" id="searchRangeHigh" name="searchRangeHigh" value="${! empty search.searchRangeHigh ? search.searchRangeHigh : ''}" />

					<!-- 검색 -->

			
<%-- 			</c:if> --%>


					<!-- 
						얘네 위치를 네이버처럼 물품 위로 올리자
					 -->
<%-- 					<c:if test="${! empty menu && menu == 'search' }"> --%>
						<input type="checkbox" id="orderAsc" name="orderAsc" ${! empty search.rankingAscValue && search.rankingAscValue == "asc" ? 'checked' : '' }>가격 낮은 순
						<input type="hidden" id="rankingAscValue" name="rankingAscValue" value="${! empty search.rankingAscValue ? search.rankingAscValue : '' }" />
						<input type="checkbox" id="orderDesc" name="orderDesc" ${! empty search.rankingDescValue && search.rankingDescValue =="desc" ? 'checked' : '' }>가격 높은 순
						<input type="hidden" id="rankingDescValue" name="rankingDescValue" value="${! empty search.rankingDescValue ? search.rankingDescValue : '' }" />
						<input type="checkbox" id="inventory" name="inventory" ${! empty search.inventoryValue && search.inventoryValue =="notShow" ? 'checked' : '' }>재고없음 보지않기
						<input type="hidden" id="inventoryValue" name="inventoryValue" value="${! empty search.inventoryValue ? search.inventoryValue : '' }" />

<%-- 					</c:if> --%>
<!-- 					</form> -->
					</div>
				</div>
			</div><!-- Container -->



			<table id="list" width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${count} 건수, 현재 ${pageInfo.currentPage}페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">상세정보</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>



				<c:set var="idx"
					value="${pageInfo.pageSize *(pageInfo.currentPage -1 )}">
				</c:set>
				<c:forEach var="i" items="${list}">
					<tr class="ct_list_pop">
<!-- 						<div id="getProTranCode" style="display:none">{i.proTranCode}</div> -->
<!-- 						<div id="getProdNo" style="display:none">{i.prodNo}</div> -->
<!-- 						div hidden은 jQuery로 못 잡음 -->
						
						<td align="center">${idx+1}</td>
						<td></td>

						<td align="left">
							${i.prodName}
							<input type="hidden" id="getProTranCode" value="${i.proTranCode}">
							<input type="hidden" id="getProdNo" value="${i.prodNo}">
							<input type="hidden" id="getTranNo" value="${i.tranNo}">
						
						</td>

						<td></td>

						<td align="left">${i.price}</td>
						<td></td>
						<td align="left">${i.regDate}</td>
						<td></td>
						<td id="detailInfo${i.prodNo}" align="left">펼치기</td>
						<td></td>
						<td align="left">${menu == 'manage' && i.proTranCode.trim() !='판매중' ? i.proTranCode : i.proTranCode.trim() == '판매중' ? '판매중' : '재고없음'}

							<c:if test="${i.proTranCode.trim() == '구매완료' && menu.trim() == 'manage'}">
<%-- 								<a href="/purchase/updateTranCode?tranNo=${i.tranNo}&tranCode=2&menu=manage">배송하기</a> --%>
								배송하기
								<!-- 여기에 td 걸고 check -->
							</c:if>

						</td>
					</tr>
					<tr id="detailInfoSpace${i.prodNo}" style="display:none;">
						<td id="${i.prodNo}" colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>

					<c:set var="idx" value="${idx+1}">
					</c:set>
				</c:forEach>

			</table>
			

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="page" name="page"
						value="" /> <c:import url="../common/pageNavigator.jsp" /></td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->

<!-- 		</form> -->
		</div> <!-- col-md-9 -->
		</form>
	</div> <!-- ROW -->
	</div>
	</div>
</body>
</html>