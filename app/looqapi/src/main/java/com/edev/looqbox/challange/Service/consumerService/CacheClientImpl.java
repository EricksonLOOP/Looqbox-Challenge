package com.edev.looqbox.challange.Service.consumerService;

import com.edev.looqbox.challange.Model.CacheRequest;
import com.edev.looqbox.challange.Model.PokemonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheClientImpl implements CacheClient {

    private final RestTemplate restTemplate;
    private final String cacheServiceUrl = "http://localhost:8081/cache";

    @Autowired
    public CacheClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean addToCache(String key, PokemonDTO pokemonDTO) {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setKey(key);
        cacheRequest.setPokemonDTO(pokemonDTO);

        String url = cacheServiceUrl + "/add";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CacheRequest> requestEntity = new HttpEntity<>(cacheRequest, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Boolean.class
        );

        return response.getBody() != null && response.getBody();
    }

    @Override
    public PokemonDTO getPokemonDto(String key) {
        String url = cacheServiceUrl + "/get/pokemons?key=" + key;

        ResponseEntity<PokemonDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                PokemonDTO.class
        );
        return response.getBody();
    }

    @Override
    public boolean isInCache(String key) {
        String url = cacheServiceUrl + "/verify?key=" + key;

        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Boolean.class
        );

        return response.getBody() != null && response.getBody();
    }
}
