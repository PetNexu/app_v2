package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nexu.projet.miashs.nexu.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PageProgramme extends AppCompatActivity {

    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton buttonAjouterProgramme;

    ImageButton modifier1;
    ImageButton modifier2;
    ImageButton modifier3;
    ImageButton modifier4;

    ImageButton modifierAmbiance1;
    ImageButton modifierAmbiance2;

    TextView programme1;
    TextView programme2;
    TextView programme3;
    TextView programme4;
    TextView ambiance1;
    TextView ambiance2;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programme);
        buttonsTool();


        //Bouton ajouter programme
        buttonCompte = (ImageButton) findViewById(R.id.creerprogramme);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageCreerProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton ajouter ambiance
        buttonAjouterProgramme = (ImageButton) findViewById(R.id.creer_ambiance);
        buttonAjouterProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageCreerAmbiance.class);
                startActivity(intentLoad);
            }
        });

        initialiserVariables();

        TextView tabprogramme[] = {programme1, programme2, programme3, programme4};
        ImageButton tabmodifier[] = {modifier1, modifier2, modifier3, modifier4};
        TextView tabambiance[] = {ambiance1, ambiance2};
        ImageButton tabmodifierAmbiance[] = {modifierAmbiance1, modifierAmbiance2};



        afficherNoms(tabprogramme, tabambiance, tabmodifier, tabmodifierAmbiance);
        modifierButton(tabmodifier, 0);
        modifierButton(tabmodifier, 1);
        modifierButton(tabmodifier, 2);
        modifierButton(tabmodifier, 3);
        modifierButtonAmbiance(tabmodifierAmbiance,0);
        modifierButtonAmbiance(tabmodifierAmbiance, 1);




    }

    private void buttonsTool(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageProgramme.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

    }

    private void initialiserVariables(){
        programme1 = findViewById(R.id.programme1);
        programme2 = findViewById(R.id.programme2);
        programme3 = findViewById(R.id.programme3);
        programme4 = findViewById(R.id.programme4);
        modifier1 = findViewById(R.id.modifier1);
        modifier2 = findViewById(R.id.modifier2);
        modifier3 = findViewById(R.id.modifier3);
        modifier4 = findViewById(R.id.modifier4);
        ambiance1 = findViewById(R.id.ambiancetext1);
        ambiance2 = findViewById(R.id.ambiancetext2);
        modifierAmbiance1 = findViewById(R.id.modifierAmbiance1);
        modifierAmbiance2 = findViewById(R.id.modifierAmbiance2);
    }

    private void afficherNoms(final TextView tabprogramme[], final TextView tabambiance[], final ImageButton tabmodifier[], final ImageButton tabmodifierAmbiance[]){

        String programme = convertirDeJson("programmes.json");
        if(programme==null){
            System.out.print("test dans la boucle ");
            programme = convertirDeJsonRessource("programmes.json");
            System.out.println("test programme"+programme);
        }
        String ambiance = convertirDeJson("ambiances.json");
        if(ambiance==null){
            ambiance = convertirDeJsonRessource("ambiances.json");
        }
        System.out.println("test ambiance"+programme);
        Ambiances ambianceJson = new Gson().fromJson(ambiance, Ambiances.class);
        Programmes programmeJson = new Gson().fromJson(programme, Programmes.class);
        System.out.println(programmeJson);
        for(int i=0;i<programmeJson.getProgrammes().size();i++) {
            if(i<3) {
                tabprogramme[i].setText(programmeJson.getProgrammes().get(i).getNomProgramme());
                tabmodifier[i].setVisibility(View.VISIBLE);
            }
        }

        for(int i=0;i<ambianceJson.getAmbiances().size();i++){
            if(i<2){
                tabambiance[i].setText(ambianceJson.getAmbiances().get(i).getNomProgramme());
                tabmodifierAmbiance[i].setVisibility(View.VISIBLE);
            }
        }
    }

    private String convertirDeJson(String fileName){
        String json = null;
        try {
            File file = new File(getFilesDir(), fileName);
            FileInputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private String convertirDeJsonRessource(String fileName){
        String programmeJson = null;

        try {
            //File file = new File(getFilesDir(), fileName);
            //FileInputStream inputStream = new FileInputStream(file);
            InputStream inputStream = getAssets().open(fileName);
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

    private void modifierButton(final ImageButton modifier[],final int i){
        modifier[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentload = new Intent(PageProgramme.this, PageModifierProgramme.class);
                intentload.putExtra("pos", i);
                startActivity(intentload);
            }
        });
    }

    private void modifierButtonAmbiance(final ImageButton modifier[],final int i){
        modifier[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentload = new Intent(PageProgramme.this, PageModifierAmbiance.class);
                intentload.putExtra("pos", i);
                startActivity(intentload);
            }
        });
    }
}

