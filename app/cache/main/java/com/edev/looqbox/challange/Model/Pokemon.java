package com.edev.looqbox.challange.Model;

public class Pokemon {
    private String name;
    private String highlight;

    public Pokemon(String name, String highlight) {
        this.name = name;
        this.highlight = highlight;
    }
    // Getters
    public String getName() {
        return name;
    }
    public String getHighlight(){
        return highlight;
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setHighlight(String highligth){
        this.highlight = highligth;
    }
 }
