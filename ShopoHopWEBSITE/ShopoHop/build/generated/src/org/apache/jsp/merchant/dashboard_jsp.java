package org.apache.jsp.merchant;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link href=\"../css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"../css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/jquery-3.2.0.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"../js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>Merchant Dashboard</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "merchant_nav.jsp", out, false);
      out.write("\n");
      out.write("        <div class='container'>\n");
      out.write("            <script>\n");
      out.write("                var xmlhttp = new XMLHttpRequest();\n");
      out.write("                function get_subcategory(category)\n");
      out.write("                {\n");
      out.write("                    xmlhttp.open(\"get\", \"../get_subcategory?category=\" + category, true);\n");
      out.write("                    xmlhttp.onreadystatechange = set_subcategory;\n");
      out.write("                    xmlhttp.send();\n");
      out.write("                }\n");
      out.write("                function set_subcategory()\n");
      out.write("                {\n");
      out.write("                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)\n");
      out.write("                    {\n");
      out.write("                        var res = xmlhttp.responseText.split(\",\");\n");
      out.write("                        var p_id = res[0];\n");
      out.write("                        document.getElementById(\"sub_category\").innerHTML = res[1];\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                function get_subcategory_of_edit(category, p_id)\n");
      out.write("                {\n");
      out.write("                    alert(\"chala\");\n");
      out.write("                    xmlhttp.open(\"get\", \"../get_subcategory?category=\" + category + \"&p_id=\" + p_id, true);\n");
      out.write("                    xmlhttp.onreadystatechange = set_subcategory_of_edit;\n");
      out.write("                    xmlhttp.send();\n");
      out.write("                }\n");
      out.write("                function set_subcategory_of_edit()\n");
      out.write("                {\n");
      out.write("                    alert(xmlhttp.responseText);\n");
      out.write("                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)\n");
      out.write("                    {\n");
      out.write("                        var res = xmlhttp.responseText.split(\",\");\n");
      out.write("                        var p_id = res[0];\n");
      out.write("                        document.getElementById(\"edit_sub_category\" + p_id).innerHTML = res[1];\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("                function readandpreview(fileobj, imageid)\n");
      out.write("                {\n");
      out.write("                    var firstfile = fileobj.files[0];\n");
      out.write("\n");
      out.write("                    var reader = new FileReader();\n");
      out.write("\n");
      out.write("                    //alert(\"File name: \" + firstfile.name);\n");
      out.write("                    // alert(\"File size: \" + firstfile.size);\n");
      out.write("\n");
      out.write("                    reader.onload = (function (f)\n");
      out.write("                    {\n");
      out.write("                        return function read12(e)\n");
      out.write("                        {\n");
      out.write("                            document.getElementById(imageid).src = e.target.result;\n");
      out.write("                        };\n");
      out.write("                    })(firstfile);\n");
      out.write("\n");
      out.write("\n");
      out.write("                    reader.readAsDataURL(firstfile);\n");
      out.write("                }\n");
      out.write("            </script>\n");
      out.write("            <script>\n");
      out.write("                function del(product_id)\n");
      out.write("                {\n");
      out.write("                    if (confirm(\"Are you sure you want to remove this product from selling\"))\n");
      out.write("                    {\n");
      out.write("                        window.location.href = \"delete.jsp?product_id=\" + product_id;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            </script>\n");
      out.write("            <script>\n");
      out.write("                function change_image(p_id)\n");
      out.write("                {\n");
      out.write("                    document.getElementById(\"file_of_photo\" + p_id).style.display = \"inherit\";\n");
      out.write("                    document.getElementById(\"file_of_photo\" + p_id).required = \"required\";\n");
      out.write("                    document.getElementById(\"edit_img\" + p_id).src = \"\";\n");
      out.write("                }\n");
      out.write("            </script>\n");
      out.write("\n");
      out.write("            ");

                String email = (String) session.getAttribute("email");
                if (email == null) {
                    response.sendRedirect("login.jsp");
                }
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs1 = stmt1.executeQuery("select distinct sub_category from products where seller_email='" + email + "'");

                while (rs1.next()) {
                    String sub_category = rs1.getString("sub_category");
            
      out.write("\n");
      out.write("            <div class=\"panel panel-info\">\n");
      out.write("                <div class=\"panel-heading\">My Products in Sub Category : ");
      out.print(sub_category);
      out.write("</div>\n");
      out.write("                <div class=\"panel-body\">\n");
      out.write("                    ");

                        Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs2 = stmt2.executeQuery("select * from products where seller_email='" + email + "' and sub_category='" + sub_category + "'");
                        while (rs2.next()) {
                            String product_id = rs2.getString("product_id");
                            String p_name = rs2.getString("p_name");
                            String p_photo = rs2.getString("p_photo");
                            String category = rs2.getString("category");
                            String p_mrp = rs2.getString("p_mrp");
                            String p_offer_price = rs2.getString("p_offer_price");
                            String p_descrip = rs2.getString("p_descrip");
                    
      out.write("\n");
      out.write("                    <div class=\"col-xs-3\">\n");
      out.write("                        <img src=\".");
      out.print(p_photo);
      out.write("\" class=\"img-thumbnail img-responsive img-rounded\" width=\"100%\">\n");
      out.write("                        <div class=\"text-center\">");
      out.print(p_name);
      out.write("</div>\n");
      out.write("                        <div class=\"text-center\">Product ID: ");
      out.print(product_id);
      out.write("</div>\n");
      out.write("                        <div class=\"btn-group btn-group-justified\" role=\"group\" aria-label=\"...\">\n");
      out.write("                            <div class=\"btn-group\" role=\"group\">\n");
      out.write("                                <button type=\"button\" class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#edit_prod_modal");
      out.print(product_id);
      out.write("\">Edit</button>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"btn-group\" role=\"group\">\n");
      out.write("                                <button type=\"button\" class=\"btn btn-default\" id=\"");
      out.print(product_id);
      out.write("\" onclick=\"del(this.id)\">Delete</button>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"btn btn-sm btn-primary\" data-toggle=\"modal\" data-target=\"#add_photo_modal");
      out.print(product_id);
      out.write("\" style=\"margin: 1% 10%\">Add Product Photos</div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <!--- EDIT PRODUCT MODAL STARTS------>\n");
      out.write("                    <div id=\"edit_prod_modal");
      out.print(product_id);
      out.write("\" class=\"modal fade\" role=\"dialog\">\n");
      out.write("                        <div class=\"modal-dialog\">\n");
      out.write("\n");
      out.write("                            <!-- Modal content-->\n");
      out.write("                            <div class=\"modal-content\">\n");
      out.write("                                <div class=\"modal-header\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("                                    <h4 class=\"modal-title\">Edit Details of ");
      out.print(p_name);
      out.write("</h4>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-body\">\n");
      out.write("                                    <form class=\"form-vertical\" action=\"../edit_product?p_id=");
      out.print(product_id);
      out.write("\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"p_name\">Name of the Product</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"p_name\" name=\"p_name\" value=\"");
      out.print(p_name);
      out.write("\" required />\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for =\"category\">Choose the Category</label>\n");
      out.write("                                            <select class=\"form-control\" name=\"category\" id=\"category\" onchange=\"get_subcategory_of_edit(this.value, '");
      out.print(product_id);
      out.write("')\" required>\n");
      out.write("                                                <option>");
      out.print(category);
      out.write("</option>\n");
      out.write("                                                ");

                                                    Statement stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                                    ResultSet rs4 = stmt4.executeQuery("select distinct c_name from categories where c_name !='" + category + "'");

                                                    while (rs4.next()) {
                                                
      out.write("\n");
      out.write("                                                <option>");
      out.print(rs4.getString("c_name"));
      out.write("</option>\n");
      out.write("                                                ");


                                                    }
                                                    stmt4.close();
                                                
      out.write("\n");
      out.write("                                            </select>\n");
      out.write("                                        </div>\n");
      out.write("\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"edit_sub_category\">Sub Category</label>\n");
      out.write("                                            <select class=\"form-control\" id=\"edit_sub_category");
      out.print(product_id);
      out.write("\" name=\"sub_category\" required >\n");
      out.write("                                                <option>");
      out.print(sub_category);
      out.write("</option>\n");
      out.write("                                            </select>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"p_photo\">Primary photo that will be visible with the product</label><br>\n");
      out.write("                                            <img src=\".");
      out.print(p_photo);
      out.write("\" id=\"edit_img");
      out.print(product_id);
      out.write("\" width=\"100px\" height=\"100px\"/><br><br>\n");
      out.write("                                            <input type=\"radio\" name=\"img_changed\" id=\"r1");
      out.print(product_id);
      out.write("\" value=\"false\" class=\"radio-inline\"  checked>\n");
      out.write("                                            <label for=\"r1");
      out.print(product_id);
      out.write("\">Don't want to change the primary photo</label><br>\n");
      out.write("                                            <input type=\"radio\" name=\"img_changed\" id=\"r2");
      out.print(product_id);
      out.write("\" value=\"true\" class=\"radio-inline\" onchange=\"change_image('");
      out.print(product_id);
      out.write("')\">\n");
      out.write("                                            <label for=\"r2");
      out.print(product_id);
      out.write("\">You Want to change the primary photo</label>\n");
      out.write("                                            <input type=\"file\" id=\"file_of_photo");
      out.print(product_id);
      out.write("\" name=\"p_photo\" onchange=\"readandpreview(this, 'edit_img");
      out.print(product_id);
      out.write("')\" style=\"display: none\"  />\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"p_descrip\">Description of the product</label>\n");
      out.write("                                            <textarea  class=\"form-control\" id=\"p_descrip\" name=\"p_descrip\" />");
      out.print(p_descrip);
      out.write("\n");
      out.write("                                            </textarea>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"p_mrp\">MRP of the Product</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"p_mrp\" name=\"p_mrp\" value=\"");
      out.print(p_mrp);
      out.write("\" required/>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div class=\"form-group\">\n");
      out.write("                                            <label for=\"p_offer_price\">Offer Price(The price at which you want to sell the product)</label>\n");
      out.write("                                            <input type=\"text\" class=\"form-control\" id=\"p_offer_price\" name=\"p_offer_price\" value=\"");
      out.print(p_offer_price);
      out.write("\" />\n");
      out.write("                                        </div>\n");
      out.write("                                        <input class=\"btn btn-lg btn-primary center-block\" type=\"submit\" value=\"Update\" />\n");
      out.write("                                    </form>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <!---- EDIT PRODUCT MODAL ENDS------->\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <!-----ADD PHOTOS MODAL STARTS----->\n");
      out.write("                    <div id=\"add_photo_modal");
      out.print(product_id);
      out.write("\" class=\"modal fade\" role=\"dialog\">\n");
      out.write("                        <div class=\"modal-dialog modal-lg\">\n");
      out.write("\n");
      out.write("                            <!-- Modal content-->\n");
      out.write("                            <div class=\"modal-content\">\n");
      out.write("                                <div class=\"modal-header\">\n");
      out.write("                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("                                    <h4 class=\"modal-title text-center\">Photos of product ");
      out.print(p_name);
      out.write("</h4>\n");
      out.write("                                </div> \n");
      out.write("                                <div class=\"modal-body\">\n");
      out.write("                                    <div class=\"caption\">\n");
      out.write("                                        <h4>Primary photo</h4>\n");
      out.write("                                    </div>\n");
      out.write("                                    <img src=\".");
      out.print(p_photo);
      out.write("\" class=\"img-responsive img-rounded img-thumbnail\" width=\"200px\" height=\"200px\"/>\n");
      out.write("                                    <div class=\"panel panel-default\">\n");
      out.write("                                        <div class=\"panel-heading\">Additional Photos</div>\n");
      out.write("                                        <div class=\"panel-body\">\n");
      out.write("                                            <div class=\"row\">\n");
      out.write("                                                ");

                                                    Statement stmt5 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                                    ResultSet rs5 = stmt5.executeQuery("select * from photo_gallery where product_id='" + product_id + "'");
                                                    while (rs5.next()) {
                                                
      out.write("\n");
      out.write("\n");
      out.write("                                                <div class=\"col-xs-3\">\n");
      out.write("                                                    <div class=\"thumbnail\">\n");
      out.write("                                                        <img src=\".");
      out.print(rs5.getString("path"));
      out.write("\" alt=\"...\">\n");
      out.write("                                                        <div class=\"caption\">\n");
      out.write("                                                            <h3>");
      out.print(rs5.getString("caption"));
      out.write("</h3>\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>\n");
      out.write("\n");
      out.write("                                                ");


                                                    }
                                                    stmt5.close();
                                                
      out.write(" \n");
      out.write("                                            </div>\n");
      out.write("                                            <div class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#add_photo_form");
      out.print(product_id);
      out.write("\"><span class=\"glyphicon glyphicon-plus-sign\"></span> Add More Photos </div>\n");
      out.write("                                            <div id=\"add_photo_form");
      out.print(product_id);
      out.write("\" class=\"collapsing\">\n");
      out.write("                                                <form class=\"form-vertical\" action=\"../add_product_gallery?p_id=");
      out.print(product_id);
      out.write("\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("                                                    <div class=\"form-group\">\n");
      out.write("                                                        <label for=\"gallery_path\">Select photo</label>\n");
      out.write("                                                        <img src=\"\" width=\"100px\" height=\"100px\" id=\"gallery_img");
      out.print(product_id);
      out.write("\"/>\n");
      out.write("                                                        <input type=\"file\" id=\"gallery_path\" name=\"g_path\" onchange=\"readandpreview(this, 'gallery_img");
      out.print(product_id);
      out.write("')\" required >\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <div class=\"form-group\">\n");
      out.write("                                                        <label for=\"gallery_caption\">Write a caption for the image(eg. front view, back view etc)</label>\n");
      out.write("                                                        <input type=\"text\" name=\"g_caption\" id=\"gallery_caption\" class=\"form-control\">\n");
      out.write("                                                    </div>\n");
      out.write("                                                    <input type=\"submit\" class=\"btn btn-success\" style=\"margin-left: 40%\" value=\"ADD\">\n");
      out.write("                                                </form>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-----ADD PHOTOS MODAL ENDS------>\n");
      out.write("\n");
      out.write("                    ");

                        }  //while of rs2 ends
                        stmt2.close();
                    
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            ");

                }    //while of rs1 ends ----of subcategory
                stmt1.close();
            
      out.write("\n");
      out.write("\n");
      out.write("            <a name=\"add_prod_btn\">\n");
      out.write("                <div type=\"button\"  class=\"btn btn-lg btn-primary\" data-toggle=\"collapse\" data-target=\"#add_prod\" style=\"margin-top: 1%\">\n");
      out.write("                    <span class=\"glyphicon glyphicon-plus-sign\"></span> Add Products\n");
      out.write("                </div>\n");
      out.write("            </a>\n");
      out.write("            <div class=\"collapsing\" id=\"add_prod\" style=\"width: 60%\">\n");
      out.write("                <form class=\"form-vertical\" action=\"../add_products\" method=\"post\" enctype=\"multipart/form-data\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for =\"category\">Choose the Category</label>\n");
      out.write("                        <select class=\"form-control\" name=\"category\" onchange=\"get_subcategory(this.value)\" required>\n");
      out.write("                            <option>select the category</option>\n");
      out.write("                            ");

                                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs = stmt.executeQuery("select distinct c_name from categories");

                                while (rs.next()) {
                            
      out.write("\n");
      out.write("                            <option>");
      out.print(rs.getString("c_name"));
      out.write("</option>\n");
      out.write("                            ");


                                }
                            
      out.write("\n");
      out.write("                        </select>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"sub_category\">Choose the Sub Category</label>\n");
      out.write("                        <select class=\"form-control\" id=\"sub_category\" name=\"sub_category\" required ></select>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"p_name\">Enter the Name of the Product</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"p_name\" name=\"p_name\"required />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"p_photo\">Select the Primary photo that will be visible with the product</label><br>\n");
      out.write("                        <img src=\"\" id=\"img\" width=\"100px\" height=\"100px\"/>\n");
      out.write("                        <input type=\"file\" id=\"p_photo\" name=\"p_photo\" onchange=\"readandpreview(this, 'img')\" />\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"p_descrip\">Write the Description of the product</label>\n");
      out.write("                        <textarea  class=\"form-control\" id=\"p_descrip\" name=\"p_descrip\" />\n");
      out.write("                        </textarea>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"p_mrp\">Enter the MRP of the Product</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"p_mrp\" name=\"p_mrp\" required/>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"p_offer_price\">Enter the price at which you want to sell the product</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"p_offer_price\" name=\"p_offer_price\" />\n");
      out.write("                    </div>\n");
      out.write("                    <input class=\"btn btn-lg btn-primary center-block\" type=\"submit\" value=\"Add\" />\n");
      out.write("                </form>\n");
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
