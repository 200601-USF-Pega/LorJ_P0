package ui;
import dao.*;
import exceptions.InvalidInputException;
import exceptions.TeamTransactionException;
import models.Pokemon;
import models.Trainer;
import services.PokemonFactory;
import services.PokemonService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Responsible for menu navigation
public class MenuConsole implements Menu
{
	final static Scanner scan = new Scanner(System.in);
	private PokemonService ps;
	private IPokemonRepo repo;
	private ITrainerRepo trainerRepo;

	public MenuConsole(ConnectionManager cm)
	{
		ps = new PokemonService();
		repo = new PokemonRepoDB(cm);
		trainerRepo = new TrainerRepoDB(cm);
	}
	
	public void mainMenu(Trainer t)
	{
		int input = 0;
		while(input != 5)
		{
			System.out.println("====================\n[1] BILL's PC\n[2] " + t.getName() + "'s PC\n[3] PROF.OAK's PC\n[4] VIEW TEAM\n[5] LOG OFF");
			try
			{
				input = scan.nextInt();
				scan.nextLine();
				if(input < 1 || input > 5)
				{
					throw new InvalidInputException();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please input a valid menu option.");
				scan.next();
			}
			catch(InvalidInputException e)
			{
				System.out.println("Please input a valid menu option.");
				input = 0;
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
			System.out.print("[1] WITHDRAW\n[2] DEPOSIT\n[3] RELEASE\n[4] CREATE\n[5] BACK\n====================\nWhat? ");
			try
			{
				input = scan.nextInt();
				if(input < 1 || input > 5)
				{
					throw new InvalidInputException();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please input a valid menu option.");
				scan.next();
			}
			catch(InvalidInputException e)
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
					System.out.println("Invalid input.");
					break;
				}
				catch(TeamTransactionException tte)
				{
					System.out.println("Your party is full.");
				}
				input = 0;
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
					scan.next();
				}
				catch(TeamTransactionException tte)
				{
					System.out.println("Your party is empty.");
				}
				input = 0;
				break;
			case 3:
				// Release
				repo.displayTrainerPokemon(t);
				System.out.print("Pick Pokemon by the [ID]: ");
				try
				{
					input = scan.nextInt();
					repo.removePokemon(input);
				}
				catch(InputMismatchException ime)
				{
					System.out.println("Invalid input.");
					break;
				}
				input = 0;
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

	public void extendedMenu(Trainer t)
	{
		int input = 0;
		while(input != 5)
		{
			System.out.println("====================\n[1] VIEW ALL POK" + '\u00E9' + "MON\n[2] REGISTER TRAINER\n[3] REMOVE TRAINER\n[4] VIEW TRAINERS\n[5] LOG OFF");
			try
			{
				input = scan.nextInt();
				scan.nextLine();
				if(input < 1 || input > 5)
				{
					throw new InvalidInputException();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please input a valid menu option.");
				scan.next();
			}
			catch(InvalidInputException e)
			{
				System.out.println("Please input a valid menu option.");
				input = 0; // Reset
			}
			switch(input)
			{
			case 1:
				List<Pokemon> list = repo.getAllPokemon();
				for(Pokemon p : list)
				{
					System.out.println(p);
				}
				break;
			case 2:
				System.out.print("Trainer Name: ");
				String name = scan.nextLine();
				System.out.print("Password: ");
				trainerRepo.addTrainer(name, scan.nextLine());
				break;
			case 3:
				System.out.print("Trainer ID: ");
				trainerRepo.deleteTrainer(scan.nextInt());
				scan.nextLine();
				break;
			case 4:
				trainerRepo.viewAllTrainers();
				break;
			default:
				break;
			}
		}
	}
	
}
