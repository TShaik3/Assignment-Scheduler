package com.example.assignmentscheduler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CalendarView mainCalender;
    Button createAssignment;
    RecyclerView mainList;
    AssignAdapter adapter;
    Assignment anAssignment;
    ArrayList<Assignment> listOfAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCalender = findViewById(R.id.calendarView);
        createAssignment = findViewById(R.id.addButton);
        mainList = findViewById(R.id.recyclerView);

        loaddata();
        buildRecyclerView();

        createAssignment.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewAssignment.class);
            startActivity(intent);
        });

        getNewAssignment();

        AssignAdapter adapter = new AssignAdapter(listOfAssignments, getApplicationContext());
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        mainList.setAdapter(adapter);
    }

    private void getNewAssignment() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("newAssignment", null);
        Type type = new TypeToken<Assignment>() {}.getType();
        anAssignment = gson.fromJson(json, type);
    }

    @Override
    protected void onPause() {
        super.onPause();
        savedata();
    }

    private void buildRecyclerView() {
        adapter = new AssignAdapter(listOfAssignments, MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(layoutManager);
        mainList.setAdapter(adapter);
    }

    private void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("listOfAssignments", null);
        Type type = new TypeToken<ArrayList<Assignment>>() {}.getType();
        listOfAssignments = gson.fromJson(json, type);
        if(listOfAssignments == null) {
            listOfAssignments = new ArrayList<>();
        }
    }

    private void savedata() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listOfAssignments);
        editor.putString("listOfAssignments", json);
        editor.apply();
    }
}