


<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container"> 
            <div class="jumbotron">
                <h1>Add New Category</h1>
            </div>
            <form action="Test" class="form-vertical" method="post" enctype="multipart/form-data">

                <div class='form-group'>
                    <label for='cat'>Enter category name</label>
                    <input  class='form-control' type="text" name="category" required /><br>
                </div>
                <div class='form-group'>
                    <label for="descrip">Description</label>
                    <textarea class='form-control' name="description"></textarea><br>
                </div>

                <div class='form-group'>
                    <label>select photo</label>
                    <input class="form-control" type="file" name="pic" required />  <br>
                </div>
                <input class="btn btn-lg btn-primary" type="submit" value="Add" /><br>
                <%
                    String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <label style="color: red"><%= msg%></label>
                <%
                    }
                %>
            </form>

            <h1 class="text-center">Manage Category</h1>

            <table class="table-bordered table table-condensed" border="5" cellspacing="5" cellpadding="10">
                <tr>
                    <th>category name</th>
                    <th>description</th>
                    <th>image</th>
                    <th colspan="2">operations</th>

                </tr>
                <%
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from categories");
                    while (rs.next()) {
                        String categoryname = rs.getString("c_name");
                        String description = rs.getString("description");
                        String filepath = rs.getString("photo");

                %>

                <tr>
                    <td><%= categoryname%></td>
                    <td><%= description%></td>
                    <td><img src="<%=filepath%>" width="100" height="100" /> </td>


                    <td><a href="edit_category.jsp?name=<%= categoryname%>"   class="btn btn-success">Edit</a></td>
                    <td><a href="delete_category.jsp?name=<%= categoryname%>" onclick="return confirm('are you sure to delete?')" class="btn btn-danger">Delete</a></td>
                </tr>  


                <%
                    }
                %>

            </table>
        </div>
    </body>
</html>
