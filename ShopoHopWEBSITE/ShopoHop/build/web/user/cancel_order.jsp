<%@page import="java.sql.*"%>
<%
    String order_id = request.getParameter("order_id");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from orders where order_id='"+order_id+"'");
    if(rs.next()){
        rs.updateString("status", "cancelled");
        rs.updateRow();
    }

%>