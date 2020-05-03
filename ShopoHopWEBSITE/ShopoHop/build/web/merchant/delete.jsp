<%@page import="java.sql.*"%>
<%
    int product_id = Integer.parseInt(request.getParameter("product_id"));
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from products where product_id='" + product_id + "'");
    if (rs.next()) {
        rs.deleteRow();
        response.sendRedirect("dashboard.jsp");
                
    }
%>