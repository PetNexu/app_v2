package com.nexu.projet.miashs.nexu;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


public class PageModifierAmbiance extends AppCompatActivity{


    ImageButton buttonInventaire;
    ImageButton buttonAccueil;
    ImageButton buttonCalendrier;
    ImageButton buttonProgramme;
    ImageButton buttonMap;
    ImageButton buttonStatistiques;
    ImageButton buttonCompte;
    ImageButton valider;

    TextInputEditText nom;
    TextView objet1text;
    TextView objet2text;
    TextView objet3text;
    TextView action1text;
    TextView action2text;
    TextView action3text;
    Spinner objet1;
    Spinner objet2;
    Spinner objet3;
    Spinner action1;
    Spinner action2;
    Spinner action3;
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

    int pos;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_ambiance);
        buttonsTool();

        Intent incomingIntent = getIntent();
        pos = incomingIntent.getIntExtra("pos", 0);
        String programmeJson = convertirDeJson("ambiances.json");
        String actionsJson = convertirDeJsonAssets("actions_possibles_ambiance.json");

        Ambiances ambiance = new Gson().fromJson(programmeJson, Ambiances.class);
        ActionsPossibles actionsPossibles = new Gson().fromJson(actionsJson, ActionsPossibles.class);



        initialiserVariables();
        TextView objets[] = {objet1text, objet2text, objet3text};
        TextView actions[] = {action1text, action2text, action3text};
        final Spinner objetSpinner[] = {objet1, objet2, objet3};
        final Spinner actionSpinner[] = {action1, action2, action3};
        ImageButton buttons[] = {ajouteraction1, ajouteraction2};


        initialiserInterieurSpinner(ambiance, objetSpinner, actionSpinner, actionsPossibles, objets, actions, buttons);

        for(int i=0;i<2;i++) {
            ajouterAction(actionsPossibles, actionSpinner, objetSpinner, i, objets, actions, buttons);
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
                    convertirProgrammeJson(actionSpinner, objetSpinner);
                    Intent intentLoad = new Intent(PageModifierAmbiance.this, PageProgramme.class);
                    startActivity(intentLoad);
                }
            }
        });

        Button supprimer = findViewById(R.id.supprimer);
        supprimer.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supprimerProgramme();
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        }));

    }

    /**
     *
     */


    private void buttonsTool(){
        //Bouton vers accueil
        buttonAccueil = (ImageButton) findViewById(R.id.logo);
        buttonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, MainActivity.class);
                startActivity(intentLoad);
            }
        });



        //Bouton vers inventaire
        buttonInventaire = (ImageButton) findViewById(R.id.inventaire);
        buttonInventaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageInventaire.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers calendrier
        buttonCalendrier = (ImageButton) findViewById(R.id.calendrier); //calendrier correspond à l'ID du bouton
        buttonCalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageCalendrier.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers programme
        buttonProgramme = (ImageButton) findViewById(R.id.programme);
        buttonProgramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageProgramme.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers map
        buttonMap = (ImageButton) findViewById(R.id.map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageMap.class);
                startActivity(intentLoad);
            }
        });

        //Bouton vers statistiques
        buttonStatistiques = (ImageButton) findViewById(R.id.statistiques);
        buttonStatistiques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoad = new Intent(PageModifierAmbiance.this, PageStatistiques.class);
                startActivity(intentLoad);
            }
        });
    }

    /**
     *
     * @param fileName
     * @return
     */

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

    /**
     *
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
        intesite1in = findViewById(R.id.inputintensite);
        intesite2in = findViewById(R.id.inputintesite1);
        intesite3in = findViewById(R.id.inputintensite2);
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

    /**
     * Permet de changer le spinner action en fonction de la sélection du spinner Objet
     * @param actionsAdapter actions à ajouter
     * @param pos des spinners dans leurs tableaux respectifs (voir Spinner objetSpinner[] et Spinner actionSpinner[])
     */

    private void adapterSpinner(final ActionsPossibles actionsAdapter, final int pos, final Spinner objetSpinner[], final Spinner actionSpinner[]){
        addItemOnSpinnerObjet(objetSpinner[pos], actionsAdapter);
        objetSpinner[pos].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(objetSpinner[pos].getSelectedItemPosition()!=0) {
                    //actionSpinner[0].setSelection(0);
                    addItemOnSpinnerAction(actionSpinner[pos], actionsAdapter, objetSpinner[pos].getSelectedItemPosition() - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
     *
     * @param actionsAdapter
     * @param pos
     * @param objetSpinner
     * @param actionSpinner
     */

    private void adapterModifierSpinner(final ActionsPossibles actionsAdapter, final int pos, final Spinner objetSpinner[], final Spinner actionSpinner[]){
        //addItemOnSpinnerObjet(objetSpinner[pos], actionsAdapter);
        objetSpinner[pos].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(objetSpinner[pos].getSelectedItemPosition()!=0) {
                    actionSpinner[pos].setSelection(0);
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
                if (objetSpinner[pos].getSelectedItemPosition() == 2) {
                    if (actionSpinner[pos].getSelectedItemPosition() == 6) {
                        System.out.println("couleur");
                        ajouterIntensiteCouleur(false, pos, intensiteText, intensiteInput, color);
                    }
                    if (actionSpinner[pos].getSelectedItemPosition() == 7) {
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
     * Permet de transformer un fichier json en une chaine de caractère
     * @param fileName désignation du fichier json à transformer en json
     * @return une chaine de caractère qui contient le fichier json
     */

    private String convertirDeJsonAssets(String fileName){
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
     *
     * @param objetSpinner
     * @param pos
     * @param actionSpinner
     * @param actionsPossibles
     */

    private void onChangeSpinner(final Spinner objetSpinner[], final int pos, final Spinner actionSpinner[], final ActionsPossibles actionsPossibles){
        objetSpinner[pos].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterModifierSpinner(actionsPossibles, pos, objetSpinner, actionSpinner);
            }
            public void onNothingSelected(AdapterView<?> adapterView){}
        });
    }

    /**
     *
     * @param ambiance
     * @param objetSpinner
     * @param actionSpinner
     * @param actionsPossibles
     * @param objets
     * @param actions
     * @param buttons
     */

    private void initialiserInterieurSpinner(Ambiances ambiance, Spinner objetSpinner[], Spinner actionSpinner[], ActionsPossibles actionsPossibles, TextView objets[], TextView actions[], ImageButton buttons[]){
        nom = findViewById(R.id.textinputnoma);
        nom.setText(ambiance.getAmbiances().get(pos).getNomProgramme());

        TextView intensiteText[] = {intensite1, intensite2, intensite3};
        TextInputLayout intensiteInput[] = {intensite1Input, intensite2Input, intensite3Input};
        ColorSeekBar color[] = {colorSeekBar1, colorSeekBar2, colorSeekBar3};

        for(int i=0;i<ambiance.getAmbiances().get(pos).getObjets().size();i++){
            if(ambiance.getAmbiances().get(pos)!=null){
                addItemOnSpinnerObjet(objetSpinner[i], actionsPossibles);
                if(i!=0) {
                    objets[i].setVisibility(View.VISIBLE);
                    actions[i].setVisibility(View.VISIBLE);
                    objetSpinner[i].setVisibility(View.VISIBLE);
                    actionSpinner[i].setVisibility(View.VISIBLE);
                    buttons[i-1].setVisibility(View.INVISIBLE);
                    System.out.println("sier"+ambiance.getAmbiances().get(pos).getObjets().size());
                    if(i!=2) {
                        buttons[i].setVisibility(View.VISIBLE);
                    }
                }
                if(ambiance.getAmbiances().get(pos).getObjets().get(i).equals("Couleur")){
                    objetSpinner[i].setSelection(2);
                    System.out.println("couleur test");
                    addItemOnSpinnerAction(actionSpinner[i], actionsPossibles, 1);
                    color[i].setVisibility(View.VISIBLE);
                }else if(ambiance.getAmbiances().get(pos).getObjets().get(i).equals("Intensite")){
                    objetSpinner[i].setSelection(2);
                    addItemOnSpinnerAction(actionSpinner[i], actionsPossibles, 1);
                    intensiteText[i].setVisibility(View.VISIBLE);
                    intensiteInput[i].setVisibility(View.VISIBLE);
                }else {
                    for (int j = 0; j < actionsPossibles.getObjets().size(); j++) {
                        if (actionsPossibles.getObjets().get(j).getNom().equals(ambiance.getAmbiances().get(pos).getObjets().get(i))) {
                            objetSpinner[i].setSelection(j + 1);
                            addItemOnSpinnerAction(actionSpinner[i], actionsPossibles, j);
                        }
                    }
                }
                int posObjet = objetSpinner[i].getSelectedItemPosition()-1;
                System.out.println("size"+actionsPossibles.getObjets().size()+"pos"+posObjet);
                for(int j=0;j<actionsPossibles.getObjets().get(posObjet).getActions().size();j++){
                    if(actionsPossibles.getObjets().get(posObjet).getActions().get(j).equals(ambiance.getAmbiances().get(pos).getActions().get(i))){
                        actionSpinner[i].setSelection(j+1);
                    }
                }
                onChangeSpinner(objetSpinner,i,actionSpinner,actionsPossibles);
            }
        }
    }


    private void ajouterAction(final ActionsPossibles actions, final Spinner actionSpinner[], final Spinner objetSpinner[], final int numero, final  TextView objet[], final TextView action[], final ImageButton ajouter[]){
        ajouter[numero].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(actionSpinner[numero].getSelectedItem()!=null) {
                    if(!actionSpinner[numero].getSelectedItem().toString().equals("")) {
                        objetSpinner[numero + 1].setVisibility(View.VISIBLE);
                        actionSpinner[numero + 1].setVisibility(View.VISIBLE);
                        objet[numero + 1].setVisibility(View.VISIBLE);
                        action[numero + 1].setVisibility(View.VISIBLE);
                        if (numero <1) {
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

    private void convertirProgrammeJson(final Spinner actionSpinner[], final Spinner objetSpinner[]){
        String programmeJson = convertirDeJson("ambiances.json");
        Ambiances ambiances = new Gson().fromJson(programmeJson, Ambiances.class);
        List<Ambiance> ambianceObjet = ambiances.getAmbiances();
        List<String> listObjet = new ArrayList<>();
        List<String> listAction = new ArrayList<>();
        intesite1in = findViewById(R.id.inputintensite);
        intesite2in = findViewById(R.id.inputintesite1);
        intesite3in = findViewById(R.id.inputintensite2);
        TextInputEditText it[] = {intesite1in,intesite2in, intesite3in};
        for(int i=0;i<3;i++){
            if(objetSpinner[i].getSelectedItem()!=null && actionSpinner[i].getSelectedItem()!=null)
            {
                if (!objetSpinner[i].getSelectedItem().toString().equals("") && !actionSpinner[i].getSelectedItem().toString().equals("")) {
                    if(objetSpinner[i].getSelectedItemPosition()==2 && actionSpinner[i].getSelectedItemPosition()==6){
                        listObjet.add("Couleur");
                        listAction.add(colorChoisie[i]);
                    }else if(objetSpinner[i].getSelectedItemPosition()==2 && actionSpinner[i].getSelectedItemPosition()==7){
                        listObjet.add("Intensite");

                        System.out.println("intesite"+it[i].getText().toString());
                        listAction.add(it[i].getText().toString());
                    }else {
                        listObjet.add(objetSpinner[i].getSelectedItem().toString());
                        listAction.add(actionSpinner[i].getSelectedItem().toString());
                    }
                }
            }
        }


        nom = findViewById(R.id.textinputnoma);
        ambianceObjet.get(pos).setNomProgramme(nom.getText().toString());
        ambianceObjet.get(pos).setObjets(listObjet);
        ambianceObjet.get(pos).setActions(listAction);
        ambiances.setAmbiances(ambianceObjet);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ambiances);



        try {
            Writer output;
            File file = new File(getFilesDir(), "ambiances.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(json);
            output.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void supprimerProgramme(){
        String programmeJson = convertirDeJson("ambiances.json");
        Ambiances ambiances = new Gson().fromJson(programmeJson, Ambiances.class);
        List<Ambiance> ambianceObjet = new ArrayList<>();
        for(int i=0;i< ambiances.getAmbiances().size();i++){
            if(i!=pos){
                ambianceObjet.add(ambiances.getAmbiances().get(i));
            }
        }
        ambiances.setAmbiances(ambianceObjet);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(ambiances);

        try {
            Writer output;
            File file = new File(getFilesDir(), "ambiances.json");
            output = new BufferedWriter(new FileWriter(file));
            output.write(json);
            output.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }
}
