package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005fdashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body class=\"container\">\n");
      out.write("        ");

            String username = (String) session.getAttribute("username");
            if (username == null) {
                response.sendRedirect("admin_login.jsp");
            }
        
      out.write("\n");
      out.write("        <h1>Welcome ");
      out.print(username);
      out.write("</h1><br>\n");
      out.write("        <div class=\"\" style=\"size: 1em; margin: 1%\">\n");
      out.write("            <span class=\"glyphicon glyphicon-arrow-right\"></span>\n");
      out.write("            <a href=\"add_category.jsp\" >add or manage your category</a>[for eg:men,women,child.......]\n");
      out.write("        </div>\n");
      out.write("        <div class=\"\" style=\"size: 1em; margin: 1%\">\n");
      out.write("            <span class=\"glyphicon glyphicon-arrow-right\"></span>\n");
      out.write("        <a href=\"add_sub_category.jsp\" >add or manage your sub category</a>[for eg:men shirt,men jean,women top.....]\n");
      out.write("        </div>\n");
      out.write("        <div class=\"\" style=\"size: 1em; margin: 1%\">\n");
      out.write("            <span class=\"glyphicon glyphicon-arrow-right\"></span>\n");
      out.write("        <a href=\"manage_sellers.jsp\" >Manage Sellers</a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"\" style=\"size: 1em; margin: 1%\">\n");
      out.write("            <span class=\"glyphicon glyphicon-arrow-right\"></span>\n");
      out.write("        <a href=\"manage_adbanner.jsp\" >Manage AD Banners</a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"\" style=\"size: 1em; margin: 1%\">\n");
      out.write("            <span class=\"glyphicon glyphicon-arrow-right\"></span>\n");
      out.write("        <a href=\"change_admin_password.jsp\" >change password</a><br>\n");
      out.write("        </div>\n");
      out.write("        ");

            String msg = request.getParameter("msg");
            if (msg != null) {
        
      out.write("\n");
      out.write("        <label style=\"color: green\">");
      out.print( msg);
      out.write("</label>\n");
      out.write("        ");

            }

        
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
