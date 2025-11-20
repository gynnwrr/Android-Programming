package com.example.miniprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityGappBinding;

public class GappActivity extends AppCompatActivity {

    private ActivityGappBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityGappBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //check eligibility button
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to med activity
                Intent checkerIntent = new Intent(GappActivity.this, CheckerActivity.class);
                startActivity(checkerIntent);
            }
        });
    }
}
