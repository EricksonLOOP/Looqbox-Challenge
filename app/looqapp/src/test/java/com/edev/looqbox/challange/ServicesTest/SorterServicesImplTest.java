package com.edev.looqbox.challange.ServicesTest;

import com.edev.looqbox.challange.Service.SorterServices.SorterServicesImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SorterServicesImplTest {

    private final SorterServicesImpl sorterServices = new SorterServicesImpl();

    @Test
    public void testSortByName() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Charmander");
        pokemons.add("Pikachu");
        pokemons.add("Bulbasaur");

        List<String> result = sorterServices.sortbByName(pokemons);

        assertEquals("Bulbasaur", result.get(0));
        assertEquals("Charmander", result.get(1));
        assertEquals("Pikachu", result.get(2));
    }

    @Test
    public void testSortByNameAlreadySorted() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Bulbasaur");
        pokemons.add("Charmander");
        pokemons.add("Pikachu");

        List<String> result = sorterServices.sortbByName(pokemons);

        assertEquals("Bulbasaur", result.get(0));
        assertEquals("Charmander", result.get(1));
        assertEquals("Pikachu", result.get(2));
    }

    @Test
    public void testSortByNameReverseOrder() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Pikachu");
        pokemons.add("Charmander");
        pokemons.add("Bulbasaur");

        List<String> result = sorterServices.sortbByName(pokemons);

        assertEquals("Bulbasaur", result.get(0));
        assertEquals("Charmander", result.get(1));
        assertEquals("Pikachu", result.get(2));
    }

    @Test
    public void testSortByLength() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Charmander");
        pokemons.add("Pikachu");
        pokemons.add("Bulbasaur");

        List<String> result = sorterServices.sortbByLength(pokemons);

        assertEquals("Pikachu", result.get(0));
        assertEquals("Bulbasaur", result.get(1));
        assertEquals("Charmander", result.get(2));
    }

    @Test
    public void testSortByLengthAlreadySorted() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Pikachu");
        pokemons.add("Charmander");
        pokemons.add("Bulbasaur");

        List<String> result = sorterServices.sortbByLength(pokemons);

        assertEquals("Pikachu", result.get(0));
        assertEquals("Bulbasaur", result.get(1));
        assertEquals("Charmander", result.get(2));
    }

    @Test
    public void testSortByLengthReverseOrder() {
        List<String> pokemons = new ArrayList<>();
        pokemons.add("Bulbasaur");
        pokemons.add("Charmander");
        pokemons.add("Pikachu");

        List<String> result = sorterServices.sortbByLength(pokemons);

        assertEquals("Pikachu", result.get(0));
        assertEquals("Bulbasaur", result.get(1));
        assertEquals("Charmander", result.get(2));
    }
}
