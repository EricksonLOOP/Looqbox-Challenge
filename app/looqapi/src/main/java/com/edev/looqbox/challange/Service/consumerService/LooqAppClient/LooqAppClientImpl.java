package com.edev.looqbox.challange.Service.consumerService.LooqAppClient;

import com.edev.looqbox.challange.Model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class LooqAppClientImpl implements LooqAppClient {
    private final RestTemplate restTemplate;
    private String LOOQ_APP_BASE_URL = "http://localhost:8082/looqapp/";
    public LooqAppClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PokemonDTO createPokemonDto(PokemonResponse response) {
        return restTemplate.postForObject(LOOQ_APP_BASE_URL+"/create/pokemondto",response, PokemonDTO.class);
    }

    @Override
    public PokemonDTO sort(String sort, PokemonDTO pokemonDTO) {
        return restTemplate.postForObject(LOOQ_APP_BASE_URL+"/sort", new SortRequest(sort, pokemonDTO), PokemonDTO.class);
    }

    @Override
    public PokemonDTO filterByQuery(String query, PokemonDTO pokemonDTO) {
        return restTemplate.postForObject(LOOQ_APP_BASE_URL+"/filterByQuery", new FilterRequest(query, pokemonDTO), PokemonDTO.class);
    }

    @Override
    public List<Pokemon> generateHighlightedResponse(PokemonDTO filteredPokemonDto, String query) {
        String url = LOOQ_APP_BASE_URL + "/create/highlight";

        GenerateHighlightedResponseRequest request = new GenerateHighlightedResponseRequest(query, filteredPokemonDto);

        ResponseEntity<List<Pokemon>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<List<Pokemon>>() {}
        );

        return responseEntity.getBody();
    }

}
