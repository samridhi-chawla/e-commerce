
package serverForMobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class add_user_address extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email=request.getParameter("email");
            String address=request.getParameter("address").toUpperCase();
            String city=request.getParameter("city").toUpperCase(Locale.ITALY);
            String state=request.getParameter("state").toUpperCase();
            String pincode=request.getParameter("pincode").toUpperCase();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from user_addresses");
            rs.moveToInsertRow();
            rs.updateString("email", email);
            rs.updateString("address", address);
            rs.updateString("city", city);
            rs.updateString("state", state);
            rs.updateString("pincode", pincode);
            rs.insertRow();
            
            out.println("success");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
