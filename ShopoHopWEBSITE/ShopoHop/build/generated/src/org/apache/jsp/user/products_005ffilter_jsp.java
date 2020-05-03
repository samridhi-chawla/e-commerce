package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class products_005ffilter_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

    String sub_category = request.getParameter("s_cat");
    String price_below = request.getParameter("price_below");
    String discount = request.getParameter("discount");
    System.out.println(sub_category+"sub_cat , pricebelow "+price_below+" discount "+discount );
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //since value of price_below and discount will always be alvailable(since value attribute is set of the range)
    ResultSet rs = stmt.executeQuery("select * from products where p_offer_price<=2000 and ((p_mrp-p_offer_price)/p_mrp)*100>=0;");
    // select * from products where sub_category='" + sub_category + "' and p_offer_price<='" + price_below + "' and (p_mrp-p_offer_price)*100/p_mrp>='" + discount + "'
    int flag = 0;
    while (rs.next()) {
        flag++;
        String p_name = rs.getString("p_name");
        String p_photo = rs.getString("p_photo");
        int product_id = rs.getInt("product_id");
        String p_mrp = rs.getString("p_mrp");
        String p_offer_price = rs.getString("p_offer_price");

      out.write("\n");
      out.write("\n");
      out.write("<a href=\"search_product.jsp?p_id=");
      out.print(product_id);
      out.write("\">\n");
      out.write("    <div class=\"col-xs-3\">\n");
      out.write("        <div class=\"thumbnail\">\n");
      out.write("            <img src=\".");
      out.print(p_photo);
      out.write("\">\n");
      out.write("            <div class='caption'>\n");
      out.write("                <label class=\"text-center\">");
      out.print(p_name);
      out.write("</label>\n");
      out.write("                <table class='table'>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Type</td>\n");
      out.write("                        <td>");
      out.print(sub_category);
      out.write("</td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>MRP</td>\n");
      out.write("                        ");

                            if (p_mrp == p_offer_price) {
                        
      out.write("\n");
      out.write("                        <td>");
      out.print(p_mrp);
      out.write("</td>\n");
      out.write("                        ");

                        } else {
                        
      out.write("\n");
      out.write("                        <td><span style=\"text-decoration: line-through\">");
      out.print(p_mrp);
      out.write("</span> <span>");
      out.print(p_offer_price);
      out.write("</span></td>\n");
      out.write("                        ");

                            }
                        
      out.write("\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</a>\n");

    }
if(flag==0){
out.println("<label>No Products Found</label>");
}

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
