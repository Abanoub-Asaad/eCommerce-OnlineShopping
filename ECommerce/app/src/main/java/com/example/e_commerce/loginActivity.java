package com.example.e_commerce;

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

import com.example.e_commerce.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText phoneNumberEditText, passwordEditText;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_btn);
        phoneNumberEditText = (EditText) findViewById(R.id.login_phone_number_input);
        passwordEditText = (EditText) findViewById(R.id.login_password_input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String phone = phoneNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(phone))
            Toast.makeText(loginActivity.this, "Please, write your phone number", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(password))
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        else if (password.length() < 6)
            Toast.makeText(this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();
        else {
            loadingBar = new ProgressDialog(loginActivity.this);
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            allowAccessToAccount(phone, password);
        }
    }

    private void allowAccessToAccount(final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if(usersData.getPassword().equals(password))
                    {
                        loadingBar.dismiss();
                        Toast.makeText(loginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        loadingBar.dismiss();
                        Toast.makeText(loginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(loginActivity.this, "Account with this "+phone+" number doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
