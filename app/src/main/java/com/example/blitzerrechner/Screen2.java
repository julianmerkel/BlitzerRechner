package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Screen2 extends AppCompatActivity {

    Button berechnen;
    TextView textView;
    Intent intent3;
    TextInputEditText inputErlaubteGeschw;
    TextInputEditText inputGefahreneGeschw;
    Switch probezeit;
    Switch toleranz;
    ArrayList<String> parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
        intent3 = new Intent(this, Screen3.class);
        textView = findViewById(R.id.textView3);
        berechnen=findViewById(R.id.button);
        inputErlaubteGeschw = findViewById(R.id.textInputEditText3);
        inputGefahreneGeschw = findViewById(R.id.textInputEditText4);
        probezeit = findViewById(R.id.switch1);
        toleranz = findViewById(R.id.switch3);



        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");


        berechnen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String erlaubt = inputErlaubteGeschw.getText().toString();
                String gefahren = inputGefahreneGeschw.getText().toString();
                String sProbezeit = probezeit.isChecked()+"";
                String sToleranz = toleranz.isChecked()+"";
                parameter.set(1,erlaubt);
                parameter.set(2,gefahren);
                parameter.set(3,sProbezeit);
                parameter.set(4,sToleranz);

                intent3.putStringArrayListExtra("parameter", parameter);
                startActivity(Screen2.this.intent3);
            }
        });

    }
}