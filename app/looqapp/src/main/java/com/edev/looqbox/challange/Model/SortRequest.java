package com.edev.looqbox.challange.Model;

public class SortRequest {
    private String sort;
    private PokemonDTO pokemonDTO;

    public PokemonDTO getPokemonDTO() {
        return pokemonDTO;
    }

    public void setPokemonDTO(PokemonDTO pokemonDTO) {
        this.pokemonDTO = pokemonDTO;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public SortRequest(String sort, PokemonDTO pokemonDTO) {
        this.sort = sort;
        this.pokemonDTO = pokemonDTO;
    }
}
