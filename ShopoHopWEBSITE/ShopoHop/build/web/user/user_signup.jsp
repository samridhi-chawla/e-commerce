<%@page import="java.sql.*" %>
<%
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String phone_no = request.getParameter("phone_no");
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "'");
    if(rs.next())
        {
            response.sendRedirect("sign_up.jsp?msg=This email is already registered as a user. Please try with some other email.");
        }
    else
    {
        rs.moveToInsertRow();
        rs.updateString("name", name);
        rs.updateString("password", password);
        rs.updateString("email", email);
        rs.updateString("phone_no", phone_no);
        rs.insertRow();
        response.sendRedirect("login.jsp");
    }
%>
