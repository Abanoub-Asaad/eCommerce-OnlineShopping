package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class SplashActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //First Slide
        addFragment(new Step.Builder().setTitle("Wecome to Our Online Shopping")
                .setContent("You will find all what you need")
                .setBackgroundColor(Color.parseColor("#171734")) // int background color
                .setDrawable(R.drawable.b_1) // int top drawable
                .setSummary("Developed by: Abanoub Asaad :)")
                .build());

        //Second Slide
        addFragment(new Step.Builder().setTitle("Electronics")
                .setContent("Laptops,Mobiles, Cameras, TVs, ...")
                .setBackgroundColor(Color.parseColor("#171734")) // int background color
                .setDrawable(R.drawable.b_2) // int top drawable
                .build());

        //Third Slide
        addFragment(new Step.Builder().setTitle("Clothes")
                .setContent("Blouses, Dresses, T-shirts, Sweaters, ...")
                .setBackgroundColor(Color.parseColor("#171734")) // int background color
                .setDrawable(R.drawable.b_3) // int top drawable
                .build());

        //Fourth Slide
        addFragment(new Step.Builder().setTitle("Fashion Accessories")
                .setContent("Watches, Sunglasses, Necklaces, Hats, ...")
                .setBackgroundColor(Color.parseColor("#171734")) // int background color
                .setDrawable(R.drawable.b_4) // int top drawable
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
