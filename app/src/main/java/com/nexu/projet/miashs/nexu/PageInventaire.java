package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import static java.lang.Thread.sleep;

public class PageInventaire extends AppCompatActivity {


    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;

    ImageButton validerCouleur;
    ImageButton validerIntesite;

    String couleur;
    String url;


    TextInputEditText intensite;

    TextView lum;
    Switch lumiere;
    Switch lumieretitre;
    Switch lumiere2;
    Switch electromenagerTitre;
    Switch electromenager1;
    Switch electromenager2;
    Switch volets;
    Switch radiateur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventaire);

        buttonTools();


        ColorSeekBar colorSeekBar = findViewById(R.id.color_seek_bar);
        lum = findViewById(R.id.textLumBureau2);
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i, int i1, int i2) {
                String hexColor = String.format("#%06X", (0xFFFFFF & i2));
                hexColor = hexColor.substring(1);
                couleur = hexColor;
                System.out.print(" " + hexColor + " ");
            }
        });

        electromenagerTitre = findViewById(R.id.electrotitre);
        electromenager1 = findViewById(R.id.electro1);
        electromenager2 = findViewById(R.id.electro2);
        lumieretitre = findViewById(R.id.lumTitre);
        lumiere = findViewById(R.id.lum1);
        lumiere2 = findViewById(R.id.lum2);


        initialiserSwitch();


        electromenagerTitre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    electromenager1.setChecked(true);
                    electromenager2.setChecked(true);
                } else {
                    electromenager1.setChecked(false);
                    electromenager2.setChecked(false);
                }
            }
        });

        lumieretitre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    lumiere.setChecked(true);
                    lumiere2.setChecked(true);
                }else{
                    lumiere.setChecked(false);
                    lumiere2.setChecked(false);
                }
            }
        });

        lumiere2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=On";
                } else {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=Off";
                }
                faireUneRequete(url);
            }
        });

        electromenager1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=24&switchcmd=On";
                } else {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=24&switchcmd=Off";
                }
                faireUneRequete(url);
            }
        });

        electromenager2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=33&switchcmd=On";
                } else {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=33&switchcmd=Off";
                }
                faireUneRequete(url);
            }
        });



        lumiere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=FFFFFF&brightness=100&iswhite=false";
                    faireUneRequete(url);
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On";

                } else {
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off";
                }
                faireUneRequete(url);


            }
        });

        intensite=findViewById(R.id.inputintensite);


        validerIntesite = findViewById(R.id.validerintensite);
        validerIntesite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intensite.getText()==null) {
                    Toast.makeText(getApplicationContext(), "Veuillez renseigner une intensité", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.print("damn"+intensite.getText().toString());
                    url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Set%20Level&level="+intensite.getText().toString();
                    faireUneRequete(url);
                }
            }
        });

        validerCouleur = findViewById(R.id.validercouleur);
        validerCouleur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=" + couleur + "&brightness=100&iswhite=false";
                faireUneRequete(url);
            }
        });



    }

    private void buttonTools(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });


        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageInventaire.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });

    }

    private void initialiserSwitch(){
        Switch switchID[]={lumiere, lumiere2, electromenager1, electromenager2};
        String id[]={"42", "23", "24", "33"};
        //trouverEtat(id, 0, switchID);
        trouverEtat(id, 1, switchID);
        trouverEtat(id, 2, switchID);
        trouverEtat(id, 3, switchID);
    }


    private void faireUneRequete(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("nique ta mere");
            }
        });

        queue.add(stringRequest);
    }

    private void trouverEtat(String id[], final int pos, final Switch switchId[]) {
        url = "http://172.20.10.5:8080/json.htm?type=devices&rid="+id[pos];
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Capteurs capteurs = new Gson().fromJson(response, Capteurs.class);
                        String status =  capteurs.getResult().get(0).getData();
                        System.out.println(status);
                        if(status.equals("Off")) {
                            System.out.println("off c'est faux");
                            switchId[pos].setChecked(false);
                        }else{
                            switchId[pos].setChecked(true);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("nique ta mere");
            }
        });

        queue.add(stringRequest);
    }

    private void clignotement(int nb) throws InterruptedException {
        for(int i=0;i<nb;i++) {
            url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On";
            faireUneRequete(url);
            url = "http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=00978C&brightness=100&iswhite=false";
            sleep(2000);
            System.out.println("ok");
            url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off";
            faireUneRequete(url);
            sleep(2000);
        }
    }

    private void clignotement2(int nb) throws InterruptedException {
        for(int i=0;i<nb;i++) {
            url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On";
            faireUneRequete(url);
            url = "http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=9f7fd0C&brightness=100&iswhite=false";
            faireUneRequete(url);
            sleep(2000);
            System.out.println("ok");
            url = "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off";
            faireUneRequete(url);
            faireUneRequete(url);
            sleep(2000);
        }
    }
}
