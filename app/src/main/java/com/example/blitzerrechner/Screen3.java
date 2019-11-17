package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

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

        setUpBottomNav();

        textView = findViewById(R.id.Ausgabe);
        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");
        int erlaubt = Integer.parseInt(parameter.get(1));
        int gefahren = Integer.parseInt(parameter.get(2));
        int zuSchnell = gefahren - erlaubt;
        String ort = parameter.get(0);
        int bußgeld = 0;
        int punkte = 0;
        int fahrverbot =0;
        int strafe[] = new int[3];

        int strafbareGeschw;
        String res = "";

        // Methodenaufruf zur Berechnung der strafbaren Geschwindigkeit
        strafbareGeschw = geschwindigkeitBerechnen(erlaubt, gefahren);
        if(strafbareGeschw <= 0){
            res = "Du bist nicht zu schnell gefahren und hast deswegen keine Strafen zu befürchten. Weiter so!";
            buttonSpeichern.setEnabled(false);
        }else{
            res = "Du bist " + ort + " " + zuSchnell + " km/h zu schnell gefahren." + "\nDie strafbare Geschwindigkeit beträgt " + strafbareGeschw + " km/h.";
        }

        // Mehtodenaufrunf zur Berechnung des Bußgeldes
        strafe = strafeBerechnen(strafbareGeschw, ort);
        bußgeld = strafe[0];
        punkte= strafe[1];
        fahrverbot=strafe[2];

        if(bußgeld != 0){
            if(punkte==1&&fahrverbot==1){
                res = res + " Dein Bußgeld beträgt " + bußgeld + "€. Du erhältst "+punkte+" Punkt in Flensburg und "+fahrverbot+" Monat Fahrverbot.";
            }else if(punkte==1&&fahrverbot!=1){
                res = res + " Dein Bußgeld beträgt " + bußgeld + "€. Du erhältst "+punkte+" Punkt in Flensburg und "+fahrverbot+" Monate Fahrverbot.";
            }else if(punkte!=1&&fahrverbot==1){
                res = res + " Dein Bußgeld beträgt " + bußgeld + "€. Du erhältst "+punkte+" Punkte in Flensburg und "+fahrverbot+" Monat Fahrverbot.";
            }else{
                res = res + " Dein Bußgeld beträgt " + bußgeld + "€. Du erhältst "+punkte+" Punkte in Flensburg und "+fahrverbot+" Monate Fahrverbot.";
            }

        }else{
            res = res + " Es wird kein Bußgeld fällig.";
        }

        // Ausgabe
        textView.setText(res);


        // Button mit Klick-Listener belegen
        int finalBußgeld = bußgeld;
        int finalPunkte = punkte;
        int finalFahrverbot = fahrverbot;
        buttonSpeichern.setOnClickListener(view -> {
            saveVergehenOnClick(strafbareGeschw, finalBußgeld, finalPunkte, finalFahrverbot);
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
    public int[] strafeBerechnen(int strafbareGeschw, String ort){
        //int bußgeld = 0;

        int strafe[] = new int[3];
        strafe[0]=0;
        strafe[1]=0;
        strafe[2]=0;
        //strafe[0] ist das bußgeld
        //strafe[1] sind die Punkte
        //strafe[2] sind die Monate Fahrverbot

        if(ort.equals("innerorts")){
            if(strafbareGeschw > 70){
                //bußgeld = 680;
                strafe[0]=680;
                strafe[1]=2;
                strafe[2]=3;
            }else if(strafbareGeschw > 60){
                //bußgeld = 480;
                strafe[0]=480;
                strafe[1]=2;
                strafe[2]=3;
            }else if(strafbareGeschw > 50){
                //bußgeld = 280;
                strafe[0]=280;
                strafe[1]=2;
                strafe[2]=2;
            }else if(strafbareGeschw > 40){
                //bußgeld = 200;
                strafe[0]=200;
                strafe[1]=2;
                strafe[2]=1;
            }else if(strafbareGeschw > 30){
                //bußgeld = 160;
                strafe[0]=160;
                strafe[1]=2;
                strafe[2]=1;
            }else if(strafbareGeschw > 25){
                //bußgeld = 100;
                strafe[0]=100;
                strafe[1]=1;
                strafe[2]=0;
            }else if(strafbareGeschw > 20){
                //bußgeld = 80;
                strafe[0]=80;
                strafe[1]=1;
                strafe[2]=0;
            }else if(strafbareGeschw > 15){
                //bußgeld = 35;
                strafe[0]=35;
                strafe[1]=0;
                strafe[2]=0;
            }else if(strafbareGeschw > 10){
                //bußgeld = 25;
                strafe[0]=25;
                strafe[1]=0;
                strafe[2]=0;
            }else if(strafbareGeschw > 0){
                //bußgeld = 15;
                strafe[0]=15;
                strafe[1]=0;
                strafe[2]=0;

            }
        }else if(ort.equals("ausserorts")){
            if(strafbareGeschw > 70){
                //bußgeld = 600;
                strafe[0]=600;
                strafe[1]=2;
                strafe[2]=3;
            }else if(strafbareGeschw > 60){
                //bußgeld = 440;
                strafe[0]=440;
                strafe[1]=2;
                strafe[2]=2;
            }else if(strafbareGeschw > 50){
                //bußgeld = 240;
                strafe[0]=240;
                strafe[1]=2;
                strafe[2]=1;

            }else if(strafbareGeschw > 40){
                //bußgeld = 160;
                strafe[0]=160;
                strafe[1]=2;
                strafe[2]=1;
            }else if(strafbareGeschw > 30){
                //bußgeld = 120;
                strafe[0]=120;
                strafe[1]=1;
                strafe[2]=0;
            }else if(strafbareGeschw > 25){
                //bußgeld = 80;
                strafe[0]=80;
                strafe[1]=1;
                strafe[2]=0;
            }else if(strafbareGeschw > 20){
                //bußgeld = 70;
                strafe[0]=70;
                strafe[1]=1;
                strafe[2]=0;
            }else if(strafbareGeschw > 15){
                //bußgeld = 30;
                strafe[0]=30;
                strafe[1]=0;
                strafe[2]=0;
            }else if(strafbareGeschw > 10){
                //bußgeld = 20;
                strafe[0]=20;
                strafe[1]=0;
                strafe[2]=0;
            }else if(strafbareGeschw > 0){
                //bußgeld = 10;
                strafe[0]=10;
                strafe[1]=0;
                strafe[2]=0;
            }
        }

        return strafe;
    }


    // Speichern der Daten in die Datenbank und Ausgeben des Toasts
    private void saveVergehenOnClick(int geschw, int bußgeld, int punkte, int fahrverbot) {
        Context context = getApplicationContext();
        new SpeichernTask()
                .execute(new Vergehen(geschw, bußgeld, punkte, fahrverbot));
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

    private void setUpBottomNav(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNav(Screen3.this,bottomNavigationViewEx);
    }



}