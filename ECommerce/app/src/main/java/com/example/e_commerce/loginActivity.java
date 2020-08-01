package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText phoneNumberEditText, passwordEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_btn);
        phoneNumberEditText = (EditText) findViewById(R.id.login_phone_number_input);
        passwordEditText = (EditText) findViewById(R.id.login_password_input);
    }
}
