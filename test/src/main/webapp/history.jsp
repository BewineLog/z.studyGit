<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>��� ��ǰ ����</title>

</head>
<body>
	����� ��� ��ǰ�� �˰� �ִ�
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	String history = null;
	Cookie[] cookies = request.getCookies();
	
	System.out.println("cookies::" + cookies.length);
	if (cookies!=null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			System.out.println("history ::" + cookie.getName() +"::" + cookie.getValue()) ;
			
// 			System.out.println(""+ cookie.getPath().toString() + "::" + cookie.getDomain().toString() + "::" );
			if (cookie.getName().equals("history")) {
				history = cookie.getValue();
				System.out.println("history:" + history);
			}
		}
		if (history != null) {
			String[] h = history.split("and");
			for (int i = 0; i < h.length; i++) {
				System.out.println("h[i]::" + h[i]);
				if (!h[i].equals("null")) {
%>
<a href="/product/getProduct?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a>
<br>
<%
				}
			}
		}
	}
%>

</body>
</html>