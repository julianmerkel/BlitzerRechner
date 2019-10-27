package com.example.blitzerrechner;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Collections;
import java.util.List;

public class History extends AppCompatActivity {

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

        setUpBottomNav();





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
            int gB = 0;
            for (int i = 0; i < vergehen.size(); i++){
                gB = gB + vergehen.get(i).getBußgeld();
            }
            gesamtBußgeld.setText("Das gesamte Bußgeld beträgt: " + gB + " €");

        }
    }

    private void setUpBottomNav(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNav(History.this,bottomNavigationViewEx);
    }


}
