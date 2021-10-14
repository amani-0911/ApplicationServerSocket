package com.Dao;

import java.io.Serializable;

public class Reglement implements Serializable{
private String numCompte;
private double montant;
public Reglement(String numCompte, double montant) {
	this.numCompte = numCompte;
	this.montant = montant;
}


public String getNumCompte() {
	return numCompte;
}


public void setNumCompte(String numCompte) {
	this.numCompte = numCompte;
}


public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}



}
