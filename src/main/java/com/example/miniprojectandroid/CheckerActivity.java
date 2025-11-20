package com.example.miniprojectandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectandroid.databinding.ActivityCheckerBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckerActivity extends AppCompatActivity {

    private ActivityCheckerBinding binding;
    private List<String> scienceSubjectsList; // To hold the list of science subjects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scienceSubjectsList = Arrays.asList(getResources().getStringArray(R.array.science));

        // 1. Set up all the dropdown menus (Spinners)
        setupSpinners();

        //calculate button
        binding.checkBtn.setOnClickListener(v -> {
            listAllEligiblePrograms();
        });
    }

    /**
     * Checks eligibility for all programs and displays a list of the ones the user qualifies for.
     */
    private void listAllEligiblePrograms() {
        // --- Universal Requirement: Must PASS Bahasa Melayu and Sejarah ---
        if (isFail(binding.gradeBM.getSelectedItem().toString()) || isFail(binding.gradeSej.getSelectedItem().toString())) {
            // Add 'false' as the third argument to show the simple "OK" button
            showResult("Sorry, you are not eligible for any of the programmes offered :(", "A pass in both Bahasa Melayu and Sejarah is required for all programmes.", false);
            return;
        }

        List<String> eligiblePrograms = new ArrayList<>();

        // --- Check each program one by one ---
        if (isEligibleForALevel()) {
            eligiblePrograms.add("• A-Level Programme\n");
        }
        if (isEligibleForFoundation()) {
            eligiblePrograms.add("• Foundation Programme\n");
        }
        if (isEligibleForDiplomaGroup1()) {
            eligiblePrograms.add(
                    "• MED | Diploma in Precision Tooling Engineering Technology\n" +
                            "\n• MED | Diploma in Innovative Product Design Engineering Technology\n");
            eligiblePrograms.add(
                    "• EED | Diploma of Mechatronics Engineering Technology\n" +
                            "\n• EED | Diploma of Engineering Technology (Instrumentation and Control)\n" +
                            "\n• EED | Diploma of Electronics Engineering Technology (Computer)\n" +
                            "\n• EED | Diploma in Engineering Technology (Sustainable Energy and Power Distribution)\n" +
                            "\n• EED | Diploma in Autotronics Engineering Technology\n" +
                            "\n• MED | Diploma of Mechanical Engineering Technology (CNC Precision)\n" +
                            "\n• MED | Diploma of Mechanical Engineering Technology (Manufacturing)\n" +
                            "\n• MED | Diploma in Industrial Quality Engineering Technology\n");
        }
        if (isEligibleForDiplomaSoftwareEngineering()) {
            eligiblePrograms.add("• CID | Diploma in Software Engineering");
        }
        if (isEligibleForDiplomaGroup2()) {
            eligiblePrograms.add(
                    "\n• CID | Diploma in Creative Multimedia\n" +
                            "\n• CID | Diploma in Cyber Security Technology\n" +
                            "\n• MED | Diploma in Engineering Technology (Industrial Design)\n" +
                            "\n• MED | Diploma in Engineering Technology (Machine Tools Maintenance)\n");
        }

        // --- Display the final result ---
        if (eligiblePrograms.isEmpty()) {
            // User is NOT eligible, show a simple "OK" button
            showResult("Sorry, you are not eligible for any of the programmes offered :(", "Please check the minimum entry requirements on GMI Official Website and try again.", false);
        } else {
            StringBuilder messageBuilder = new StringBuilder("You are eligible for the following programs:\n");
            for (String program : eligiblePrograms) {
                messageBuilder.append("\n").append(program);
            }
            // User IS eligible, show the "APPLY NOW!" button
            showResult("Congratulations!", messageBuilder.toString(), true);
        }
    }

    // --- BOOLEAN ELIGIBILITY CHECKS FOR EACH PROGRAM ---

    private boolean isEligibleForALevel() {
        // At least C in: BI, Maths, Fizik, Kimia, Add Maths, 2 others (Total 7 Credits)
        int credits = 0;
        if (isCredit(binding.gradeBI.getSelectedItem().toString())) credits++;
        if (isCredit(binding.gradeMaths.getSelectedItem().toString())) credits++;

        List<String> requiredSubjects = Arrays.asList("Fizik", "Kimia", "Matematik Tambahan");
        if (countCreditsForSpecificSubjects(requiredSubjects) < 3) return false;
        credits += 3;

        credits += countCreditsForOtherSubjects(requiredSubjects, 2);
        return credits >= 7;
    }

    private boolean isEligibleForFoundation() {
        // At least C in: BM, BI, Maths, Add Maths, Fizik, Kimia (Total 6 Credits)
        int credits = 0;
        if (isCredit(binding.gradeBM.getSelectedItem().toString())) credits++;
        if (isCredit(binding.gradeBI.getSelectedItem().toString())) credits++;
        if (isCredit(binding.gradeMaths.getSelectedItem().toString())) credits++;

        List<String> requiredSubjects = Arrays.asList("Fizik", "Kimia", "Matematik Tambahan");
        if (countCreditsForSpecificSubjects(requiredSubjects) < 3) return false;
        credits += 3;

        return credits >= 6;
    }

    private boolean isEligibleForDiplomaGroup1() {
        // At least 3 credits: Maths, 1 Science/Technical, 1 other
        if (!isCredit(binding.gradeMaths.getSelectedItem().toString())) return false;

        int scienceCredits = countCreditsForScienceSubjects(1);
        if (scienceCredits < 1) return false;

        int otherCredits = countCreditsForOtherSubjects(getScienceSubjects(), 1);
        return (1 + scienceCredits + otherCredits) >= 3;
    }

    private boolean isEligibleForDiplomaSoftwareEngineering() {
        // At least C in Any 3 subjects (inclusive of Matematik)
        if (!isCredit(binding.gradeMaths.getSelectedItem().toString())) return false;

        int otherCredits = countCreditsForOtherSubjects(new ArrayList<>(), 2);
        return (1 + otherCredits) >= 3;
    }

    private boolean isEligibleForDiplomaGroup2() {
        // At least 3 credits in any subjects
        int totalCredits = 0;
        if (isCredit(binding.gradeBM.getSelectedItem().toString())) totalCredits++;
        if (isCredit(binding.gradeBI.getSelectedItem().toString())) totalCredits++;
        if (isCredit(binding.gradeMaths.getSelectedItem().toString())) totalCredits++;
        if (isCredit(binding.gradeSej.getSelectedItem().toString())) totalCredits++;

        totalCredits += countCreditsForOtherSubjects(Arrays.asList("Bahasa Melayu", "Bahasa Inggeris", "Matematik", "Sejarah"), 5);
        return totalCredits >= 3;
    }

    // --- HELPER METHODS to count credits ---

    private int countCreditsForSpecificSubjects(List<String> requiredSubjects) {
        List<String> selectedSubjects = getSelectedSubjects();
        List<Spinner> gradeSpinners = getSelectedGradeSpinners();
        int creditCount = 0;
        for (String subjectToFind : requiredSubjects) {
            int index = selectedSubjects.indexOf(subjectToFind);
            if (index != -1 && isCredit(gradeSpinners.get(index).getSelectedItem().toString())) {
                creditCount++;
            }
        }
        return creditCount;
    }

    private int countCreditsForScienceSubjects(int limit) {
        List<String> selectedSubjects = getSelectedSubjects();
        List<Spinner> gradeSpinners = getSelectedGradeSpinners();
        List<String> scienceSubjects = getScienceSubjects();
        int creditCount = 0;
        for (int i = 0; i < selectedSubjects.size(); i++) {
            if (creditCount >= limit) break;
            if (scienceSubjects.contains(selectedSubjects.get(i))) {
                if (isCredit(gradeSpinners.get(i).getSelectedItem().toString())) {
                    creditCount++;
                }
            }
        }
        return creditCount;
    }

    private int countCreditsForOtherSubjects(List<String> subjectsToExclude, int limit) {
        List<String> selectedSubjects = getSelectedSubjects();
        List<Spinner> gradeSpinners = getSelectedGradeSpinners();
        int creditCount = 0;
        Set<String> exclusionSet = new HashSet<>(subjectsToExclude);

        for (int i = 0; i < selectedSubjects.size(); i++) {
            if (creditCount >= limit) break;
            String subject = selectedSubjects.get(i);
            if (!subject.equals("SUBJECT") && !exclusionSet.contains(subject)) {
                if (isCredit(gradeSpinners.get(i).getSelectedItem().toString())) {
                    creditCount++;
                }
            }
        }
        return creditCount;
    }

    // --- General Helper Methods ---

    private boolean isCredit(String grade) {
        return "A+".equals(grade) || "A".equals(grade) || "A-".equals(grade) || "B+".equals(grade) || "B".equals(grade) || "C+".equals(grade) || "C".equals(grade);
    }

    private boolean isFail(String grade) {
        // "GRADE" is used as the default item in the string arrays, which acts like a "not selected" state.
        return "F".equals(grade) || "GRADE".equals(grade);
    }

    /**
     * Displays the result in a dialog.
     * @param title The title of the dialog.
     * @param message The main message to show.
     * @param isEligible If true, it will show an "APPLY NOW!" button. If false, it will show a simple "OK" button.
     */
    private void showResult(String title, String message, boolean isEligible) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message);

        if (isEligible) {
            // If the user is eligible, add the "APPLY NOW!" button
            builder.setPositiveButton("APPLY NOW!", (dialog, which) -> {
                // Create an Intent to open a web browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
                startActivity(browserIntent);
            });
            // Also add a "CANCEL" button to simply close the dialog
            builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());
        } else {
            // If the user is not eligible, just show a simple "OK" button
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        }

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // --- DATA & SPINNER SETUP ---

    private List<String> getSelectedSubjects() {
        return Arrays.asList(
                binding.Sc1.getSelectedItem().toString(),
                binding.Sc2.getSelectedItem().toString(),
                binding.best1.getSelectedItem().toString(),
                binding.best2.getSelectedItem().toString(),
                binding.best3.getSelectedItem().toString()
        );
    }

    private List<Spinner> getSelectedGradeSpinners() {
        return Arrays.asList(
                binding.gradeSc1, binding.gradeSc2,
                binding.gradeBest1, binding.gradeBest2, binding.gradeBest3
        );
    }

    private List<String> getScienceSubjects() {
        // Now loads the list from the member variable initialized in onCreate
        return scienceSubjectsList;
    }

    private void setupSpinners() {
        String[] grades = {"GRADE", "A+", "A", "A-", "B+", "B", "C+", "C", "D", "E", "F"};
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grades);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<Spinner> allGradeSpinners = Arrays.asList(
                binding.gradeBM, binding.gradeBI, binding.gradeMaths, binding.gradeSej,
                binding.gradeSc1, binding.gradeSc2, binding.gradeBest1, binding.gradeBest2, binding.gradeBest3
        );
        for (Spinner spinner : allGradeSpinners) {
            spinner.setAdapter(gradeAdapter);
        }

        // Adapter for Science/Technical/Vocation subjects (for Sc1 and Sc2)
        ArrayAdapter<CharSequence> scienceAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.science,
                android.R.layout.simple_spinner_item
        );
        scienceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.Sc1.setAdapter(scienceAdapter);
        binding.Sc2.setAdapter(scienceAdapter);

        // Adapter for "Other" subjects (for best1, best2, best3)
        ArrayAdapter<CharSequence> otherAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.other,
                android.R.layout.simple_spinner_item
        );
        otherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.best1.setAdapter(otherAdapter);
        binding.best2.setAdapter(otherAdapter);
        binding.best3.setAdapter(otherAdapter);
    }
}
