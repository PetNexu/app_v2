package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton test;

    TextView temperatureText;
    TextView humiditeText;
    TextView luminositeText;
    TextView uvText;

    String url;
    String reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTools();

        url="http://172.20.10.5:8080/json.htm?type=devices&rid=65";
        reponse = temperature(url, true);
        System.out.println("test"+reponse);
        url="http://172.20.10.5:8080/json.htm?type=devices&rid=7";
        reponse = temperature(url, false);



    }


    private void buttonTools(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(MainActivity.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });
    }

    private String temperature(String url, final boolean temperature) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        reponse = response;
                        Capteurs capteurs = new Gson().fromJson(reponse, Capteurs.class);
                        if(temperature) {
                            Double temperature = capteurs.getResult().get(0).getTemp();
                            String niveauHumidite = capteurs.getResult().get(0).getHumidityStatus();
                            int humidite = capteurs.getResult().get(0).getHumidity();
                            temperatureText = findViewById(R.id.temperatureText);
                            humiditeText = findViewById(R.id.humiditeText);
                            temperatureText.setText(temperature + "°C");
                            humiditeText.setText(humidite + "%");
                            if(niveauHumidite.equals("Wet")||niveauHumidite.equals("Dry")){
                                humiditeText.setTextColor(getResources().getColor(R.color.mauvaiseHumidite));
                            }
                        }
                        else {
                            String lux = capteurs.getResult().get(0).getData();
                            luminositeText = findViewById(R.id.luminositeText);
                            luminositeText.setText(lux);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("nique ta mere");
            }
        });
        System.out.println("test 3"+reponse);
        queue.add(stringRequest);
        return reponse;
    }






}
