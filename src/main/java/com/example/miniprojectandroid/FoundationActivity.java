package com.example.miniprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityFoundationBinding;

public class FoundationActivity extends AppCompatActivity {

    private ActivityFoundationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityFoundationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //check eligibility button
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to med activity
                Intent checkerIntent = new Intent(FoundationActivity.this, CheckerActivity.class);
                startActivity(checkerIntent);
            }
        });
    }
}
