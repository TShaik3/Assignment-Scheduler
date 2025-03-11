package com.example.assignmentscheduler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

public class newAssignment extends AppCompatActivity {
    TextInputLayout nameText;
    TextInputLayout startDateText;
    TextInputLayout endDateText;
    TextInputLayout typeText;
    TextInputLayout lengthText;
    Button saveButton;

    Assignment newAssign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assignment);

        nameText = findViewById(R.id.nameEditText);
        startDateText = findViewById(R.id.startEditText);
        endDateText = findViewById(R.id.endEditText);
        typeText = findViewById(R.id.typeEditText);
        lengthText = findViewById(R.id.lengthEditText);
        saveButton = findViewById(R.id.saveButton);

        newAssign = new Assignment();

        MaterialDatePicker.Builder startDatePicker = MaterialDatePicker.Builder.datePicker();
        startDatePicker.setTitleText("SELECT A DATE");
        final MaterialDatePicker mSDatePicker = startDatePicker.build();

        startDateText.setOnClickListener(v -> {
            mSDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        mSDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startDateText.getEditText().setText(mSDatePicker.getHeaderText());
                }
        });

        MaterialDatePicker.Builder endDatePicker = MaterialDatePicker.Builder.datePicker();
        endDatePicker.setTitleText("SELECT A DATE");
        final MaterialDatePicker mEDatePicker = endDatePicker.build();

        endDateText.setOnClickListener(v -> {
            mEDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        mEDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endDateText.getEditText().setText(mEDatePicker.getHeaderText());
                }
        });

        saveButton.setOnClickListener(v -> {
            String name = nameText.toString();
            String[] startDateDetail = startDateText.toString().split("/");
            String[] endDateDetail = endDateText.toString().split("/");
            String type = typeText.toString();
            String length = lengthText.toString();

            newAssign.modifyAssignment(name, startDateDetail, endDateDetail, type, length);

            savedInstanceState.putParcelable("assignment", (Parcelable) newAssign);
        });
    }
}
