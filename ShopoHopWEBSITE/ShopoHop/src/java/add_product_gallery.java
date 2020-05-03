
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig
public class add_product_gallery extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String product_id = request.getParameter("p_id");
            String caption = request.getParameter("g_caption");
            Part part = request.getPart("g_path");
            String abspath = request.getServletContext().getRealPath("/prod_images");
            String path = "./prod_images/"+vmm.FileUploader.savefileonserver(part, abspath, System.currentTimeMillis()+".jpg");
            System.out.println("caption "+caption);
            System.out.println("pit "+product_id);
            System.out.println("path "+path);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from photo_gallery");
            rs.moveToInsertRow();
            rs.updateString("path", path);
            rs.updateString("caption", caption);
            rs.updateString("product_id", product_id);
            rs.insertRow();
            response.sendRedirect("merchant/dashboard.jsp");
                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
