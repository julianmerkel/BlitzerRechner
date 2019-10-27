package com.example.blitzerrechner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Information extends AppCompatActivity {
    TextView infoÜberschrift;
    TextView infoText;
    Button buttonTabelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setUpBottomNav();

        infoÜberschrift = findViewById(R.id.überschriftInformation);
        infoText = findViewById(R.id.textInformation);

        infoText.setText("Du warst zu schnell unterwegs und wurdest gebitzt? \n" +
                        "Mit Hilfe des Blitzer Rechners kannst Du ganz einfach ausrechnen, welches Strafmaß auf Dich zukommt." +
                        "Gib einfach die erlaubte Geschwindigkeit und deine gefahrene Geschwindigkeit ein und schon geht es los! \n" +
                        "Du kannst darüber entscheiden, ob die Toleranz für dich abgezogen werden soll oder nicht. Der Toleranzabzug beträgt meist 3 km/h. \n" +
                        "Sobald du dein Ergebnis hast, kannst du entscheiden, ob du dieses Vergehen in der Datenbank speicher willst oder nicht." +
                        "Aus allen abgespeicherten Vergehen wird das Gesamtbußgeld und die Gesamtzahl der Punkte in Flensburg errechnet.");

        buttonTabelle = findViewById(R.id.tabelleButton);
        buttonTabelle.setOnClickListener((view) -> {
            Intent intent = createIntentBrowserOeffnen();
            startActivity(intent);
        });
    }

    protected Intent createIntentBrowserOeffnen(){
        Uri httpUri = Uri.parse("https://www.bussgeldkatalog.org/geschwindigkeitsueberschreitung/");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(httpUri);
        return intent;
    }

    private void setUpBottomNav(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNav(Information.this,bottomNavigationViewEx);
    }
}
