package com.example.e_commerce.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.ui.settings.SettingsActivity;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toast.makeText(CartActivity.this, "Cart", Toast.LENGTH_SHORT).show();
    }
}
