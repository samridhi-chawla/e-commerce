
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script>
            var xmlhttp = new XMLHttpRequest();
            function make_active(email)
            {
                xmlhttp.open("post", "make_merchant_active.jsp?email=" + email, true);
                xmlhttp.onreadystatechange = function ()
                {
                    window.location.reload();
                };
                xmlhttp.send();
            }
            function deactivate(email)
            {
                xmlhttp.open("post", "make_merchant_deactive.jsp?email=" + email, true);
                xmlhttp.onreadystatechange = function ()
                {
                    window.location.reload();
                };
                xmlhttp.send();
            }
        </script>
        <title>Manage Sellers</title>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container-fluid">
            <div class="jumbotron">
                <h1 class="text-center">Manage Sellers</h1>
            </div>
            <%
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/myproject", "root", "system");
                java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from merchant where status='pending'");
            %>
            <h3>Sellers with status Pending</h3>
            <table class="table table-bordered table-responsive table-striped">
                <tr>
                    <th>S.No</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Aadhar Number</th>
                    <th>Tin Number</th>
                    <th>Company Icon</th>
                    <th>Bank Acc.No</th>
                    <th>IFSC Code</th>
                    <th>Status</th>
                    <th>Operation</th>
                </tr>
                <%
                    for (int i = 1; rs.next(); i++) {
                %>
                <tr>
                    <td><%=i%></td>
                    <td><%=rs.getString("email")%></td>
                    <td><%=rs.getString("name")%></td>
                    <td><%=rs.getString("phone")%></td>
                    <td><%=rs.getString("address")%></td>
                    <td><%=rs.getString("city")%></td>
                    <td><%=rs.getString("state")%></td>
                    <td><img src="<%=rs.getString("aadhar_no")%>" width="100px" height="100px" alt="Not Available"></td>
                    <td><img src="<%=rs.getString("tin_no")%>" width="100px" height="100px" alt="Not Available" /></td>
                    <td><img src="<%=rs.getString("co_icon")%>" width="100px" height="100px"  alt="Not Available"/></td>
                    <td><%=rs.getString("bank_acc")%></td>
                    <td><%=rs.getString("ifsc_code")%></td>
                    <td><%=rs.getString("status")%></td>
                    <td><input type="button" class="btn btn-primary" onclick="make_active('<%=rs.getString("email")%>')" value="Approve"/></td>
                </tr>
                <%
                    }
                %>
            </table>

            <%
                ResultSet rs1 = stmt.executeQuery("select * from merchant  where status='active'");
            %>
            <h3>Sellers with status Active</h3>
            <table class="table table-bordered table-responsive table-striped">
                <tr>
                    <th>S.No</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Aadhar Number</th>
                    <th>Tin Number</th>
                    <th>Company Icon</th>
                    <th>Bank Acc.No</th>
                    <th>IFSC Code</th>
                    <th>Status</th>
                    <th>Operation</th>
                </tr>
                <%
                    for (int j = 1; rs1.next(); j++) {
                %>
                <tr>
                    <td><%=j%></td>
                    <td><%=rs1.getString("email")%></td>
                    <td><%=rs1.getString("name")%></td>
                    <td><%=rs1.getString("phone")%></td>
                    <td><%=rs1.getString("address")%></td>
                    <td><%=rs1.getString("city")%></td>
                    <td><%=rs1.getString("state")%></td>
                    <td><img src="<%=rs1.getString("aadhar_no")%>" width="100px" height="100px"></td>
                    <td><img src="<%=rs1.getString("tin_no")%>" width="100px" height="100px" /></td>
                    <td><img src="<%=rs1.getString("co_icon")%>" width="100px" height="100px" /></td>
                    <td><%=rs1.getString("bank_acc")%></td>
                    <td><%=rs1.getString("ifsc_code")%></td>
                    <td><%=rs1.getString("status")%></td>
                    <td><input type="button" class="btn btn-primary" onclick="deactivate('<%=rs1.getString("email")%>')" value="Deactivate"/></td>
                </tr>    
                <%

                    }
                %>
            </table>
        </div>
    </body>
</html>
