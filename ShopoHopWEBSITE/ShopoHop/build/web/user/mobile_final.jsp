<%@page import="java.io.DataInputStream"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="jdk.nashorn.api.scripting.JSObject"%>
<%@page import="vmm.SMSsender"%>
<%@page import="vmm.cart_item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<!--//sms send to both user and merchant and db entry-->
<%
    DataInputStream dis=new DataInputStream(request.getInputStream());
    String object = dis.readLine();
    System.out.println(object+" from android");
    JSONParser parser = new JSONParser();
    JSONObject jSONObject = (JSONObject) parser.parse(object);
    System.out.println(jSONObject);

    JSONArray al_cart = (JSONArray) jSONObject.get("al_cart");
    
    String email = jSONObject.get("email").toString();
    String net_amt = jSONObject.get("net_amt").toString();
    String vat = jSONObject.get("vat").toString();
    String taxes = jSONObject.get("taxes").toString();
    String delivery_charges = jSONObject.get("delivery_charges").toString();
    String net_amt_payable = jSONObject.get("net_payable_amt").toString();
    java.util.Date d = new java.util.Date();
    Date today = new Date(d.getTime());
    String booking_date = today.toString();
    long d2=d.getTime()+24*60*60*1000*7;
    Date del_date=new Date(d2);
    String delivery_date = del_date.toString();
    String address_id = jSONObject.get("add_id").toString();
    String payment_mode = jSONObject.get("payment_mode").toString();

    String customer_phone = ""; //needed to send sms to customer
    int add_id = 0;
    if (address_id != null) {
        add_id = Integer.parseInt(address_id);
    }
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs = stmt.executeQuery("select * from orders");
    rs.moveToInsertRow();
    rs.updateString("order_amt", net_amt);
    rs.updateString("taxes", taxes);
    rs.updateString("vat", vat);
    rs.updateString("delivery_charges", delivery_charges);
    rs.updateString("net_amt", net_amt_payable);
    rs.updateString("delivery_date", delivery_date);
    rs.updateString("booking_date", booking_date);
    rs.updateInt("address_id", add_id);
    rs.updateString("user_email", email);
    rs.updateString("payment_mode", payment_mode);
    rs.updateString("status", "active");
    rs.insertRow();

    Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs1 = stmt1.executeQuery("select max(order_id) as order_id from orders");
    int order_id = 0;
    if (rs1.next()) {
        order_id = rs1.getInt("order_id");
    }
    Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    ResultSet rs2 = stmt2.executeQuery("select * from order_details");

    for(int i=0;i<al_cart.size();i++)
    {
        JSONObject single=(JSONObject)al_cart.get(i);
//    }
//
//    for (int i = 0; i < al_cart.size(); i++) {
        //cart_item item = (cart_item)single.get(i);
        String product_id=(String)single.get("p_id");
        int p_id = Integer.parseInt(product_id);
        
        String p_name = (String)single.get("p_name");
        String unit_price = (String)single.get("p_offer_price");
        long q=(Long)single.get("qty");
        String qty = String.valueOf(q);
        String p_photo = (String)single.get("p_photo");
        rs2.moveToInsertRow();
        rs2.updateInt("order_id", order_id);
        rs2.updateInt("product_id", p_id);
        rs2.updateString("product_name", p_name);
        rs2.updateString("p_offer_price", unit_price);
        rs2.updateString("qty", qty);
        rs2.updateString("product_photo", p_photo);
        rs2.insertRow();

        //send sms to merchant regarding this product_id
        Statement stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs3 = stmt3.executeQuery("select phone,email from merchant,products where products.product_id='" + p_id + "' and products.seller_email=merchant.email ");
        if (rs3.next()) {
            String merchant_phone = rs3.getString("phone");
            String merchant_email = rs3.getString("email");
            String customer = "";

            Statement stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs4 = stmt4.executeQuery("select * from users,user_addresses where users.email='" + email + "' and user_addresses.add_id=" + add_id + "");
            if (rs4.next()) {
                customer_phone = rs4.getString("phone_no"); //needed to send sms to customer
                customer = rs4.getString("name") + "," + rs4.getString("email") + "," + customer_phone + ", " + rs4.getString("address") + "," + rs4.getString("city") + "," + rs4.getString("state") + ", " + rs4.getString("pincode") + ". Payment is via " + payment_mode;
            }
            String merchant_msg = "Your product " + p_name + " with Product_id: " + p_id + " is booked to deliver by " + delivery_date + " to " + customer;
            vmm.SMSsender obj = new SMSsender(merchant_phone, merchant_msg, "text");
            new Thread(obj).start();
            //msg sent to merchant
        }
    }
    //sending sms to customer
    String customer_msg = "Thank You for shoping with us. Your order is is " + order_id + ". You will receive your order by " + delivery_date;
    vmm.SMSsender obj1 = new SMSsender(customer_phone, customer_msg, "text");
    new Thread(obj1).start();

    out.println("success");

%>
