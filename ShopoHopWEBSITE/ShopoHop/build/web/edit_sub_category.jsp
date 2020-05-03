
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
        <script>

            function readandpreview(fileobj, imageid)
            {
                var firstfile = fileobj.files[0];

                var reader = new FileReader();

                reader.onload = (function (f)
                {
                    return function read12(e)
                    {
                        document.getElementById(imageid).src = e.target.result;
                    };
                })(firstfile);


                reader.readAsDataURL(firstfile);
            }

        </script>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container">
            <div class="jumbotron">
                <h1> edit your category</h1>
            </div>
            <form action="./MultiFileUploaderForEditSub" method="post" enctype="multipart/form-data" style="margin: 5%"> 

                <%
                    String name = request.getParameter("name");
                    String description = "";
                    String filepath = "";
                    String c_name = "";
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("driver loaded successfully");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                    System.out.println("connection built");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from sub_categories where sub_c_name='" + name + "'");
                    if (rs.next()) {
                        c_name = rs.getString("c_name");
                        description = rs.getString("description");
                        filepath = rs.getString("photo");

                    }

                %>
                <div class="form-group">
                    <label>sub category name</label>
                    <input class="form-control"  type="text" name="subcatgname" value="<%=name%>" readonly="true"  /><br>
                </div>
                <div class="form-group">
                    <label>select category name</label>
                    <select class="form-control" name=" catg" required>
                        <option selected disabled value="">Select the category</option>
                        <%
                            Class.forName("com.mysql.jdbc.Driver");
                            System.out.println("driver loaded successfully");
                            Connection connn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myproject", "root", "system");
                            System.out.println("connection built");
                            Statement stmtm = conn.createStatement();
                            ResultSet rsp = stmtm.executeQuery("select * from categories");
                            while (rsp.next()) {
                                String cname = rsp.getString("c_name");
                        %>
                        <option><%=cname%></option>
                        <%
                            }
                        %>

                    </select>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea class='form-control' name="text" ><%=description%></textarea><br>
                </div>
                <tr>
                    <td><img src="<%=filepath%>" id="im2" style="width: 150px;height: 150px;"></td>  
                    <td><input type="file" id="f2" name="f2" onchange="readandpreview(this, 'im2')"  /> </td>
                </tr>
                <br>     
                <input class="btn  btn-lg btn-primary" type="submit" value="Edit" style="margin-left: 40%"/><br>

            </form>
        </div>
    </body>
</html>
