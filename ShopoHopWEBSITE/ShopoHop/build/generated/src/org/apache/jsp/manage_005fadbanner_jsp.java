package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class manage_005fadbanner_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/jquery-3.2.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body class=\"container\">\n");
      out.write("        <script>\n");
      out.write("            function readandpreview(fileobj, imageid)\n");
      out.write("            {\n");
      out.write("                var firstfile = fileobj.files[0];\n");
      out.write("\n");
      out.write("                var reader = new FileReader();\n");
      out.write("\n");
      out.write("                //alert(\"File name: \" + firstfile.name);\n");
      out.write("                // alert(\"File size: \" + firstfile.size);\n");
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
      out.write("            var xmlhttp = new XMLHttpRequest();\n");
      out.write("            function get_subcategories() {\n");
      out.write("                var c_name = document.getElementById(\"category\").value;\n");
      out.write("                xmlhttp.open(\"get\", \"get_subcategory?category=\" + c_name, true);\n");
      out.write("                xmlhttp.onreadystatechange = function () {\n");
      out.write("                    alert(xmlhttp.responseText);\n");
      out.write("                    var res=xmlhttp.responseText.split(\",\");\n");
      out.write("                    document.getElementById(\"sub_category\").innerHTML = res[1];\n");
      out.write("                };\n");
      out.write("                xmlhttp.send();\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("        ");

            String username = (String) session.getAttribute("username");
            if (username == null) {
                response.sendRedirect("admin_login.jsp");
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select distinct c_name from categories");

        
      out.write("\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1>Manage AD Banners</h1>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"row\">\n");
      out.write("            ");

            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = stmt1.executeQuery("select * from banners");
            while(rs1.next()){
                
      out.write("\n");
      out.write("                <div class=\"col-md-3 thumbnail\">\n");
      out.write("                    <img src=\".");
      out.print(rs.getString("banner_photo"));
      out.write("\" class=\"img-responsive img-thumbnail\">\n");
      out.write("                    <table class=\"table\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Sub Category</td>\n");
      out.write("                            <td>");
      out.print(rs.getString("sub_category"));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Min price</td>\n");
      out.write("                            <td>");
      out.print(rs.getString("min_price"));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Max price</td>\n");
      out.write("                            <td>");
      out.print(rs.getString("max_price"));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Discount</td>\n");
      out.write("                            <td>");
      out.print(rs.getString("discount"));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>New Item</td>\n");
      out.write("                            <td>");
      out.print(rs.getString("new_item"));
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("                ");

            }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <!----ADD BANNER --->\n");
      out.write("        <div class=\"btn btn-lg btn-primary\" data-toggle=\"collapse\" data-target=\"#add_banner\" ><span class=\"glyphicon glyphicon-plus-sign\"></span> Add New Banner</div>\n");
      out.write("\n");
      out.write("        <div id=\"add_banner\" class=\"\" style=\"margin-top: 20px;width: 50%\">\n");
      out.write("            <form class=\"form-vertical\" action=\"add_banner.jsp\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"photo\">Choose the picture for banner</label>\n");
      out.write("                    <input type=\"file\" id=\"photo\" name=\"banner_photo\" onchange=\"readandpreview(this, 'img')\" >\n");
      out.write("                    <img src=\"\" class=\"img-responsive \" width=\"200px\" height=\"200px\" id=\"img\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"category\">Category</label>\n");
      out.write("                    <select id=\"category\" class=\"form-control\" onchange=\"get_subcategories()\">\n");
      out.write("                        ");
                            while (rs.next()) {
                        
      out.write("\n");
      out.write("                        <option>");
      out.print(rs.getString("c_name"));
      out.write("</option>\n");
      out.write("                        ");

                            }
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"sub_categories\">Subcategory</label>\n");
      out.write("                    <select class='form-control' id=\"sub_category\">\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"min_price\"><input type=\"radio\" id=\"min_p\" name=\"x\" value=\"min_price\" class=\"from-control\" onchange=\"visible_input()\"/>Price below filter</label><br>\n");
      out.write("                        <label for=\"max_price\"><input type=\"radio\" id=\"max_p\" name=\"x\" value=\"max_price\" class=\"from-control\"/>Price Above Filter</label><br>\n");
      out.write("                        <label for=\"min_max\"><input type=\"radio\" id=\"min_max\" name=\"x\" value=\"min_max\" class=\"from-control\"/>Combined Min and Max Filter</label><br>\n");
      out.write("                        <label for=\"discount\"><input type=\"radio\" name=\"x\" value=\"discount\" class=\"from-control\"/>Discount Filter</label><br>\n");
      out.write("                        <label for=\"new_prod\"><input type=\"radio\" name=\"x\" value=\"new_prod\" class=\"from-control\"/>New Item</label>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\" id=\"price_below\" >\n");
      out.write("                        <div class=\"col-x-4\"><label>Enter min price here</label></div>\n");
      out.write("                        <div class=\"col-xs-8\"><input type=\"text\" class=\"form-control\" name='price_below' /></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\" id=\"price_above\">\n");
      out.write("                        <div class=\"col-x-4\"><label>Enter max price here</label></div>\n");
      out.write("                        <div class=\"col-xs-8\"><input type=\"text\" class=\"form-control\" name='price_above'  /></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\" id=\"discount\">\n");
      out.write("                        <div class=\"col-x-4\"><label>Enter Discount here</label></div>\n");
      out.write("                        <div class=\"col-xs-8\"><input type=\"text\" class=\"form-control\" name='discount'  /></div>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <input type=\"submit\" class=\"btn-primary btn-lg\" value=\"Add Banner\" style=\"margin-top: 40%\" />\n");
      out.write("            </form>\n");
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
