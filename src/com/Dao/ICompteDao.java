package com.Dao;

public interface ICompteDao {
	public void add(Compte c );
	
	public Compte getOne(String nemero);
	public Compte getOne(long id);
}
