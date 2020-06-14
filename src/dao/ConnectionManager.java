package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager
{
	private Connection connection;

	public ConnectionManager()
	{
		try
		{
			FileInputStream fis = new FileInputStream("connection.prop");
			Properties p = new Properties();
			p.load(fis);

			connection = DriverManager.getConnection(p.getProperty("hostname"), p.getProperty("username"), p.getProperty("password"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return this.connection;
	}

	@Override
	public void finalize()
	{
		try
		{
			connection.close();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}

}
