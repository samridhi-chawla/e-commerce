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
                <h1>Add new sub category</h1>
            </div>
            <form action="./MultiFileUploaderForSub" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <label>Enter sub category name</label>
                    <input class="form-control" type="text" name="subcatgname" required /><br>
                </div>
                <div class="form-group">
                    <label>select category</label>
                    <select class="form-control" name=" catg">
                        <%
                            Class.forName("com.mysql.jdbc.Driver");
                            System.out.println("driver loaded successfully");
                            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                            System.out.println("connection built");
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select * from categories");
                            while (rs.next()) {
                                String name = rs.getString("c_name");
                        %>
                        <option><%=name%></option>
                        <%
                            }
                        %>

                    </select>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea class="form-control" name="text"></textarea><br>
                </div>
                <div class="form-group">
                    <label>select photo</label>
                    <input class="form-control" type="file" name="pic" required />  <br>
                </div>
                <input class="btn btn-lg btn-primary" type="submit" value="Add" /><br>
                <%
                    String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <label style="color: green"><%= msg%></label>
                <%
                    }
                %>
            </form>
            <table  class="table table-bordered table-condensed" border="5" cellspacing="5" cellpadding="10">
                <tr>
                    <th>Sub category name</th>
                    <th>Category name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th colspan="2">Operations</th>

                </tr>
                <%
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("driver loaded successfully");
                    Connection connn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                    System.out.println("connection built");
                    Statement stmtm = connn.createStatement();
                    ResultSet rsp = stmtm.executeQuery("select * from sub_categories");
                    while (rsp.next()) {
                        String subcategoryname = rsp.getString("sub_c_name");
                        String categoryname = rsp.getString("c_name");
                        String description = rsp.getString("description");
                        String filepath = rsp.getString("photo");
                %>

                <tr>
                    <td><%= subcategoryname%></td>
                    <td><%= categoryname%></td>
                    <td><%= description%></td>
                    <td><img src="<%=filepath%>" width="100" height="100" /> </td>                               
                    <td><a href="edit_sub_category.jsp?name=<%= subcategoryname%>"   class="btn btn-success">Edit</a></td>
                    <td><a href="delete_sub_category.jsp?name=<%= subcategoryname%>" onclick="return confirm('are you sure to delete?')" class="btn btn-danger">Delete</a></td>
                </tr>  


                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
