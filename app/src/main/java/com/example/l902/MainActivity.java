package com.example.l902;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    Context context;
    Spinner teatteriValikko;
    List<String> teatterilista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        teatteriValikko = (Spinner)findViewById(R.id.teatteriValikko);
        TeatteriLista tl = TeatteriLista.getInstance();
        teatterilista = tl.lueXML();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,teatterilista);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        teatteriValikko.setAdapter(adapter);
    }
}
