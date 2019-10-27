package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    //Boss-Comment

    ImageView imageViewInnerorts;
    ImageView ImageViewAusserorts;
    Button buttonHistory;
    Button buttonInformation;
    TextView textView;
    Intent intent;
    //Intent intentHistory = new Intent(this, History.class);
    ArrayList<String> parameter;

    private HistoryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBottomNav();

        dao = HistoryRoomDatabase.getDatabase(this).historyDao();

        parameter = new ArrayList<>();

        for (int i =0; i<5;i++){
            parameter.add("");
        }


        intent = new Intent(this, Screen2.class);



        imageViewInnerorts = findViewById(R.id.imageView2);
        ImageViewAusserorts = findViewById(R.id.imageView4);
        textView = findViewById(R.id.textView3);


        imageViewInnerorts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                parameter.set(0,"innerorts");
                intent.putStringArrayListExtra("parameter", parameter);
                startActivity(MainActivity.this.intent);
            }
        });

        ImageViewAusserorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameter.set(0,"ausserorts");
                intent.putStringArrayListExtra("parameter", parameter);
                startActivity(MainActivity.this.intent);
            }
        });


        buttonHistory = findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener((view) -> {
            Intent intentHistory = new Intent(this, History.class);
            startActivity(intentHistory);
        });

        buttonInformation = findViewById(R.id.buttonInformation);
        buttonInformation.setOnClickListener((view) -> {
            Intent intentInformation = new Intent(this, Information.class);
            startActivity(intentInformation);
        });

    }

    private void setUpBottomNav(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
    }

}
