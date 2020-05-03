<%@page import="java.sql.*"%>
<%
            String username = (String)session.getAttribute("username");
            String oldpass=request.getParameter("old");
            String newpass=request.getParameter("new"); 
          try{
            Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
                java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from admin_login where password='"+oldpass+"' && username='"+username+"'");
                if(rs.next())
                {
                    rs.updateString("password",newpass);
                    rs.updateRow();
                    response.sendRedirect("admin_dashboard.jsp?msg=password changed successfully");
                   
                  
               }
                else
                {
                  response.sendRedirect("change_admin_password.jsp?msg=old password doesn't match");

                }
          }
         
                     catch(Exception e)
                             {e.printStackTrace();}

        %>
    