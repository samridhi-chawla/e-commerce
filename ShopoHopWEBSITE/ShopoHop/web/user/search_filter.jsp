<%@page import="java.sql.*"%>
<%
    
    String search = request.getParameter("search");
    String price_below = request.getParameter("price_below");
    String price_above = request.getParameter("price_above");
    String discount = request.getParameter("discount");
    //System.out.println(sub_category + "sub_cat , pricebelow " + price_below +"priceabove="+price_above+ " discount " + discount);
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //since value of price_below and discount will always be alvailable(since value attribute is set of the range)

    ResultSet rs= stmt.executeQuery("select * from products where p_name like '%" + search + "%' and p_offer_price<=" + price_below + " and p_offer_price>="+price_above+" and ((p_mrp-p_offer_price)/p_mrp)*100>=" + discount);
   
    int flag = 0;
    while (rs.next()) {
        flag++;
        String p_name = rs.getString("p_name");
        String sub_category = rs.getString("sub_category");
        String p_photo = rs.getString("p_photo");
        int product_id = rs.getInt("product_id");
        String p_mrp = rs.getString("p_mrp");
        String p_offer_price = rs.getString("p_offer_price");
%>

<a href="search_product.jsp?p_id=<%=product_id%>">
    <div class="col-xs-3">
        <div class="thumbnail">
            <img src=".<%=p_photo%>">
            <div class='caption'>
                <label class="text-center"><%=p_name%></label>
                <table class='table'>
                    <tr>
                        <td>Type</td>
                        <td><%=sub_category%></td>
                    </tr>
                    <tr>
                        <td>MRP</td>
                        <%
                            if (p_mrp == p_offer_price) {
                        %>
                        <td><%=p_mrp%></td>
                        <%
                        } else {
                        %>
                        <td><span style="text-decoration: line-through"><%=p_mrp%></span> <span><%=p_offer_price%></span></td>
                        <%
                            }
                        %>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</a>
<%
    }
    if (flag == 0) {
        out.println("<label>No Products Found</label>");
    }
%>