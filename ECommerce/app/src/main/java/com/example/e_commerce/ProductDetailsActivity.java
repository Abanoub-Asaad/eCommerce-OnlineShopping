package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.e_commerce.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.FloatingActionButton;
import com.rey.material.widget.ImageView;
import com.rey.material.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProductDetailsActivity extends AppCompatActivity {

    private FloatingActionButton addToCart;
    private ImageView productImage;
    private ElegantNumberButton numberBtn;
    private TextView productName, productDescription, productPrice;
    private String productID ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("product_id");


//        addToCart = (FloatingActionButton) findViewById(R.id.add_product_to_cart_fab);
//        productImage = (ImageView) findViewById(R.id.product_image_details);
//        numberBtn = (ElegantNumberButton) findViewById(R.id.number_of_items_btn);
//
//        productName = (TextView) findViewById(R.id.product_name_details);
//        productDescription = (TextView) findViewById(R.id.product_description_details);
//        productPrice = (TextView) findViewById(R.id.product_price_details);

         //getProductDetails(productID);
    }

    private void getProductDetails(String productID){

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("products");

        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Products products = snapshot.getValue(Products.class);


                    //Toast.makeText(ProductDetailsActivity.this, products.getProduct_name().toString(), Toast.LENGTH_SHORT ).show();
                    System.out.println(products.getProduct_name().toString());

                    productName.setText(products.getProduct_name());
                    productDescription.setText(products.getProduct_description());
                    productPrice.setText("Price = "+products.getProduct_price());

                    Picasso.get().load(products.getProduct_image()).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
