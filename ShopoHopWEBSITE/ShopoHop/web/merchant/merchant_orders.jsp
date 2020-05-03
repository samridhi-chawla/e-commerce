
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
        <title>Merchant Dashboard</title>
    </head>
    <body>
        <jsp:include page="merchant_nav.jsp"/>
        <div class='container-fluid'>
            <table class="table table-responsive">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Customer Name</th>
                    <th>Customer Email</th>
                    <th>Customer Phone</th>
                    <th>Customer Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Pincode</th>
                    <th>Order Id</th>
                    <th>Booking Date</th>
                    <th>Delivery Date</th>
                    <th>Payment Mode</th>
                </tr>
                <%
                    String merchant_email = (String) session.getAttribute("email");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select order_details.product_id,order_details.product_name,order_details.product_photo,order_details.p_offer_price,order_details.order_id,qty,user_email,booking_date,delivery_date,payment_mode,address,city,state,pincode,users.name,users.phone_no from products,order_details,orders,user_addresses,users where products.seller_email='"+merchant_email+"' and orders.order_id=order_details.order_id and orders.address_id=user_addresses.add_id order by delivery_date desc");
                    while (rs.next()) {
                        %>
                        <tr>
                            <td><%=rs.getString("product_id")%></td>
                            <td><%=rs.getString("product_name")%></td>
                            <td><img src=".<%=rs.getString("product_photo")%>" width="100px" height="100px"/></td>
                            <td><%=rs.getString("p_offer_price")%></td>
                            <td><%=rs.getString("qty")%></td>
                            <td><%=rs.getString("name")%></td>
                            <td><%=rs.getString("user_email")%></td>
                            <td><%=rs.getString("phone_no")%></td>
                            <td><%=rs.getString("address")%></td>
                            <td><%=rs.getString("city")%></td>
                            <td><%=rs.getString("state")%></td>
                            <td><%=rs.getString("pincode")%></td>
                            <td><%=rs.getString("order_id")%></td>
                            <td><%=rs.getString("booking_date")%></td>
                            <td><%=rs.getString("delivery_date")%></td>
                        </tr>
                        
                        <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
