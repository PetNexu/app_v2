package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

public class PageStatistiques extends AppCompatActivity {

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    Button buttonObjectif;
    Button buttonTravail;
    ImageButton test;
    ImageView bar0;
    ImageView bar020;
    ImageView bar20;
    ImageView bar40;
    ImageView bar60;
    ImageView bar80;
    ImageView bar100;
    private int tempsDetravail;
    private int tempsPasse;
    private int value;
    protected TextView nbHeureTravail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiques);
        tempsPasse = 0;
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });


        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageStatistiques.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });
        nbHeureTravail = (TextView) findViewById(R.id.textView7);
        //Bouton objectifs
        buttonObjectif = (Button)findViewById(R.id.button3);
        buttonObjectif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempsDetravail = R.id.max;
                nbHeureTravail.setText(tempsDetravail+"");
            }
        });
        //Bouton Travail
        buttonTravail = (Button)findViewById(R.id.button4);
        buttonTravail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                tempsPasse = R.id.tempsPasse;
                update(tempsPasse);
            }
        });
    }

    public void update(int tempsPasse){
        value =(100-(tempsDetravail - tempsPasse) * 100 / tempsDetravail);
        if((value<20) && (value>0)){
            bar0.setVisibility(View.INVISIBLE);
            bar020.setVisibility(View.VISIBLE);
        }
        else if((value>=20)&&(value<40)){
                bar020.setVisibility(View.INVISIBLE);
                bar20.setVisibility(View.VISIBLE);
        }
        else if((value>=40)&&(value<60)){
            bar20.setVisibility(View.INVISIBLE);
            bar40.setVisibility(View.VISIBLE);
        }
        else if((value>=60)&&(value<80)){
            bar40.setVisibility(View.INVISIBLE);
            bar60.setVisibility(View.VISIBLE);
        }
        else if((value>=80)&&(value<100)){
            bar60.setVisibility(View.INVISIBLE);
            bar80.setVisibility(View.VISIBLE);
        }
        else{
            bar80.setVisibility(View.INVISIBLE);
            bar100.setVisibility(View.VISIBLE);
        }
    }
}
