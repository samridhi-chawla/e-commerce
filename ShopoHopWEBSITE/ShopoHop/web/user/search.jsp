
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <link href="../css/range_slider.css" rel="stylesheet" type="text/css"/>
        <script src="../js/range_slider.js" type="text/javascript"></script>
        <script src="../js/range_slider2.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>

        <style>
            .filter_bar
            {
                background-color: whitesmoke;
                height: 100vh;
                margin-left: 15px;
            }
            .margin{
                margin: 10px;
            }

            #custom-handle {
                width: 3em;
                height: 1.6em;
                top: 50%;
                margin-top: -.8em;
                text-align: center;
                line-height: 1.6em;
            }

        </style>

        <script>
            var minvalue = 0, maxvalue = 5000, discount = 0;

            $(function ()
            {
                $("#bt1").click(function ()
                {
                    //alert("called");

                    $("#slider-range").slider({values: [0, 5000]});
                });

                $("#slider-range").slider(
                        {
                            range: true,
                            min: 0,
                            max: 5000,
                            values: [75, 3000],
                            slide: function (event, ui)
                            {
                                minvalue = ui.values[ 0 ];
                                maxvalue = ui.values[ 1 ];

                                $("#amount").val(minvalue + " - " + maxvalue);

                            }
                        });

                $(function () {
                    var handle = $("#custom-handle");
                    $("#slider").slider({
                        create: function () {
                            handle.text($(this).slider("value"));
                        },
                        slide: function (event, ui) {
                            handle.text(ui.value);
                            discount = ui.value;
                        }
                    });
                });

                //to show initial value
                //$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );
            });


            function go()
            {
                alert(minvalue + " -- " + maxvalue);
            }

        </script>
        <script>
            var xmlhttp = new XMLHttpRequest();
            function filter(search) {
                xmlhttp.open("post", "search_filter.jsp?search=" + search + "&price_below=" + maxvalue + "&price_above=" + minvalue + "&discount=" + discount, true);
                xmlhttp.onreadystatechange = function () {
                    document.getElementById("fit_products").innerHTML = xmlhttp.responseText;
                };
                xmlhttp.send();
            }
//            function filter() {
//                var index = window.location.href.indexOf("&price_below");
//                if (index == (-1)) {
//                    window.location.href = window.location.href + "&price_below=" + maxvalue + "&price_above=" + minvalue + "&discount=" + discount;
//                } else {
//                    window.location.href = window.location.href.substring(0, index) + "&price_below=" + maxvalue + "&price_above=" + minvalue + "&discount=" + discount;
//                }
//            }

        </script>

    </head>
    <body>

        <%
            String session_email = (String) session.getAttribute("email");

            String search = request.getParameter("search");
            if (search == null) {
                response.sendRedirect("home.jsp");
            }
            
        %>
        <jsp:include page= "../includes/header.jsp" />


        <div class="row">
            <div class="col-md-2 filter_bar">
                <!--filters added here--->
                <h4 class="text-center">Filters</h4>
                <p>
                <div class="row">
                    <div class="col-xs-6">
                        <label for="slider-range">Price range:</label>
                    </div>
                    <div class="col-xs-6">
                        <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                    </div>
                </div>
                </p>
                <div class="row">
                    <div id="slider-range" class="margin"></div>
                </div>
                <!--                <div class="row margin" >
                                    <div class="col-xs-6">
                                        <input  type="button" value="RESET VALUES" id="bt1"/>
                                    </div>
                                    <div class="col-xs-6">
                                        <input type="button" value="Show" onclick="go()" />
                                    </div>
                                </div>-->


                <div class="margin">
                    <label for='discount_range'>Discount percentage</label>
                </div>

                <div id="slider" class="margin">
                    <div id="custom-handle" class="ui-slider-handle"></div>
                </div>
                <div class="btn btn-sm btn-primary" style="margin-left: 40%" onclick="filter('<%=search%>')">OK</div>
            </div>
            <div class="col-md-8" >
                <h3>Search results for <%=search%></h3>
                <%
                    
                    String price_below = request.getParameter("price_below");
                    String price_above = request.getParameter("price_above");
                    String discount = request.getParameter("discount");

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
                    ResultSet rs =stmt.executeQuery("select * from products where p_name like '%" + search + "%'");
                   
                    int flag = 0;
                    while (rs.next()) {
                        flag++;
                        int product_id = rs.getInt("product_id");
                        String p_name = rs.getString("p_name");
                        String p_descrip = rs.getString("p_descrip");
                        String p_photo = rs.getString("p_photo");
                        String category = rs.getString("category");
                        String sub_category = rs.getString("sub_category");
                        String p_mrp = rs.getString("p_mrp");
                        String p_offer_price = rs.getString("p_offer_price");

                %>
                <a href="search_product.jsp?p_id=<%=product_id%>">
                    <div class="row" id="fit_products">

                        <div class="col-xs-3">
                            <div class="thumbnail">
                                <img src=".<%=p_photo%>">
                                <div class='caption'>
                                    <label class="text-center"><%=p_name%></label>
                                    <table class='table'>
                                        <tr>
                                            <td>Type</td>
                                            <td><%=sub_category%></td>
                                        </tr>
                                        <tr>
                                            <td>MRP</td>
                                            <td><%=p_mrp%></td>
                                        </tr>
                                        <tr>
                                            <td>Offered Price</td>
                                            <td><%=p_offer_price%></td>
                                        </tr>
                                    </table>
                                </div>

                            </div>
                        </div>
                </a>
                <%
                    }
                    if (flag == 0) {
                %>
                <div class="col-xs-3">
                    <label>No Results Found</label>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>

</body>
</html>
