package test;
import static org.junit.Assert.assertTrue;

import exceptions.TeamTransactionException;
import models.Pokemon;
import org.junit.Test;
import services.PokemonService;

public class Tests
{

	PokemonService ps = new PokemonService();

	@Test
	public void shouldAnswerTrue()
	{
		assertTrue(true);
	}

	@Test(expected = TeamTransactionException.class)
	public void pokemonWithdrawException()
	{
		ps.removePokemon(0);
	}

	@Test(expected = TeamTransactionException.class)
	public void pokemonDepositException()
	{
		for(int i = 0; i < 7; i++)
		{
			ps.addPokemon(new Pokemon());
		}
	}

}
