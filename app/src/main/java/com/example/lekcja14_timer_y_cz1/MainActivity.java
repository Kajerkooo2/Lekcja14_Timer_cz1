package com.example.lekcja14_timer_y_cz1;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTimer;
    private Button btnStart,btnReset,btnZapisz,btnStop;
    private boolean Isrunning = false;
    private int sekundy = 0;
    private int minuty = 0;
    private int godziny = 0;

    private ArrayList<String> arrayListTime = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewTimer = findViewById(R.id.textViewTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);
        btnZapisz = findViewById(R.id.btnZapisz);
        listView = findViewById(R.id.ListViewZapis);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayListTime);
        listView.setAdapter(arrayAdapter);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(Isrunning) {
                    sekundy++;
                    textViewTimer.setText(displayTime(sekundy));
                }
                handler.postDelayed(this, 1000);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Isrunning = true;
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Isrunning = false;
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Isrunning = false;
                sekundy = 0;
                textViewTimer.setText(displayTime(sekundy));
            }
        });
        btnZapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListTime.add(displayTime(sekundy));
                arrayAdapter.notify();
            }
        });
    }
    private String displayTime(int howMuch){
        int sekundy = howMuch%60;
        int minuty = (howMuch/60)%60;
        int godziny = howMuch/3600;
        return String.format("%02d:%02d:%02d",godziny,minuty,sekundy);
    }
}