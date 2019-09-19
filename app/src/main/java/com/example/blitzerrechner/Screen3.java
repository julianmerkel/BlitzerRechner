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
        for (int i=0; i<parameter.size();i++){
            result=result+parameter.get(i)+" ";
        }

        textView.setText(result);

    }
}