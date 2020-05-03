package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>home</title>\n");
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/jquery-3.2.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <style>\n");
      out.write("            .main\n");
      out.write("            {\n");
      out.write("                width:100%;\n");
      out.write("                height: 400px;\n");
      out.write("                background-image:url('../includes/shop_back.jpg');\n");
      out.write("                background-repeat: no-repeat;\n");
      out.write("                background-size: cover;\n");
      out.write("                opacity: 0.7;    \n");
      out.write("                margin-top: -2%;\n");
      out.write("            }\n");
      out.write("            .search\n");
      out.write("            {\n");
      out.write("                position:relative;\n");
      out.write("                top:20%;\n");
      out.write("                left: 35%;\n");
      out.write("                width:30%;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../includes/header.jsp", out, false);
      out.write("\n");
      out.write("        ");

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = stmt1.executeQuery("select * from banners");


        
      out.write("\n");
      out.write("        <!------carousel code---->\n");
      out.write("        <div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\" style=\"margin-top: -2%\">\n");
      out.write("            <!-- Indicators -->\n");
      out.write("            <!--                                <ol class=\"carousel-indicators\">\n");
      out.write("                                                <li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>\n");
      out.write("                                                <li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>\n");
      out.write("                                                <li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>\n");
      out.write("                                                <li data-target=\"#myCarousel\" data-slide-to=\"3\"></li>\n");
      out.write("                                            </ol>-->\n");
      out.write("\n");
      out.write("            <!-- Wrapper for slides -->\n");
      out.write("            <div class=\"carousel-inner\" role=\"listbox\">\n");
      out.write("                ");
                    if (rs1.next()) {
                        //creting link
                        String link = "";
                        String min_price = rs1.getString("min_price");
                        String max_price = rs1.getString("max_price");
                        String discount = rs1.getString("discount");
                        String sub_category = rs1.getString("sub_category");
                        String new_item_link = rs1.getString("new_item_link");
                        System.out.println(" " + min_price + " " + max_price + " " + discount + " " + sub_category + " " + new_item_link);
                        if (new_item_link != null) {
                            link = new_item_link;
                        } else {
                            link = "products.jsp?s_cat=" + sub_category;
                            if (min_price != null) { // from database value will never come empty therefore null check
                                link = link + "&price_above=" + min_price;
                            }
                            if (max_price != null) {
                                link = link + "&price_below=" + max_price;
                            }
                            if (discount != null) {
                                link = link + "&discount=" + discount;
                            }
                        }

                
      out.write("\n");
      out.write("                <div  class=\"item active\">\n");
      out.write("                    <a href=\"");
      out.print(link);
      out.write("\"><img src=\".");
      out.print(rs1.getString("banner_photo"));
      out.write("\" alt=\"\"  style=\"width:100%;height: 600px\" ></a>\n");
      out.write("                    <!--                                        <div class=\"carousel-caption\">\n");
      out.write("                                                                <h3>caption</h3>\n");
      out.write("                                                                <p>The atmosphere in Chania has a touch of Florence and Venice.</p>\n");
      out.write("                                                            </div>-->\n");
      out.write("                </div>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("                ");

                    int i = 0;
                    while (rs1.next()) {
                        //creting link
                        String link = "";
                        String min_price = rs1.getString("min_price");
                        String max_price = rs1.getString("max_price");
                        String discount = rs1.getString("discount");
                        String sub_category = rs1.getString("sub_category");
                        String new_item_link = rs1.getString("new_item_link");
                        System.out.println(" " + min_price + " " + max_price + " " + discount + " " + sub_category + " " + new_item_link);
                        if (new_item_link != null) {
                            link = new_item_link;
                        } else {
                            link = "products.jsp?s_cat=" + sub_category;
                            if (min_price != null) { // from database value will never come empty therefore null check
                                link = link + "&price_above=" + min_price;
                            }
                            if (max_price != null) {
                                link = link + "&price_below=" + max_price;
                            }
                            if (discount != null) {
                                link = link + "&discount=" + discount;
                            }
                        }

                
      out.write("\n");
      out.write("                <div class=\"item \">\n");
      out.write("                    <a href=\"");
      out.print(link);
      out.write("\"><img src=\".");
      out.print(rs1.getString("banner_photo"));
      out.write("\" alt=\"\" style=\"width:100%;height: 600px\" ></a>\n");
      out.write("                    <!--                    <div class=\"carousel-caption\">\n");
      out.write("                                            <h3>caption</h3>\n");
      out.write("                                            <p>The atmosphere in Chania has a touch of Florence and Venice.</p>\n");
      out.write("                                        </div>-->\n");
      out.write("                </div>\n");
      out.write("                ");

                        i++;
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Left and right controls -->\n");
      out.write("            <a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">\n");
      out.write("                <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>\n");
      out.write("                <span class=\"sr-only\">Previous</span>\n");
      out.write("            </a>\n");
      out.write("            <a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">\n");
      out.write("                <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>\n");
      out.write("                <span class=\"sr-only\">Next</span>\n");
      out.write("            </a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!--------carousel code ends----->\n");
      out.write("        <div class=\"container\" style=\"padding: 5%\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("\n");
      out.write("                ");

                    Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs2 = stmt2.executeQuery("select * from sub_categories");
                    while (rs2.next()) {
                
      out.write("\n");
      out.write("                <a href=\"\">\n");
      out.write("                <div class=\"col-xs-3\">\n");
      out.write("                    <div class=\"thumbnail\">\n");
      out.write("                        <img src=\".");
      out.print(rs2.getString("photo"));
      out.write("\" class=\"img-responsive\" alt=\"...\" width=\"100%\" style=\"height: 200px;\">\n");
      out.write("                        <div class=\"caption\">\n");
      out.write("                            <h4>");
      out.print(rs2.getString("sub_c_name"));
      out.write("</h4>\n");
      out.write("                            <p>");
      out.print(rs2.getString("description"));
      out.write("</p>\n");
      out.write("                            <!--<p><a href=\"#\" class=\"btn btn-primary\" role=\"button\">Button</a> <a href=\"#\" class=\"btn btn-default\" role=\"button\">Button</a></p>-->\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                </a>\n");
      out.write("\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!--footer-->\n");
      out.write("        <div style=\"background-color: black;height: 8%;padding: 2%\">\n");
      out.write("            <p style=\"margin-left: 30%;color: aliceblue;\">Want to sell your products at Shopohop <a href=\"../merchant/login.jsp\">Click here to become a seller </a></p>\n");
      out.write("        </div> \n");
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
