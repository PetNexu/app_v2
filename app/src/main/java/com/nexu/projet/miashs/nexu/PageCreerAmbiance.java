package com.nexu.projet.miashs.nexu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PageCreerAmbiance extends AppCompatActivity{


    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton valider;

    TextView objet1text;
    TextView objet2text;
    TextView objet3text;
    TextView objet4text;
    TextView action1text;
    TextView action2text;
    TextView action3text;
    TextView action4text;
    Spinner objet1;
    Spinner objet2;
    Spinner objet3;
    Spinner objet4;
    Spinner action1;
    Spinner action2;
    Spinner action3;
    Spinner action4;
    ImageButton ajouteraction1;
    ImageButton ajouteraction2;

    TextView intensite1;
    TextView intensite2;
    TextView intensite3;

    ColorSeekBar colorSeekBar1;
    ColorSeekBar colorSeekBar2;
    ColorSeekBar colorSeekBar3;

    TextInputLayout intensite1Input;
    TextInputLayout intensite2Input;
    TextInputLayout intensite3Input;

    TextInputEditText intesite1in;
    TextInputEditText intesite2in;
    TextInputEditText intesite3in;

    String colorChoisie[]={"a","b","c"};

    String intensiteChoisie[]={"a","b","c"};





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_ambiance);
        buttonToolBar();




        String json = convertirDeJson("actions_possibles_ambiance.json");
        final ActionsPossibles actionsJson = new Gson().fromJson(json, ActionsPossibles.class);
        initialiserVariables();
        TextView objets[] = {objet1text, objet2text, objet3text};
        TextView actions[] = {action1text, action2text, action3text};
        final Spinner objetSpinner[] = {objet1, objet2, objet3};
        final Spinner actionSpinner[] = {action1, action2, action3};
        adapterSpinner(actionsJson, 0, objetSpinner, actionSpinner);
        ImageButton buttons[] = {ajouteraction1, ajouteraction2};

        for(int i=0;i<2;i++) {
            ajouterAction(actionsJson, actionSpinner, objetSpinner, i, objets, actions, buttons);
        }

        //Bouton valider
        valider = (ImageButton) findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText nom = findViewById(R.id.textinputnoma);
                if ((nom.getText().toString().trim().equals("")))
                {
                    Toast.makeText(getApplicationContext(), "Le nom du programme est vide", Toast.LENGTH_SHORT).show();
                }else {
                    convertirAmbianceJson(actionSpinner, objetSpinner);
                    Intent intentLoad = new Intent(PageCreerAmbiance.this, PageProgramme.class);
                    startActivity(intentLoad);
                }
            }
        });




    }

    /**
     * Met en place tous les boutons de la toolbar
     */

    private void buttonToolBar(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers compte
        buttonCompte = (ImageButton) findViewById(R.id.Compte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageCreerAmbiance.this, PageCompte.class);
                startActivity(intentLoad);
            }
        });

    }

    /**
     * Permet d'initialiser toutes les variables qui seront souvent utilisées dans la classe
     */

    private void initialiserVariables(){
        objet1text = findViewById(R.id.objet1text);
        objet2text = findViewById(R.id.objet2text);
        objet3text = findViewById(R.id.objet3text);
        action1text = findViewById(R.id.action1text);
        action2text = findViewById(R.id.action2text);
        action3text = findViewById(R.id.action3text);
        objet1 = findViewById(R.id.objet1);
        objet2 = findViewById(R.id.objet2);
        objet3 = findViewById(R.id.objet3);
        action1 = findViewById(R.id.action1);
        action2 = findViewById(R.id.action2);
        action3 = findViewById(R.id.action3);
        ajouteraction1 = findViewById(R.id.ajouterAction1);
        ajouteraction2 = findViewById(R.id.ajouteraction2);
        intensite1 = findViewById(R.id.Intensite1);
        intensite2 = findViewById(R.id.intensite2);
        intensite3 = findViewById(R.id.intensite3);
        intensite1Input = findViewById(R.id.textInputLayout4);
        intensite2Input = findViewById(R.id.textInputLayout5);
        intensite3Input = findViewById(R.id.textInputLayout6);
        colorSeekBar1 = findViewById(R.id.color_seek_bar);
        colorSeekBar2 = findViewById(R.id.color_seek_bar2);
        colorSeekBar3 = findViewById(R.id.color_seek_bar3);
        intesite1in = findViewById(R.id.inputintesite1);
        intesite2in = findViewById(R.id.inputintensite2);
        intesite3in = findViewById(R.id.inputintensite3);
    }


    /**
     * Permet de transformer un fichier json en une chaine de caractère
     * @param fileName désignation du fichier json à transformer en json
     * @return une chaine de caractère qui contient le fichier json
     */

    private String convertirDeJson(String fileName){
        String json = null;
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(fileName);
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


    /**
     * Permet d'ajouter tous les objets disponibles dans les spinners de type Objet
     * @param spinner pour lequel il faut ajouter des objets
     * @param actions liste des actions et nom des objets => permet d'ajouter les noms des objets
     */


    private void addItemOnSpinnerObjet(Spinner spinner, ActionsPossibles actions){
        List<String> list = new ArrayList<>();
        list.add("");
        for(int i=0;i<actions.getObjets().size();i++){
            list.add(actions.getObjets().get(i).getNom());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    private void ajouterIntensiteCouleur(boolean intensite,final int pos, TextView intensiteText[], TextInputLayout intensiteLayout[], ColorSeekBar color[]){
        if(intensite){
            intensiteText[pos].setVisibility(View.VISIBLE);
            intensiteLayout[pos].setVisibility(View.VISIBLE);
        }else{
            color[pos].setVisibility(View.VISIBLE);
            color[pos].setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
                @Override
                public void onColorChangeListener(int i, int i1, int i2) {
                    String hexColor = String.format("#%06X", (0xFFFFFF & i2));
                    hexColor = hexColor.substring(1);
                    colorChoisie[pos] = hexColor;
                    System.out.print(" " + hexColor + " ");
                }
            });
        }
    }

    /**
     * Permet de changer le spinner action en fonction de la sélection du spinner Objet
     * @param actionsAdapter actions à ajouter
     * @param pos des spinners dans leurs tableaux respectifs (voir Spinner objetSpinner[] et Spinner actionSpinner[])
     *
     *
     */

    private void adapterSpinner(final ActionsPossibles actionsAdapter, final int pos, final Spinner objetSpinner[], final Spinner actionSpinner[]){
        addItemOnSpinnerObjet(objetSpinner[pos], actionsAdapter);
        objetSpinner[pos].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(objetSpinner[pos].getSelectedItemPosition()!=0) {
                    addItemOnSpinnerAction(actionSpinner[pos], actionsAdapter, objetSpinner[pos].getSelectedItemPosition() - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        actionSpinner[pos].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView intensiteText[] = {intensite1, intensite2, intensite3};
                System.out.print(intensite1.getText().toString());
                TextInputLayout intensiteInput[] = {intensite1Input, intensite2Input, intensite3Input};
                ColorSeekBar color[] = {colorSeekBar1, colorSeekBar2, colorSeekBar3};
                if(objetSpinner[pos].getSelectedItemPosition()==2){
                    if(actionSpinner[pos].getSelectedItemPosition()==6){
                        System.out.println("couleur");
                        ajouterIntensiteCouleur(false, pos, intensiteText, intensiteInput, color);
                    }
                    if(actionSpinner[pos].getSelectedItemPosition()==7){
                        ajouterIntensiteCouleur(true, pos, intensiteText, intensiteInput, color);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Permet d'ajouter des actions dans un Spinner de type action
     * @param spinner pour lequel il faut ajouter des actions
     * @param actions tableau pour ajouter actions dans le spinner
     * @param i position des actions à ajouter dans le taleau
     */

    private void addItemOnSpinnerAction(Spinner spinner, ActionsPossibles actions, int i){
        List<String> list = new ArrayList<>();
        list.add("");
        for(int j=0;j<actions.getObjets().get(i).getActions().size();j++){
            list.add(actions.getObjets().get(i).getActions().get(j));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * Gere l'evenement lorsque l'utilisateur clique sur le bouton + pour ajouter un objet et une action
     * @param actions possibles
     * @param actionSpinner tableau des spinners de type action
     * @param objetSpinner tableau des spinners de type objet
     * @param numero numero du bouton appuyé (ou non)
     * @param objet tableau des textes Objets (=> permet de mettre la visibilité en place)
     * @param action tableau des textes Actions (=> permet de mettre la visibiité en place)
     * @param ajouter tableau des ImageButton ajouterAction (=> Idem)
     */

    private void ajouterAction(final ActionsPossibles actions, final Spinner actionSpinner[], final Spinner objetSpinner[], final int numero, final  TextView objet[], final TextView action[], final ImageButton ajouter[]){
        ajouter[numero].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actionSpinner[numero].getSelectedItem()!=null) {
                    if(!actionSpinner[numero].getSelectedItem().toString().equals(" ")) {
                        objetSpinner[numero + 1].setVisibility(View.VISIBLE);
                        actionSpinner[numero + 1].setVisibility(View.VISIBLE);
                        objet[numero + 1].setVisibility(View.VISIBLE);
                        action[numero + 1].setVisibility(View.VISIBLE);
                        if (numero != 1) {
                            ajouter[numero + 1].setVisibility(View.VISIBLE);
                        }
                        ajouter[numero].setVisibility(View.INVISIBLE);
                        adapterSpinner(actions, numero + 1, objetSpinner, actionSpinner);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Veuillez renseigner l'action avant d'en rajouter une autre", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void convertirAmbianceJson(final Spinner actionSpinner[], final Spinner objetSpinner[]){
        String programmeJson = null;

        try {
            File file = new File(getFilesDir(), "ambiances.json");
            FileInputStream inputStream = new FileInputStream(file);
            //InputStream inputStream = getAssets().open("ambiances.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            programmeJson = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("hey"+programmeJson);
        TextInputEditText it[] = {intesite1in,intesite2in, intesite3in};

        Ambiances ambiances = new Gson().fromJson(programmeJson, Ambiances.class);
        TextInputEditText nomAmbiance = findViewById(R.id.textinputnoma);
        List<Ambiance> ambianceObjet;
        ambianceObjet = ambiances.getAmbiances();
        List<String> listObjet = new ArrayList<>();
        List<String> listAction = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(objetSpinner[i].getSelectedItem()!=null && actionSpinner[i].getSelectedItem()!=null)
            {
                if (!objetSpinner[i].getSelectedItem().toString().equals("") && !actionSpinner[i].getSelectedItem().toString().equals("")) {
                    if(objetSpinner[i].getSelectedItemPosition()==2 && actionSpinner[i].getSelectedItemPosition()==6){
                        listObjet.add("Couleur");
                        listAction.add(colorChoisie[i]);
                    }else if(objetSpinner[i].getSelectedItemPosition()==2 && actionSpinner[i].getSelectedItemPosition()==7){
                        listObjet.add("Intensite");
                        System.out.println(it[i].getText().toString());
                        listAction.add(it[i].getText().toString());
                    }else {
                        listObjet.add(objetSpinner[i].getSelectedItem().toString());
                        listAction.add(actionSpinner[i].getSelectedItem().toString());
                    }
                }
            }
        }
        Ambiance ambianceAjouter = new Ambiance();
        ambianceAjouter.setNomProgramme(nomAmbiance.getText().toString());
        System.out.println(ambianceAjouter.getNomProgramme());
        ambianceAjouter.setObjets(listObjet);
        ambianceAjouter.setActions(listAction);
        ambianceObjet.add(ambianceAjouter);
        ambiances.setAmbiances(ambianceObjet);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ambiances);


        try {
            Writer output;
            File file = new File(getFilesDir(), "ambiances.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(json);
            System.out.println("okey");
            output.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
