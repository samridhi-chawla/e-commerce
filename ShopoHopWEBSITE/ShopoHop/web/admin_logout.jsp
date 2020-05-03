<%
session.removeAttribute("username");
response.sendRedirect("admin_login.jsp");
%>