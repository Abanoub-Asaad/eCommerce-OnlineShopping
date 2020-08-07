package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText inputName, inputPhoneNumber, inputPassword;
    private ProgressDialog loadingBar;
    private ImageView eye_password_register;
    private boolean passwordIsHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountButton = (Button) findViewById(R.id.register_btn);
        inputName = (EditText) findViewById(R.id.register_username_input);
        inputPhoneNumber = (EditText) findViewById(R.id.register_phone_input);
        inputPassword = (EditText) findViewById(R.id.register_password_input);
        eye_password_register = (ImageView) findViewById(R.id.eye_register);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatAccount();
            }
        });

        eye_password_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordIsHidden) {
                    // show password
                    passwordIsHidden = false;
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    ImageView image_view  = (ImageView) findViewById(R.id.eye_register);
                    image_view.setImageResource(R.drawable.invisible_eye);
                } else {
                    // hide password
                    passwordIsHidden = true;
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    ImageView image_view  = (ImageView) findViewById(R.id.eye_register);
                    image_view.setImageResource(R.drawable.eye);
                }
                // set cursor to the end of the password text
                inputPassword.setSelection(inputPassword.getText().toString().length());
            }
        });

    }

    private void creatAccount() {

        String name = inputName.getText().toString();
        String phone = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name))
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(phone))
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(password))
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        else if(password.length() < 6)
            Toast.makeText(this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();
        else {
            loadingBar = new ProgressDialog(RegisterActivity.this);
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validatePhoneNumber(name, phone, password);
        }
    }

    private void validatePhoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
