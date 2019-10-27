package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Screen2 extends AppCompatActivity {

    Button berechnen;
    TextView textView;
    TextView überschriftBerechnung;
    Intent intent3;
    TextInputLayout inputErlaubteGeschw;
    TextInputLayout inputGefahreneGeschw;
    Switch probezeit;
    //Switch toleranz;
    ToggleButton toleranz;
    ArrayList<String> parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
        intent3 = new Intent(this, Screen3.class);
        textView = findViewById(R.id.textView3);
        überschriftBerechnung = findViewById(R.id.überschriftBerechnung);
        berechnen = findViewById(R.id.buttonBerechnung);
        inputErlaubteGeschw = findViewById(R.id.inputErlaubteGeschwindigkeit);
        inputGefahreneGeschw = findViewById(R.id.inputGefahreneGeschwindigkeit);
        // editLayout = findViewById(R.id.inputGefahreneGeschwindigkeit);
        // editText = new TextInputEditText(editLayout.getContext());
        //probezeit = findViewById(R.id.switchProbezeit);
        toleranz = findViewById(R.id.switchToleranz);



        Intent intent = getIntent();
        parameter = intent.getStringArrayListExtra("parameter");

        String ort = parameter.get(0);
        if(ort.equals("innerorts")) {
            überschriftBerechnung.setText("Innerorts");
        }else{
            überschriftBerechnung.setText("Ausserorts");
        }
     /*   berechnen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String erlaubt = inputErlaubteGeschw.getEditText().getText().toString();
                String gefahren = inputGefahreneGeschw.getEditText().getText().toString();
                // String gefahren = editLayout.getEditText().getText().toString();
                String sProbezeit = probezeit.isChecked()+"";
                String sToleranz = toleranz.isChecked()+"";
                parameter.set(1,erlaubt);
                parameter.set(2,gefahren);
                parameter.set(3,sProbezeit);
                parameter.set(4,sToleranz);

                intent3.putStringArrayListExtra("parameter", parameter);
                startActivity(Screen2.this.intent3);
            }
        });  */

    }

    private boolean validateGefahreneGeschw(){
        String gefahren = inputGefahreneGeschw.getEditText().getText().toString().trim();
        if(gefahren.isEmpty()){
            inputGefahreneGeschw.setError("Dieses Feld darf nicht leer sein.");
            return false;
        }else if(inputGefahreneGeschw.getEditText().getText().toString().trim().length() > 3){
            inputGefahreneGeschw.setError("Dies ist eine unrealistische Geschwindigkeit.");
            return false;
        }else{
            inputGefahreneGeschw.setError(null);
            return true;
        }
    }

    private boolean validateErlaubteGeschw(){
        String erlaubt = inputErlaubteGeschw.getEditText().getText().toString().trim();
        if(erlaubt.isEmpty()){
            inputErlaubteGeschw.setError("Dieses Feld darf nicht leer sein.");
            return false;
        }else if(inputErlaubteGeschw.getEditText().getText().toString().trim().length() > 3){
            inputErlaubteGeschw.setError("Dies ist eine unrealistische Geschwindigkeit.");
            return false;
        }else{
            inputErlaubteGeschw.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if(!validateErlaubteGeschw() | !validateGefahreneGeschw()){
            return;
        }else{
            String erlaubt = inputErlaubteGeschw.getEditText().getText().toString();
            String gefahren = inputGefahreneGeschw.getEditText().getText().toString();
            // String gefahren = editLayout.getEditText().getText().toString();
         //   String sProbezeit = probezeit.isChecked()+"";
            String sToleranz = toleranz.isChecked()+"";
            parameter.set(1,erlaubt);
            parameter.set(2,gefahren);
         //   parameter.set(3,sProbezeit);
            parameter.set(4,sToleranz);

            intent3.putStringArrayListExtra("parameter", parameter);
            startActivity(Screen2.this.intent3);
        }
    }

    public void saveInput(View v){
        String erlaubt = inputErlaubteGeschw.getEditText().getText().toString();
        String gefahren = inputGefahreneGeschw.getEditText().getText().toString();
        // String gefahren = editLayout.getEditText().getText().toString();
        String sProbezeit = probezeit.isChecked()+"";
        String sToleranz = toleranz.isChecked()+"";
        parameter.set(1,erlaubt);
        parameter.set(2,gefahren);
       // parameter.set(3,sProbezeit);
        parameter.set(4,sToleranz);

        intent3.putStringArrayListExtra("parameter", parameter);
        startActivity(Screen2.this.intent3);
    }
}