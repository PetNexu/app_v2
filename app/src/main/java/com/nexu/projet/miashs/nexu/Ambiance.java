package com.nexu.projet.miashs.nexu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ambiance {

    @SerializedName("nomProgramme")
    @Expose
    private String nomProgramme;
    @SerializedName("Objets")
    @Expose
    private List<String> objets = null;
    @SerializedName("Actions")
    @Expose
    private List<String> actions = null;

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public List<String> getObjets() {
        return objets;
    }

    public void setObjets(List<String> objets) {
        this.objets = objets;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

}