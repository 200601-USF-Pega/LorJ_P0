package dao;

import models.Pokemon;
import models.Trainer;

import java.util.List;

public interface IPokemonRepo
{
    void addPokemon(Pokemon pokemon);
    Pokemon getPokemon(int id);
    Pokemon withdrawPokemon(int id);
    void displayTrainerPokemon(Trainer t);
    List<Pokemon> getAllPokemon();

}
