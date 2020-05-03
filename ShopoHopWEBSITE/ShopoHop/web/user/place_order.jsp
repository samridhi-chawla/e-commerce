
<%@page import="vmm.cart_item"%>
<%@page import="java.util.ArrayList"%>
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
        <style>
            .heading{
                font-size: 18px;
            }
        </style>
        <script>
            var xmlhttp = new XMLHttpRequest();
            function set_add_id(add_id) {
                xmlhttp.open("get", "set_add_id_session.jsp?add_id=" + add_id, true);
                xmlhttp.onreadystatechange = function () {

                };
                xmlhttp.send();
            }
            function check_address(){
                if(document.getElementById("add_id")==null){
                    alert("Please add the delivery address");
                    return false;
                }
                else{
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page= "../includes/header.jsp" />
        <%
            ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
            if (al_cart == null || al_cart.size() == 0) {
                response.sendRedirect("view_cart.jsp");
            }
            String email = (String) session.getAttribute("email");
            if (email == null) {
        %>
        <script>
            $('#LoginModal').modal({
                show: 'false',
                backdrop: 'static',
                keyboard: false
            });
            document.getElementById("LoginModal_CloseBtn1").disabled = 'disabled';
            document.getElementById("LoginModal_CloseBtn2").disabled = 'disabled';
        </script>
        <%
            }
            Double net_amt_payable = (Double) session.getAttribute("net_amt_payable");
            String username = "";
            String phone_no = "";
            String address = "";
            String city = "";
            String state = "";
            String pincode = "";
            if (email != null) {

                String d = "2017-04-04";
                Date date = Date.valueOf(d);
                long date_milis = date.getTime();

                if (System.currentTimeMillis() > date_milis) {
                    System.out.println("validity over");
                }

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select name,phone_no from users where email='" + email + "'");
                if (rs.next()) {
                    username = rs.getString("name");
                    phone_no = rs.getString("phone_no");
                }
        %>
        <div class="container" style="margin-top: 3%">
            <h1 class="text-center">Place Order</h1>

            <div class="col-xs-9">
                <div class="form-group">
                    <label class="heading" for="username">Username</label>
                    <input type="text" id="username" value="<%=username%>" readonly class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="username" class="heading">Email</label>
                    <input type="text" id="username" value="<%=email%>" readonly class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="username" class="heading">Phone No.</label>
                    <input type="text" id="username" value="<%=phone_no%>" readonly class="form-control"/>
                </div>
                <label class="heading">Select the Delivery Address</label><br>

                <div class="btn btn-lg btn-primary" data-toggle="collapse" data-target="#add_new_address"><span class="glyphicon glyphicon-plus-sign"> ADD New Address</span></div>
                <div id="add_new_address" class="collapsing">
                    <form class="form-vertical" action="add_new_address.jsp?email=<%=email%>" method="post">
                        <div class="form-group">
                            <label for="address">Enter your address</label>
                            <input type="text" id="address" name="address" value="<%=address%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="city">Enter the city</label>
                            <input type="text" id="city" name="city" value="<%=city%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="state">Enter the State</label>
                            <input type="text" id="state" name="state" value="<%=state%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="pincode">Enter the Pincode</label>
                            <input type="number" id="pincode" name="pincode" value="<%=pincode%>" class="form-control"/>
                        </div>
                        <input type="submit"  class="btn-lg btn btn-primary" style="margin-left: 40%" value="Save"/>
                    </form>
                </div>

                        <form action="place_order2.jsp" method="post" onsubmit="return check_address()" style="margin-top: 3%">
                    <%

                        Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs1 = stmt1.executeQuery("select * from user_addresses where email='" + email + "' ");

                        while (rs1.next()) {
                            int add_id = rs1.getInt("add_id");
                            address = rs1.getString("address");
                            city = rs1.getString("city");
                            state = rs1.getString("state");
                            pincode = rs1.getString("pincode");

                    %>
                    <label>
                        <input type="radio" name="add_id" id="add_id" required checked onchange="set_add_id(this.value)" value="<%=add_id%>"/>
                        <p><%=address%></p>
                        <p><%=city%></p>
                        <p><%=state%></p>
                        <p><%=pincode%></p>
                    </label>
                    <br>
                    <% session.setAttribute("add_id", add_id);
                            }
                        }
                        java.util.Date today = new java.util.Date();
                        long del_date = today.getTime() + (1000 * 24 * 60 * 60 * 7);
                        Date delivery_date = new Date(del_date);
                        session.setAttribute("delivery_date", delivery_date);

                    %>

                    <div class="form-group">
                        <label class="heading">Your order will be delivered by : </label>
                        <input type="text" value="<%=delivery_date%>" name="delivery_date" readonly/>
                    </div>
                    <div class="form-group">
                        <label class="heading">Select the Mode of payment</label><br>
                        <label for ="cod"><input type="radio" value="cod" id="cod" name="mode" checked/> Cash On Delivery</label><br>
                        <label for ="digital"><input type="radio" value="digital" id="digital" name="mode"/> Digital Mode of Payment</label>
                    </div>
                    <input type="submit" class="btn btn-lg btn-primary" value="Place order"/>
                </form>
            </div>
            <div class="col-xs-3">
                <div class="row">
                    <div class="col-xs-8">
                        <label style="font-size: 20px">Net Amount Payable:</label>
                    </div>
                    <div class="col-xs-4"><p style="font-size: 20px"><%=net_amt_payable%></p></div>
                </div>
                <div class="row">
                    <a href="view_cart.jsp"><div class="btn btn-primary btn-lg" style="width: 80%;margin: 5%">Review Order</div></a>
                </div>
            </div>
        </div>
    </body>
</html>
