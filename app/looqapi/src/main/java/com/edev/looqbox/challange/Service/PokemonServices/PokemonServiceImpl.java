package com.edev.looqbox.challange.Service.PokemonServices;

import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonDTOWithHighlight;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.edev.looqbox.challange.Service.OkHttpServices.OkHttpServices;
import com.edev.looqbox.challange.Service.consumerService.LooqAppClient.LooqAppClient;
import com.edev.looqbox.challange.Service.consumerService.CacheClient.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PokemonServiceImpl implements PokemonServices {
    @Autowired
    private final OkHttpServices okHttpServices;
    @Autowired
    private final LooqAppClient appClient;
    @Autowired
    private final CacheClient cacheClient;

    @Value("${pokeapi.url}")
    private String apiUrl;

    public PokemonServiceImpl(OkHttpServices okHttpServices, LooqAppClient appClient, CacheClient cacheClient) {
        this.okHttpServices = okHttpServices;
        this.appClient = appClient;

        this.cacheClient = cacheClient;
    }

    @Override
    public ResponseEntity<?> getPokemons(String query, String sort) {
        try {
            if (query != null) {
               query = query.replaceAll("\\s+", "");
            }
            sort = sort.replaceAll("\\s+", "");


            if (query == null) {
                return handleNoQueryCache(sort);
            }

            if (cacheClient.isInCache(query.toLowerCase())) {
                PokemonDTO cachedPokemon = cacheClient.getPokemonDto(query.toLowerCase());
                return ResponseEntity.status(HttpStatus.OK).body(appClient.sort(sort, cachedPokemon));  // Passar sort limpo
            }

            if (cacheClient.isInCache("noquery")) {
                PokemonDTO cachedNoQueryPokemon = cacheClient.getPokemonDto("noquery");
                PokemonDTO filteredPokemon = appClient.filterByQuery(query, cachedNoQueryPokemon);
                cacheClient.addToCache(query.toLowerCase(), filteredPokemon);
                return ResponseEntity.status(HttpStatus.OK).body(appClient.sort(sort, filteredPokemon));  // Passar sort limpo
            }

            PokemonResponse response = okHttpServices.getPokemons(apiUrl);
            PokemonDTO pokemonDTO = appClient.createPokemonDto(response);
            cacheClient.addToCache("noquery", pokemonDTO);
            PokemonDTO filteredPokemon = appClient.filterByQuery(query, pokemonDTO);
            cacheClient.addToCache(query.toLowerCase(), filteredPokemon);
            return ResponseEntity.status(HttpStatus.OK).body(filteredPokemon);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching pokemons: " + e.getCause());
        }
    }


    @Override
    public ResponseEntity<?> getPokemonsWithHighligth(String query, String sort) {
        try {
            if (query != null) {
                query = query.replaceAll("\\s+", "");
            }
            sort = sort.replaceAll("\\s+", "");

            if (query == null || query.isEmpty()) {
                return handleNoQueryCache(sort.trim());
            }

            if (cacheClient.isInCache(query.toLowerCase())) {
                PokemonDTO cachedPokemon = cacheClient.getPokemonDto(query.toLowerCase());
                PokemonDTO filteredPokemon = appClient.filterByQuery(query, cachedPokemon);
                return ResponseEntity.ok(new PokemonDTOWithHighlight(appClient.generateHighlightedResponse(filteredPokemon, query)));
            }

            if (cacheClient.isInCache("noquery")) {
                PokemonDTO cachedNoQueryPokemon = cacheClient.getPokemonDto("noquery");
                PokemonDTO filteredPokemon = appClient.filterByQuery(query, cachedNoQueryPokemon);
                return ResponseEntity.ok(new PokemonDTOWithHighlight(appClient.generateHighlightedResponse(filteredPokemon, query)));
            }

            PokemonResponse response = okHttpServices.getPokemons(apiUrl);
            PokemonDTO pokemonDTO = appClient.createPokemonDto(response);
            cacheClient.addToCache("noquery", pokemonDTO);
            PokemonDTO filteredPokemon = appClient.filterByQuery(query, pokemonDTO);
            cacheClient.addToCache(query.toLowerCase(), filteredPokemon);
            return ResponseEntity.ok(new PokemonDTOWithHighlight(appClient.generateHighlightedResponse(filteredPokemon, query)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching highlighted pokemons: " + e.getCause());
        }
    }

    private ResponseEntity<?> handleNoQueryCache(String sort) {
        if (cacheClient.isInCache("noquery")) {
            PokemonDTO cachedNoQueryPokemon = cacheClient.getPokemonDto("noquery");
            return ResponseEntity.ok(appClient.sort(sort, cachedNoQueryPokemon));
        }

        PokemonResponse response = okHttpServices.getPokemons(apiUrl);
        PokemonDTO pokemonDTO = appClient.createPokemonDto(response);
        cacheClient.addToCache("noquery", pokemonDTO);
        return ResponseEntity.ok(appClient.sort(sort, pokemonDTO));
    }
}
