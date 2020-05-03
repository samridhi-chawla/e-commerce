
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class get_subcategory extends HttpServlet {

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try {
            String category=request.getParameter("category");
            String product_id=request.getParameter("p_id");
            System.out.println("plese con=me here");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select distinct sub_c_name from sub_categories where c_name='"+category+"'");
            
            out.println(product_id+",");
            
            while(rs.next())
            {
                out.println("<option>"+rs.getString("sub_c_name")+"</option>");
            }

        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
