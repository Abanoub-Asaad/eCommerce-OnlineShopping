package com.example.e_commerce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.e_commerce.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    private ElegantNumberButton numberBtn;
    private Button addToCartBtn;
    private TextView productName, productDescription, productPrice;
    private ImageView productImage;
    private String productID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("product_id");

        productName = (TextView) findViewById(R.id.product_name_details);
        productDescription = (TextView) findViewById(R.id.product_description_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
        numberBtn = (ElegantNumberButton) findViewById(R.id.number_of_items_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        addToCartBtn = (Button) findViewById(R.id.add_to_cart_btn_details);

        getProductDetails(productID);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProductToCartList();
            }
        });
    }

    private void AddProductToCartList(){

    }

    private void getProductDetails(String productID) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Products products = dataSnapshot.getValue(Products.class);

                    productName.setText(products.getProduct_name());
                    productPrice.setText(products.getProduct_price());
                    productDescription.setText(products.getProduct_description());
                    Picasso.get().load(products.getProduct_image()).into(productImage);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

