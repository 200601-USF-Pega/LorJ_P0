package ui;

import dao.ConnectionManager;
import models.Trainer;

public interface Login
{
	public abstract Trainer inputLogin(ConnectionManager cm);
}
