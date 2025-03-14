package com.example.assignmentscheduler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
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
        buildRecyclerView();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Assignment deletedAssignment = listOfAssignments.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                listOfAssignments.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Snackbar.make(mainList, deletedAssignment.getName(), Snackbar.LENGTH_LONG).setAction("Undo", v -> {
                    listOfAssignments.add(position, deletedAssignment);
                    adapter.notifyItemInserted(position);
                }).show();
            }
        }).attachToRecyclerView(mainList);
    }

    private void getNewAssignment() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("newAssignment", null);
        Type type = new TypeToken<Assignment>() {}.getType();
        anAssignment = gson.fromJson(json, type);
        listOfAssignments.add(anAssignment);
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
        checkIDS(listOfAssignments);
    }

    private void checkIDS(ArrayList<Assignment> list) {
        int latestID = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getID() > latestID) {
                latestID = list.get(i).getID();
            }
            else if (list.get(i).getID() <= latestID){
                list.remove(i);
            }
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