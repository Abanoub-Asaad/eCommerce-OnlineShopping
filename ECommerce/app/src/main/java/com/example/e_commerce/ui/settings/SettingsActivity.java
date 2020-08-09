package com.example.e_commerce.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.HomeActivity;
import com.example.e_commerce.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText userPhoneEditText, userNameEditText, userAddressEditText;
    private TextView profileChangeTextBtn, closeTextBtn, saveTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toast.makeText(SettingsActivity.this, "Settings", Toast.LENGTH_SHORT).show();

        profileImageView = (CircleImageView) findViewById(R.id.settings_profile_image);
        userPhoneEditText = (EditText) findViewById(R.id.settings_phone_number);
        userNameEditText = (EditText) findViewById(R.id.settings_full_name);
        userAddressEditText = (EditText) findViewById(R.id.settings_address);

        profileChangeTextBtn = (TextView) findViewById(R.id.profile_image_change_btn);
        closeTextBtn = (TextView) findViewById(R.id.close_settings_btn);
        saveTextBtn = (TextView) findViewById(R.id.update_account_settings_btn);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void userInfoDisplay(CircleImageView profileImageView, EditText userPhoneEditText, EditText userNameEditText, EditText userAddressEditText){

        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference();

    }
}
