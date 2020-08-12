package com.example.e_commerce.Model;

public class Products {

        private String product_name, product_price, product_description, product_id, date, time, product_image, category;

        public Products(){

        }

        public Products(String product_name, String product_price, String product_description, String product_id, String date, String time, String product_image, String category) {
                this.product_name = product_name;
                this.product_price = product_price;
                this.product_description = product_description;
                this.product_id = product_id;
                this.date = date;
                this.time = time;
                this.product_image = product_image;
                this.category = category;
        }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
