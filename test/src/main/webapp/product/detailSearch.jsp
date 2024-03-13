<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="../javascript/list.js">
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${param.menu}&searchCondition='2'" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상세검색
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0"style="margin-top: 10px;">
		<tr>
			<td>
				<a><input type="checkbox" name="fixedSearchRangeOne" ${! empty searchVO.fixedSearchRangeLow ? 'checked' : ''} />1만원~2만원</a>
			</td>
			<td>
				<a><input type="checkbox" name="fixedSearchRangeTwo" ${! empty searchVO.fixedSearchRangeLow ? 'checked' : ''} />2만원~3만원</a>
			</td>
			<td>
				<a><input type="checkbox" name="fixedSearchRangeThree" ${! empty searchVO.fixedSearchRangeLow ? 'checked' : ''} />3만원~4만원</a>
			</td>
		<tr/>
		
		<tr>

			<!-- 
				위 아래 중복 안되도록 처리 필요
				
				
			 -->
		<tr>
		<td align="right">
			<input type="text" name="searchRangeLow" value="${! empty searchVO.searchRangeLow ? searchVO.searchRangeLow.trim() : ''}" class="ct_input_g" style="width: 200px; height: 19px" />
		</td>
		<td align="right">
			<input type="text" name="searchRangeHigh" value="${! empty searchVO.searchRangeHigh ? searchVO.searchRangeHigh.trim() : ''}" class="ct_input_g" style="width: 200px; height: 19px" />
		</td>

			<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>

						</table>
					</td>
			</tr>
	</table>

</form>

</div>
</body>
</html>