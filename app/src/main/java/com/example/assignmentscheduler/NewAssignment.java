package com.example.assignmentscheduler;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class NewAssignment extends AppCompatActivity {
    TextInputLayout nameTextLayout;
    TextInputLayout startDateLayout;
    TextInputLayout endDateLayout;
    TextInputLayout typeTextLayout;
    TextInputLayout lengthTextLayout;
    Button saveButton;
    Button cancelButton;

    Assignment newAssign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assignment);

        nameTextLayout = findViewById(R.id.nameEditText);
        startDateLayout = findViewById(R.id.startEditText);
        endDateLayout = findViewById(R.id.endEditText);
        typeTextLayout = findViewById(R.id.typeEditText);
        lengthTextLayout = findViewById(R.id.lengthEditText);
        saveButton = findViewById(R.id.saveButton);

        newAssign = new Assignment();

        MaterialDatePicker.Builder startDatePicker = MaterialDatePicker.Builder.datePicker();
        startDatePicker.setTitleText("SELECT A DATE");
        final MaterialDatePicker mSDatePicker = startDatePicker.build();

        startDateLayout.setEndIconOnClickListener(v -> {
            mSDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        mSDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startDateLayout.getEditText().setText(mSDatePicker.getHeaderText());
                }
        });

        MaterialDatePicker.Builder endDatePicker = MaterialDatePicker.Builder.datePicker();
        endDatePicker.setTitleText("SELECT A DATE");
        final MaterialDatePicker mEDatePicker = endDatePicker.build();

        endDateLayout.setEndIconOnClickListener(v -> {
            mEDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        mEDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endDateLayout.getEditText().setText(mEDatePicker.getHeaderText());
                }
        });

        saveButton.setOnClickListener(v -> {
            String name = nameTextLayout.getEditText().getText().toString();
            String[] startDateDetail = startDateLayout.getEditText().getText().toString().split("/");
            String[] endDateDetail = endDateLayout.getEditText().getText().toString().split("/");
            String type = typeTextLayout.getEditText().getText().toString();
            String length = lengthTextLayout.getEditText().getText().toString();

            newAssign.modifyAssignment(name, startDateDetail, endDateDetail, type, length);
            saveAssignment(newAssign);
            finish();
        });

//        cancelButton.setOnClickListener(v -> {
//            finish();
//        });
    }

    private void saveAssignment(Assignment newAssign) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(newAssign);
        editor.putString("newAssignment", json);
        editor.apply();
    }
}
