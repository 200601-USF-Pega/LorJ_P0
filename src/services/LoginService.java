package services;

import dao.ConnectionManager;
import models.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService
{

	ConnectionManager cm;

	public LoginService(ConnectionManager cm)
	{
		this.cm = cm;
	}

	public Trainer login(String name, String pass)
	{
		try
		{
			PreparedStatement login = cm.getConnection().prepareStatement("SELECT * FROM trainer_table WHERE UPPER(trainer_name)=UPPER(?) and t_password=?");
			login.setString(1, name);
			login.setString(2, pass);
			ResultSet rs = login.executeQuery();
			if (rs.next())
			{
				return new Trainer(rs.getString("trainer_name"), rs.getInt("t_id"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
