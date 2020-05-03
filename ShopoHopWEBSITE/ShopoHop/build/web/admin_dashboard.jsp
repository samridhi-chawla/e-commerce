
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container">
            
            <%
                String username = (String) session.getAttribute("username");
                if (username == null) {
                    response.sendRedirect("admin_login.jsp");
                }
            %>
            <h1>Welcome <%=username%></h1><br>
            <div class="" style="size: 1em; margin: 1%">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <a href="add_category.jsp" >add or manage your category</a>[for eg:men,women,child.......]
            </div>
            <div class="" style="size: 1em; margin: 1%">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <a href="add_sub_category.jsp" >add or manage your sub category</a>[for eg:men shirt,men jean,women top.....]
            </div>
            <div class="" style="size: 1em; margin: 1%">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <a href="manage_sellers.jsp" >Manage Sellers</a>
            </div>
            <div class="" style="size: 1em; margin: 1%">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <a href="manage_adbanner.jsp" >Manage AD Banners</a>
            </div>
            <div class="" style="size: 1em; margin: 1%">
                <span class="glyphicon glyphicon-arrow-right"></span>
                <a href="change_admin_password.jsp" >change password</a><br>
            </div>
            <%
                String msg = request.getParameter("msg");
                if (msg != null) {
            %>
            <label style="color: green"><%= msg%></label>
            <%
                }

            %>


        </div>
    </body>
</html>
