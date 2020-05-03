package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class slider_005fdemo_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!doctype html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <title>jQuery UI Slider - Range slider</title>\n");
      out.write("  \n");
      out.write("<!--  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\n");
      out.write("  \n");
      out.write("  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n");
      out.write("  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n");
      out.write("  -->\n");
      out.write("  <link href=\"../css/range_slider.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("  <script src=\"../js/range_slider.js\" type=\"text/javascript\"></script>\n");
      out.write("<!--  <script src=\"../js/range_slider_mob.js\" type=\"text/javascript\"></script>-->\n");
      out.write("<script src=\"../js/range_slider2.js\" type=\"text/javascript\"></script>\n");
      out.write("  <script>\n");
      out.write("   var minvalue,maxvalue;\n");
      out.write("    \n");
      out.write("    $( function() \n");
      out.write("    {\n");
      out.write("      $(\"#bt1\").click(function ()\n");
      out.write("      {\n");
      out.write("          //alert(\"called\");\n");
      out.write("          \n");
      out.write("          $(\"#slider-range\").slider({values: [0, 500 ]});\n");
      out.write("      });\n");
      out.write("              \n");
      out.write("      $( \"#slider-range\" ).slider(\n");
      out.write("      {\n");
      out.write("      range: true,\n");
      out.write("      min: 0,\n");
      out.write("      max: 500,\n");
      out.write("      values: [ 75, 300 ],\n");
      out.write("      \n");
      out.write("      slide: function( event, ui ) \n");
      out.write("      {\n");
      out.write("        minvalue=ui.values[ 0 ];\n");
      out.write("        maxvalue=ui.values[ 1 ];\n");
      out.write("          \n");
      out.write("        $( \"#amount\" ).val(minvalue + \" - \" + maxvalue );\n");
      out.write("        \n");
      out.write("      }\n");
      out.write("      });\n");
      out.write("    \n");
      out.write("    //to show initial value\n");
      out.write("    //$( \"#amount\" ).val( \"$\" + $( \"#slider-range\" ).slider( \"values\", 0 ) + \" - $\" + $( \"#slider-range\" ).slider( \"values\", 1 ) );\n");
      out.write("    } );\n");
      out.write("\n");
      out.write("    \n");
      out.write("    function go()\n");
      out.write("    {\n");
      out.write("       alert(minvalue+\" -- \"+maxvalue);    \n");
      out.write("    }\n");
      out.write("    \n");
      out.write("  </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write(" \n");
      out.write("    \n");
      out.write("<p>\n");
      out.write("  <label for=\"amount\">Price range:</label>\n");
      out.write("  <input type=\"text\" id=\"amount\" readonly style=\"border:0; color:#f6931f; font-weight:bold;\">\n");
      out.write("</p>\n");
      out.write(" \n");
      out.write("<div id=\"slider-range\"></div>\n");
      out.write("\n");
      out.write("<input type=\"button\" value=\"RESET VALUES\" id=\"bt1\"  />\n");
      out.write("\n");
      out.write("<input type=\"button\" value=\"Show\" onclick=\"go()\" />\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
