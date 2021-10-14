package com.Dao;

import java.util.List;

public interface IOperationDao {
	public void add(Operation o);
	public List<Operation> getOpCompte(Compte c);
}
