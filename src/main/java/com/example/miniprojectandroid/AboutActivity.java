package com.example.miniprojectandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityAboutBinding; //import binding class

public class AboutActivity extends AppCompatActivity {

    //declare variable for binding class
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
