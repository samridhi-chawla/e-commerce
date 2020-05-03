
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class merchant_signup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String city = request.getParameter("city").toUpperCase();
            String state = request.getParameter("state").toUpperCase();
            String bank_acc = request.getParameter("bank_acc");
            String ifsc_code = request.getParameter("ifsc_code");
            String phone = request.getParameter("phone");

            Part aadhar = request.getPart("aadhar_no");
            Part tin = request.getPart("tin_no");
            Part coicon = request.getPart("co_icon");

            String abspath = request.getServletContext().getRealPath("/merchant_pics");
            String aadhar_no = "./merchant_pics/" + vmm.FileUploader.savefileonserver(aadhar, abspath, email + "_aadhar.jpg");
            String tin_no = "./merchant_pics/" + vmm.FileUploader.savefileonserver(tin, abspath, email + "_tin.jpg");
            String co_icon = "./merchant_pics/" + vmm.FileUploader.savefileonserver(coicon, abspath, email + "_icon.jpg");

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
            java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from merchant where email='" + email + "'");
            if (rs.next()) {
                response.sendRedirect("merchant/sign_up.jsp?msg=email already registered");
            } else {
                rs.moveToInsertRow();
                rs.updateString("email", email);
                rs.updateString("name", name);
                rs.updateString("phone", phone);
                rs.updateString("address", address);
                rs.updateString("city", city);
                rs.updateString("state", state);
                rs.updateString("bank_acc", bank_acc);
                rs.updateString("ifsc_code", ifsc_code);
                rs.updateString("password", password);
                rs.updateString("aadhar_no", aadhar_no);
                rs.updateString("tin_no", tin_no);
                rs.updateString("co_icon", co_icon);
                rs.updateString("status", "pending");
                rs.insertRow();
                response.sendRedirect("merchant/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
