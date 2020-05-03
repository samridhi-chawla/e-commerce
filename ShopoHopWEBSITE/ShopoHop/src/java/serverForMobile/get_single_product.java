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

public class get_single_product extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
            String p_id=request.getParameter("p_id");
             Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products where product_id='"+p_id+"'");
            JSONObject jSONObject=new JSONObject();
            if(rs.next()){
                
                jSONObject.put("product_id",rs.getString("product_id"));
                jSONObject.put("p_name",rs.getString("p_name"));
                jSONObject.put("category",rs.getString("category"));
                jSONObject.put("sub_category",rs.getString("sub_category"));
                jSONObject.put("p_photo",rs.getString("p_photo"));
                jSONObject.put("p_descrip",rs.getString("p_descrip"));
                jSONObject.put("p_mrp",rs.getString("p_mrp"));
                jSONObject.put("p_offer_price",rs.getString("p_offer_price"));
                jSONObject.put("seller_email",rs.getString("seller_email"));
                
                
            }
            out.println(jSONObject);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     processRequest(request, response);
 }
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      processRequest(request, response);
  }
}
