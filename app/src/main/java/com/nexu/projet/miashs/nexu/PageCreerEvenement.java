package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PageCreerEvenement extends AppCompatActivity{

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton buttonValider;

    TextInputEditText nomEvenement;
    TextView heureDebut;
    TextView heureFin;
    TextView minuteDebut;
    TextView minuteFin;

    Spinner programme;
    Spinner ambiance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_evenement);

        String programmeJson = convertirFromJson("programmes.json");
        String ambianceJson = convertirFromJson("ambiances.json");

        programme = findViewById(R.id.programmeSpinner);
        addItemOnSpinnerProgramme(programme, programmeJson);
        ambiance = findViewById(R.id.ambianceSpinner);
        addItemOnSpinnerAmbiance(ambiance, ambianceJson);



        //bouton valider

        buttonValider = (ImageButton) findViewById(R.id.valider);
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomEvenement = findViewById(R.id.textinputtitre);
                if(nomEvenement.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Le nom de l'evenement est vide", Toast.LENGTH_SHORT).show();

                }else if(programme.getSelectedItemPosition()==0 && ambiance.getSelectedItemPosition()==0) {
                    Toast.makeText(getApplicationContext(), "Vous devez au moins choisir un programme ou une application", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intentLoad = new Intent(PageCreerEvenement.this, PageCalendrier.class);
                    convertirEvenementJson(ambiance, programme, nomEvenement);
                    startActivity(intentLoad);
                }
            }
        });







    }

    /**
     * Met en place tous les boutons de chaque ToolBar
     */

    private void buttonTools(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerEvenement.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });

    }

    /**
     * Permet de choisir l'heure du début du programme
     * @param v view de l'activité
     */

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Permet de chosiir l'heure de fin du programme
     * @param v view de l'activité
     */

    public void showTimePickerDialogFin(View v) {
        DialogFragment newFragment = new TimePickerFragment2();
        newFragment.show(getSupportFragmentManager(), "timePicker");
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

    private String convertirFromJsonAssets(String FileName){
        String programmeJson = null;

        try {
            InputStream inputStream = getAssets().open(FileName);
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

    /**
     * Permet d'ajouter tous les programmes créer dans la liste déroulante (= spinner)
     * @param spinner dans lequel il faut ajouter la liste des programmes
     * @param programmeJson chaine de caractère contenant les programmes
     */

    private void addItemOnSpinnerProgramme(Spinner spinner, String programmeJson){
        Programmes programmes = new Gson().fromJson(programmeJson, Programmes.class);
        List<String> list = new ArrayList<>();
        list.add("Aucun");
        for(int i=0;i<programmes.getProgrammes().size();i++){
            list.add(programmes.getProgrammes().get(i).getNomProgramme());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void addItemOnSpinnerAmbiance(Spinner spinner, String ambianceJson){
        Ambiances ambiances = new Gson().fromJson(ambianceJson, Ambiances.class);
        List<String> list = new ArrayList<>();
        list.add("Aucune");
        for(int i=0;i<ambiances.getAmbiances().size();i++){
            list.add(ambiances.getAmbiances().get(i).getNomProgramme());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    private void convertirEvenementJson(final Spinner ambiance, final Spinner programme, final TextInputEditText nomEvenement){
        String evenementJson = convertirFromJsonAssets("evenements.json");
        Evenements evenements = new Gson().fromJson(evenementJson, Evenements.class);
        List<Evenement> evenementObjet = evenements.getEvenements();
        List<String> listObjet = new ArrayList<>();
        List<String> listaAction = new ArrayList<>();
        if(ambiance.getSelectedItemPosition()!=0){
            String ambianceJson = convertirFromJson("ambiances.json");
            Ambiances ambiances = new Gson().fromJson(ambianceJson, Ambiances.class);
            int pos = ambiance.getSelectedItemPosition()-1;
            System.out.println(pos+" "+ambiances.getAmbiances().get(pos).getObjets().size());
            for(int i=0;i<ambiances.getAmbiances().get(pos).getObjets().size();i++) {
                listObjet.add(ambiances.getAmbiances().get(pos).getObjets().get(i));
                listaAction.add(ambiances.getAmbiances().get(pos).getActions().get(i));
            }
        }
        if(programme.getSelectedItemPosition()!=0){
            String programmeJson = convertirFromJson("programmes.json");
            Programmes programmes = new Gson().fromJson(programmeJson, Programmes.class);
            int pos = programme.getSelectedItemPosition() -1;
            for(int i=0;i<programmes.getProgrammes().get(pos).getObjets().size();i++){
                listObjet.add(programmes.getProgrammes().get(pos).getObjets().get(i));
                listaAction.add(programmes.getProgrammes().get(pos).getActions().get(i));
            }
        }

        heureDebut = findViewById(R.id.hdeb);
        heureFin = findViewById(R.id.hfin);
        minuteDebut = findViewById(R.id.mdeb);
        minuteFin = findViewById(R.id.mfin);
        Intent incomingIntent = getIntent();
        int jour = incomingIntent.getIntExtra("jour",1);
        int mois = incomingIntent.getIntExtra("mois", 1);

        System.out.println("jour creer"+jour);

        Evenement evenementAjouter = new Evenement();
        evenementAjouter.setNom(nomEvenement.getText().toString());
        evenementAjouter.setActions(listaAction);
        evenementAjouter.setObjets(listObjet);
        evenementAjouter.setHeureDebut(Integer.parseInt(heureDebut.getText().toString()));
        evenementAjouter.setHeureFin(Integer.parseInt(heureFin.getText().toString()));
        evenementAjouter.setMinuteDebut(Integer.parseInt(minuteDebut.getText().toString()));
        evenementAjouter.setMinuteFin(Integer.parseInt(minuteFin.getText().toString()));
        evenementAjouter.setJour(jour);
        evenementAjouter.setMois(mois);

        evenementObjet.add(evenementAjouter);
        evenements.setEvenements(evenementObjet);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(evenements);



        try {
            Writer output;
            File file = new File(getFilesDir(), "evenements.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(json);
            output.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



}
