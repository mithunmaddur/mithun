<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Login here</h1>
	
	<form action="LoginInt.jsp" method="post">
	
		Enter email: <input type="text" name="email" value="<%=request.getParameter("email")==null?"":request.getParameter("email")%>"/><br/>
		Enter pass: <input type="password" name="pass"/><br/>
		<br/>
		<input type="submit"/>
	</form>
	
	<%
		if(request.getAttribute("errorMsg")!=null)
		{
			out.println("<h1>Error</h1>");
			out.println(request.getAttribute("errorMsg"));
		}
	%>
	
</body>
</html>



















