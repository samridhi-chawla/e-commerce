package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import vmm.cart_item;
import java.util.ArrayList;

public final class view_005fcart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/jquery-3.2.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script>\n");
      out.write("            var xmlhttp = new XMLHttpRequest();\n");
      out.write("            function change_al(pos, qty) {\n");
      out.write("                xmlhttp.open(\"get\", \"../alter_qty_cart?pos=\" + pos + \"&qty=\" + qty, true);\n");
      out.write("                xmlhttp.onreadystatechange = function () {\n");
      out.write("                    window.location.reload();\n");
      out.write("                };\n");
      out.write("                xmlhttp.send();\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../includes/header.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container-fluid\" style=\"margin-top: 3%\">\n");
      out.write("            ");

                ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
                if (al_cart != null) {
            
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-xs-9\">\n");
      out.write("                    <table class=\"table table-responsive table-bordered\">\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Product</th>\n");
      out.write("                            <th>Name</th>\n");
      out.write("                            <th>Description</th>\n");
      out.write("                            <th>Quantity</th>\n");
      out.write("                            <th>Unit Price</th>\n");
      out.write("                            <th>Total Price</th>\n");
      out.write("                            <th>Remove</th>\n");
      out.write("                        </tr>\n");
      out.write("                        ");

                            Double net_amt = 0.0;
                            for (int i = 0; i < al_cart.size(); i++) {
                                cart_item item = al_cart.get(i);
                                String p_id = item.p_id;
                                String p_name = item.p_name;
                                String unit_price = item.p_offer_price;
                                String qty = item.qty;
                                String p_photo = "";
                                String p_descrip = "";
                                Double tot_price = Integer.parseInt(qty) * Double.parseDouble(unit_price);
                                net_amt = net_amt + tot_price;
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs = stmt.executeQuery("select p_photo,p_descrip from products where product_id='" + p_id + "'");
                                if (rs.next()) {
                                    p_photo = rs.getString("p_photo");
                                    p_descrip = rs.getString("p_descrip");
                                }
                        
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td><img src=\".");
      out.print(p_photo);
      out.write("\" class=\"img-responsive\" width=\"150px\" height=\"150px\"/></td>\n");
      out.write("                            <td>");
      out.print(p_name);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p_descrip);
      out.write("</td>\n");
      out.write("                            <td><input type=\"number\" onchange=\"change_al('");
      out.print(i);
      out.write("', this.value)\" value=\"");
      out.print(qty);
      out.write("\" style=\"width: 50px\"/></td>\n");
      out.write("                            <td>");
      out.print(unit_price);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(tot_price);
      out.write("</td>\n");
      out.write("                            <td><a href=\"../remove_from_cart?pos=");
      out.print(i);
      out.write("\"><div class=\"btn btn-primary btn-sm\">Remove From Cart</div></a></td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");

                            }
                            session.setAttribute("net_amt", net_amt);
                        
      out.write("\n");
      out.write("\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-3\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-xs-8\">\n");
      out.write("                            <label style=\"font-size: 20px\">Net Amount Payable:</label>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-xs-4\"><p style=\"font-size: 20px\">");
      out.print(net_amt);
      out.write("</p></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <a href=\"place_order.jsp\"><div class=\"btn btn-primary btn-lg\" style=\"width: 80%;margin: 5%\">Place Order</div></a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");

                } else {
                
      out.write("\n");
      out.write("\n");
      out.write("                <h2>Your Cart is empty</h2>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </div>\n");
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
