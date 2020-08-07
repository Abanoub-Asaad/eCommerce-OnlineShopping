package com.example.e_commerce.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.e_commerce.HomeActivity;
import com.example.e_commerce.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toast.makeText(SettingsActivity.this, "Settings", Toast.LENGTH_SHORT).show();
    }
}
