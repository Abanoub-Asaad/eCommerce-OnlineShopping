package com.example.e_commerce.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.RegisterActivity;

public class SellerRegisterationActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private EditText sellerName, sellerPhone, sellerEmail, sellerPassword, sellerAddress;
    private Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registeration);

        sellerName= findViewById(R.id.seller_name);
        sellerPhone = findViewById(R.id.seller_phone);
        sellerEmail = findViewById(R.id.seller_mail);
        sellerPassword = findViewById(R.id.seller_password);
        sellerAddress = findViewById(R.id.seller_address);

        register = findViewById(R.id.seller_register_btn);
        login = findViewById(R.id.seller_have_account);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSellerAccount();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerRegisterationActivity.this, SellerLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void createSellerAccount() {
        String name = sellerName.getText().toString();
        String phone = sellerPhone.getText().toString();
        String email = sellerEmail.getText().toString();
        String password = sellerPassword.getText().toString();
        String address = sellerAddress.getText().toString();

        if (TextUtils.isEmpty(name))
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(phone))
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(email))
            Toast.makeText(this, "Please write your email", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(password))
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        else if(password.length() < 6)
            Toast.makeText(this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(address))
            Toast.makeText(this, "Please write your address", Toast.LENGTH_SHORT).show();
        else{
            loadingBar = new ProgressDialog(SellerRegisterationActivity.this);
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatePhoneNumber(name, phone, email, password, address);
        }
    }

    private void validatePhoneNumber(String name, String phone, String email, String password, String address) {

    }
}
