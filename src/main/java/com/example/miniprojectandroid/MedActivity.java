package com.example.miniprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.miniprojectandroid.databinding.ActivityMedBinding;
import com.example.miniprojectandroid.databinding.MedDescBinding;

public class MedActivity extends AppCompatActivity {

    private ActivityMedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tukar variable ActivityMedBinding ikut nama file
        binding = ActivityMedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //course name - tukar ikut department
        String[] programTitles = getResources().getStringArray(R.array.med_course);

        //course description - tukar ikut department
        String[] programDescriptions = getResources().getStringArray(R.array.med_desc);

        for (int i = 0; i < programTitles.length; i++) {
            addExpandableItem(programTitles[i], programDescriptions[i]);
        }
    }

    /**
     * Inflates and configures a single expandable item, then adds it to the list.
     * @param title The title of the programme.
     * @param description The description of the programme.
     */
    private void addExpandableItem(String title, String description) {
        LayoutInflater inflater = LayoutInflater.from(this);

        // tukar variable MedDescBinding
        MedDescBinding itemBinding = MedDescBinding.inflate(inflater, binding.expandableListContainer, false);

        itemBinding.courseName.setText(title);
        itemBinding.courseDescription.setText(description);

        itemBinding.titleLayout.setOnClickListener(v -> {
            boolean isVisible = itemBinding.courseDescription.getVisibility() == View.VISIBLE;

            AutoTransition autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
            TransitionManager.beginDelayedTransition(binding.expandableListContainer, autoTransition);

            if (isVisible) {
                // collapse description
                itemBinding.courseDescription.setVisibility(View.GONE);
                itemBinding.arrowIcon.setRotation(0);
                itemBinding.arrowIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.plus));
            } else {
                // expand description
                itemBinding.courseDescription.setVisibility(View.VISIBLE);
                itemBinding.arrowIcon.setRotation(45);
            }
        });

        //check eligibility button
        binding.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to med activity
                Intent checkerIntent = new Intent(MedActivity.this, CheckerActivity.class);
                startActivity(checkerIntent);
            }
        });

        binding.expandableListContainer.addView(itemBinding.getRoot());
    }
}
