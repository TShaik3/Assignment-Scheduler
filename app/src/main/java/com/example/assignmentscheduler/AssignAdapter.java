package com.example.assignmentscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignAdapter extends RecyclerView.Adapter<AssignAdapter.AssignViewHolder> {

    private ArrayList<Assignment> assignmentList;

    private Context mcontext;

    public AssignAdapter(ArrayList<Assignment> assignmentList, Context mcontext) {
        this.assignmentList = assignmentList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public AssignAdapter.AssignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AssignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignAdapter.AssignViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        if (assignment == null) {
            return;
        }
        holder.assignmentName.setText(assignment.getName());
        holder.assignmentDueDate.setText(assignment.printTotalDate());
        holder.assignmentType.setText(assignment.getType());
        holder.timeLeft.setText(assignment.getTimeBetween());
        holder.progressBar.setProgress(assignment.getProgress());
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public static class AssignViewHolder extends RecyclerView.ViewHolder {
        private final TextView assignmentName;
        private final TextView assignmentDueDate;
        private final TextView timeLeft;
        private final TextView assignmentType;
        private final ProgressBar progressBar;

        public AssignViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentName = itemView.findViewById(R.id.assignmentName);
            assignmentDueDate = itemView.findViewById(R.id.assignmentDue);
            timeLeft = itemView.findViewById(R.id.timeText);
            assignmentType = itemView.findViewById(R.id.typeText);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }
}
