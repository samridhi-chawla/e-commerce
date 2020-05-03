package com.example.shopohop;


public class cart_item {
    public String p_id,p_name,p_subcategory,p_descrip,p_offer_price,p_photo;
    public int qty;

    public cart_item(String p_id, String p_name, String p_subcategory, String p_descrip, int qty, String p_offer_price, String p_photo) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_subcategory = p_subcategory;
        this.p_descrip = p_descrip;
        this.qty = qty;
        this.p_offer_price = p_offer_price;
        this.p_photo = p_photo;
    }
}
