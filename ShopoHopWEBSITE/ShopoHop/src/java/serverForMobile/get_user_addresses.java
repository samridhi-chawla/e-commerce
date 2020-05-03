package serverForMobile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class get_user_addresses extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
            String email=request.getParameter("email");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user_addresses where email='"+email+"'");
            JSONArray jSONArray=new JSONArray();
            while(rs.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("add_id",rs.getString("add_id"));
            jsonObject.put("address",rs.getString("address"));
            jsonObject.put("city",rs.getString("city"));
            jsonObject.put("state",rs.getString("state"));
            jsonObject.put("pincode",rs.getString("pincode"));
            jSONArray.add(jsonObject);
            }
            out.println(jSONArray);
           
        } catch(Exception e){
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
