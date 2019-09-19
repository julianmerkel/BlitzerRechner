package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Screen3 extends AppCompatActivity {

    TextView textView;
    ArrayList<String> parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

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


        textView.setText(res);

    }
}