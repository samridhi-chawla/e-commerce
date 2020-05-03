
<%@page import="java.sql.*"%>
<%@page import="vmm.cart_item"%>
<%@page import="java.util.ArrayList"%>
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
            var xmlhttp = new XMLHttpRequest();
            function change_al(pos, qty) {
                xmlhttp.open("get", "../alter_qty_cart?pos=" + pos + "&qty=" + qty, true);
                xmlhttp.onreadystatechange = function () {
                    window.location.reload();
                };
                xmlhttp.send();
            }
        </script>
    </head>
    <body>
        <jsp:include page= "../includes/header.jsp" />
        <div class="container-fluid" style="margin-top: 3%">
            <%
                ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
                if (al_cart != null) {
            %>
            <div class="row">
                <div class="col-xs-9">
                    <table class="table table-responsive table-bordered">
                        <tr>
                            <th>Product</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Total Price</th>
                            <th>Remove</th>
                        </tr>
                        <%
                            Double net_amt = 0.0;
                            for (int i = 0; i < al_cart.size(); i++) {
                                cart_item item = al_cart.get(i);
                                String p_id = item.p_id;
                                String p_name = item.p_name;
                                String unit_price = item.p_offer_price;
                                String qty = item.qty;
                                String p_photo = "";
                                String p_descrip = "";
                                Double tot_price = Integer.parseInt(qty) * Double.parseDouble(unit_price);
                                net_amt = net_amt + tot_price;
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs = stmt.executeQuery("select p_photo,p_descrip from products where product_id='" + p_id + "'");
                                if (rs.next()) {
                                    p_photo = rs.getString("p_photo");
                                    p_descrip = rs.getString("p_descrip");
                                }
                        %>
                        <tr>
                            <td><img src=".<%=p_photo%>" class="img-responsive" width="150px" height="150px"/></td>
                            <td><%=p_name%></td>
                            <td><%=p_descrip%></td>
                            <td><input type="number" onchange="change_al('<%=i%>', this.value)" value="<%=qty%>" style="width: 50px"/></td>
                            <td><%=unit_price%></td>
                            <td><%=tot_price%></td>
                            <td><a href="../remove_from_cart?pos=<%=i%>"><div class="btn btn-primary btn-sm">Remove From Cart</div></a></td>
                        </tr>
                        <%
                            }
                            Double vat = net_amt * 0.05;
                            Double taxes = net_amt * 0.12;
                            Double delivery_charges = 50.0;
                            Double net_amt_payable = net_amt + vat + taxes + delivery_charges;
                            session.setAttribute("net_amt", net_amt);
                            session.setAttribute("vat", vat);
                            session.setAttribute("taxes", taxes);
                            session.setAttribute("delivery_charges", delivery_charges);
                            session.setAttribute("net_amt_payable", net_amt_payable);
                        %>

                    </table>
                </div>
                <div class="col-xs-3">
                    <div class="row">
                        <h2 class="text-center">Order Summary</h2>
                        <table class="table">
                            <tr>
                                <td>Total</td>
                                <td><%=net_amt%></td>
                            </tr>
                            <tr>
                                <td>Vat</td>
                                <td><%=vat%></td>
                            </tr>
                            <tr>
                                <td>Taxes</td>
                                <td><%=taxes%></td>
                            </tr>
                            <tr>
                                <td>Delivery Charges</td>
                                <td><%=delivery_charges%></td>
                            </tr>
                            <tr>
                                <td><label style="font-size: 20px">Net Amount Payable:</label></td>
                                <td style="font-size: 20px"><%=net_amt_payable%></td>
                            </tr>
                        </table>
                    </div>

                    <div class="row">
                        <a href="place_order.jsp"><div class="btn btn-primary btn-lg" style="width: 80%;margin: 5%">Place Order</div></a>
                    </div>
                </div>
            </div>
            <%
            } else {
            %>

            <h2>Your Cart is empty</h2>
            <%
                }
            %>
        </div>
    </body>
</html>
