package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Screen3 extends AppCompatActivity {

    TextView textView;
    ArrayList<String> parameter;
    Button buttonSpeichern;
    Button buttonHistory;

    private HistoryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

        dao = HistoryRoomDatabase.getDatabase(this).historyDao();

        textView = findViewById(R.id.textView3);

        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");

        String result = "";
        for (int i = 0; i < parameter.size(); i++) {
            result = result + parameter.get(i) + " ";
        }

        String ort = parameter.get(0);
        int erlaubt = Integer.parseInt(parameter.get(1));
        int gefahren = Integer.parseInt(parameter.get(2));
        String zuSchnell = Integer.toString(gefahren - erlaubt);
        String zuSchnellmitToleranz = "";
        String res = "";

        if(gefahren <= erlaubt){
            res = "Du bist doch überhaupt nicht zu schnell gefahren du Otto. Alles richtig gemacht - du kannst dein Geld behalten.";
        }else {
            if (gefahren <= 100) {
                zuSchnellmitToleranz = Integer.toString(gefahren - erlaubt - 3);
            } else {
                double d = 0.97 * gefahren;
                int t = (int) d;
                zuSchnellmitToleranz = Integer.toString(t - erlaubt);
            }
            res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren.";

            if (Boolean.parseBoolean(parameter.get(4)) == true) {
                res = res + " Abzüglich Toleranz sind das " + zuSchnellmitToleranz + " km/h zu schnell.";
            }
        }
        textView.setText(res);

        buttonSpeichern = findViewById(R.id.button_speichern);
        buttonSpeichern.setOnClickListener(view -> {
            saveVergehenOnClick();
        });

        buttonHistory = findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener((view) -> {
            Intent intentHistory = new Intent(this, History.class);
            startActivity(intentHistory);
        });

    }

    private void saveVergehenOnClick() {
        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");
        String ort = parameter.get(0);
        int erlaubt = Integer.parseInt(parameter.get(1));
        int gefahren = Integer.parseInt(parameter.get(2));
        String zuSchnell = Integer.toString(gefahren - erlaubt);
        String zuSchnellmitToleranz = "";

        if (gefahren <= 100) {
            zuSchnellmitToleranz = Integer.toString(gefahren - erlaubt - 3);
        } else {
            double d = 0.97 * gefahren;
            int t = (int) d;
            zuSchnellmitToleranz = Integer.toString(t - erlaubt);
        }
        String res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren.";

        if (Boolean.parseBoolean(parameter.get(4)) == true) {
            res = res + " Abzüglich Toleranz sind das " + zuSchnellmitToleranz + " km/h zu schnell.";
        }
        if(!zuSchnellmitToleranz.isEmpty()){
            new SpeichernTask()
                    .execute(new Vergehen(zuSchnellmitToleranz.toString()));
        }
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Die Geschwindigkeitsübertretung von " + zuSchnellmitToleranz + " km/h wurde gespeichert.", Toast.LENGTH_LONG);
        toast.show();
    }

    class SpeichernTask extends AsyncTask<Vergehen, Void, Void> {

        @Override
        protected Void doInBackground(Vergehen... vergehen) {
            dao.insert(vergehen[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}