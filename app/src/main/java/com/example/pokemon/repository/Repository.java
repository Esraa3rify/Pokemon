package com.example.pokemon.repository;

import com.example.pokemon.Model.PokemonResponse;
import com.example.pokemon.network.PokemonAPIService;

import javax.inject.Inject;


import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private PokemonAPIService pokemonAPIService;


    @Inject
    public Repository(PokemonAPIService pokemonAPIService) {
        this.pokemonAPIService = pokemonAPIService;
    }

    public Observable<PokemonResponse> getPokemons(){

        return pokemonAPIService.getPokemons();

    }
}
