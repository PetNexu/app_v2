package com.nexu.projet.miashs.nexu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.MenuInflater;

import com.google.firebase.messaging.FirebaseMessaging;
import com.nexu.projet.miashs.nexu.Modele.Notification;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    //commentaire pour tester

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(getApplicationContext());
        Intent i = getIntent();
        String activityToStart = i.getStringExtra("startActivity");
        Log.e(TAG,activityToStart+"");
        if(activityToStart!=null&&activityToStart.equals("PageParametresNotications")){
            startActivity(new Intent(MainActivity.this,PageParametresNotications.class));
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(System.currentTimeMillis()+"",
                    "test", NotificationManager.IMPORTANCE_LOW));
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.e(TAG,"subscribe");*/

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
}
