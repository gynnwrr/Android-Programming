package com.example.miniprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojectandroid.databinding.ActivityDiplomaBinding;

public class DiplomaActivity extends AppCompatActivity {

    // 3. Declare a variable for the binding class
    private ActivityDiplomaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiplomaBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //med button
        binding.medBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to med activity
                Intent medIntent = new Intent(DiplomaActivity.this, MedActivity.class);
                startActivity(medIntent);
            }
        });

        //eed button
        binding.eedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to eed activity
                Intent eedIntent = new Intent(DiplomaActivity.this, EedActivity.class);
                startActivity(eedIntent);
            }
        });

        //cid button
        binding.cidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to cid activity
                Intent cidIntent = new Intent(DiplomaActivity.this, CidActivity.class);
                startActivity(cidIntent);
            }
        });
    }
}
