
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
    </head>
    <body>
        <jsp:include page= "../includes/header.jsp" />
        <div class="container">
            <h1 class="text-center">My Orders</h1>
            <%
                String order_id = request.getParameter("order_id");
            %>
            <h1>Order Details for : <%=order_id%></h1>
            <table class="table table-responsive">
                <tr>
                    <th>S.No</th>
                    <th>Photo</th>
                    <th>Product Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                </tr>
                <%
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");

                    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs1 = stmt1.executeQuery("select * from order_details where order_id='" + order_id + "'");
                    int i=0;
                    while (rs1.next()) {
                        i++;
                %>
                <tr>
                    <td><%=i%></td>
                    <td><img src='.<%=rs1.getString("product_photo")%>' width="150px" height="150px" /></td>
                    <td><%=rs1.getString("product_name")%></td>
                    <td><%=rs1.getString("p_offer_price")%></td>
                    <td><%=rs1.getString("qty")%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
