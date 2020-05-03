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
import org.json.simple.*;

public class get_banners extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from banners");
           
            JSONArray jsonArrray = new JSONArray();
            while(rs.next()){
                String banner_photo = rs.getString("banner_photo");
                String min_price = rs.getString("min_price");
                String max_price = rs.getString("max_price");
                String discount = rs.getString("discount");
                String new_item_link = rs.getString("new_item_link");
                String sub_category = rs.getString("sub_category");
                
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("banner_photo", banner_photo);
                jsonObject.put("min_price", min_price);
                jsonObject.put("max_price", max_price);
                jsonObject.put("sub_category", sub_category);
                jsonObject.put("new_item_link", new_item_link);
                
                jsonArrray.add(jsonObject);
                        
            }
            
            out.println(jsonArrray);
        } catch(Exception e){
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
