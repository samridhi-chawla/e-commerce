package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class manage_005fsellers_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>Manage Sellers</title>\n");
      out.write("    </head>\n");
      out.write("    <body  class=\"container\">\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1 class=\"text-center\">Manage Sellers</h1>\n");
      out.write("        </div>\n");
      out.write("        ");

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
            java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from merchant where status='pending'");
        
      out.write("\n");
      out.write("        <h3>Sellers with status Pending</h3>\n");
      out.write("        <table class=\"table table-bordered table-responsive table-striped\">\n");
      out.write("            <tr>\n");
      out.write("                <th>S.No</th>\n");
      out.write("                <th>Email</th>\n");
      out.write("                <th>Name</th>\n");
      out.write("                <th>Phone</th>\n");
      out.write("                <th>Address</th>\n");
      out.write("                <th>City</th>\n");
      out.write("                <th>State</th>\n");
      out.write("                <th>Aadhar Number</th>\n");
      out.write("                <th>Tin Number</th>\n");
      out.write("                <th>Company Icon</th>\n");
      out.write("                <th>Bank Acc.No</th>\n");
      out.write("                <th>IFSC Code</th>\n");
      out.write("                <th>Status</th>\n");
      out.write("                <th>Operation</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

                for (int i = 1; rs.next(); i++) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(i);
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("email"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("name"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("phone"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("address"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("city"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("state"));
      out.write("</td>\n");
      out.write("                <td><img src=\"");
      out.print(rs.getString("aadhar_no"));
      out.write("\" width=\"100px\" height=\"100px\"></td>\n");
      out.write("                <td><img src=\"");
      out.print(rs.getString("tin_no"));
      out.write("\" width=\"100px\" height=\"100px\" /></td>\n");
      out.write("                <td><img src=\"");
      out.print(rs.getString("co_icon"));
      out.write("\" width=\"100px\" height=\"100px\" /></td>\n");
      out.write("                <td>");
      out.print(rs.getString("bank_acc"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("ifsc_code"));
      out.write("</td>\n");
      out.write("                <td>");
      out.print(rs.getString("status"));
      out.write("</td>\n");
      out.write("                <td><input type=\"button\" class=\"btn btn-primary\" value=\"Approve\"/></td>\n");
      out.write("            </tr>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        ");

            ResultSet rs1 = stmt.executeQuery("select * from merchant  where status='active'");
        
      out.write("\n");
      out.write("        <h3>Sellers with status Active</h3>\n");
      out.write("        <table class=\"table table-bordered table-responsive table-striped\">\n");
      out.write("            <tr>\n");
      out.write("                <th>S.No</th>\n");
      out.write("                <th>Email</th>\n");
      out.write("                <th>Name</th>\n");
      out.write("                <th>Phone</th>\n");
      out.write("                <th>Address</th>\n");
      out.write("                <th>City</th>\n");
      out.write("                <th>State</th>\n");
      out.write("                <th>Aadhar Number</th>\n");
      out.write("                <th>Tin Number</th>\n");
      out.write("                <th>Company Icon</th>\n");
      out.write("                <th>Bank Acc.No</th>\n");
      out.write("                <th>IFSC Code</th>\n");
      out.write("                <th>Status</th>\n");
      out.write("                <th>Operation</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

                for (int j = 1; rs1.next(); j++) {
            
      out.write("\n");
      out.write("            <td>");
      out.print(j);
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("email"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("name"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("phone"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("address"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("city"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("state"));
      out.write("</td>\n");
      out.write("            <td><img src=\"");
      out.print(rs1.getString("aadhar_no"));
      out.write("\" width=\"100px\" height=\"100px\"></td>\n");
      out.write("            <td><img src=\"");
      out.print(rs1.getString("tin_no"));
      out.write("\" width=\"100px\" height=\"100px\" /></td>\n");
      out.write("            <td><img src=\"");
      out.print(rs1.getString("co_icon"));
      out.write("\" width=\"100px\" height=\"100px\" /></td>\n");
      out.write("            <td>");
      out.print(rs1.getString("bank_acc"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("ifsc_code"));
      out.write("</td>\n");
      out.write("            <td>");
      out.print(rs1.getString("status"));
      out.write("</td>\n");
      out.write("            <td><input type=\"button\" class=\"btn btn-primary\" value=\"Deactivate\"/></td>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("        </table>\n");
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
