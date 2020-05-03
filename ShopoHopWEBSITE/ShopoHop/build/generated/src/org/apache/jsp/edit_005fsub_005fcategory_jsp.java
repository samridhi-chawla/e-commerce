package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class edit_005fsub_005fcategory_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("          <script>\n");
      out.write("\n");
      out.write("            function readandpreview(fileobj, imageid)\n");
      out.write("            {\n");
      out.write("                var firstfile = fileobj.files[0];\n");
      out.write("\n");
      out.write("                var reader = new FileReader();\n");
      out.write("\n");
      out.write("                reader.onload = (function (f)\n");
      out.write("                {\n");
      out.write("                    return function read12(e)\n");
      out.write("                    {\n");
      out.write("                        document.getElementById(imageid).src = e.target.result;\n");
      out.write("                    };\n");
      out.write("                })(firstfile);\n");
      out.write("\n");
      out.write("\n");
      out.write("                reader.readAsDataURL(firstfile);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("            <form action=\"./MultiFileUploaderForEditSub\" method=\"post\" enctype=\"multipart/form-data\"> \n");
      out.write("            <h1> edit your category</h1>\n");
      out.write("            ");

                String name = request.getParameter("name");
                String description = "";
                String filepath = "";
                String c_name="";               
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("driver loaded successfully");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "123456");
                System.out.println("connection built");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from sub_categories where sub_c_name='" + name + "'");
                if (rs.next())
                {
                    c_name=rs.getString("c_name");
                    description = rs.getString("description");
                    filepath = rs.getString("photo");
                      
                }

            
      out.write("\n");
      out.write("            sub category name\n");
      out.write("            <input  type=\"text\" name=\"subcatgname\" value=\"");
      out.print(name);
      out.write("\" readonly=\"true\"  /><br>\n");
      out.write("            select category name\n");
      out.write("                <select name=\" catg\">\n");
      out.write("            ");

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded successfully");
            Connection connn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "123456");
            System.out.println("connection built");
            Statement stmtm = conn.createStatement();
            ResultSet rsp = stmtm.executeQuery("select * from categories");
            while (rsp.next())
            {
                      String cname = rsp.getString("c_name");
                      
      out.write("\n");
      out.write("                      <option>");
      out.print(cname);
      out.write("</option>\n");
      out.write("                      ");

            }
            
      out.write("\n");
      out.write("                  \n");
      out.write("        </select><br>\n");
      out.write("            Description\n");
      out.write("            <textarea name=\"text\" >");
      out.print(description);
      out.write("</textarea><br>\n");
      out.write("            <tr>\n");
      out.write("                <td><img src=\"");
      out.print(filepath);
      out.write("\" id=\"im2\" style=\"width: 100px;height: 100px;\"></td>  \n");
      out.write("                <td><input type=\"file\" id=\"f2\" name=\"f2\" onchange=\"readandpreview(this, 'im2')\"  /> </td>\n");
      out.write("            </tr>\n");
      out.write("            <br>     \n");
      out.write("            <input type=\"submit\" value=\"edit\" /><br>\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
