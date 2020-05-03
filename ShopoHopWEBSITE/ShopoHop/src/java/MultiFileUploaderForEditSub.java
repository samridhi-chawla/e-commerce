
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
public class MultiFileUploaderForEditSub extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            PrintWriter out = response.getWriter();
            
            String filepath="";
            //Part part1= request.getPart("fn");
            Collection<Part> parts = request.getParts();
            String ans = "";

            String absolutepath = request.getServletContext().getRealPath("/categorypics");

            System.out.println(absolutepath+" complete path");
            for (Part part : parts) 
            {
               if(part.getSize()!=0)
               {
                String filename = vmm.FileUploader.savefileonserver(part, absolutepath);
//                String filename=vmm2.FileUploader.savefileonserver(part,absolutepath,newfilename);
                //you can pass third paramater to change filename on serverside

                if (filename.equals("not-a-file")) 
                {
                   
                } 
                else 
                {
                    ans += "<br>" + filename;
                 filepath="./categorypics/" + filename;
                }
               }
            }
            
            
         
            String subcatgname = request.getParameter("subcatgname");
            String catgname = request.getParameter("catg");
            String textarea = request.getParameter("text");

            
//            String s = request.getParameter("s1");
//            out.println("<h3>Size : "+s+"</h3>");
            
            
           try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
                java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from sub_categories where sub_c_name='"+subcatgname+"'");
               while (rs.next())
                {
                rs.updateString("sub_c_name", subcatgname);
                rs.updateString("c_name",catgname);
                rs.updateString("description",textarea );
                
                if(!filepath.equals(""))
                {
                    rs.updateString("photo",filepath);
                }
                rs.updateRow();
                response.sendRedirect("add_sub_category.jsp?msg=category edited successfully");
                }
           }
           catch(Exception e){e.printStackTrace();}
        } 
        
      
            }
    


