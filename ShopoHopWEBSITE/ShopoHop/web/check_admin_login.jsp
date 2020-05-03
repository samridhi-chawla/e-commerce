
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String username = request.getParameter("username");
            String password =request.getParameter("pass");
            session.setAttribute("username", username);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded successfully");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            System.out.println("connection built");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from admin_login where username='"+username+"' and password='"+password+"'");
             if(rs.next())
            {
                session.setAttribute("username", username);
                response.sendRedirect("admin_dashboard.jsp");
            }
            else
            {
                response.sendRedirect("admin_login.jsp?msg=invalid user name or password");
            }
         %>
    </body>
</html>
