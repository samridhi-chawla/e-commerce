package org.apache.jsp.merchant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sign_005fup_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Merchant Signup</title>\n");
      out.write("        <link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    ");

        String msg = request.getParameter("msg");
        if (msg != null) {
    
      out.write("\n");
      out.write("    <script>\n");
      out.write("            alert(\"");
      out.print(msg);
      out.write("\");\n");
      out.write("    </script>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function readandpreview(fileobj, imageid)\n");
      out.write("        {\n");
      out.write("            var firstfile = fileobj.files[0];\n");
      out.write("\n");
      out.write("            var reader = new FileReader();\n");
      out.write("\n");
      out.write("            //alert(\"File name: \" + firstfile.name);\n");
      out.write("            // alert(\"File size: \" + firstfile.size);\n");
      out.write("\n");
      out.write("            reader.onload = (function (f)\n");
      out.write("            {\n");
      out.write("                return function read12(e)\n");
      out.write("                {\n");
      out.write("                    document.getElementById(imageid).src = e.target.result;\n");
      out.write("                };\n");
      out.write("            })(firstfile);\n");
      out.write("\n");
      out.write("\n");
      out.write("            reader.readAsDataURL(firstfile);\n");
      out.write("        }\n");
      out.write("        function go()\n");
      out.write("        {   \n");
      out.write("            if (document.getElementById(\"password\").value == document.getElementById(\"cp\").value)\n");
      out.write("            {\n");
      out.write("                return true;\n");
      out.write("            } else\n");
      out.write("            {\n");
      out.write("                document.getElementById(\"cp_error\").innerHTML = \"**Password and Confirm Password does not match\";\n");
      out.write("                document.getElementById(\"cp\").value = \"\";\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("\n");
      out.write("        var xmlhttp = new XMLHttpRequest();\n");
      out.write("        function check_unique()\n");
      out.write("        {   \n");
      out.write("            var email=document.getElementById('email').value;\n");
      out.write("            xmlhttp.open(\"get\", \"../check_unique_merchant?email=\" + email, true);\n");
      out.write("            xmlhttp.onreadystatechange = checked;\n");
      out.write("            xmlhttp.send();\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function checked()\n");
      out.write("        {\n");
      out.write("            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)\n");
      out.write("            {\n");
      out.write("                document.getElementById(\"checked\").innerHTML = xmlhttp.responseText;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("    <body class=\"container\">\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1 class=\"text-center\">Merchant SignUp</h1>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"frame\">\n");
      out.write("            <form action=\"../merchant_signup\" method=\"post\" class=\"form-vertical\" enctype=\"multipart/form-data\" onsubmit=\"return go()\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"name\">Enter your Name</label>\n");
      out.write("                    <input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"email\">Enter a valid email id (this will be used to contact you in the proceedings)</label>\n");
      out.write("                    <label id=\"checked\" style=\"color:red\"></label>\n");
      out.write("                    <input type=\"email\" class=\"form-control\" name=\"email\" id=\"email\" required onblur =\"check_unique()\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"phone\">Enter a 10 digit mobile number</label>\n");
      out.write("                    <input type=\"text\" name=\"phone\"class=\"form-control\" id=\"phone\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"address\">Enter ypur address</label>\n");
      out.write("                    <input type=\"text\" name=\"address\" class=\"form-control\" id=\"address\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"city\">Enter the city</label>\n");
      out.write("                    <input type=\"text\" name=\"city\" id=\"city\"  class=\"form-control\"required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"state\">Enter the state</label>\n");
      out.write("                    <input type=\"text\" name=\"state\" id=\"state\" class=\"form-control\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"bank_acc\">Enter the bank account number(this will be used during transactions)</label>\n");
      out.write("                    <input type=\"text\" name=\"bank_acc\"  class=\"form-control\" id=\"bank_acc\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"ifsc_code\">Enter teh IFSC code of your bank</label>\n");
      out.write("                    <input type=\"text\" name=\"ifsc_code\" class=\"form-control\" id=\"ifsc_code\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"password\">Create a password(it will be used during your login)</label>\n");
      out.write("                    <input type=\"password\" name=\"password\" class=\"form-control\" id=\"password\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"password\">Re-enter the password</label>\n");
      out.write("                    <label id=\"cp_error\" style=\"color:red\"></label>\n");
      out.write("                    <input type=\"password\"  class=\"form-control\" id=\"cp\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"aadhar_no\">Upload the the picture of your Aadhaar Card</label>\n");
      out.write("                    <img src=\"\" id=\"img\" style=\"width: 100px;height: 100px;\">\n");
      out.write("                    <input type=\"file\" name=\"aadhar_no\" id=\"aadhar_no\" class=\"form-control\" onchange=\"readandpreview(this, 'img')\" required/>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"tin_no\">Upload the the picture of your TIN Number</label>\n");
      out.write("                    <img src=\"\" id=\"img1\" style=\"width: 100px;height: 100px;\">\n");
      out.write("                    <input type=\"file\" name=\"tin_no\" id=\"tin_no\" class=\"form-control\" onchange=\"readandpreview(this, 'img1')\" required/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for =\"co_icon\">Upload the the picture of your Company Icon(if exists)</label>\n");
      out.write("                    <img src=\"\" id=\"img2\" style=\"width: 100px;height: 100px;\">\n");
      out.write("                    <input type=\"file\" name=\"co_icon\" id=\"co_icon\" class=\"form-control\" onchange=\"readandpreview(this, 'img2')\"/>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <input type=\"submit\" />\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </form>\n");
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
