package com.nexu.projet.miashs.nexu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evenement {

    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("heureDebut")
    @Expose
    private Integer heureDebut;
    @SerializedName("heureFin")
    @Expose
    private Integer heureFin;
    @SerializedName("minuteDebut")
    @Expose
    private Integer minuteDebut;
    @SerializedName("minuteFin")
    @Expose
    private Integer minuteFin;
    @SerializedName("jour")
    @Expose
    private Integer jour;
    @SerializedName("mois")
    @Expose
    private Integer mois;
    @SerializedName("Objets")
    @Expose
    private List<String> objets = null;
    @SerializedName("Actions")
    @Expose
    private List<String> actions = null;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public Integer getMinuteDebut() {
        return minuteDebut;
    }

    public void setMinuteDebut(Integer minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    public Integer getMinuteFin() {
        return minuteFin;
    }

    public void setMinuteFin(Integer minuteFin) {
        this.minuteFin = minuteFin;
    }

    public Integer getJour() {
        return jour;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
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