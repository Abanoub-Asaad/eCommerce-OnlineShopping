package com.example.e_commerce.Sellers;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SellerRegisterationActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private EditText sellerName, sellerPhone, sellerEmail, sellerPassword, sellerAddress;
    private Button register, login;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registeration);

        auth = FirebaseAuth.getInstance();

        sellerName= findViewById(R.id.seller_name);
        sellerPhone = findViewById(R.id.seller_phone);
        sellerEmail = findViewById(R.id.seller_mail);
        sellerPassword = findViewById(R.id.seller_password);
        sellerAddress = findViewById(R.id.seller_address);

        register = findViewById(R.id.seller_register_btn);
        login = findViewById(R.id.seller_have_account);

        loadingBar = new ProgressDialog(this);

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
        final String name = sellerName.getText().toString();
        final String phone = sellerPhone.getText().toString();
        final String email = sellerEmail.getText().toString();
        final String password = sellerPassword.getText().toString();
        final String address = sellerAddress.getText().toString();

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
            loadingBar.setTitle("Create Seller Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            auth .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                                String sid = auth.getCurrentUser().getUid();

                                HashMap<String, Object> sellerMap = new HashMap<>();

                                sellerMap.put("seller_id", sid);
                                sellerMap.put("name", name);
                                sellerMap.put("phone", phone);
                                sellerMap.put("email", email);
                                sellerMap.put("password", password);
                                sellerMap.put("address", address);

                                rootRef.child("Sellers").child(sid).updateChildren(sellerMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                loadingBar.dismiss();

                                                Intent intent = new Intent(SellerRegisterationActivity.this, SellerLoginActivity.class);
                                                startActivity(intent);

                                                Toast.makeText(SellerRegisterationActivity.this, "You are registered successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });
        }
    }
}
