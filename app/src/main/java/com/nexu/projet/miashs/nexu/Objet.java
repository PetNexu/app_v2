package com.nexu.projet.miashs.nexu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Objet {

    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("actions")
    @Expose
    private List<String> actions = null;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}

