package com.example.miniprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityProgramBinding; //import binding class

public class ProgramActivity extends AppCompatActivity {

    //declare variable for binding class
    private ActivityProgramBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProgramBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //diploma button
        binding.dipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to diploma activity
                Intent diplomaIntent = new Intent(ProgramActivity.this, DiplomaActivity.class);
                startActivity(diplomaIntent);
            }
        });

        //foundation button
        binding.fndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to foundation activity
                Intent foundationIntent = new Intent(ProgramActivity.this, FoundationActivity.class);
                startActivity(foundationIntent);
            }
        });

        //a level button
        binding.alevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gappIntent = new Intent(ProgramActivity.this, GappActivity.class);
                startActivity(gappIntent);
            }
        });
    }
}
