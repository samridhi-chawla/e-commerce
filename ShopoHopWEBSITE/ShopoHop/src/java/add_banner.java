
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class add_banner extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Part part = request.getPart("banner_photo");
            String abspath = request.getServletContext().getRealPath("/banners");
            String banner_photo = "./banners/" + vmm.FileUploader.savefileonserver(part, abspath, System.currentTimeMillis() + ".jpg");
            String min_price = request.getParameter("min_price");
            String max_price = request.getParameter("max_price");
            String discount = request.getParameter("discount");
            String sub_category = request.getParameter("sub_category");
            String new_item_pid = request.getParameter("new_item_pid");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
            java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from banners");

            rs.moveToInsertRow();
            rs.updateString("banner_photo", banner_photo);
            if (!discount.equals("")) {
                rs.updateString("discount", discount);
            }
            if (!min_price.equals("")) {
                rs.updateString("min_price", min_price);
            }
            if (!max_price.equals("")) {
                rs.updateString("max_price", max_price);
            }
            if(!new_item_pid.equals("")){
                rs.updateString("new_item_link", "search_product.jsp?p_id="+new_item_pid);
            }
            rs.updateString("sub_category", sub_category);
            rs.insertRow();
            response.sendRedirect("manage_adbanner.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
