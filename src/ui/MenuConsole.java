package ui;
import dao.ConnectionManager;
import dao.IPokemonRepo;
import dao.PokemonRepoDB;
import exceptions.InvalidInputException;
import exceptions.TeamTransactionException;
import models.Pokemon;
import models.Trainer;
import services.PokemonFactory;
import services.PokemonService;

import java.util.InputMismatchException;
import java.util.Scanner;

// Responsible for menu navigation
public class MenuConsole implements Menu
{
	final static Scanner scan = new Scanner(System.in);
	private PokemonService ps;
	private IPokemonRepo repo;

	public MenuConsole(ConnectionManager cm)
	{
		ps = new PokemonService();
		repo = new PokemonRepoDB(cm);
	}
	
	public void mainMenu(Trainer t)
	{
		int input = 0;
		while(input != 5)
		{
			System.out.println("====================\n[1] BILL's PC\n[2] " + t.getName() + "'s PC\n[3] PROF.OAK's PC\n[4] View Team\n[5] LOG OFF");
			try
			{
				input = scan.nextInt();
				if(input < 1 || input > 5)
				{
					throw new InvalidInputException();
				}
			}
			catch(Exception e)
			{
				System.out.println("Please input a valid menu option.");
				input = 0; // Reset
			}
			
			switch(input)
			{
			case 1:
				System.out.println("Accessed POK" + '\u00E9' + "MON Storage System.\n====================");
				billMenu(t);
				break;
			case 2:
				System.out.println("Item Database not implemented.");
				break;
			case 3:
				System.out.println("There is no mail feature implemented. Who ever used mail in Pok" + '\u00E9' + "mon anyway?");
				break;
			case 4:
				ps.viewTeam();
				break;
			case 5:
				System.out.println("Exporting team...");
				ps.exportTeam(t.getName());
				System.out.println("Bye!");
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		}
		
		
	}
	
	public void billMenu(Trainer t)
	{
		int input = 0;
		while(input != 5)
		{
			System.out.print("[1] Withdraw\n[2] Deposit\n[3] Release\n[4] Create Pokemon\n[5] Back\n====================\nWhat? ");
			try
			{
				input = scan.nextInt();
				if(input < 1 || input > 5)
				{
					throw new InvalidInputException();
				}
			}
			catch(Exception e)
			{
				System.out.println("Please input a valid menu option.");
				input = 0; // Reset
			}

			switch(input)
			{
			case 1:
				// Withdraw
				repo.displayTrainerPokemon(t);
				System.out.print("Pick Pokemon by the [ID]: ");
				try
				{
					input = scan.nextInt();
					ps.addPokemon(repo.withdrawPokemon(input));
				}
				catch(InputMismatchException ime)
				{
					System.out.println("Please input a valid ID number.");
					input = 0;
				}
				catch(TeamTransactionException tte)
				{
					System.out.println("Your party is full.");
					input = 0;
				}
				break;
			case 2:
				// Deposit
				System.out.println("Select Pokemon:");
				ps.viewTeam();
				try
				{
					if(ps.getIndex() <= 0)
					{
						throw new TeamTransactionException();
					}
					input = scan.nextInt();
					repo.addPokemon(ps.removePokemon(input));
				}
				catch(InputMismatchException ime)
				{
					System.out.println("Invalid input.");
					input = 0; // Reset
				}
				catch(TeamTransactionException tte)
				{
					System.out.println("Your party is empty.");
				}
				break;
			case 3:
				// Release
				break;
			case 4:
				Pokemon p = PokemonFactory.makePokemon(t);
				if(p != null)
				{
					ps.addPokemon(p);
				}
				break;
			default:
				break;
			}
		}
		
	}
	
}
