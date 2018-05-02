package com.nexu.projet.miashs.nexu;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionsPossibles {

    @SerializedName("actions")
    @Expose
    private List<String> actions = null;
    @SerializedName("nbactions")
    @Expose
    private Integer nbactions;

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public Integer getNbactions() {
        return nbactions;
    }

    public void setNbactions(Integer nbactions) {
        this.nbactions = nbactions;
    }

}