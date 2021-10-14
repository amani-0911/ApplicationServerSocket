package com.connexion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

import com.Dao.Compte;
import com.Dao.CompteDaoImpl;
import com.Dao.ICompteDao;
import com.Dao.IOperationDao;
import com.Dao.Operation;
import com.Dao.OperationDaoImpl;

import com.Dao.Reglement;









public class ServerThread extends Thread {
	 private Socket socketEnd1=null;
	    private  InputStream input=null;
	    private OutputStream output=null;
	      ICompteDao cdao=new CompteDaoImpl();
	    IOperationDao odao=new OperationDaoImpl();
	    private boolean trouve;
	    
		public ServerThread(Socket socketEnd1) {
			this.socketEnd1=socketEnd1;
			try {
				input=socketEnd1.getInputStream();
				 output=socketEnd1.getOutputStream();
		System.out.println("FLux sortant et entrant (server) prétes...");
		    start();
				
			} catch (Exception e) {
					System.out.println(e);
			}
		}
		
	
		
		@Override
		public void run() {
			EnvoyerMessage();
			if(trouve==true){
			debiter();
			}
			
		}
		public void EnvoyerMessage(){
			try{	
				BufferedReader br=new BufferedReader(new InputStreamReader(input));
				String num=br.readLine();
				Compte c=cdao.getOne(num);
				   PrintWriter pw=new PrintWriter(output);
				 String s;
				   if(c==null){ 
					 s="echec";
					 trouve=false;
				  }else{
					 s="success";
					 trouve=true;
				  }
				   pw.println(s);
				   pw.flush();
				
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
private boolean compteSuffisant(Compte c,double montant){
	 List<Operation> ops=odao.getOpCompte(c);
	double solde=0;
     for (Operation o : ops) {
    	 String Type="VERS";
		if(o.getType().equals(Type)==true){
			solde+=o.getMontant();
			 
		}else{ 
			solde-=o.getMontant();
			 
		}
		
	}
     System.out.println(solde);
   if(solde>=montant) return true;
   return false;
}		
 public synchronized void debiter(){
    try{ String s;	
    	ObjectInputStream OInputS=new ObjectInputStream(input);
	     Reglement  r=(Reglement)OInputS.readObject();
	     
	     Compte c=cdao.getOne(r.getNumCompte());
	      
	    if(compteSuffisant(c,r.getMontant())==false){
	    	
	    	s="le solde est insuffisant";
	    	
			  
	    }else{
	    	
	    Operation o=new Operation(LocalDate.now(), r.getMontant(), "PDIST", c);
       odao.add(o);
       s="le paiment est bien recu";
	    }  
	       PrintWriter pw=new PrintWriter(output);
		   pw.println(s);
		   pw.flush();
		   
}catch(Exception e){
	System.out.println(e);
}    
}
 
}