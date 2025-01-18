package com.edev.looqbox.challange.Service.AppServices;

import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.edev.looqbox.challange.Service.SorterServices.SorterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServicesImpl implements AppServices{
    @Autowired
    private final SorterServices sorterServices;

    public AppServicesImpl(SorterServices sorterServices) {

        this.sorterServices = sorterServices;
    }

    @Override
    public PokemonDTO createPokemonDto(PokemonResponse response) {
        List<String> pokemonsNames = new ArrayList<>();
        response.getResults().forEach(pokemon -> pokemonsNames.add(pokemon.getName()));
        return new PokemonDTO(pokemonsNames);
    }

    @Override
    public PokemonDTO sort(String sort, PokemonDTO pokemonDTO) {
        if (sort.equals("length")) {
            return new PokemonDTO(sorterServices.sortbByLength(pokemonDTO.getResult()));
        }

        return new PokemonDTO(sorterServices.sortbByName(pokemonDTO.getResult()));

    }

    @Override
    public PokemonDTO filterByQuery(String query, PokemonDTO pokemonDTO) {
        List<String> filteredResults = pokemonDTO.getResult().stream()
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        return new PokemonDTO(filteredResults);
    }

    @Override
    public List<Pokemon> generateHighlightedResponse(PokemonDTO filteredPokemonDto, String query) {

        return filteredPokemonDto.getResult().stream()
                .map(name -> {
                    String highlight = name.replaceAll("(?i)("+query+")", "<pre>$1</pre>");
                    return new Pokemon(name, highlight);
                })
                .collect(Collectors.toList());
    }
}
