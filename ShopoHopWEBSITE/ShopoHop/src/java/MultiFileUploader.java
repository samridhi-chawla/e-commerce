
import com.mysql.jdbc.Statement;
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
public class MultiFileUploader extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            PrintWriter out = response.getWriter();
            
            String filepath="";
            //Part part1= request.getPart("fn");
            Collection<Part> parts = request.getParts();
            String ans = "";

            String absolutepath = request.getServletContext().getRealPath("/merchant_pics");

            System.out.println(absolutepath+" complete path");
            for (Part part : parts) {

                String filename = vmm.FileUploader.savefileonserver(part, absolutepath);
//                String filename=vmm2.FileUploader.savefileonserver(part,absolutepath,newfilename);
                //you can pass third paramater to change filename on serverside

                if (filename.equals("not-a-file")) {
                } else {
                    ans += "<br>" + filename;
                    filepath="./merchant_pics/" + filename;
                }
            }
            
            
         
            String catgname = request.getParameter("catgname");
            String textarea = request.getParameter("text");

            
//            String s = request.getParameter("s1");
//            out.println("<h3>Size : "+s+"</h3>");
            
            
           try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
                java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from categories");
//                if(rs.next())
//                {
//                    response.sendRedirect("add_category.jsp?msg=category Already exists");
//                }
//                else
//                {
                rs.moveToInsertRow();
                rs.updateString("c_name", catgname);
                rs.updateString("description",textarea );
                rs.updateString("photo",filepath);
                rs.insertRow();
                response.sendRedirect("add_category.jsp?msg=category added successfully");
//                }
           }
           catch(Exception e){e.printStackTrace();}
        } 
        
      
            }
    


