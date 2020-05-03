
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
public class SimplePhotoUploader extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Part part = request.getPart("banner_photo");
            String abspath = request.getServletContext().getRealPath("/banners");
            String banner_photo = "./banners/" + vmm.FileUploader.savefileonserver(part, abspath, System.currentTimeMillis() + ".jpg");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
