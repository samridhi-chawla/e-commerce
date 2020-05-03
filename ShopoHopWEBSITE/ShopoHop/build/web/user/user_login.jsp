<%@page import="java.sql.*"%>
<%
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "' and password='"+password+"'");
                if (rs.next())
                {
                    session.setAttribute("email", email);
                    session.setMaxInactiveInterval(60*60*24);
                    //response.sendRedirect("home.jsp");
                } 
                else 
                {
                    out.println("gadbad");
                }
           
        %>