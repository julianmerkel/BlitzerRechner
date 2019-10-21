package com.example.blitzerrechner;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class History extends AppCompatActivity {

    private List<Vergehen> vergehensList = Collections.emptyList();
    private HistoryDao dao;
    private RecyclerView recyclerView;
    private HistoryListAdapter adapter;
    private TextView gesamtBußgeld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        adapter = new HistoryListAdapter(this);

        gesamtBußgeld = findViewById(R.id.gesamtBußgeld);
        recyclerView = findViewById(R.id.vergehen_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dao = HistoryRoomDatabase.getDatabase(this).historyDao();


    /*    int gB = 0;
        for (int i = 0; i < vergehensList.size(); i++){
            gB = gB + vergehensList.get(i).getBußgeld();
        }

        gesamtBußgeld.setText("Gesamtes Bußgeld: " + gB); */


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
