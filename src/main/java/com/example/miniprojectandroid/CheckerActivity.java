package com.example.miniprojectandroid;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojectandroid.databinding.ActivityCheckerBinding;

public class CheckerActivity extends AppCompatActivity {

    private ActivityCheckerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCheckerBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        // grade
        String[] grades = {"GRADE", "A+", "A", "A-", "B+", "B", "C+", "C", "D", "E", "F"};
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grades);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply to all grade spinners
        binding.gradeBM.setAdapter(gradeAdapter);
        binding.gradeBI.setAdapter(gradeAdapter);
        binding.gradeMaths.setAdapter(gradeAdapter);
        binding.gradeSej.setAdapter(gradeAdapter);
        binding.gradeSc1.setAdapter(gradeAdapter);
        binding.gradeSc2.setAdapter(gradeAdapter);
        binding.gradeBest1.setAdapter(gradeAdapter);
        binding.gradeBest2.setAdapter(gradeAdapter);

        // subject science/technical/vocational spinners
        String[] subSc = getResources().getStringArray(R.array.science);
        ArrayAdapter<String> subScAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subSc);
        subScAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.Sc1.setAdapter(subScAdapter);
        binding.Sc2.setAdapter(subScAdapter);

        // other subject
        String[] subOther = getResources().getStringArray(R.array.other);
        ArrayAdapter<String> subOtherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subOther);
        subOtherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // other subject
        binding.best1.setAdapter(subOtherAdapter);
        binding.best2.setAdapter(subOtherAdapter);

        // education qualification
        String[] educationLevels = {"SPM"};
        ArrayAdapter<String> eduAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, educationLevels);
        eduAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEducation.setAdapter(eduAdapter);

        // Button click listener
        binding.checkBtn.setOnClickListener(v -> {
            // You can add merit calculation logic here
        });


    }
}
