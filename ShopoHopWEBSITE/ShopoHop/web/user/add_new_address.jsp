<%@page import="java.sql.*"%>
<%
    String email = request.getParameter("email");
    String address = request.getParameter("address").toUpperCase();
    String city = request.getParameter("city").toUpperCase();
    String state = request.getParameter("state").toUpperCase();
    String pincode = request.getParameter("pincode");
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs1 = stmt1.executeQuery("select * from user_addresses");
    rs1.moveToInsertRow();
    rs1.updateString("email", email);
    rs1.updateString("address", address);
    rs1.updateString("state", state);
    rs1.updateString("city", city);
    rs1.updateString("pincode", pincode);
    rs1.insertRow();
    response.sendRedirect("place_order.jsp");

%>
