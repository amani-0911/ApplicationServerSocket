package com.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OperationDaoImpl implements IOperationDao{
	 private Connection connection;
		public OperationDaoImpl(){
			String url="jdbc:mysql://localhost:3306/compte_exam";
			try{
				connection=DriverManager.getConnection(url,"root","");
				System.out.println("vous ete connecter");
			}catch(SQLException exp){
			System.out.println(exp);
			}
		}
		
	
	@Override
	public void add(Operation o) {
		 String sql="insert into operation (id_compte,type,montant,date) values (?,?,?,?)";
			try{ 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setLong(1, o.getCompte().getId());
				ps.setString(2, o.getType());
				ps.setDouble(3, o.getMontant());
				Date date =Date.valueOf(o.getDate());
				ps.setDate(4, date);
				ps.execute();
			}catch(SQLException exp){
				System.out.println(exp.getMessage()); 
			 }
		
	}

	@Override
	public List<Operation> getOpCompte(Compte c) {
List<Operation> liste=new ArrayList<Operation>();
		
		String sql="select* from operation where id_compte=?";
		 try{
		 PreparedStatement ps=connection.prepareStatement(sql);
		 ps.setLong(1, c.getId());
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()){
			 Date date=rs.getDate("date");
			Operation o=new Operation(date.toLocalDate(), rs.getDouble("montant"), rs.getString("type"), c);
				 liste.add(o);
			 }
		 }catch(SQLException exp){
			System.out.println(exp.getMessage()); 
		 }
		 return liste;	 
	}
	
}
