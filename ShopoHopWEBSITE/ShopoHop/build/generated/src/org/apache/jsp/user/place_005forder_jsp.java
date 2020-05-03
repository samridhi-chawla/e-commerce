package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class place_005forder_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <style>\n");
      out.write("            .heading{\n");
      out.write("                font-size: 18px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <script>\n");
      out.write("            var xmlhttp=new XMLHttpRequest();\n");
      out.write("            function det_add_id(add_id){\n");
      out.write("                xmlhttp.open(\"get\",\"set_add_id_session.jsp?add_id=\"+add_id,true);\n");
      out.write("                xmlhttp.onreadystatechange=function (){\n");
      out.write("                    \n");
      out.write("                };\n");
      out.write("                xmlhttp.send();\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../includes/header.jsp", out, false);
      out.write("\n");
      out.write("        ");

            String email = (String) session.getAttribute("email");
            if (email == null) {
        
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $('#LoginModal').modal({\n");
      out.write("                show: 'false',\n");
      out.write("                backdrop: 'static',\n");
      out.write("                keyboard: false\n");
      out.write("            });\n");
      out.write("            document.getElementById(\"LoginModal_CloseBtn1\").disabled = 'disabled';\n");
      out.write("            document.getElementById(\"LoginModal_CloseBtn2\").disabled = 'disabled';\n");
      out.write("        </script>\n");
      out.write("        ");

            }
            String net_amt=(String)session.getAttribute("net_amt");
            String username = "";
            String phone_no = "";
            String address = "";
            String city = "";
            String state = "";
            String pincode = "";
            if (email != null) {

                String d = "2017-04-04";
                Date date = Date.valueOf(d);
                long date_milis = date.getTime();

                if (System.currentTimeMillis() > date_milis) {
                    System.out.println("validity over");
                }

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select name,phone_no from users where email='" + email + "'");
                if (rs.next()) {
                    username = rs.getString("name");
                    phone_no = rs.getString("phone_no");
                }
        
      out.write("\n");
      out.write("        <div class=\"container\" style=\"margin-top: 3%\">\n");
      out.write("            <h1 class=\"text-center\">Place Order</h1>\n");
      out.write("            <div class=\"col-xs-9\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label class=\"heading\" for=\"username\">Username</label>\n");
      out.write("                    <input type=\"text\" id=\"username\" value=\"");
      out.print(username);
      out.write("\" readonly class=\"form-control\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"username\" class=\"heading\">Email</label>\n");
      out.write("                    <input type=\"text\" id=\"username\" value=\"");
      out.print(email);
      out.write("\" readonly class=\"form-control\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"username\" class=\"heading\">Phone No.</label>\n");
      out.write("                    <input type=\"text\" id=\"username\" value=\"");
      out.print(phone_no);
      out.write("\" readonly class=\"form-control\"/>\n");
      out.write("                </div>\n");
      out.write("                <label class=\"heading\">Select the Delivery Address</label><br>\n");
      out.write("                ");

                    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1 = stmt1.executeQuery("select * from user_addresses where email='" + email + "' ");

                    while (rs1.next()) {
                        int add_id = rs1.getInt("add_id");
                        address = rs1.getString("address");
                        city = rs1.getString("city");
                        state = rs1.getString("state");
                        pincode = rs1.getString("pincode");

                
      out.write("\n");
      out.write("                <label>\n");
      out.write("                    <input type=\"radio\" name=\"add_id\" onclick=\"set_add_id(this.value)\" value=\"");
      out.print(add_id);
      out.write("\"/>\n");
      out.write("                    <p>");
      out.print(address);
      out.write("</p>\n");
      out.write("                    <p>");
      out.print(city);
      out.write("</p>\n");
      out.write("                    <p>");
      out.print(state);
      out.write("</p>\n");
      out.write("                    <p>");
      out.print(pincode);
      out.write("</p>\n");
      out.write("                </label>\n");
      out.write("                <br>\n");
      out.write("                ");
        }
                    }
                
      out.write("\n");
      out.write("\n");
      out.write("                <div class=\"btn btn-lg btn-primary\" data-toggle=\"collapse\" data-target=\"#add_new_address\"><span class=\"glyphicon glyphicon-plus-sign\"> ADD New Address</span></div>\n");
      out.write("                <div id=\"add_new_address\" class=\"collapsing\">\n");
      out.write("                    <form class=\"form-vertical\" action=\"add_new_address.jsp?email=");
      out.print(email);
      out.write("\" method=\"post\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"address\">Enter your address</label>\n");
      out.write("                            <input type=\"text\" id=\"address\" name=\"address\" value=\"");
      out.print(address);
      out.write("\" class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"city\">Enter the city</label>\n");
      out.write("                            <input type=\"text\" id=\"city\" name=\"city\" value=\"");
      out.print(city);
      out.write("\" class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"state\">Enter the State</label>\n");
      out.write("                            <input type=\"text\" id=\"state\" name=\"state\" value=\"");
      out.print(state);
      out.write("\" class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"pincode\">Enter the Pincode</label>\n");
      out.write("                            <input type=\"number\" id=\"pincode\" name=\"pincode\" value=\"");
      out.print(pincode);
      out.write("\" class=\"form-control\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"submit\"  class=\"btn-lg btn btn-primary\" style=\"margin-left: 40%\" value=\"Save\"/>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label class=\"heading\">Select the Mode of payment</label><br>\n");
      out.write("                    <label for =\"cod\"><input type=\"radio\" value=\"cod\" id=\"cod\" name=\"mode\"/>Cash On Delivery</label><br>\n");
      out.write("                    <label for =\"digital\"><input type=\"radio\" value=\"digital\" id=\"digital\" name=\"mode\"/>Digital Mode of Payment</label>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-xs-3\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-xs-8\">\n");
      out.write("                        <label style=\"font-size: 20px\">Net Amount Payable:</label>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-xs-4\"><p style=\"font-size: 20px\">");
      out.print(net_amt);
      out.write("</p></div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <a href=\"view_cart.jsp\"><div class=\"btn btn-primary btn-lg\" style=\"width: 80%;margin: 5%\">Review Order</div></a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
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
