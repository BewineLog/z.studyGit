<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	User vo=(User)session.getAttribute("user");
%>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function() {
		$('td:contains("login")').on("click", function(){
			$(window.parent.frames["rightFrame"].document.location).attr("href","/user/loginView.jsp");
		});
		
		$('td:contains("logout")').on("click", function(){
// 			self.location = "/user/logout";
			$(window.parent.document.location).attr("href","/user/logout");
		});
	});

</script>

</head>

<body topmargin="0" leftmargin="0">
 
<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="800" height="30"><h2>Model2 MVC Shop</h2></td>
    <td height="30" >&nbsp;</td>
  </tr>
  <tr>
    <td height="20" align="right" background="/images/img_bg.gif">
	    <table width="200" border="0" cellspacing="0" cellpadding="0">
	        <tr> 
	          <td width="115">
	          <%
	          	if(vo == null) {
	          %>
<!-- 	              <a href="/user/loginView.jsp" target="rightFrame">login</a>    -->
					login
	          <%
	          	}
	          %>        
	          </td>
	          <td width="14">&nbsp;</td>
	          <td width="56">
	          <%
	          	if(vo != null) {
	          %>
<!-- 	            <a href="/user/logout" target="_parent">logout</a>   -->
					logout
	           <%
	          	}
	           %>
	          </td>
	        </tr>
	    </table>
    </td>
    <td height="20" background="/images/img_bg.gif">&nbsp;</td>
  </tr>
</table>

</body>
</html>
