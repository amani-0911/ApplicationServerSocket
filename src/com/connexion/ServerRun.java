package com.connexion;


import java.net.ServerSocket;
import java.net.Socket;


public class ServerRun {
	 private static final int port=3333;
	    private ServerSocket serverSocket=null;
	    private Socket socketEnd1=null;
	    
	    
		public ServerRun() {
			
			try {
				serverSocket=new ServerSocket(port);
				System.out.println("server demaré...");
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		public void accepterConnecxion(){
			try{
			socketEnd1=serverSocket.accept();
			new ServerThread(socketEnd1);
			System.out.println("connecter...");
			}catch(Exception exp){
				System.out.println(exp);
			}
		}
		


		public static void main(String[] args) {
			ServerRun server=new ServerRun();
			server.accepterConnecxion();
	        
		}

}
