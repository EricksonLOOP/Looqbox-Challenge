package com.edev.looqbox.challange.Service.PokemonServices;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonDTOWithHighlight;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.edev.looqbox.challange.Service.OkHttpServices.OkHttpServices;
import com.edev.looqbox.challange.Service.appServices.AppServices;
import com.edev.looqbox.challange.Service.cacheServices.CacheServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PokemonServiceImpl implements PokemonServices {

    private final OkHttpServices okHttpServices;
    private final CacheServices cacheServices;
    private final AppServices appServices;

    @Value("${pokeapi.url}")
    private String apiUrl;

    public PokemonServiceImpl(OkHttpServices okHttpServices, CacheServices cacheServices, AppServices appServices) {
        this.okHttpServices = okHttpServices;
        this.cacheServices = cacheServices;
        this.appServices = appServices;

    }

    @Override
    public ResponseEntity<?> getPokemons(String query, String sort) {
        try {
            if (query == null) {
                return handleNoQueryCache(sort);
            }

            if (cacheServices.isInCache(query.toLowerCase())) {
                PokemonDTO cachedPokemon = cacheServices.getFromCacheAsPokemonDTO(query.toLowerCase());
                return ResponseEntity.status(HttpStatus.OK).body(appServices.sort(sort, cachedPokemon));
            }

            if (cacheServices.isInCache("noquery")) {
                PokemonDTO cachedNoQueryPokemon = cacheServices.getFromCacheAsPokemonDTO("noquery");
                PokemonDTO filteredPokemon = appServices.filterByQuery(query, cachedNoQueryPokemon);
                cacheServices.addtoCache(query.toLowerCase(), filteredPokemon);
                return ResponseEntity.status(HttpStatus.OK).body(appServices.sort(sort, filteredPokemon));
            }

            PokemonResponse response = okHttpServices.getPokemons(apiUrl);
            PokemonDTO pokemonDTO = appServices.createPokemonDto(response);
            cacheServices.addtoCache("noquery", pokemonDTO);
            PokemonDTO filteredPokemon = appServices.filterByQuery(query, pokemonDTO);
            cacheServices.addtoCache(query.toLowerCase(), filteredPokemon);
            return ResponseEntity.status(HttpStatus.OK).body(filteredPokemon);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching pokemons: " + e.getCause());
        }
    }

    @Override
    public ResponseEntity<?> getPokemonsWithHighligth(String query, String sort) {
        try {
            if (query == null || query.isEmpty()) {
                return handleNoQueryCache(sort);
            }

            if (cacheServices.isInCache(query.toLowerCase())) {
                PokemonDTO cachedPokemon = cacheServices.getFromCacheAsPokemonDTO(query.toLowerCase());
                return ResponseEntity.ok(appServices.sort(sort, cachedPokemon));
            }

            if (cacheServices.isInCache("noquery")) {
                PokemonDTO cachedNoQueryPokemon = cacheServices.getFromCacheAsPokemonDTO("noquery");
                PokemonDTO filteredPokemon = appServices.filterByQuery(query, cachedNoQueryPokemon);
                return ResponseEntity.ok(new PokemonDTOWithHighlight(appServices.generateHighlightedResponse(filteredPokemon, query)));
            }

            PokemonResponse response = okHttpServices.getPokemons(apiUrl);
            PokemonDTO pokemonDTO = appServices.createPokemonDto(response);
            cacheServices.addtoCache("noquery", pokemonDTO);
            PokemonDTO filteredPokemon = appServices.filterByQuery(query, pokemonDTO);
            cacheServices.addtoCache(query.toLowerCase(), filteredPokemon);
            return ResponseEntity.ok(new PokemonDTOWithHighlight(appServices.generateHighlightedResponse(filteredPokemon, query)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching highlighted pokemons: " + e.getCause());
        }
    }

    private ResponseEntity<?> handleNoQueryCache(String sort) {
        if (cacheServices.isInCache("noquery")) {
            PokemonDTO cachedNoQueryPokemon = cacheServices.getFromCacheAsPokemonDTO("noquery");
            return ResponseEntity.ok(appServices.sort(sort, cachedNoQueryPokemon));
        }

        PokemonResponse response = okHttpServices.getPokemons(apiUrl);
        PokemonDTO pokemonDTO = appServices.createPokemonDto(response);
        cacheServices.addtoCache("noquery", pokemonDTO);
        return ResponseEntity.ok(appServices.sort(sort, pokemonDTO));
    }
}
