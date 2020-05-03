
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-3.2.0.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script>
            function make_active(i)
            {
                document.getElementById("make_active"+i).className="item active";
            }
            var xmlhttp=new XMLHttpRequest();
            function add_to_cart(p_id){
                var qty=document.getElementById("qty").value;
                alert("Product added successfully to your cart");
                xmlhttp.open("post","add_to_cart.jsp?p_id="+p_id+"&qty="+qty,true);
                xmlhttp.onreadystatechange=function (){
                    
                    window.location.reload();
                };
                xmlhttp.send();
            }
            </script>

    </head>
    <body>

        <%
            String session_email = (String) session.getAttribute("email");
            String p_id = request.getParameter("p_id");
            if (p_id == null) {
                response.sendRedirect("home.jsp");
            }

            int product_id = 0;
            if (p_id != null) {
                product_id = Integer.parseInt(p_id);
            }

        %>
        <jsp:include page= "../includes/header.jsp" />
        <div class="container">
            <%                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from products where product_id='" + product_id + "'");
                if (rs.next()) {
                    String p_name = rs.getString("p_name");
                    String p_descrip = rs.getString("p_descrip");
                    String p_photo = rs.getString("p_photo");
                    String category = rs.getString("category");
                    String sub_category = rs.getString("sub_category");
                    String p_mrp = rs.getString("p_mrp");
                    String p_offer_price = rs.getString("p_offer_price");

            %>
            <h2> <%=p_name%> </h2>
            <div class="row" style="border-radius: 20;border: 2; background-color: whitesmoke">
                <div class="col-md-6">
                    <div class="row">
                        <img src=".<%=p_photo%>" class="img-thumbnail img-responsive" alt="primary photo of product" style="margin-left: 20%"/>
                    </div>
                    <div class="row">
                        <div class="row" style="margin-left: 10%">
                            <label>Additional Photos of product</label>
                        </div>
                        <%
                            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs1 = stmt1.executeQuery("select * from photo_gallery where product_id='" + product_id + "'");
                            int count = 0;  //to use in carousel indexing later
                            while (rs1.next()) {
                                
                        %>

                        <div class="col-xs-3">
                            <img src='.<%=rs1.getString("path")%>' class="img-responsive img-thumbnail hover-shadow cursor" data-toggle="modal" data-target="#myModal" onclick="make_active('<%=count%>')" style="height:100px"/>
                        </div>

                        <% count++;
                            }
                            rs1.beforeFirst(); //so that we can use the same result set to generate slides in lightbox
                        %>
                    </div>
                </div>
                <div class="col-md-6">
                    <table class="table table-responsive">
                        <tr>
                            <td><label>Name of product</label></td>
                            <td><%=p_name%></td>   
                        </tr>
                        <tr>
                            <td><label>Type of product</label></td>
                            <td><%=sub_category%></td>   
                        </tr>
                        <tr>
                            <td><label>Category</label></td>
                            <td><%=category%></td>   
                        </tr>
                        <tr>
                            <td><label>Description</label></td>
                            <td><%=p_descrip%></td>   
                        </tr>
                        <tr>
                            <td><label>Price of product</label></td>
                            <td><%=p_mrp%></td>   
                        </tr>
                        <tr>
                            <td><label>Offered price</label></td>
                            <td><%=p_offer_price%></td>   
                        </tr>
                        <tr>
                            <td><label for="qty">Enter the Quantity you want to buy</label></td>
                            <td><input type="number" id="qty" value="1"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="btn btn-primary btn-lg" style="float: right" onclick="add_to_cart('<%=product_id%>')">Add to Cart</div></td>
                        </tr>
                    </table>
                </div>
            </div>

            <!----Light Box -------->
            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close"  data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Modal Header</h4>
                        </div>
                        <div class="modal-body">

                            <!------caroudel code---->
                            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
<!--                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                    <li data-target="#myCarousel" data-slide-to="3"></li>
                                </ol>-->

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner" role="listbox">
                                    <%
                                        int i=0;
                                    while(rs1.next()){
                                    %>
                                    <div id="make_active<%=i%>" class="item ">
                                        <img src=".<%=rs1.getString("path")%>" alt="" width="100%" height="400px">
                                        <div class="carousel-caption">
                                            <h3><%=rs1.getString("caption")%></h3>
                                            <!--<p>The atmosphere in Chania has a touch of Florence and Venice.</p>-->
                                        </div>
                                    </div>
                                    <%
                                    i++;
                                        }
                                    %>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                           

                            <!--------carousel code ends----->




                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
            <!----light box ends----->



            <%            }  //outermost if ends  ---everything on this page must be inside this
            %>


        </div>

    </body>
</html>
