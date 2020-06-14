package dao;

import models.Pokemon;

import java.util.List;

public interface IPokemonRepo
{
    void addPokemon(Pokemon pokemon);
    boolean updatePokemon(Pokemon pokemon);
    List<Pokemon> getAllPokemon();
}
