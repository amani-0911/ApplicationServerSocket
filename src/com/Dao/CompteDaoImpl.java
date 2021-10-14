package com.Dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CompteDaoImpl implements ICompteDao {
 private Connection connection;
	public CompteDaoImpl(){
		String url="jdbc:mysql://localhost:3306/compte_exam";
		try{
			connection=DriverManager.getConnection(url,"root","");
			System.out.println("vous ete connecter au db");
		}catch(SQLException exp){
		System.out.println(exp);
		}
	}
	
	@Override
	public void add(Compte c) {
		 String sql="insert into compte (numero,nom,prenom) values (?,?,?)";
			try{ 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, c.getNumero());
				ps.setString(2, c.getNom());
				ps.setString(3, c.getPrenom());
				ps.execute();
			}catch(SQLException exp){
				System.out.println(exp.getMessage()); 
			 }
		
	}

	@Override
	public Compte getOne(String nemero) {
		 String sql="select* from compte where numero=?";
		 try{
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ps.setString(1, nemero);
		 ResultSet rs=ps.executeQuery();
		 if(rs.next()){
			
			 Compte c=new Compte(rs.getLong("id"),rs.getString("numero"), rs.getString("nom"), rs.getString("prenom"));
			 return c;
		 }
		 }catch(SQLException exp){
				System.out.println(exp.getMessage()); 
			 }
		 return null;
	}

	@Override
	public Compte getOne(long id) {
		 String sql="select* from compte where id=?";
		 try{
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ps.setLong(1, id);
		 ResultSet rs=ps.executeQuery();
		 if(rs.next()){
			
			 Compte c=new Compte(rs.getLong("id"),rs.getString("numero"), rs.getString("nom"), rs.getString("prenom"));
			 return c;
		 }
		 }catch(SQLException exp){
				System.out.println(exp.getMessage()); 
			 }
		 return null;
	}
	

}
