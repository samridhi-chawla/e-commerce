
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.2.0.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container">
            <script>
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
                var xmlhttp = new XMLHttpRequest();
                function get_subcategories() {
                    var c_name = document.getElementById("category").value;
                    xmlhttp.open("get", "get_subcategory?category=" + c_name, true);
                    xmlhttp.onreadystatechange = function () {
                        var res = xmlhttp.responseText.split(",");
                        document.getElementById("sub_category").innerHTML = res[1];
                    };
                    xmlhttp.send();
                }
                function del(b_id) {
                    if (confirm("Are you Sure You Want to Delete this banner??")) {
                        xmlhttp.open("get", "del_banner?b_id=" + b_id, true);
                        xmlhttp.onreadystatechange = function () {
                            window.location.reload();
                        };
                        xmlhttp.send();
                    } else {
                        return;
                    }
                }
                function visible_min() {
                    document.getElementById("min_price").style.display = "inherit";
                    document.getElementById("max_price").style.display = "none";
                    document.getElementById("max_price").value = "";
                    document.getElementById("discount").style.display = "none";
                    document.getElementById("discount").value = "";
                    document.getElementById("new_item").style.display = "none";
                    document.getElementById("new_item").value = "";
                }
                function visible_max() {
                    document.getElementById("min_price").style.display = "none";
                    document.getElementById("min_price").value = "";
                    document.getElementById("max_price").style.display = "inherit";
                    document.getElementById("discount").style.display = "none";
                    document.getElementById("discount").value = "";
                    document.getElementById("new_item").style.display = "none";
                    document.getElementById("new_item").value = "";
                }
                function visible_minmax() {
                    document.getElementById("min_price").style.display = "inherit";
                    document.getElementById("max_price").style.display = "inherit";
                    document.getElementById("discount").style.display = "none";
                    document.getElementById("discount").value = "";
                    document.getElementById("new_item").style.display = "none";
                    document.getElementById("new_item").value = "";
                }
                function visible_discount() {
                    document.getElementById("min_price").style.display = "none";
                    document.getElementById("min_price").value = "";
                    document.getElementById("max_price").style.display = "none";
                    document.getElementById("max_price").value = "";
                    document.getElementById("discount").style.display = "inherit";
                    document.getElementById("new_item").style.display = "none";
                    document.getElementById("new_item").value = "";
                }
                function visible_new_item() {
                    document.getElementById("min_price").style.display = "none";
                    document.getElementById("min_price").value = "";
                    document.getElementById("max_price").style.display = "none";
                    document.getElementById("max_price").value = "";
                    document.getElementById("discount").style.display = "none";
                    document.getElementById("discount").value = "";
                    document.getElementById("new_item").style.display = "inherit";
                }

            </script>

            <%
                String username = (String) session.getAttribute("username");
                if (username == null) {
                    response.sendRedirect("admin_login.jsp");
                }
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select distinct c_name from categories");

            %>
            <div class="jumbotron">
                <h1>Manage AD Banners</h1>
            </div>
            <div class="row">
                <%                Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1 = stmt1.executeQuery("select * from banners");
                    while (rs1.next()) {
                %>
                <div class="col-md-3 thumbnail">
                    <img src="<%=rs1.getString("banner_photo")%>" style="height: 200px" class="img-responsive">
                    <table class="table">
                        <tr>
                            <td>Sub Category</td>
                            <td><%=rs1.getString("sub_category")%></td>
                        </tr>
                        <tr>
                            <td>Min price</td>
                            <td><%=rs1.getString("min_price")%></td>
                        </tr>
                        <tr>
                            <td>Max price</td>
                            <td><%=rs1.getString("max_price")%></td>
                        </tr>
                        <tr>
                            <td>Discount</td>
                            <td><%=rs1.getString("discount")%></td>
                        </tr>
                        <tr>
                            <td>New Item</td>
                            <td><%=rs1.getString("new_item_link")%></td>
                        </tr>
                    </table>
                    <div class="btn btn-primary btn-sm" style="margin-left: 30%" onclick="del('<%=rs1.getString("banner_id")%>')">Delete Banner</div>
                </div>
                <%
                    }
                %>
            </div>
            <!----ADD BANNER --->
            <div class="btn btn-lg btn-primary" data-toggle="collapse" data-target="#add_banner" ><span class="glyphicon glyphicon-plus-sign"></span> Add New Banner</div>

            <div id="add_banner" class="" style="margin-top: 20px;width: 50%">
                <form class="form-vertical" action="./add_banner" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="photo">Choose the picture for banner</label>
                        <input type="file" id="photo" name="banner_photo" onchange="readandpreview(this, 'img')" required >
                        <img src="" class="img-responsive " width="200px" height="200px" id="img"/>
                    </div>
                    <div class="form-group">
                        <label for="category">Category</label>
                        <select id="category" class="form-control" onchange="get_subcategories()">
                            <%                            while (rs.next()) {
                            %>
                            <option><%=rs.getString("c_name")%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sub_categories">Subcategory</label>
                        <select class='form-control' name="sub_category" id="sub_category" required>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="r1"><input type="radio" id="r1" name="x"  class="from-control" onchange="visible_min()"/> Price below filter</label><br>
                        <label for="r2"><input type="radio" id="r2" name="x"  class="from-control" onchange="visible_max()"/> Price Above Filter</label><br>
                        <label for="r3"><input type="radio" id="r3" name="x"  class="from-control" onchange="visible_minmax()"/> Combined Min and Max Filter</label><br>
                        <label for="r4"><input type="radio" id="r4" name="x"  class="from-control" onchange="visible_discount()"/> Discount Filter</label><br>
                        <label for="r5"><input type="radio" id="r5" name="x"  class="from-control" onchange="visible_new_item()" checked/> New Item</label>
                    </div>
                    <div class="form-group row" id="min_price" style="display: none" >
                        <div class="col-xs-4"><label>Enter price below here</label></div>
                        <div class="col-xs-8"><input type="number" class="form-control" name='min_price' /></div>
                    </div>
                    <div class="form-group row" id="max_price" style="display: none">
                        <div class="col-xs-4"><label>Enter price above here</label></div>
                        <div class="col-xs-8"><input type="number" class="form-control" name='max_price'  /></div>
                    </div>
                    <div class="form-group row" id="discount" style="display: none">
                        <div class="col-xs-4"><label>Enter Discount here</label></div>
                        <div class="col-xs-8"><input type="number" class="form-control" name='discount'  /></div>
                    </div>
                    <div class="form-group row" id="new_item" style="display: inherit">
                        <div class="col-xs-4"><label>Enter Product Id here</label></div>
                        <div class="col-xs-8"><input type="number" class="form-control" name='new_item_pid'  /></div>
                    </div>

                    <input type="submit" class="btn-primary btn-lg" value="Add Banner" style="margin-left: 40%" />
                </form>
            </div>
        </div>
    </body>
</html>
