package com.example.blitzerrechner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Vergehen> vergehensList = Collections.emptyList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView vergehensView = holder.itemView.findViewById(R.id.history_item);
        vergehensView.setText(vergehensList.get(position).getVergehen());
    }

    @Override
    public int getItemCount() {
        return vergehensList.size();
    }

    public void setVergehen(List<Vergehen> vergehen){
        this.vergehensList = vergehen;
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
