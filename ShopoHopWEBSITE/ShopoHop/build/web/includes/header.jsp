
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="vmm.cart_item"%>


<!-- this navbar is for pages in user folder or merchant folder bcoz of the linking---->
<!----------ADD JQUERY IN THE FILE --->
<!-----ADD THIS NAVBER AFTER SESSION_EMAIL VARIABLE HAS A VALUE - TO BE DISPLAYED IN NAVBAR--------> 

<script>
    var xmlhttp_header = new XMLHttpRequest();
    function do_login()
    {
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        xmlhttp_header.open("post", "user_login.jsp?email=" + email + "&password=" + password, true);
        xmlhttp_header.onreadystatechange = function ()
        {
            window.location.reload();
        };
        xmlhttp_header.send();
    }

    function fetch_search(search)
    {//this is not implemented for now ********
        xmlhttp.open("get", "../fetch_search_products?search=" + search, true);
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
//                        document.
            }
        };
        xmlhttp.send();
    }

</script>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp">Shopo Hop</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="home.jsp">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">All Products <span class="caret"></span></a>
                    <ul class="dropdown-menu ">
                        <div class="row" style="width: 400px;padding: 10px">
                            <%
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                                Statement stmt11 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                ResultSet rs11 = stmt11.executeQuery("select c_name from categories");
                                while (rs11.next()) {
                                    String c_name = rs11.getString("c_name");
                            %>
                            <div class="col-md-3">
                                <label><a href="#"><%=c_name%></a></label>
                                    <%
                                        Statement stmt22 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                        ResultSet rs22 = stmt22.executeQuery("select sub_c_name from sub_categories where c_name='" + c_name + "'");
                                        while (rs22.next()) {
                                            String sub_c_name = rs22.getString("sub_c_name");
                                    %>
                                <li><a href="products.jsp?s_cat=<%=sub_c_name%>&cat=<%=c_name%>"><%=sub_c_name%></a></li>
                                    <%
                                        }   // inner while ends
                                    %>

                            </div>
                            <%                      }   //outer while ends
                            %>
                        </div>
                    </ul>
                </li>

            </ul>
            <form action="search.jsp" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search For Products here" name="search" onchange="fetch_search(this.value)">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
            <ul class="nav navbar-nav navbar-right">

                <%
                    String session_email = (String) session.getAttribute("email");
                    String name = "";
                    if (session_email != null) {

                        Statement stmt33 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs33 = stmt33.executeQuery("select name from users where email='" + session_email + "'");
                        if (rs33.next()) {
                            name = rs33.getString("name");
                %>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%=name%> logged in </a></li>
                <li><a href="my_orders.jsp">My Orders</a></li>
                    <%
                        }
                    %>
                <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                    <%
                    } else {

                    %>
                <li style="cursor: pointer;" data-toggle="modal" data-target="#LoginModal"><a ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <%                    }

                    %>
                    <%                ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
                        int size_cart;
                        if (al_cart == null) {
                            size_cart = 0;
                        } else {
                            size_cart = al_cart.size();
                        }
                    %>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-shopping-cart"><span class="badge"><%=size_cart%></span></span></a>
                    <ul class="dropdown-menu ">
                        <%
                            for (int i = 0; i < size_cart; i++) {
                                cart_item obj = al_cart.get(i);
                                String item = obj.p_name;
                        %>
                        <li><a href="view_cart.jsp"><%=item%></a></li>
                            <%
                                }
                            %>
                        <li><a href="view_cart.jsp"><div class="btn btn-sm btn-primary" style="float: right">Checkout</div></a></li>
                    </ul>
                </li>     
                <!-- Modal -->
                <div id="LoginModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" id="LoginModal_CloseBtn1" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title text-center">Login</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-vertical">
                                    <label id='login_error' style="color: red; size: 1em; font-style: italic"></label>
                                    <div class="form-group">
                                        <label for='email'>Enter Email</label>
                                        <input type="text" class="form-control" id='email' name="email"/>
                                    </div>
                                    <div class="form-group">
                                        <label for='password'>Enter the Password</label>
                                        <input type="password" class="form-control" id='password' name="password"/>
                                    </div>
                                    <input type="button" class="btn btn-primary btn-lg" onclick="do_login()" value="Login"/>
                                </form>
                                <label>Don't have a user account? <a href='sign_up.jsp'>Click here.</a></label>
                            </div>
                            <div class="modal-footer" >
                                <button type="button" id="LoginModal_CloseBtn2" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>
            </ul>
        </div>
    </div>
</nav>