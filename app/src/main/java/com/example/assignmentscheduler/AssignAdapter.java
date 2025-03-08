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

    public AssignAdapter(ArrayList<Assignment> assignmentList) { this.assignmentList = assignmentList.toArray(new Assignment[0]); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Assignment assignData = assignmentList[position];
        holder.nameText.setText(assignmentList[position].getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameText = itemView.findViewById(R.id.nameText);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
