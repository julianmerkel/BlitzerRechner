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

        infoText.setText("Diese App ist ein Blitzer Rechner.\n" +
                "Durch Eingeben Deiner gefahrenen sowie der eigentlich erlaubten Geschwindigkeit (Einheit: km/h), errechnet der Blitzer Rechner das Strafmaß, welches auf Dich zukommen könnte. Dabei werden sowohl das fällige Bußgeld als auch die möglicherweise anfallenden Punkte in Flensburg oder Fahrverbote berücksichtigt.\n\n" +
                "Es besteht die Möglichkeit, den Toleranzabzug bei der Rechnung miteinzubeziehen. Die Toleranz errechnet sich hierbei folgendermaßen:\n" +
                "Bei Geschwindigkeiten unter 100 km/h: \nToleranzabzug = 3 km/h\n" +
                "Bei Geschwindigkeiten über 100 km/h: \nToleranzabzug = (0.03 * gefahrene Geschw.) km/h\n\n" +
                "Das Strafmaß wird anhand der überschrittenen Geschwindigkeit und dem Bußgeldkatalog berechnet. Allerdings kann es durch wiederholte Vergehen zu Punktstrafen kommen, welche in dieser App nicht berücksichtigt sind. Es wird außerdem davon ausgegangen, dass Du Dich nicht mehr in der Probezeit befindest.\n" +
                "Die aktuelle Version des Bußgeldkatalogs, auf welchem diese App basiert, kannst Du über den Button am Ende dieser Seite erreichen.\n");

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
