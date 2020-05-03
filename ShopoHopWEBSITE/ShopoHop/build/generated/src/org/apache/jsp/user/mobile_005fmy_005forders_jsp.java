package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class mobile_005fmy_005forders_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("            var xmlhttp=new XMLHttpRequest();\n");
      out.write("            function del(order_id){\n");
      out.write("                if(confirm(\"Are you sure you want to cancel this order?\")){\n");
      out.write("                    xmlhttp.open(\"get\",\"cancel_order.jsp?order_id=\"+order_id,true);\n");
      out.write("                    xmlhttp.onreadystatechange=function (){\n");
      out.write("                        window.location.reload();\n");
      out.write("                    };\n");
      out.write("                    xmlhttp.send();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h1 class=\"text-center\">My Orders</h1>\n");
      out.write("\n");
      out.write("            <table class=\"table table-responsive\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>S.No</th>\n");
      out.write("                    <th>Order ID</th>\n");
      out.write("                    <th>Net Amount</th>\n");
      out.write("                    <th>Booked On</th>\n");
      out.write("                    <th>Delivery Date</th>\n");
      out.write("                    <th>Status</th>\n");
      out.write("                    <th>Operations</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

                    String email = request.getParameter("email");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from orders where user_email='" + email + "'");
                    int i = 0;

                    while (rs.next()) {
                        i++;
                        String order_id = rs.getString("order_id");
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(i);
      out.write("</td>\n");
      out.write("                    <td><a href=\"my_order_details.jsp?order_id=");
      out.print(order_id);
      out.write('"');
      out.write('>');
      out.print(order_id);
      out.write("</a></td>\n");
      out.write("                    <td>");
      out.print(rs.getString("net_amt"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getString("booking_date"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getString("delivery_date"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(rs.getString("status"));
      out.write("</td>\n");
      out.write("                    <td><div class=\"btn btn-sm btn-primary\" onclick=\"del('");
      out.print(order_id);
      out.write("')\">Cancel Order</div></td>\n");
      out.write("                </tr>\n");
      out.write("                \n");
      out.write("                <!--order_details modal-->\n");
      out.write("                \n");
      out.write("            \n");
      out.write("                ");
                    }


                
      out.write("\n");
      out.write("        \n");
      out.write("            </table>\n");
      out.write("            ");
                if (i == 0) {
            
      out.write("\n");
      out.write("            <h2>You Don't have any order history</h2>\n");
      out.write("            ");

                    }
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
