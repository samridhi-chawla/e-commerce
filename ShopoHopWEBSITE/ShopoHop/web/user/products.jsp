
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
            function filter(s_cat) {
                xmlhttp.open("post", "products_filter.jsp?s_cat=" + s_cat + "&price_below=" + maxvalue + "&price_above=" + minvalue + "&discount=" + discount, true);
                xmlhttp.onreadystatechange = function () {
                    document.getElementById("fit_products").innerHTML = xmlhttp.responseText;
                };
                xmlhttp.send();
            }


        </script>
    </head>
    <body>
        <jsp:include page = "../includes/header.jsp"/>

        <%
            String sub_category = request.getParameter("s_cat");
            if (sub_category == null) {
                response.sendRedirect("home.jsp");
            }
            String category = "";
            String price_below = request.getParameter("price_below");
            String price_above = request.getParameter("price_above");
            String discount = request.getParameter("discount");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
            // stmt for category
            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = stmt1.executeQuery("select c_name from sub_categories where sub_c_name='"+sub_category+"'");
            if(rs1.next()){
                category = rs1.getString("c_name");
            }
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs;
            String s ="select * from products where sub_category='" + sub_category + "' ";
            
            if (price_below == null && price_above == null && discount == null) {
                rs = stmt.executeQuery(s);
            }
            else{
                if(price_below!=null){
                    s=s+" and p_offer_price<="+price_below ;
                }
                if(price_above!=null){
                    s=s+" and p_offer_price>="+price_above ;
                }
                if(discount!=null){
                    s=s+" and ((p_mrp-p_offer_price)/p_mrp)*100>=" + discount;
                }
                rs= stmt.executeQuery(s);
            }
        %>
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
                <div class="btn btn-sm btn-primary" style="margin-left: 40%" onclick="filter('<%=sub_category%>')">OK</div>
            </div>
            <div class="col-md-8">
                <h2>Products under <%=category%> > <%=sub_category%></h2>
                <div class="row" id="fit_products">

                    <%
                        while (rs.next()) {
                            String p_name = rs.getString("p_name");
                            String p_photo = rs.getString("p_photo");
                            int product_id = rs.getInt("product_id");
                            String p_mrp = rs.getString("p_mrp");
                            String p_offer_price = rs.getString("p_offer_price");

                    %>
                    <a href="search_product.jsp?p_id=<%=product_id%>">
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
                                            <%
                                                if (p_mrp == p_offer_price) {
                                            %>
                                            <td><%=p_mrp%></td>
                                            <%
                                            } else {
                                            %>
                                            <td><span style="text-decoration: line-through"><%=p_mrp%></span> <span><%=p_offer_price%></span></td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </a>
                    <%            }
                    %>
                </div>
            </div>

        </div>
    </body>
</html>
