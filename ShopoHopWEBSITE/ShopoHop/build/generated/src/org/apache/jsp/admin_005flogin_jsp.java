package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005flogin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body class='container'>\n");
      out.write("        <div class=\"frame\">\n");
      out.write("            <form action=\"check_admin_login.jsp\" class=\"form-vertical\">\n");
      out.write("                <h1>Admin Login</h1>\n");
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
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"username\"> Enter Username</label>\n");
      out.write("                <input  type=\"text\" name=\"username\" class=\"form-control\" required /><br>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"password\">\n");
      out.write("                        Enter Password</label>\n");
      out.write("                <input type=\"password\" class=\"form-control\" name=\"pass\" required /><br>\n");
      out.write("                </div>\n");
      out.write("                <input class=\"btn btn-primary btn-lg text-center\" type=\"submit\" value=\"Login\" /><br>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                <a href=\"recover_admin_password.jsp\" target=\"_blank\">Recover password</a><br>\n");
      out.write("\n");
      out.write("\n");
      out.write("            </form>    \n");
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
