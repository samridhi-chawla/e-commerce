<%@page import="java.sql.*"%>
<nav class="navbar navbar-default navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="admin_dashboard.jsp">Shopohop</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="dashboard.jsp">Merchant Dashboard <span class="sr-only">(current)</span></a></li>
                <li><a href="dashboard.jsp">Manage My Products</a></li>
                <li><a href="#add_prod_btn">Add New Products</a></li>
                <li><a href="merchant_orders.jsp">My Orders</a></li>

            </ul>
            <%
                String nav_email = (String) session.getAttribute("email");
                if (nav_email == null) {
                    response.sendRedirect("login.jsp");
                }
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt33 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs33 = stmt33.executeQuery("select name from merchant where email='" + nav_email + "'");
                if (rs33.next()) {
            %>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Welcome <%=rs33.getString("name")%></a></li>
                <li><a href="merchant_logout.jsp"><span class="glyphicon glyphicon-log-out"> Logout</span></a></li>

            </ul>
            <%
                }
                stmt33.close();
            %>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>