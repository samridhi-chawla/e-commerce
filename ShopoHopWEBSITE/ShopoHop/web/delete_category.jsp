
<%@page import="java.sql.*"%>
<%
    String name = request.getParameter("name");
    try {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("driver loaded successfully");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
        System.out.println("connection built");
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("select * from categories where c_name='" + name + "'");

        if (rs.next()) {
            rs.deleteRow();
            response.sendRedirect("add_category.jsp?msg=category deleted succesfully");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }


%>
