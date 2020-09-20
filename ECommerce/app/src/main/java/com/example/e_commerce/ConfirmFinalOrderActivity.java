package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText userName, userPhone, userAddress, userCity, userStreet;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        userName = (EditText) findViewById(R.id.shippment_userName);
        userPhone = (EditText) findViewById(R.id.shippment_userPhoneNumber);
        userAddress= (EditText) findViewById(R.id.shippment_userHomeAddress);
        userCity = (EditText) findViewById(R.id.shippment_userCity);
        userStreet = (EditText) findViewById(R.id.shippment_userStreet);
        confirmBtn = findViewById(R.id.confirm_final_order_btn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userName.getText().toString()))
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Please write your name", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(userPhone.getText().toString()))
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Please write your phone number", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(userAddress.getText().toString()))
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Please write your address", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(userCity.getText().toString()))
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Please write your City", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(userStreet.getText().toString()))
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Please write your Street", Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(ConfirmFinalOrderActivity.this, "Your products are shipped successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
