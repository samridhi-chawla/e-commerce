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

public class get_subcategories extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
           String category = request.getParameter("category");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sub_categories where c_name='"+category+"'");
            JSONArray jSONArray = new JSONArray();
            while(rs.next()){
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("photo", rs.getString("photo"));
                jSONObject.put("sub_c_name", rs.getString("sub_c_name"));
                jSONObject.put("description",rs.getString("description"));
                jSONArray.add(jSONObject);
            }
            out.println(jSONArray);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        processRequest(request, response);
    }
}
