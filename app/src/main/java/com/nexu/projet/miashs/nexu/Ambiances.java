package com.nexu.projet.miashs.nexu;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ambiances {

    @SerializedName("ambiances")
    @Expose
    private List<Ambiance> ambiances = null;

    public List<Ambiance> getAmbiances() {
        return ambiances;
    }

    public void setAmbiances(List<Ambiance> ambiances) {
        this.ambiances = ambiances;
    }

}
