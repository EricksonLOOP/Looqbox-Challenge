package com.edev.looqbox.challange.Model;

public class FilterRequest {
    private String query;
    private PokemonDTO pokemonDTO;

    public PokemonDTO getPokemonDTO() {
        return pokemonDTO;
    }

    public void setPokemonDTO(PokemonDTO pokemonDTO) {
        this.pokemonDTO = pokemonDTO;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public FilterRequest(String query, PokemonDTO pokemonDTO) {
        this.query = query;
        this.pokemonDTO = pokemonDTO;
    }
}
