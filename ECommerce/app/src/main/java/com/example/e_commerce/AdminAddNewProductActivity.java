package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminAddNewProductActivity extends AppCompatActivity {

    private String CategoryName, ProductName, ProductDescription, ProductPrice;
    private ImageView InputProductImage;
    private Button AddNewProductButton;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        CategoryName = getIntent().getExtras().get("category").toString();
        //Toast.makeText(this, CategoryName, Toast.LENGTH_SHORT).show();

        InputProductImage = (ImageView) findViewById(R.id.select_product_image);
        AddNewProductButton = (Button) findViewById(R.id.add_new_product_btn);
        InputProductName = (EditText) findViewById(R.id.product_name);
        InputProductDescription = (EditText) findViewById(R.id.product_description);
        InputProductPrice = (EditText) findViewById(R.id.product_price);

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProductData();
            }
        });

    }

    private void openGallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }

    private void validateProductData(){
        ProductName = InputProductName.getText().toString();
        ProductDescription = InputProductDescription.getText().toString();
        ProductPrice = InputProductPrice.getText().toString();

        if(ImageUri == null)
            Toast.makeText(this, "Product Image is mandatory !", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(ProductName))
            Toast.makeText(this, "Please write product name", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(ProductDescription))
            Toast.makeText(this, "Please write product description", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(ProductPrice))
            Toast.makeText(this, "Please write product price", Toast.LENGTH_SHORT).show();

    }

    private void storeProductInformation(){

    }
}
