package com.Dao;

import java.time.LocalDate;

public class Operation {
private long id;
private LocalDate date;
private  double montant;
private String type;
private Compte compte;
public Operation( LocalDate date, double montant, String type,Compte compte) {

	this.compte = compte;
	this.date = date;
	this.montant = montant;
	this.type = type;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Compte getCompte() {
	return compte;
}
public void setCompte(Compte compte) {
	this.compte = compte;
}


}
