package com.edev.looqbox.challange.Controllers;


import com.edev.looqbox.challange.Model.CacheRequest;
import com.edev.looqbox.challange.Model.PokemonDTO;
import com.edev.looqbox.challange.Service.cacheServices.CacheServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private final CacheServices cacheServices;

    public CacheController(CacheServices cacheServices) {
        this.cacheServices = cacheServices;
    }

    @GetMapping("/get/pokemons")
    public PokemonDTO getPokemons(
          @RequestParam String key
    ) {
        return cacheServices.getFromCacheAsPokemonDTO(key);

    }
    @GetMapping("/verify")
    public boolean isInCache(@RequestParam String key){
        return cacheServices.isInCache(key);
    }
    @PostMapping("/add")
    public ResponseEntity<Boolean> addToCache(
        @RequestBody CacheRequest cacheRequest
    ){
        cacheServices.addtoCache(cacheRequest.getKey(), cacheRequest.getPokemonDTO());
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
