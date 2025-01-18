package com.edev.looqbox.challange.Model;

import java.util.List;

public class PokemonDTO {
    public List<String> result;
    public PokemonDTO(List<String> result){
        this.result = result;
    }
    public List<String> getResult(){
        return result;
    }
    public void setResult(List<String> result){
        this.result = result;
    }
}
