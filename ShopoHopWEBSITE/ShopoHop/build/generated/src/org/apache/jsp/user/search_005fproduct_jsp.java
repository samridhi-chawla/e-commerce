package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class search_005fproduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/jquery-3.2.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script>\n");
      out.write("            function make_active(i)\n");
      out.write("            {\n");
      out.write("                document.getElementById(\"make_active\"+i).className=\"item active\";\n");
      out.write("            }\n");
      out.write("            </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");

            String session_email = (String) session.getAttribute("email");
            String p_id = request.getParameter("p_id");
            if (p_id == null) {
                response.sendRedirect("home.jsp");
            }

            int product_id = 0;
            if (p_id != null) {
                product_id = Integer.parseInt(p_id);
            }

        
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../includes/header.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            ");
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from products where product_id='" + product_id + "'");
                if (rs.next()) {
                    String p_name = rs.getString("p_name");
                    String p_descrip = rs.getString("p_descrip");
                    String p_photo = rs.getString("p_photo");
                    String category = rs.getString("category");
                    String sub_category = rs.getString("sub_category");
                    String p_mrp = rs.getString("p_mrp");
                    String p_offer_price = rs.getString("p_offer_price");

            
      out.write("\n");
      out.write("            <h2> ");
      out.print(p_name);
      out.write(" </h2>\n");
      out.write("            <div class=\"row\" style=\"border-radius: 20;border: 2; background-color: whitesmoke\">\n");
      out.write("                <div class=\"col-md-6\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <img src=\".");
      out.print(p_photo);
      out.write("\" class=\"img-thumbnail img-responsive\" alt=\"primary photo of product\" style=\"margin-left: 20%\"/>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"row\" style=\"margin-left: 10%\">\n");
      out.write("                            <label>Additional Photos of product</label>\n");
      out.write("                        </div>\n");
      out.write("                        ");

                            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs1 = stmt1.executeQuery("select * from photo_gallery where product_id='" + product_id + "'");
                            int count = 0;  //to use in carousel indexing later
                            while (rs1.next()) {
                                
                        
      out.write("\n");
      out.write("\n");
      out.write("                        <div class=\"col-xs-3\">\n");
      out.write("                            <img src='.");
      out.print(rs1.getString("path"));
      out.write("' class=\"img-responsive img-thumbnail hover-shadow cursor\" data-toggle=\"modal\" data-target=\"#myModal\" onclick=\"make_active('");
      out.print(count);
      out.write("')\" style=\"height:100px\"/>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        ");
 count++;
                            }
                            rs1.beforeFirst(); //so that we can use the same result set to generate slides in lightbox
                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-6\">\n");
      out.write("                    <table class=\"table table-responsive\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Name of product</label></td>\n");
      out.write("                            <td>");
      out.print(p_name);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Type of product</label></td>\n");
      out.write("                            <td>");
      out.print(sub_category);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Category</label></td>\n");
      out.write("                            <td>");
      out.print(category);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Description</label></td>\n");
      out.write("                            <td>");
      out.print(p_descrip);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Price of product</label></td>\n");
      out.write("                            <td>");
      out.print(p_mrp);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><label>Offered price</label></td>\n");
      out.write("                            <td>");
      out.print(p_offer_price);
      out.write("</td>   \n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td></td>\n");
      out.write("                            <td><div class=\"btn btn-primary btn-lg\" style=\"float: right\">Add to Cart</div></td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!----Light Box -------->\n");
      out.write("            <!-- Modal -->\n");
      out.write("            <div id=\"myModal\" class=\"modal fade\" role=\"dialog\">\n");
      out.write("                <div class=\"modal-dialog\">\n");
      out.write("\n");
      out.write("                    <!-- Modal content-->\n");
      out.write("                    <div class=\"modal-content\">\n");
      out.write("                        <div class=\"modal-header\">\n");
      out.write("                            <button type=\"button\" class=\"close\"  data-dismiss=\"modal\">&times;</button>\n");
      out.write("                            <h4 class=\"modal-title\">Modal Header</h4>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("\n");
      out.write("                            <!------caroudel code---->\n");
      out.write("                            <div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">\n");
      out.write("                                <!-- Indicators -->\n");
      out.write("<!--                                <ol class=\"carousel-indicators\">\n");
      out.write("                                    <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>\n");
      out.write("                                    <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>\n");
      out.write("                                    <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>\n");
      out.write("                                    <li data-target=\"#myCarousel\" data-slide-to=\"3\"></li>\n");
      out.write("                                </ol>-->\n");
      out.write("\n");
      out.write("                                <!-- Wrapper for slides -->\n");
      out.write("                                <div class=\"carousel-inner\" role=\"listbox\">\n");
      out.write("                                    ");

                                        int i=0;
                                    while(rs1.next()){
                                    
      out.write("\n");
      out.write("                                    <div id=\"make_active");
      out.print(i);
      out.write("\" class=\"item \">\n");
      out.write("                                        <img src=\".");
      out.print(rs1.getString("path"));
      out.write("\" alt=\"\" width=\"100%\" height=\"400px\">\n");
      out.write("                                        <div class=\"carousel-caption\">\n");
      out.write("                                            <h3>");
      out.print(rs1.getString("caption"));
      out.write("</h3>\n");
      out.write("                                            <!--<p>The atmosphere in Chania has a touch of Florence and Venice.</p>-->\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    ");

                                    i++;
                                        }
                                    
      out.write("\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Left and right controls -->\n");
      out.write("                                <a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\n");
      out.write("                                    <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n");
      out.write("                                    <span class=\"sr-only\">Previous</span>\n");
      out.write("                                </a>\n");
      out.write("                                <a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\n");
      out.write("                                    <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n");
      out.write("                                    <span class=\"sr-only\">Next</span>\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                           \n");
      out.write("\n");
      out.write("                            <!--------carousel code ends----->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!----light box ends----->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
            }  //outermost if ends  ---everything on this page must be inside this
            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
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
