package com.example.pokemon.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.Model.PokemonResponse;
import com.example.pokemon.repository.Repository;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PokemonViewModel extends ViewModel {
    private Repository repository;

    private MutableLiveData<ArrayList<Pokemon>> pokemonList = new MutableLiveData<>();
    private LiveData<List<Pokemon>> favList = null;

    public LiveData<List<Pokemon>> getFavList() {
        return favList;
    }

    public PokemonViewModel(LiveData<List<Pokemon>> favList) {
        this.favList = favList;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonList;
    }


    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Pokemon> list = pokemonResponse.getResults();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getURL();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setURL("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                    })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }


    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }

    public void getFavPokemon() {
        favList = repository.getFavPokemon();
    }
}
