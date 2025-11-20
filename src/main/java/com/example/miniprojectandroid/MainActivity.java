package com.example.miniprojectandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityMainBinding; //import binding class

public class MainActivity extends AppCompatActivity {

    //declare variable for binding class
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //programmes offered button
        binding.progBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to diploma activity
                Intent diplomaIntent = new Intent(MainActivity.this, ProgramActivity.class);
                startActivity(diplomaIntent);
            }
        });

        //eligibility checker button
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to foundation activity
                Intent foundationIntent = new Intent(MainActivity.this, CheckerActivity.class);
                startActivity(foundationIntent);
            }
        });

        //about us button
        binding.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to about us activity
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

        binding.address.setOnClickListener(v -> {
            String address = "Jalan Ilmiah, Taman Universiti, 43000, Kajang, Selangor, Malaysia";

            Uri uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address));

            //create intent for Chrome
            Intent chromeIntent = new Intent(Intent.ACTION_VIEW, uri);
            chromeIntent.setPackage("com.android.chrome"); // force Chrome

            //check if Chrome is installed
            if (chromeIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chromeIntent);
            } else {
                //fallback: open in default browser
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });



    }
}
