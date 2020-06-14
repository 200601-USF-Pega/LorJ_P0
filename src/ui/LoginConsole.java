package ui;

import dao.ConnectionManager;
import dao.IPokemonRepo;
import dao.PokemonRepoDB;
import models.Trainer;
import services.LoginService;

import java.util.Scanner;

// Responsible for login.
public class LoginConsole implements Login
{
	
	public Trainer inputLogin(ConnectionManager cm)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Input your name: ");
		String name = scan.nextLine();
		System.out.print("Enter Password: ");
		String pass = scan.nextLine();

		LoginService ls = new LoginService(cm);
		Trainer t = ls.login(name, pass);

		if(t != null)
		{
			System.out.println(t.getName() + " turned on the PC.");
		}
		else
		{
			System.out.println("Login Failed.");
		}
		return t;
	}
	
}
