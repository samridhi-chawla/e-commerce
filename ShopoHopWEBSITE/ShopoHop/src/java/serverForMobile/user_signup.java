package serverForMobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class user_signup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        try {
           String email = request.getParameter("email");
           String phone = request.getParameter("phone");
           String name = request.getParameter("name");
           String pass = request.getParameter("pass");
           
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","system");
           Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
           ResultSet rs = stmt.executeQuery("select * from users where email='"+email+"'");
           if(rs.next()){
               out.println("email exists");
           }
           else{
               rs.moveToInsertRow();
               rs.updateString("email", email);
               rs.updateString("phone_no", phone);
               rs.updateString("name", name);
               rs.updateString("password", pass);
               rs.insertRow();
               out.println("success");
           }
           
        }catch(Exception e){ e.printStackTrace();}
    }

    
}
