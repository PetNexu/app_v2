package com.nexu.projet.miashs.nexu.Modele;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

public class Notification extends RealmObject {
    private String name;

    @Override
    public String toString() {
        Timestamp date = new Timestamp(this.getTimestamp());
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Notification "+sdf.format(date)+"  "+"\n"+this.getText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @PrimaryKey
    private long timestamp;
    private String text;


}
