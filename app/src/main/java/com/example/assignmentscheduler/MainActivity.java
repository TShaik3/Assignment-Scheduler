package com.example.assignmentscheduler;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CalendarView mainCalender;
    Button createAssignment;
    RecyclerView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCalender = findViewById(R.id.calendarView);
        createAssignment = findViewById(R.id.button);
        mainList = findViewById(R.id.recyclerView);

        ArrayList<Assignment> listOfAssignments = new ArrayList<>();

        createAssignment.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), newAssignment.class);
            startActivity(intent);

            listOfAssignments.add((Assignment) savedInstanceState.get("assignment"));

        });

        AssignAdapter adapter = new AssignAdapter(listOfAssignments);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        mainList.setAdapter(adapter);
    }
}