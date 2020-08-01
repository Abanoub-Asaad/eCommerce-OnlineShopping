package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button joinNowButton, loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Made by: Abanoub Asaad",Toast.LENGTH_LONG).show();

        joinNowButton = (Button) findViewById(R.id.main_join_now_btn);
        loginbutton = (Button) findViewById(R.id.main_login_btn);


    }
}
