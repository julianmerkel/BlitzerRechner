package com.example.blitzerrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    //Boss-Comment

    ImageView imageViewInnerorts;
    ImageView ImageViewAusserorts;
    TextView textView;
    Intent intent;
    ArrayList<String> parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parameter = new ArrayList<>();

        for (int i =0; i<5;i++){
            parameter.add("");
        }


        intent = new Intent(this, Screen2.class);

        imageViewInnerorts = findViewById(R.id.imageView2);
        ImageViewAusserorts = findViewById(R.id.imageView4);
        textView = findViewById(R.id.textView3);


        imageViewInnerorts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                parameter.set(0,"innerorts");
                intent.putStringArrayListExtra("parameter", parameter);
                startActivity(MainActivity.this.intent);
            }
        });

        ImageViewAusserorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameter.set(0,"ausserorts");
                intent.putStringArrayListExtra("parameter", parameter);
                startActivity(MainActivity.this.intent);
            }
        });

    }
}
