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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Model.Users;
import com.example.e_commerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText phoneNumberEditText, passwordEditText;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;
    private TextView adminLink, notAdminLink;
    private ImageView eye_password_login;
    private boolean passwordIsHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_btn);
        phoneNumberEditText = (EditText) findViewById(R.id.login_phone_number_input);
        passwordEditText = (EditText) findViewById(R.id.login_password_input);
        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_check_box);
        adminLink = (TextView) findViewById(R.id.admin_panel_link);
        notAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        eye_password_login = (ImageView) findViewById(R.id.eye_login);
        loadingBar = new ProgressDialog(LoginActivity.this);

        Paper.book().destroy();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                chkBoxRememberMe.setVisibility(View.INVISIBLE);
                parentDbName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                chkBoxRememberMe.setVisibility(View.VISIBLE);
                parentDbName = "Users";
            }
        });

        eye_password_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordIsHidden) {
                    // show password
                    passwordIsHidden = false;
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    ImageView image_view = (ImageView) findViewById(R.id.eye_login);
                    image_view.setImageResource(R.drawable.invisible_eye);
                } else {
                    // hide password
                    passwordIsHidden = true;
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    ImageView image_view = (ImageView) findViewById(R.id.eye_login);
                    image_view.setImageResource(R.drawable.eye);
                }
                // set cursor to the end of the password text
                passwordEditText.setSelection(passwordEditText.getText().toString().length());
            }
        });

    }

    private void loginUser() {
        String phone = phoneNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(phone))
            Toast.makeText(LoginActivity.this, "Please, write your phone number", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(password))
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        else if (password.length() < 6)
            Toast.makeText(this, "Password should contain at least 6 characters", Toast.LENGTH_SHORT).show();
        else {

            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            allowAccessToAccount(phone, password);
        }
    }

    private void allowAccessToAccount(final String phone, final String password) {

        if (chkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        } else {
            Paper.book().destroy();
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(parentDbName).child(phone).exists()) {

                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPassword().equals(password)) {

                        if (parentDbName.equals("Users")) {

                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            System.out.println(Prevalent.currentOnlineUser.getName());

                            startActivity(intent);
                        } else if (parentDbName.equals("Admins")) {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Account with this " + phone + " number doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
