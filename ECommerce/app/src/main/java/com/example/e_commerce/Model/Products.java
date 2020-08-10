package com.example.e_commerce.Model;

public class Products {

        private static String product_name, product_price, product_description, product_id, date, time, product_image, category;

        Products(){

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

        public static String getProduct_name() {
                return product_name;
        }

        public static void setProduct_name(String product_name) {
                Products.product_name = product_name;
        }

        public static String getProduct_price() {
                return product_price;
        }

        public static void setProduct_price(String product_price) {
                Products.product_price = product_price;
        }

        public static String getProduct_description() {
                return product_description;
        }

        public static void setProduct_description(String product_description) {
                Products.product_description = product_description;
        }

        public static String getProduct_id() {
                return product_id;
        }

        public static void setProduct_id(String product_id) {
                Products.product_id = product_id;
        }

        public static String getDate() {
                return date;
        }

        public static void setDate(String date) {
                Products.date = date;
        }

        public static String getTime() {
                return time;
        }

        public static void setTime(String time) {
                Products.time = time;
        }

        public static String getProduct_image() {
                return product_image;
        }

        public static void setProduct_image(String product_image) {
                Products.product_image = product_image;
        }

        public static String getCategory() {
                return category;
        }

        public static void setCategory(String category) {
                Products.category = category;
        }
}
