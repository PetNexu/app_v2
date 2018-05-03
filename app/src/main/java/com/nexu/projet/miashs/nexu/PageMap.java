package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PageMap extends AppCompatActivity {

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton buttonLightBed ;
    ImageButton buttonLightBed1 ;
    ImageButton buttonPlugBed;
    ImageButton buttonRadiatorBed;
    ImageButton buttonBlind;
    ImageButton buttonCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet de la lampe Chambre 1
        buttonLightBed = (ImageButton) findViewById(R.id.lightBed1);
        buttonLightBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet de la lampe Chambre 2
        buttonLightBed1 = (ImageButton) findViewById(R.id.lightBed2);
        buttonLightBed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet de la prise Chambre
        buttonPlugBed = (ImageButton) findViewById(R.id.plugBed);
        buttonPlugBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet du radiateur de Chambre
        buttonRadiatorBed = (ImageButton) findViewById(R.id.radiatorBed);
        buttonRadiatorBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet des volets de Chambre
        buttonBlind = (ImageButton) findViewById(R.id.blindBed);
        buttonBlind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers Objet de la machine à café de Cuisine
        buttonCoffee = (ImageButton) findViewById(R.id.coffeeKitchen;
        buttonCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageObjet.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageMap.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });

    }
}
