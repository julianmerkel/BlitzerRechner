package com.example.blitzerrechner;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class History extends AppCompatActivity {

    private HistoryDao dao;
    private RecyclerView recyclerView;
    private HistoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        adapter = new HistoryListAdapter();

        recyclerView = findViewById(R.id.vergehen_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dao = HistoryRoomDatabase.getDatabase(this).historyDao();
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadeVergehensTask().execute();
    }

    class LadeVergehensTask extends AsyncTask<Void, Void, List<Vergehen>> {
        @Override
        protected List<Vergehen> doInBackground(Void... voids){
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Vergehen> vergehen){
            super.onPostExecute(vergehen);
            adapter.setVergehen(vergehen);
        }
    }
}
