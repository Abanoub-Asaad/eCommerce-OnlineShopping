package com.example.e_commerce.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.Sellers.SellerProductCategoryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainProductsActivity extends AppCompatActivity {

    private Button applyChangesBtn, deleteProductBtn;
    private EditText name, price, description;
    private ImageView imageView;

    private String productId="";
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_products);

        productId = getIntent().getStringExtra("product_id");
        productRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productId);

        applyChangesBtn = findViewById(R.id.apply_changes_btn);
        deleteProductBtn = findViewById(R.id.delete_product_btn);

        name = findViewById(R.id.product_name_maintain);
        price = findViewById(R.id.product_price_maintain);
        description = findViewById(R.id.product_description_maintain);
        imageView = findViewById(R.id.product_image_maintain);

        displayExistedProductInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

        deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteThisProduct();
            }
        });
    }

    private void deleteThisProduct() {

        productRef.removeValue();

        Toast.makeText(AdminMaintainProductsActivity.this, "Removed Successfully :)", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminMaintainProductsActivity.this, SellerProductCategoryActivity.class);
        startActivity(intent);
        finish();
    }

    private void applyChanges(){
        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();

        if(pName.equals(""))
            Toast.makeText(AdminMaintainProductsActivity.this, "Write down product name", Toast.LENGTH_SHORT).show();
        else if(pPrice.equals(""))
            Toast.makeText(AdminMaintainProductsActivity.this, "Write down product price", Toast.LENGTH_SHORT).show();
        else if(pDescription.equals(""))
            Toast.makeText(AdminMaintainProductsActivity.this, "Write down product description", Toast.LENGTH_SHORT).show();
        else{
            HashMap<String, Object> productMap = new HashMap<>();

            productMap.put("product_id", productId);
            productMap.put("product_name", pName);
            productMap.put("product_price", pPrice);
            productMap.put("product_description", pDescription);

            productRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AdminMaintainProductsActivity.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void displayExistedProductInfo() {

        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String pName = snapshot.child("product_name").getValue().toString();
                    String pPrice = snapshot.child("product_price").getValue().toString();
                    String pDescription = snapshot.child("product_description").getValue().toString();
                    String pImage = snapshot.child("product_image").getValue().toString();

                    name.setText(pName); name.setSelection(pName.length());
                    price.setText(pPrice);
                    description.setText(pDescription);

                    Picasso.get().load(pImage).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
