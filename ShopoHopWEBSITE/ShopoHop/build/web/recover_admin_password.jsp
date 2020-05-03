
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container">
            <form>

                <%
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("driver loaded successfully");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                    System.out.println("connection built");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from admin_login where username='abc' ");
                    if (rs.next()) {
                        String email = rs.getString("email");

                %>
                <h1> your password will be sent to <%=email%></h1>
                <%
                    }
                %>
                <input type="submit" value="send email" /><br>
            </form>   
        </div>
    </body>
</html>
