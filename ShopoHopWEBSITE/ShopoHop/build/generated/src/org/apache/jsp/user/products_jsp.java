package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class products_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link href=\"../css/range_slider.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/range_slider.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/range_slider2.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <style>\n");
      out.write("            .filter_bar\n");
      out.write("            {\n");
      out.write("                background-color: whitesmoke;\n");
      out.write("                height: 100vh;\n");
      out.write("                margin-left: 15px;\n");
      out.write("            }\n");
      out.write("            .margin{\n");
      out.write("                margin: 10px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #custom-handle {\n");
      out.write("                width: 3em;\n");
      out.write("                height: 1.6em;\n");
      out.write("                top: 50%;\n");
      out.write("                margin-top: -.8em;\n");
      out.write("                text-align: center;\n");
      out.write("                line-height: 1.6em;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var minvalue = 0, maxvalue = 5000,discount=0;\n");
      out.write("\n");
      out.write("            $(function ()\n");
      out.write("            {\n");
      out.write("                $(\"#bt1\").click(function ()\n");
      out.write("                {\n");
      out.write("                    //alert(\"called\");\n");
      out.write("\n");
      out.write("                    $(\"#slider-range\").slider({values: [0, 5000]});\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                $(\"#slider-range\").slider(\n");
      out.write("                        {\n");
      out.write("                            range: true,\n");
      out.write("                            min: 0,\n");
      out.write("                            max: 5000,\n");
      out.write("                            values: [75, 3000],\n");
      out.write("                            slide: function (event, ui)\n");
      out.write("                            {\n");
      out.write("                                minvalue = ui.values[ 0 ];\n");
      out.write("                                maxvalue = ui.values[ 1 ];\n");
      out.write("\n");
      out.write("                                $(\"#amount\").val(minvalue + \" - \" + maxvalue);\n");
      out.write("\n");
      out.write("                            }\n");
      out.write("                        });\n");
      out.write("\n");
      out.write("                $(function () {\n");
      out.write("                    var handle = $(\"#custom-handle\");\n");
      out.write("                    $(\"#slider\").slider({\n");
      out.write("                        create: function () {\n");
      out.write("                            handle.text($(this).slider(\"value\"));\n");
      out.write("                        },\n");
      out.write("                        slide: function (event, ui) {\n");
      out.write("                            handle.text(ui.value);\n");
      out.write("                            discount = ui.value;\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                //to show initial value\n");
      out.write("                //$( \"#amount\" ).val( \"$\" + $( \"#slider-range\" ).slider( \"values\", 0 ) + \" - $\" + $( \"#slider-range\" ).slider( \"values\", 1 ) );\n");
      out.write("            });\n");
      out.write("\n");
      out.write("\n");
      out.write("            function go()\n");
      out.write("            {\n");
      out.write("                alert(minvalue + \" -- \" + maxvalue);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("        <script>\n");
      out.write("            var xmlhttp = new XMLHttpRequest();\n");
      out.write("            function filter(s_cat) {\n");
      out.write("                xmlhttp.open(\"post\", \"products_filter.jsp?s_cat=\" + s_cat + \"&price_below=\" + maxvalue + \"&price_above=\" + minvalue + \"&discount=\" + discount, true);\n");
      out.write("                xmlhttp.onreadystatechange = function () {\n");
      out.write("                    document.getElementById(\"fit_products\").innerHTML = xmlhttp.responseText;\n");
      out.write("                };\n");
      out.write("                xmlhttp.send();\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../includes/header.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        ");

            String sub_category = request.getParameter("s_cat");
            if (sub_category == null) {
                response.sendRedirect("home.jsp");
            }
            String category = request.getParameter("cat");
            String price_below = request.getParameter("price_below");
            String discount = request.getParameter("discount");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from products where sub_category='" + sub_category + "'");

        
      out.write("\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-2 filter_bar\">\n");
      out.write("                <!--filters added here--->\n");
      out.write("                <h4 class=\"text-center\">Filters</h4>\n");
      out.write("                <p>\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-xs-6\">\n");
      out.write("                        <label for=\"slider-range\">Price range:</label>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-xs-6\">\n");
      out.write("                        <input type=\"text\" id=\"amount\" readonly style=\"border:0; color:#f6931f; font-weight:bold;\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                </p>\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div id=\"slider-range\" class=\"margin\"></div>\n");
      out.write("                </div>\n");
      out.write("                <!--                <div class=\"row margin\" >\n");
      out.write("                                    <div class=\"col-xs-6\">\n");
      out.write("                                        <input  type=\"button\" value=\"RESET VALUES\" id=\"bt1\"/>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-xs-6\">\n");
      out.write("                                        <input type=\"button\" value=\"Show\" onclick=\"go()\" />\n");
      out.write("                                    </div>\n");
      out.write("                                </div>-->\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"margin\">\n");
      out.write("                        <label for='discount_range'>Discount percentage</label>\n");
      out.write("                </div>\n");
      out.write("            <div id=\"slider\" class=\"margin\">\n");
      out.write("                <div id=\"custom-handle\" class=\"ui-slider-handle\"></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"btn btn-sm btn-primary\" style=\"margin-left: 40%\" onclick=\"filter('");
      out.print(sub_category);
      out.write("')\">OK</div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-md-8\">\n");
      out.write("            <h2>Products under ");
      out.print(category);
      out.write(" > ");
      out.print(sub_category);
      out.write("</h2>\n");
      out.write("            <div class=\"row\" id=\"fit_products\">\n");
      out.write("\n");
      out.write("                ");

                    while (rs.next()) {
                        String p_name = rs.getString("p_name");
                        String p_photo = rs.getString("p_photo");
                        int product_id = rs.getInt("product_id");
                        String p_mrp = rs.getString("p_mrp");
                        String p_offer_price = rs.getString("p_offer_price");

                
      out.write("\n");
      out.write("                <a href=\"search_product.jsp?p_id=");
      out.print(product_id);
      out.write("\">\n");
      out.write("                    <div class=\"col-xs-3\">\n");
      out.write("                        <div class=\"thumbnail\">\n");
      out.write("                            <img src=\".");
      out.print(p_photo);
      out.write("\">\n");
      out.write("                            <div class='caption'>\n");
      out.write("                                <label class=\"text-center\">");
      out.print(p_name);
      out.write("</label>\n");
      out.write("                                <table class='table'>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>Type</td>\n");
      out.write("                                        <td>");
      out.print(sub_category);
      out.write("</td>\n");
      out.write("                                    </tr>\n");
      out.write("                                    <tr>\n");
      out.write("                                        <td>MRP</td>\n");
      out.write("                                        ");

                                            if (p_mrp == p_offer_price) {
                                        
      out.write("\n");
      out.write("                                        <td>");
      out.print(p_mrp);
      out.write("</td>\n");
      out.write("                                        ");

                                        } else {
                                        
      out.write("\n");
      out.write("                                        <td><span style=\"text-decoration: line-through\">");
      out.print(p_mrp);
      out.write("</span> <span>");
      out.print(p_offer_price);
      out.write("</span></td>\n");
      out.write("                                        ");

                                            }
                                        
      out.write("\n");
      out.write("                                    </tr>\n");
      out.write("                                </table>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </a>\n");
      out.write("                ");
            }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</body>\n");
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
