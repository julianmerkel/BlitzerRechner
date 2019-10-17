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
       // String bußgeld = "";
        int bußgeld = 0;
        String zuSchnell = Integer.toString(gefahren - erlaubt);
        String zuSchnellmitToleranz = "";
        String strafbareGeschw = "";
        String res = "";


        // Geschwindigkeit überprüfen und ggf. Toleranz abziehen
        if(gefahren <= erlaubt){
            res = "Du bist doch überhaupt nicht zu schnell gefahren du Otto. Alles richtig gemacht - du kannst dein Geld behalten.";
            strafbareGeschw = "0";
        }else {
            strafbareGeschw = zuSchnell;
            if (gefahren <= 100) {
                zuSchnellmitToleranz = Integer.toString(gefahren - erlaubt - 3);
            } else {
                double d = 0.97 * gefahren;
                int t = (int) d;
                zuSchnellmitToleranz = Integer.toString(t - erlaubt);
            }
            res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren.";

            if (Boolean.parseBoolean(parameter.get(4)) == true) {
                strafbareGeschw = zuSchnellmitToleranz;
                res = res + " Abzüglich Toleranz sind das " + zuSchnellmitToleranz + " km/h zu schnell.";
            }
        }

        bußgeld = bußgeldBerechnen(strafbareGeschw, ort);

        if(bußgeld != 0){
            res = res + " Das Bußgeld beträgt " + bußgeld + "€. ";
        }else{
            res = res + " Es wird kein Bußgeld fällig.";
        }

        // Ausgabe
        textView.setText(res);


        // Button mit Klick-Listener belegen
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


    // Methode, die das Bußgeld berechnet
    public int bußgeldBerechnen(String geschw, String o){
        String ort = o;
        String strafbareGeschw = geschw;
        int bußgeld = 0;

        if(Integer.parseInt(strafbareGeschw) > 0 && Integer.parseInt(strafbareGeschw) <= 10 && ort.equals("innerorts")){
            bußgeld = 15;
        }else if(Integer.parseInt(strafbareGeschw) > 10 && Integer.parseInt(strafbareGeschw) <= 15 && ort.equals("innerorts")){
            bußgeld = 25;
        }else if(Integer.parseInt(strafbareGeschw) > 15 && Integer.parseInt(strafbareGeschw) <= 20 && ort.equals("innerorts")){
            bußgeld = 35;
        }else if(Integer.parseInt(strafbareGeschw) > 20 && Integer.parseInt(strafbareGeschw) <= 25 && ort.equals("innerorts")){
            bußgeld = 80;
        }else if(Integer.parseInt(strafbareGeschw) > 25 && Integer.parseInt(strafbareGeschw) <= 30 && ort.equals("innerorts")){
            bußgeld = 100;
        }else if(Integer.parseInt(strafbareGeschw) > 30 && Integer.parseInt(strafbareGeschw) <= 40 && ort.equals("innerorts")){
            bußgeld = 160;
        }else if(Integer.parseInt(strafbareGeschw) > 40 && Integer.parseInt(strafbareGeschw) <= 50 && ort.equals("innerorts")){
            bußgeld = 200;
        }else if(Integer.parseInt(strafbareGeschw) > 50 && Integer.parseInt(strafbareGeschw) <= 60 && ort.equals("innerorts")){
            bußgeld = 280;
        }else if(Integer.parseInt(strafbareGeschw) > 60 && Integer.parseInt(strafbareGeschw) <= 70 && ort.equals("innerorts")){
            bußgeld = 480;
        }else if(Integer.parseInt(strafbareGeschw) > 70 && ort.equals("innerorts")){
            bußgeld = 680;
        }else if(Integer.parseInt(strafbareGeschw) > 0 && Integer.parseInt(strafbareGeschw) <= 10 && ort.equals("ausserorts")){
            bußgeld = 10;
        }else if(Integer.parseInt(strafbareGeschw) > 10 && Integer.parseInt(strafbareGeschw) <= 15 && ort.equals("ausserorts")){
            bußgeld = 20;
        }else if(Integer.parseInt(strafbareGeschw) > 15 && Integer.parseInt(strafbareGeschw) <= 20 && ort.equals("ausserorts")){
            bußgeld = 30;
        }else if(Integer.parseInt(strafbareGeschw) > 20 && Integer.parseInt(strafbareGeschw) <= 25 && ort.equals("ausserorts")){
            bußgeld = 70;
        }else if(Integer.parseInt(strafbareGeschw) > 25 && Integer.parseInt(strafbareGeschw) <= 30 && ort.equals("ausserorts")){
            bußgeld = 80;
        }else if(Integer.parseInt(strafbareGeschw) > 30 && Integer.parseInt(strafbareGeschw) <= 40 && ort.equals("ausserorts")){
            bußgeld = 120;
        }else if(Integer.parseInt(strafbareGeschw) > 40 && Integer.parseInt(strafbareGeschw) <= 50 && ort.equals("ausserorts")){
            bußgeld = 160;
        }else if(Integer.parseInt(strafbareGeschw) > 50 && Integer.parseInt(strafbareGeschw) <= 60 && ort.equals("ausserorts")){
            bußgeld = 240;
        }else if(Integer.parseInt(strafbareGeschw) > 60 && Integer.parseInt(strafbareGeschw) <= 70 && ort.equals("ausserorts")){
            bußgeld = 440;
        }else if(Integer.parseInt(strafbareGeschw) > 70 && ort.equals("ausserorts")){
            bußgeld = 600;
        }

        return bußgeld;
    }



    private void saveVergehenOnClick() {
        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");
        String ort = parameter.get(0);
        int erlaubt = Integer.parseInt(parameter.get(1));
        int gefahren = Integer.parseInt(parameter.get(2));
        String zuSchnell = Integer.toString(gefahren - erlaubt);
        String zuSchnellmitToleranz = "";
        int bußgeld = 15;

        // Toleranz abziehen und Geschwindigkeit speichern

        if (gefahren <= 100) {
            zuSchnellmitToleranz = Integer.toString(gefahren - erlaubt - 3);
        } else {
            double d = 0.97 * gefahren;
            int t = (int) d;
            zuSchnellmitToleranz = Integer.toString(t - erlaubt);
        }
        String res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren.";
        Context context = getApplicationContext();

        if (Boolean.parseBoolean(parameter.get(4)) == true && !zuSchnellmitToleranz.isEmpty()) {
            res = res + " Abzüglich Toleranz sind das " + zuSchnellmitToleranz + " km/h zu schnell.";
            new SpeichernTask()
                    .execute(new Vergehen(zuSchnellmitToleranz.toString(), bußgeld));
            Toast toast = Toast.makeText(context, "Die Geschwindigkeitsübertretung von " + zuSchnellmitToleranz + " km/h wurde gespeichert.", Toast.LENGTH_LONG);
            toast.show();
        }else if(Boolean.parseBoolean(parameter.get(4)) == false && !zuSchnell.isEmpty()) {
            new SpeichernTask()
                    .execute(new Vergehen(zuSchnell.toString(), bußgeld));
            Toast toast = Toast.makeText(context, "Die Geschwindigkeitsübertretung von " + zuSchnell + " km/h wurde gespeichert.", Toast.LENGTH_LONG);
            toast.show();
        }else{}

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