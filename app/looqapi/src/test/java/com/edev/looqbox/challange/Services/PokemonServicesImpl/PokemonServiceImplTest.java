package com.edev.looqbox.challange.Services.PokemonServicesImpl;

import com.edev.looqbox.challange.Model.Pokemon;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Model.PokemonResponse;
import com.edev.looqbox.challange.Service.OkHttpServices.OkHttpServices;
import com.edev.looqbox.challange.Service.PokemonServices.PokemonServiceImpl;
import com.edev.looqbox.challange.Service.consumerService.LooqAppClient.LooqAppClient;
import com.edev.looqbox.challange.Service.cacheServices.CacheServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PokemonServiceImplTest {

    @Mock
    private OkHttpServices okHttpServices;

    @Mock
    private CacheServices cacheServices;

    @Mock
    private LooqAppClient appServices;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    private PokemonResponse mockPokemonResponse;
    private PokemonDTO mockPokemonDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criando mocks para simular as respostas da API
        mockPokemonResponse = new PokemonResponse(1000,
                "http://next.pokemon.url",
                "https://previous.pokemon.url",
                List.of(new Pokemon("pikachu", ""), new Pokemon("bulbasour", "")));
        // Aqui você configura o PokemonResponse com os dados necessários para o teste

        mockPokemonDTO = new PokemonDTO(List.of("pikachu", "bulbasaur"));
    }

    @Test
    public void testGetPokemons_WithQueryInCache_ReturnsSortedResults() {

        when(cacheServices.isInCache("pikachu")).thenReturn(true);
        when(cacheServices.getFromCacheAsPokemonDTO("pikachu")).thenReturn(mockPokemonDTO);
        when(appServices.sort("alphabetical", mockPokemonDTO)).thenReturn(mockPokemonDTO);

        ResponseEntity<?> response = pokemonService.getPokemons("pikachu", "alphabetical");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPokemonDTO, response.getBody());

        verify(cacheServices, times(1)).isInCache("pikachu");
        verify(cacheServices, times(1)).getFromCacheAsPokemonDTO("pikachu");
        verify(appServices, times(1)).sort("alphabetical", mockPokemonDTO);
    }

    @Test
    public void testGetPokemons_NoQuery_CallsAPIAndCachesResult() {
        when(cacheServices.isInCache("noquery")).thenReturn(false);
        when(okHttpServices.getPokemons(anyString())).thenReturn(mockPokemonResponse);
        when(appServices.createPokemonDto(mockPokemonResponse)).thenReturn(mockPokemonDTO);
        when(appServices.sort("alphabetical", mockPokemonDTO)).thenReturn(mockPokemonDTO);

        ResponseEntity<?> response = pokemonService.getPokemons(null, "alphabetical");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPokemonDTO, response.getBody());

        verify(cacheServices, times(1)).isInCache("noquery");
        verify(okHttpServices, times(1)).getPokemons(anyString());
        verify(appServices, times(1)).createPokemonDto(mockPokemonResponse);
        verify(cacheServices, times(1)).addtoCache("noquery", mockPokemonDTO);
    }

    @Test
    public void testGetPokemons_WithQueryNotInCache_ReturnsFilteredResults() {
        when(cacheServices.isInCache("noquery")).thenReturn(true);
        when(cacheServices.getFromCacheAsPokemonDTO("noquery")).thenReturn(mockPokemonDTO);
        when(appServices.filterByQuery("pikachu", mockPokemonDTO)).thenReturn(mockPokemonDTO);
        when(appServices.sort("alphabetical", mockPokemonDTO)).thenReturn(mockPokemonDTO);

        ResponseEntity<?> response = pokemonService.getPokemons("pikachu", "alphabetical");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPokemonDTO, response.getBody());

        verify(cacheServices, times(1)).isInCache("noquery");
        verify(cacheServices, times(1)).addtoCache("pikachu", mockPokemonDTO);
        verify(appServices, times(1)).filterByQuery("pikachu", mockPokemonDTO);
        verify(appServices, times(1)).sort("alphabetical", mockPokemonDTO);
    }
}
