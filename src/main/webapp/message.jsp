<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
</head>
<link rel="stylesheet" href="css/message.css">
<body>
<h2>Menu</h2>
<%! String s1 = ""; %>
    <% s1  = (String) session.getAttribute("message");%>
    <% if(s1==null){ %>
    <% }else{ %>
    <p>
    <%=s1 %>
    </p>
    <% } %>
    <a href="index.jsp">Login Here</a>
    <a href="register.jsp">Register Here</a>
</body>
</html>