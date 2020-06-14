import dao.ConnectionManager;
import models.Pokemon;
import models.Trainer;
import ui.*;

public class Driver
{

	public static void main(String [] args)
	{
		ConnectionManager cm = new ConnectionManager();
		Login login = new LoginConsole();
		Menu menu = new MenuConsole(cm);
		Trainer t = login.inputLogin(cm);
		if(t != null)
		{
			menu.mainMenu(t);
		}
		cm.finalize();
	}
	
}
