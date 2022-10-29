package com.example.pokemon.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "fav_table")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String URL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
