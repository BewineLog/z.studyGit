<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetUserList(){
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	
	
	<c:if test="${! empty searchCondition }">
	<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${searchVO.getSearchCondition == "0" ? "selected" : "" }>회원ID</option>
				<option value="1" ${searchVO.getSearchCondition == "1" ? "selected" : "" }>회원명</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	</c:if>
	
	<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">회원ID</option>
				<option value="1">회원명</option>
			</select>
			<input type="text" name="searchKeyword" value="${searchVO.searchKeyword}"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">이메일</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
<%-- 	<% 	 
// 		int no=list.size();
// 		for(int i=0; i<list.size(); i++) {
// 			UserVO vo = (UserVO)list.get(i);
	%> --%>
<!-- 	<tr class="ct_list_pop"> -->
<%-- 		<td align="center"><%=no %></td> --%>
<!-- 		<td></td> -->
<!-- 		<td align="left"> -->
<%-- 			<a href="/getUser.do?userId=<%=vo.getUserId() %>"><%= vo.getUserId() %></a> --%>
<!-- 		</td> -->
<!-- 		<td></td> -->
<%-- 		<td align="left"><%= vo.getUserName() %></td> --%>
<!-- 		<td></td> -->
<%-- 		<td align="left"><%= vo.getEmail() %> --%>
<!-- 		</td>		 -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td colspan="11" bgcolor="D6D7D6" height="1"></td> -->
<!-- 	</tr> -->
<%-- 	<% } %> --%>
	
	<c:set var="idx" value="0">
	</c:set>
	
	<c:forEach var="i" items="${list}" begin="${idx}" step="1" end="${idx+pageInfo.pageSize}">
		<tr class="ct_list_pop">
		<td align="center">${idx+1 + pageInfo.pageSize*(pageInfo.currentPage-1)}</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${i.userId}">${i.userId}</a>
		</td>
		<td></td>
		<td align="left">${i.userName}</td>
		<td></td>
		<td align="left">${i.email}
		</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
	<c:set var="idx" value="${idx+1}">
	</c:set>
	
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
		<c:if test="${pageInfo.beginUnitPage > pageInfo.pageSize}">
			<a href="/listUser.do?page=${pageInfo.beginUnitPage-1}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondition}">${'<'}</a>
		</c:if>
		
		<c:forEach var="i" begin="${pageInfo.beginUnitPage}" end="${pageInfo.endUnitPage}">
			<a href="/listUser.do?page=${i}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondition}">${i}</a>
		</c:forEach>
		
		<c:if test="${pageInfo.endUnitPage < pageInfo.maxPage}">
			<a href="/listUser.do?page=${pageInfo.endUnitPage+1}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondition}">${'>'}</a>
		</c:if>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>