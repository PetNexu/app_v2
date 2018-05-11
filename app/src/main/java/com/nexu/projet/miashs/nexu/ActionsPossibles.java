package com.nexu.projet.miashs.nexu;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionsPossibles {

    @SerializedName("objets")
    @Expose
    private List<Objet> objets = null;

    public List<Objet> getObjets() {
        return objets;
    }

    public void setObjets(List<Objet> objets) {
        this.objets = objets;
    }

}