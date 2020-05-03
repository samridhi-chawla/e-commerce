<%@page import="java.sql.*"%>
<%
    String email = request.getParameter("email");
    System.out.println(email);
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
    java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from merchant where email='"+email+"'");
    if(rs.next())
    {
        rs.updateString("status", "active");
        rs.updateRow();
    }

%>