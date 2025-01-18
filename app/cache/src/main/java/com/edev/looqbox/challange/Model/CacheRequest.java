package com.edev.looqbox.challange.Model;

public class CacheRequest {
    private String key;
    private PokemonDTO pokemonDTO;

    public PokemonDTO getPokemonDTO() {
        return pokemonDTO;
    }

    public void setPokemonDTO(PokemonDTO pokemonDTO) {
        this.pokemonDTO = pokemonDTO;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
