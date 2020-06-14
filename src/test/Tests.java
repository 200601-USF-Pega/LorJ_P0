package test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import dao.ConnectionManager;
import dao.PokemonRepoDB;
import exceptions.TeamTransactionException;
import models.Genders;
import models.Natures;
import models.Pokemon;
import models.Trainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import services.LoginService;
import services.PokemonService;

public class Tests
{

	PokemonService ps = new PokemonService();
	Pokemon p = new Pokemon();
	static ConnectionManager cm;

	@BeforeClass
	public static void start()
	{
		cm = new ConnectionManager();
	}

	@Test
	public void shouldAnswerTrue()
	{
		assertTrue(true);
	}

	@Test
	public void trainerData()
	{
		Trainer t = new Trainer("TEST", 0);
		assertEquals(t.getName(), "TEST");

	}

	@Test
	public void pokemonTransactionSuccess()
	{
		ps.addPokemon(p);
		assertEquals(p, ps.removePokemon(0));
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

	@Test
	public void testSmogon()
	{
		assertEquals("MissingNo.\nAbility: N/A\nLevel: 100\nShiny: No\nEVs: \nAdamant Nature\n - \n - \n - \n - ", p.exportSmogon());
	}

	@Test
	public void testGender()
	{
		assertEquals(p.getGender(), Genders.Genderless);
	}

	@Test
	public void testNature()
	{
		assertEquals(p.getNature(), Natures.Adamant);
	}

	@Test
	public void testLogin()
	{
		LoginService ls = new LoginService(cm);
		assertEquals(ls.login("RED", "Pikachu").getName(), "RED");
	}

	@Test
	public void testLoginFail()
	{
		LoginService ls = new LoginService(cm);
		assertNull(ls.login("a", "a"));
	}

	@Test
	public void repoView()
	{
		PokemonRepoDB repo = new PokemonRepoDB(cm);
		repo.getAllPokemon();
	}

	@AfterClass
	public static void finish()
	{
		cm.finalize();
	}

}
