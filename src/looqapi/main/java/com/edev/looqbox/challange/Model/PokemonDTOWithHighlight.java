package com.edev.looqbox.challange.Model;

import java.util.List;

public class PokemonDTOWithHighlight {
    public List<Pokemon> result;
    public PokemonDTOWithHighlight(List<Pokemon> result){
        this.result = result;
    }
    public List<Pokemon> getResult(){
        return result;
    }
    public void setResult(List<Pokemon> result){
        this.result = result;
    }
}
