package com.example.pokemon.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.Model.PokemonResponse;
import com.example.pokemon.db.PokemonDao;
import com.example.pokemon.network.PokemonAPIService;

import java.util.List;

import javax.inject.Inject;


import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private PokemonAPIService pokemonAPIService;
    private PokemonDao pokemonDao;


    @Inject
    public Repository(PokemonAPIService pokemonAPIService, PokemonDao pokemonDao) {
        this.pokemonAPIService = pokemonAPIService;
        this.pokemonDao=pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons(){

        return pokemonAPIService.getPokemons();

    }


    public void insertPokemon(Pokemon pokemon) {
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getFavPokemon() {
        return pokemonDao.getPokemons();
    }
}
