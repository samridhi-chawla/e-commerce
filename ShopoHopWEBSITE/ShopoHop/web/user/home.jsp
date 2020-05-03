
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-3.2.0.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <style>
            .main
            {
                width:100%;
                height: 400px;
                background-image:url('../includes/shop_back.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                opacity: 0.7;    
                margin-top: -2%;
            }
            .search
            {
                position:relative;
                top:20%;
                left: 35%;
                width:30%;

            }
        </style>
    </head>
    <body>
        <jsp:include page= "../includes/header.jsp" />
        <%
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = stmt1.executeQuery("select * from banners");


        %>
        <!------carousel code---->
        <div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top: -2%">
            <!-- Indicators -->
            <!--                                <ol class="carousel-indicators">
                                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                                <li data-target="#myCarousel" data-slide-to="2"></li>
                                                <li data-target="#myCarousel" data-slide-to="3"></li>
                                            </ol>-->

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <%                    if (rs1.next()) {
                        //creting link
                        String link = "";
                        String min_price = rs1.getString("min_price");
                        String max_price = rs1.getString("max_price");
                        String discount = rs1.getString("discount");
                        String sub_category = rs1.getString("sub_category");
                        String new_item_link = rs1.getString("new_item_link");
                        System.out.println(" " + min_price + " " + max_price + " " + discount + " " + sub_category + " " + new_item_link);
                        if (new_item_link != null) {
                            link = new_item_link;
                        } else {
                            link = "products.jsp?s_cat=" + sub_category;
                            if (min_price != null) { // from database value will never come empty therefore null check
                                link = link + "&price_above=" + min_price;
                            }
                            if (max_price != null) {
                                link = link + "&price_below=" + max_price;
                            }
                            if (discount != null) {
                                link = link + "&discount=" + discount;
                            }
                        }

                %>
                <div  class="item active">
                    <a href="<%=link%>"><img src=".<%=rs1.getString("banner_photo")%>" alt=""  style="width:100%;height: 600px" ></a>
                    <!--                                        <div class="carousel-caption">
                                                                <h3>caption</h3>
                                                                <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
                                                            </div>-->
                </div>
                <%
                    }
                %>
                <%
                    int i = 0;
                    while (rs1.next()) {
                        //creting link
                        String link = "";
                        String min_price = rs1.getString("min_price");
                        String max_price = rs1.getString("max_price");
                        String discount = rs1.getString("discount");
                        String sub_category = rs1.getString("sub_category");
                        String new_item_link = rs1.getString("new_item_link");
                        System.out.println(" " + min_price + " " + max_price + " " + discount + " " + sub_category + " " + new_item_link);
                        if (new_item_link != null) {
                            link = new_item_link;
                        } else {
                            link = "products.jsp?s_cat=" + sub_category;
                            if (min_price != null) { // from database value will never come empty therefore null check
                                link = link + "&price_above=" + min_price;
                            }
                            if (max_price != null) {
                                link = link + "&price_below=" + max_price;
                            }
                            if (discount != null) {
                                link = link + "&discount=" + discount;
                            }
                        }

                %>
                <div class="item ">
                    <a href="<%=link%>"><img src=".<%=rs1.getString("banner_photo")%>" alt="" style="width:100%;height: 600px" ></a>
                    <!--                    <div class="carousel-caption">
                                            <h3>caption</h3>
                                            <p>The atmosphere in Chania has a touch of Florence and Venice.</p>
                                        </div>-->
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
        <div class="container" style="padding: 5%">
            <div class="row">

                <%
                    Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs2 = stmt2.executeQuery("select * from sub_categories");
                    while (rs2.next()) {
                %>
                <a href="products.jsp?s_cat=<%=rs2.getString("sub_c_name")%>&cat=">
                <div class="col-xs-3">
                    <div class="thumbnail">
                        <img src=".<%=rs2.getString("photo")%>" class="img-responsive" alt="..." width="100%" style="height: 200px;">
                        <div class="caption">
                            <h4><%=rs2.getString("sub_c_name")%></h4>
                            <p><%=rs2.getString("description")%></p>
                            <!--<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>-->
                        </div>
                    </div>
                </div>
                </a>

                <%
                    }
                %>
            </div>
        </div>
        <!--footer-->
        <div style="background-color: black;height: 8%;padding: 2%">
            <p style="margin-left: 30%;color: aliceblue;">Want to sell your products at Shopohop <a href="../merchant/login.jsp">Click here to become a seller </a></p>
        </div> 

    </body>
</html>
