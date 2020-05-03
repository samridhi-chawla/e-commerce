package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class change_005fadmin_005fpassword_jsp extends org.apache.jasper.runtime.HttpJspBase
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


String username = (String)session.getAttribute("username");
if(username == null)
{
response.sendRedirect("admin_login.jsp?msg=Please Login TO perform This Action");
}

else
{


      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function go()\n");
      out.write("            {\n");
      out.write("                var pass= document.getElementById(\"pass\").value;\n");
      out.write("                var a= document.getElementById(\"t1\").value;\n");
      out.write("                var b= document.getElementById(\"t2\").value;\n");
      out.write("                if(a!=b)\n");
      out.write("                {\n");
      out.write("                    alert(\"please confirm your new password\");\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                else\n");
      out.write("                {\n");
      out.write("                    return true;\n");
      out.write("                }\n");
      out.write("                                   \n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"change_admin_password2.jsp\" method=\"get\">\n");
      out.write("            Enter old password\n");
      out.write("            <input type=\"password\" id=\"pass\" name=\"old\" required /><br>         \n");
      out.write("            Enter new password\n");
      out.write("            <input type=\"password\" id=\"t1\" name=\"new\" required /><br>\n");
      out.write("            confirm password\n");
      out.write("            <input type=\"password\" id=\"t2\" required /><br>\n");
      out.write("            <input type=\"submit\" value=\"submit\" onclick=\"return go()\" /><br>\n");
      out.write("               ");

            String msg = request.getParameter("msg");
            if(msg!=null)
            {
             
      out.write("\n");
      out.write("                         <label style=\"color: green\">");
      out.print( msg );
      out.write("</label>\n");
      out.write("             ");

            }
   
            
      out.write("\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
