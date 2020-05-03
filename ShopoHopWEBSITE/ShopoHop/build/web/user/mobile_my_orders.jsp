
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
            var xmlhttp=new XMLHttpRequest();
            function del(order_id){
                if(confirm("Are you sure you want to cancel this order?")){
                    xmlhttp.open("get","cancel_order.jsp?order_id="+order_id,true);
                    xmlhttp.onreadystatechange=function (){
                        window.location.reload();
                    };
                    xmlhttp.send();
                }
            }
            </script>
    </head>
    <body>
        
        <div class="container">
            <h1 class="text-center">My Orders</h1>

            <table class="table table-responsive">
                <tr>
                    <th>S.No</th>
                    <th>Order ID</th>
                    <th>Net Amount</th>
                    <th>Booked On</th>
                    <th>Delivery Date</th>
                    <th>Status</th>
                    <th>Operations</th>
                </tr>
                <%
                    String email = request.getParameter("email");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery("select * from orders where user_email='" + email + "'");
                    int i = 0;

                    while (rs.next()) {
                        i++;
                        String order_id = rs.getString("order_id");
                %>
                <tr>
                    <td><%=i%></td>
                    <td><a href="my_order_details.jsp?order_id=<%=order_id%>"><%=order_id%></a></td>
                    <td><%=rs.getString("net_amt")%></td>
                    <td><%=rs.getString("booking_date")%></td>
                    <td><%=rs.getString("delivery_date")%></td>
                    <td><%=rs.getString("status")%></td>
                    <td><div class="btn btn-sm btn-primary" onclick="del('<%=order_id%>')">Cancel Order</div></td>
                </tr>
                
                <!--order_details modal-->
                
            
                <%                    }


                %>
        
            </table>
            <%                if (i == 0) {
            %>
            <h2>You Don't have any order history</h2>
            <%
                    }%>


        </div>
    </body>
</html>
