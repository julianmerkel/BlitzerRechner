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

    private HistoryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);
        buttonSpeichern = findViewById(R.id.button_speichern);

        dao = HistoryRoomDatabase.getDatabase(this).historyDao();

        textView = findViewById(R.id.textView3);
        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");
        int erlaubt = Integer.parseInt(parameter.get(1));
        int gefahren = Integer.parseInt(parameter.get(2));
        int zuSchnell = gefahren - erlaubt;
        String ort = parameter.get(0);
        int bußgeld = 0;
        int strafbareGeschw;
        String res = "";

        // Methodenaufruf zur Berechnung der strafbaren Geschwindigkeit
        strafbareGeschw = geschwindigkeitBerechnen(erlaubt, gefahren);
        if(strafbareGeschw <= 0){
            res = "Du bist doch überhaupt nicht zu schnell gefahren du Otto. Alles richtig gemacht - du kannst dein Geld behalten.";
            buttonSpeichern.setEnabled(false);
        }else{
            res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren." + "\nDie strafbare Geschwindigkeit beträgt " + strafbareGeschw + " km/h.";
        }

        // Mehtodenaufrunf zur Berechnung des Bußgeldes
        bußgeld = bußgeldBerechnen(strafbareGeschw, ort);
        if(bußgeld != 0){
            res = res + " Das Bußgeld beträgt " + bußgeld + "€. ";
        }else{
            res = res + " Es wird kein Bußgeld fällig.";
        }

        // Ausgabe
        textView.setText(res);


        // Button mit Klick-Listener belegen
        int finalBußgeld = bußgeld;
        buttonSpeichern.setOnClickListener(view -> {
            saveVergehenOnClick(strafbareGeschw, finalBußgeld);
            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        });

    }


    // Methode, welche die Geschwindigkeitsübertretung berechnet
    public int geschwindigkeitBerechnen(int erlaubt, int gefahren){
        int zuSchnell = gefahren - erlaubt;
        if (Boolean.parseBoolean(parameter.get(4)) == true) {
            if(gefahren <= 100){
                zuSchnell = zuSchnell - 3;
            }else{
                zuSchnell = (int) (0.97 * gefahren - erlaubt);
            }
        }
        return zuSchnell;
    }

    // Methode, die das Bußgeld berechnet
    public int bußgeldBerechnen(int strafbareGeschw, String ort){
        int bußgeld = 0;

        if(ort.equals("innerorts")){
            if(strafbareGeschw > 70){
                bußgeld = 680;
            }else if(strafbareGeschw > 60){
                bußgeld = 480;
            }else if(strafbareGeschw > 50){
                bußgeld = 280;
            }else if(strafbareGeschw > 40){
                bußgeld = 200;
            }else if(strafbareGeschw > 30){
                bußgeld = 160;
            }else if(strafbareGeschw > 25){
                bußgeld = 100;
            }else if(strafbareGeschw > 20){
                bußgeld = 80;
            }else if(strafbareGeschw > 15){
                bußgeld = 35;
            }else if(strafbareGeschw > 10){
                bußgeld = 25;
            }else if(strafbareGeschw > 0){
                bußgeld = 15;
            }
        }else if(ort.equals("ausserorts")){
            if(strafbareGeschw > 70){
                bußgeld = 600;
            }else if(strafbareGeschw > 60){
                bußgeld = 440;
            }else if(strafbareGeschw > 50){
                bußgeld = 240;
            }else if(strafbareGeschw > 40){
                bußgeld = 160;
            }else if(strafbareGeschw > 30){
                bußgeld = 120;
            }else if(strafbareGeschw > 25){
                bußgeld = 80;
            }else if(strafbareGeschw > 20){
                bußgeld = 70;
            }else if(strafbareGeschw > 15){
                bußgeld = 30;
            }else if(strafbareGeschw > 10){
                bußgeld = 20;
            }else if(strafbareGeschw > 0){
                bußgeld = 10;
            }
        }

        return bußgeld;
    }


    // Speichern der Daten in die Datenbank und Ausgeben des Toasts
    private void saveVergehenOnClick(int geschw, int bußgeld) {
        Context context = getApplicationContext();
        new SpeichernTask()
                .execute(new Vergehen(geschw, bußgeld));
        Toast toast = Toast.makeText(context, "Die Geschwindigkeitsübertretung von " + geschw + " km/h wurde gespeichert.", Toast.LENGTH_LONG);
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