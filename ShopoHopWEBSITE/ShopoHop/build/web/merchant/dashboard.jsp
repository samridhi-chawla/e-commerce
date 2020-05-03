<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-3.2.0.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <title>Merchant Dashboard</title>
    </head>
    <body>
        <jsp:include page="merchant_nav.jsp"/>
        <div class='container'>
            <script>
                var xmlhttp = new XMLHttpRequest();
                function get_subcategory(category)
                {
                    xmlhttp.open("get", "../get_subcategory?category=" + category, true);
                    xmlhttp.onreadystatechange = set_subcategory;
                    xmlhttp.send();
                }
                function set_subcategory()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        var res = xmlhttp.responseText.split(",");
                        var p_id = res[0];
                        document.getElementById("sub_category").innerHTML = res[1];

                    }
                }
                function get_subcategory_of_edit(category, p_id)
                {
                    alert("chala");
                    xmlhttp.open("get", "../get_subcategory?category=" + category + "&p_id=" + p_id, true);
                    xmlhttp.onreadystatechange = set_subcategory_of_edit;
                    xmlhttp.send();
                }
                function set_subcategory_of_edit()
                {
                    alert(xmlhttp.responseText);
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        var res = xmlhttp.responseText.split(",");
                        var p_id = res[0];
                        document.getElementById("edit_sub_category" + p_id).innerHTML = res[1];

                    }
                }
                function readandpreview(fileobj, imageid)
                {
                    var firstfile = fileobj.files[0];

                    var reader = new FileReader();

                    //alert("File name: " + firstfile.name);
                    // alert("File size: " + firstfile.size);

                    reader.onload = (function (f)
                    {
                        return function read12(e)
                        {
                            document.getElementById(imageid).src = e.target.result;
                        };
                    })(firstfile);


                    reader.readAsDataURL(firstfile);
                }
            </script>
            <script>
                function del(product_id)
                {
                    if (confirm("Are you sure you want to remove this product from selling"))
                    {
                        window.location.href = "delete.jsp?product_id=" + product_id;
                    }
                }
            </script>
            <script>
                function change_image(p_id)
                {
                    document.getElementById("file_of_photo" + p_id).style.display = "inherit";
                    document.getElementById("file_of_photo" + p_id).required = "required";
                    document.getElementById("edit_img" + p_id).src = "";
                }
            </script>

            <%
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
            %>
            <div class="panel panel-info">
                <div class="panel-heading">My Products in Sub Category : <%=sub_category%></div>
                <div class="panel-body">
                    <%
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
                    %>
                    <div class="col-xs-3">
                        <img src=".<%=p_photo%>" class="img-thumbnail img-responsive img-rounded" width="100%">
                        <div class="text-center"><%=p_name%></div>
                        <div class="text-center">Product ID: <%=product_id%></div>
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#edit_prod_modal<%=product_id%>">Edit</button>
                            </div>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" id="<%=product_id%>" onclick="del(this.id)">Delete</button>
                            </div>
                        </div>
                        <div class="btn btn-sm btn-primary" data-toggle="modal" data-target="#add_photo_modal<%=product_id%>" style="margin: 1% 10%">Add Product Photos</div>
                    </div>

                    <!--- EDIT PRODUCT MODAL STARTS------>
                    <div id="edit_prod_modal<%=product_id%>" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Edit Details of <%=p_name%></h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-vertical" action="../edit_product?p_id=<%=product_id%>" method="post" enctype="multipart/form-data">

                                        <div class="form-group">
                                            <label for="p_name">Name of the Product</label>
                                            <input type="text" class="form-control" id="p_name" name="p_name" value="<%=p_name%>" required />
                                        </div>
                                        <div class="form-group">
                                            <label for ="category">Choose the Category</label>
                                            <select class="form-control" name="category" id="category" onchange="get_subcategory_of_edit(this.value, '<%=product_id%>')" required>
                                                <option><%=category%></option>
                                                <%
                                                    Statement stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                                    ResultSet rs4 = stmt4.executeQuery("select distinct c_name from categories where c_name !='" + category + "'");

                                                    while (rs4.next()) {
                                                %>
                                                <option><%=rs4.getString("c_name")%></option>
                                                <%

                                                    }
                                                    stmt4.close();
                                                %>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="edit_sub_category">Sub Category</label>
                                            <select class="form-control" id="edit_sub_category<%=product_id%>" name="sub_category" required >
                                                <option><%=sub_category%></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="p_photo">Primary photo that will be visible with the product</label><br>
                                            <img src=".<%=p_photo%>" id="edit_img<%=product_id%>" width="100px" height="100px"/><br><br>
                                            <input type="radio" name="img_changed" id="r1<%=product_id%>" value="false" class="radio-inline"  checked>
                                            <label for="r1<%=product_id%>">Don't want to change the primary photo</label><br>
                                            <input type="radio" name="img_changed" id="r2<%=product_id%>" value="true" class="radio-inline" onchange="change_image('<%=product_id%>')">
                                            <label for="r2<%=product_id%>">You Want to change the primary photo</label>
                                            <input type="file" id="file_of_photo<%=product_id%>" name="p_photo" onchange="readandpreview(this, 'edit_img<%=product_id%>')" style="display: none"  />
                                        </div>
                                        <div class="form-group">
                                            <label for="p_descrip">Description of the product</label>
                                            <textarea  class="form-control" id="p_descrip" name="p_descrip" /><%=p_descrip%>
                                            </textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="p_mrp">MRP of the Product</label>
                                            <input type="text" class="form-control" id="p_mrp" name="p_mrp" value="<%=p_mrp%>" required/>
                                        </div>
                                        <div class="form-group">
                                            <label for="p_offer_price">Offer Price(The price at which you want to sell the product)</label>
                                            <input type="text" class="form-control" id="p_offer_price" name="p_offer_price" value="<%=p_offer_price%>" />
                                        </div>
                                        <input class="btn btn-lg btn-primary center-block" type="submit" value="Update" />
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!---- EDIT PRODUCT MODAL ENDS------->


                    <!-----ADD PHOTOS MODAL STARTS----->
                    <div id="add_photo_modal<%=product_id%>" class="modal fade" role="dialog">
                        <div class="modal-dialog modal-lg">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title text-center">Photos of product <%=p_name%></h4>
                                </div> 
                                <div class="modal-body">
                                    <div class="caption">
                                        <h4>Primary photo</h4>
                                    </div>
                                    <img src=".<%=p_photo%>" class="img-responsive img-rounded img-thumbnail" width="200px" height="200px"/>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">Additional Photos</div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <%
                                                    Statement stmt5 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                                    ResultSet rs5 = stmt5.executeQuery("select * from photo_gallery where product_id='" + product_id + "'");
                                                    while (rs5.next()) {
                                                %>

                                                <div class="col-xs-3">
                                                    <div class="thumbnail">
                                                        <img src=".<%=rs5.getString("path")%>" alt="...">
                                                        <div class="caption">
                                                            <h3><%=rs5.getString("caption")%></h3>
                                                        </div>
                                                    </div>
                                                </div>

                                                <%

                                                    }
                                                    stmt5.close();
                                                %> 
                                            </div>
                                            <div class="btn btn-primary" data-toggle="collapse" data-target="#add_photo_form<%=product_id%>"><span class="glyphicon glyphicon-plus-sign"></span> Add More Photos </div>
                                            <div id="add_photo_form<%=product_id%>" class="collapsing">
                                                <form class="form-vertical" action="../add_product_gallery?p_id=<%=product_id%>" method="post" enctype="multipart/form-data">
                                                    <div class="form-group">
                                                        <label for="gallery_path">Select photo</label>
                                                        <img src="" width="100px" height="100px" id="gallery_img<%=product_id%>"/>
                                                        <input type="file" id="gallery_path" name="g_path" onchange="readandpreview(this, 'gallery_img<%=product_id%>')" required >
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="gallery_caption">Write a caption for the image(eg. front view, back view etc)</label>
                                                        <input type="text" name="g_caption" id="gallery_caption" class="form-control">
                                                    </div>
                                                    <input type="submit" class="btn btn-success" style="margin-left: 40%" value="ADD">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <!-----ADD PHOTOS MODAL ENDS------>

                    <%
                        }  //while of rs2 ends
                        stmt2.close();
                    %>
                </div>
            </div>
            <%
                }    //while of rs1 ends ----of subcategory
                stmt1.close();
            %>

            <a name="add_prod_btn">
                <div type="button"  class="btn btn-lg btn-primary" data-toggle="collapse" data-target="#add_prod" style="margin-top: 1%">
                    <span class="glyphicon glyphicon-plus-sign"></span> Add Products
                </div>
            </a>
            <div class="collapsing" id="add_prod" style="width: 60%">
                <form class="form-vertical" action="../add_products" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for ="category">Choose the Category</label>
                        <select class="form-control" name="category" onchange="get_subcategory(this.value)" required>
                            <option>select the category</option>
                            <%
                                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs = stmt.executeQuery("select distinct c_name from categories");

                                while (rs.next()) {
                            %>
                            <option><%=rs.getString("c_name")%></option>
                            <%

                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="sub_category">Choose the Sub Category</label>
                        <select class="form-control" id="sub_category" name="sub_category" required ></select>
                    </div>
                    <div class="form-group">
                        <label for="p_name">Enter the Name of the Product</label>
                        <input type="text" class="form-control" id="p_name" name="p_name"required />
                    </div>
                    <div class="form-group">
                        <label for="p_photo">Select the Primary photo that will be visible with the product</label><br>
                        <img src="" id="img" width="100px" height="100px"/>
                        <input type="file" id="p_photo" name="p_photo" onchange="readandpreview(this, 'img')" />
                    </div>
                    <div class="form-group">
                        <label for="p_descrip">Write the Description of the product</label>
                        <textarea  class="form-control" id="p_descrip" name="p_descrip" />
                        </textarea>
                    </div>
                    <div class="form-group">
                        <label for="p_mrp">Enter the MRP of the Product</label>
                        <input type="text" class="form-control" id="p_mrp" name="p_mrp" required/>
                    </div>
                    <div class="form-group">
                        <label for="p_offer_price">Enter the price at which you want to sell the product</label>
                        <input type="text" class="form-control" id="p_offer_price" name="p_offer_price" />
                    </div>
                    <input class="btn btn-lg btn-primary center-block" type="submit" value="Add" />
                </form>
            </div>
        </div>
    </body>
</html>
