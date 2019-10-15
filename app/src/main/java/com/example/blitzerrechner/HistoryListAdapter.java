package com.example.blitzerrechner;

import android.content.Context;
import android.os.AsyncTask;
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

    private HistoryDao dao;
    private Context mContext;

    HistoryListAdapter(Context context){
        mContext = context;
    }

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

        dao = HistoryRoomDatabase.getDatabase(mContext).historyDao();

        holder.itemView.setOnClickListener((view) -> {
            new DeleteTask().execute(vergehensList.get(position));
        });
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


    class DeleteTask extends AsyncTask<Vergehen, Void, List<Vergehen>>{

        @Override
        protected List<Vergehen> doInBackground(Vergehen... vergehen) {
            dao.delete(vergehen[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Vergehen> vergehen) {
            super.onPostExecute(vergehen);
            setVergehen(vergehen);
        }
    }
}
