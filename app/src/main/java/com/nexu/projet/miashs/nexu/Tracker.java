package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

public class Tracker extends AppCompatActivity{

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton test;

    private static final int MY_PERMISSION_REQUEST_CODE = 7171;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 7172;
    private TextView coordonnees;
    private Button getcoordonnees,getlocation;
    private boolean mRequestingLocationUpdates= false;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

    static private int UPDATE_INTERVAL = 5000; //sec
    static private int FASTEST_INTERVAL = 3000; //sec
    static private int DISPLACEMENT = 10;//meters

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendrier);

        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(Tracker.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });

        //ADD ASHLEY
        coordonnees = (TextView)findViewById(R.id.coordonnees);
        getcoordonnees =(Button)findViewById(R.id.getcoordonnees);
        getlocation = (Button)findViewById(R.id.getlocation);

        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != com.nexu.projet.miashs.nexu.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != com.nexu.projet.miashs.nexu.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, new String[]){
                android.Manifest.permission.ACCESS_COARSE_LOCATION;
                android.Manifest.permission.ACCESS_COARSE_LOCATION;
                },MY_PERMISSION_REQUEST_CODE;
        }

    }
}


