<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vmm.cart_item"%>
<%
    String p_id = request.getParameter("p_id");
    String qty = request.getParameter("qty");

    ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from products where product_id='" + p_id + "'");
    if (rs.next()) {
        String p_name = rs.getString("p_name");
        String p_offer_price = rs.getString("p_offer_price");
        String p_photo = rs.getString("p_photo");

        if (al_cart == null) {
            al_cart = new ArrayList<cart_item>();
            al_cart.add(new cart_item(p_id,p_name,qty,p_offer_price,p_photo));
            session.setAttribute("al_cart", al_cart);
        }
        else{
            al_cart.add(new cart_item(p_id,p_name,qty,p_offer_price,p_photo));
            session.setAttribute("al_cart", al_cart);
        }
    }
%>