package com.nexu.projet.miashs.nexu;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Programmes {

    @SerializedName("programmes")
    @Expose
    private List<Programme> programmes = null;

    public List<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<Programme> programmes) {
        this.programmes = programmes;
    }

}