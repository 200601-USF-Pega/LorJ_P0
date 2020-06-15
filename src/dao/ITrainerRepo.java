package dao;

import models.Trainer;

public interface ITrainerRepo
{
	void addTrainer(String name, String password);
	void deleteTrainer(int id);
	void viewAllTrainers();
}
