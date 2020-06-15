import dao.ConnectionManager;
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
			if(t.getID() == 1)
			{
				menu.extendedMenu(t);
			}
			else
			{
				menu.mainMenu(t);
			}
		}
		cm.finalize();
	}
	
}
