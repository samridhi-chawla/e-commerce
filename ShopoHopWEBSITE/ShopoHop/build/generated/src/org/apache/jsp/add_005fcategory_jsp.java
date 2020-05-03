package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class add_005fcategory_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "admin_nav.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container\"> \n");
      out.write("            <div class=\"jumbotron\">\n");
      out.write("                <h1>Add New Category</h1>\n");
      out.write("            </div>\n");
      out.write("            <form action=\"Test\" class=\"form-vertical\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("\n");
      out.write("                <div class='form-group'>\n");
      out.write("                    <label for='cat'>Enter category name</label>\n");
      out.write("                    <input  class='form-control' type=\"text\" name=\"category\" required /><br>\n");
      out.write("                </div>\n");
      out.write("                <div class='form-group'>\n");
      out.write("                    <label for=\"descrip\">Description</label>\n");
      out.write("                    <textarea class='form-control' name=\"description\"></textarea><br>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class='form-group'>\n");
      out.write("                    <label>select photo</label>\n");
      out.write("                    <input class=\"form-control\" type=\"file\" name=\"pic\" required />  <br>\n");
      out.write("                </div>\n");
      out.write("                <input class=\"btn btn-lg btn-primary\" type=\"submit\" value=\"Add\" /><br>\n");
      out.write("                ");

                    String msg = request.getParameter("msg");
                    if (msg != null) {
                
      out.write("\n");
      out.write("                <label style=\"color: red\">");
      out.print( msg);
      out.write("</label>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            <h1 class=\"text-center\">Manage Category</h1>\n");
      out.write("\n");
      out.write("            <table class=\"table-bordered table table-condensed\" border=\"5\" cellspacing=\"5\" cellpadding=\"10\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>category name</th>\n");
      out.write("                    <th>description</th>\n");
      out.write("                    <th>image</th>\n");
      out.write("                    <th colspan=\"2\">operations</th>\n");
      out.write("\n");
      out.write("                </tr>\n");
      out.write("                ");

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from categories");
                    while (rs.next()) {
                        String categoryname = rs.getString("c_name");
                        String description = rs.getString("description");
                        String filepath = rs.getString("photo");

                
      out.write("\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( categoryname);
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( description);
      out.write("</td>\n");
      out.write("                    <td><img src=\"");
      out.print(filepath);
      out.write("\" width=\"100\" height=\"100\" /> </td>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <td><a href=\"edit_category.jsp?name=");
      out.print( categoryname);
      out.write("\"   class=\"btn btn-success\">Edit</a></td>\n");
      out.write("                    <td><a href=\"delete_category.jsp?name=");
      out.print( categoryname);
      out.write("\" onclick=\"return confirm('are you sure to delete?')\" class=\"btn btn-danger\">Delete</a></td>\n");
      out.write("                </tr>  \n");
      out.write("\n");
      out.write("\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
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
