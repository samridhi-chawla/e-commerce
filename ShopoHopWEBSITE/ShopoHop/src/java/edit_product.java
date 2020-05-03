
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class edit_product extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String seller_email = (String) session.getAttribute("email");
            String product_id = request.getParameter("p_id");  //updation will be done according to this 
            String p_name = request.getParameter("p_name");
            String p_mrp = request.getParameter("p_mrp");
            String p_offer_price = request.getParameter("p_offer_price");
            String category = request.getParameter("category");
            String sub_category = request.getParameter("sub_category");
            String p_descrip = request.getParameter("p_descrip");
            String p_photo="";
            String radio_imgchanged = request.getParameter("img_changed");
            System.out.println("radio btn value " +radio_imgchanged );
            if (radio_imgchanged.equals("true")) {
                Part part = request.getPart("p_photo");
                String abspath = request.getServletContext().getRealPath("/prod_images");
                p_photo = "./prod_images/" + vmm.FileUploader.savefileonserver(part, abspath, System.currentTimeMillis() + ".jpg");
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from products where product_id='" + product_id + "'");
            System.out.println("connection build");
            if (rs.next()) {
                rs.updateString("p_name", p_name);
                rs.updateString("p_mrp", p_mrp);
                rs.updateString("p_offer_price", p_offer_price);
                rs.updateString("p_descrip", p_descrip);
                rs.updateString("category", category);
                rs.updateString("sub_category", sub_category);
                if (radio_imgchanged.equals("true")) {
                    rs.updateString("p_photo", p_photo);
                }
                rs.updateString("seller_email", seller_email);
                rs.updateRow();
                response.sendRedirect("merchant/dashboard.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
