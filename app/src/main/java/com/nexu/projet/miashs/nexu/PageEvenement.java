package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class PageEvenement extends AppCompatActivity{

    private static final String TAG = "PageCalendrier";
    private TextView theDate;
    ImageButton buttonCreerEvent;
    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;

    String url;

    TextView evenement1;
    TextView evenement2;
    TextView evenement3;
    TextView evenement4;

    int pos;

    int jour;
    int mois;

    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.evenement);

        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });


        //calendrier

        theDate = (TextView) findViewById(R.id.date);
        Intent incomingIntent = getIntent();
        jour = incomingIntent.getIntExtra("jour",1);
        mois = incomingIntent.getIntExtra("mois", 1);
        System.out.println(jour+" "+mois);
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        buttonCreerEvent = (ImageButton) findViewById(R.id.creerEvent);
        buttonCreerEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageEvenement.this, PageCreerEvenement.class);
                Intent incomingIntent = getIntent();
                int jour = incomingIntent.getIntExtra("jour",1);
                int mois = incomingIntent.getIntExtra("mois", 1);
                String date = incomingIntent.getStringExtra("date");
                intentLoad.putExtra("mois", mois);
                intentLoad.putExtra("jour", jour);

                startActivity(intentLoad);
            }
        });

        evenement1 = findViewById(R.id.evenement1);
        evenement2 = findViewById(R.id.evenement2);
        evenement3 = findViewById(R.id.evenement3);
        evenement4 = findViewById(R.id.evenement4);

        TextView tabEvent[] = {evenement1, evenement2, evenement3, evenement4};

        String evementJson = convertirFromJson("evenements.json");
        Evenements evenements = new Gson().fromJson(evementJson, Evenements.class);

        int j=0;

        for (int i = 0; i < evenements.getEvenements().size(); i++) {
            if (jour == evenements.getEvenements().get(i).getJour() && mois == evenements.getEvenements().get(i).getMois() &&j<4) {
                tabEvent[j].setText(evenements.getEvenements().get(i).getHeureDebut() + ":" +
                        evenements.getEvenements().get(i).getMinuteDebut() + "-" + evenements.getEvenements().get(i).getHeureFin() +
                        ":" + evenements.getEvenements().get(i).getMinuteFin() + "    "
                        + evenements.getEvenements().get(i).getNom());
                j++;
            }
        }

        test(evenements);
    }

    /**
     * Permet de convertir un fichier Json du dossier data en une chaine de caractère
     * @param FileName nom du fichier à convertir
     */

    private String convertirFromJson(String FileName){
        String programmeJson = null;

        try {
            File file = new File(getFilesDir(), FileName);
            FileInputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            programmeJson = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return programmeJson;
    }

    private void test(final Evenements evenements){
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            boolean faire = true;
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat heure = new SimpleDateFormat("HH");
                            SimpleDateFormat minute = new SimpleDateFormat("mm");
                            heure.format(calendar.getTime());
                            System.out.println("calendrier");
                            Calendar c = Calendar.getInstance();
                            System.out.println(minute.format(calendar.getTime()));
                            for(int i=0; i < evenements.getEvenements().size();i++) {
                                if (jour == evenements.getEvenements().get(i).getJour() && mois == evenements.getEvenements().get(i).getMois()) {
                                    String minuteEv = evenements.getEvenements().get(i).getMinuteDebut().toString();
                                    if(evenements.getEvenements().get(i).getMinuteDebut() <10){
                                        minuteEv = "0"+evenements.getEvenements().get(i).getMinuteDebut();
                                    }
                                    if(heure.format(calendar.getTime()).equals(evenements.getEvenements().get(i).getHeureDebut())){
                                        System.out.println("tout va bien pour l'heure");
                                    }
                                    if(minute.format(calendar.getTime()).equals(minuteEv)){
                                        System.out.println("tout va bien pour les minutes");
                                    }
                                    if (heure.format(calendar.getTime()).equals(evenements.getEvenements().get(i).getHeureDebut().toString()) && minute.format(calendar.getTime()).equals(minuteEv) && faire) {
                                        faireUnEvenement(evenements, i);
                                        System.out.println("Evenement");
                                        faire = false;
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 60000);
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

    private void faireUnEvenement(Evenements evenements, int numEvenement) throws InterruptedException {
        String nom[] = {"Machine à Café", "Télévision"};
        String id[] = {"24", "33"};
        for(int i=0;i<evenements.getEvenements().get(numEvenement).getActions().size();i++){
            System.out.println("ok");
                if (evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Lampe Principale")) {
                    System.out.println("lampe");
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Allumer")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=On");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Eteindre")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=Off");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x2")) {
                        clignotementNormal(2, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x3")) {
                        clignotementNormal(3, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x4")) {
                        clignotementNormal(4, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=23&switchcmd=");
                    }
                }
                if (evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Machine à Café")) {
                    System.out.println("machine");
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Faire un café")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=24&switchcmd=On");
                    }

                }
                if (evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Télévision")) {
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Allumer")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=33&switchcmd=On");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Eteindre")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=33&switchcmd=Off");
                    }
                }
                if (evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Volets")) {
                    System.out.println("volets");
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Ouvrir")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On");
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=008000&brightness=80&iswhite=false");
                        sleep(1000);
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off");
                    }
                    if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Fermer")) {
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On");
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=008000&brightness=80&iswhite=false");
                        sleep(1000);
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off");
                    }
                }

                if(evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Lampe de Bureau")){
                    if(evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Allumer")){
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On");
                    }
                    if(evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Eteindre")){
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off");
                    }
                    if(evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x2")){
                        clignotement(2, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=43&switchcmd=Set%20Level&level=" );
                    }
                    if(evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x3")){
                        clignotement(3, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=43&switchcmd=Set%20Level&level=" );
                    }
                    if(evenements.getEvenements().get(numEvenement).getActions().get(i).equals("Clignoter x4")){
                        clignotement(4, "http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=43&switchcmd=Set%20Level&level=" );
                    }
                }
                if(evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Couleur")){
                    faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex="+evenements.getEvenements().get(numEvenement).getActions().get(i)+"&brightness=80&iswhite=false");
                }
                if(evenements.getEvenements().get(numEvenement).getObjets().get(i).equals("Intensite")) {
                    faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=43&switchcmd=Set%20Level&level="+evenements.getEvenements().get(numEvenement).getActions().get(i));
                }
                if(evenements.getEvenements().get(numEvenement).getObjets().equals("Radiateur Principal")){
                    if(evenements.getEvenements().get(numEvenement).getActions().equals("Allumer")){
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On");
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=ff8c00&brightness=80&iswhite=false");
                        sleep(1000);
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off");
                    }
                    if(evenements.getEvenements().get(numEvenement).getActions().equals("Eteindre")){
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=On");
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=setcolbrightnessvalue&idx=43&hex=00978C&brightness=80&iswhite=false");
                        sleep(1000);
                        faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=42&switchcmd=Off");
                    }
                    int intensite = 10;
                    String actionNom[] = {"Mettre à 18°C", "Mettre à 18,5°C", "Mettre à 19°C", "Mettre à 19,5°C", "Mettre à 20°C", "Mettre à 20,5°C", "Mettre à 21°C", "Mettre à 21,5°C", "Mettre à 22°C"};
                    for(int j=0;j<9;j++) {
                        intensite = intensite + 10;
                        if (evenements.getEvenements().get(numEvenement).getActions().get(i).equals(actionNom[j])) {
                            faireUneRequete("http://172.20.10.5:8080/json.htm?type=command&param=switchlight&idx=43&switchcmd=Set%20Level&level=" + intensite);
                        }
                    }
                }


        }

    }

    private void clignotementNormal(int nb, String url) throws InterruptedException {
        for(int i=0;i<nb;i++) {
            ///json.htm?type=command&param=switchlight&idx=99&switchcmd=Set%20Level&level=6
            url = url+"On";
            faireUneRequete(url);
            sleep(2000);
            System.out.println("ok");
            url = url+"Off";
            faireUneRequete(url);
            sleep(2000);
        }
    }


    private void clignotement(int nb, String url) throws InterruptedException {
        for(int i=0;i<nb;i++) {
            ///json.htm?type=command&param=switchlight&idx=99&switchcmd=Set%20Level&level=6
            url = url+"100";
            faireUneRequete(url);
            sleep(2000);
            System.out.println("ok");
            url = url+"0";
            faireUneRequete(url);
            sleep(2000);
        }
    }

}
