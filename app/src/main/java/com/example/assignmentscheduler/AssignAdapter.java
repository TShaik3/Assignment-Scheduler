package com.example.assignmentscheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.ViewHolder> {

    private Assignment[] assignmentList;

    public AssignAdapter(ArrayList<Assignment> assignmentList) {
    }

    @NonNull
    @Override
    public AssignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
