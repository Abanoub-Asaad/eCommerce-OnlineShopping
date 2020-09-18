package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

import io.kommunicate.Kommunicate;

public class SplashActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Connect to Kommunicate and Dialogflow for chatbot
        //Kommunicate.init(getApplicationContext(), "a9bcb7711de0b89c3f34740a58351086");

        //First Slide
        addFragment(new Step.Builder().setTitle("Wecome to Our Online Shopping")
                .setContent("You will find all what you need")
                .setBackgroundColor(Color.parseColor("#df861d")) // int background color
                .setDrawable(R.drawable.b_1) // int top drawable
                .setSummary("Developed by: Abanoub Asaad :)")
                .build());

        //Second Slide
        addFragment(new Step.Builder().setTitle("Electronics")
                .setContent("Laptops, Mobiles, Cameras, TVs, Headphones, ...")//aa3d01
                .setBackgroundColor(Color.parseColor("#e4820b")) // int background color
                .setDrawable(R.drawable.b_2) // int top drawable
                .setSummary("Continue to know more about us")
                .build());

        //Third Slide
        addFragment(new Step.Builder().setTitle("Clothes")
                .setContent("Blouses, Dresses, T-shirts, Sweaters, Shoes, Bags, ...")
                .setBackgroundColor(Color.parseColor("#f57c06")) // int background color
                .setDrawable(R.drawable.b_3) // int top drawable
                .setSummary("Buy any product from any place at any time very easily")
                .build());

        //Fourth Slide
        addFragment(new Step.Builder().setTitle("Fashion Accessories")
                .setContent("Watches, Sunglasses, Necklaces, Hats, Rings, Gloves, ...")
                .setBackgroundColor(Color.parseColor("#f37305")) // int background color
                .setDrawable(R.drawable.b_4) // int top drawable
                .setSummary("Trendy products for the highest customer service")
                .build());
    }


    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void finishTutorial() {
        super.finishTutorial();

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
