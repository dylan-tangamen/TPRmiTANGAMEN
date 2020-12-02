package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.SeveurIntf;

public class Serveur extends UnicastRemoteObject implements SeveurIntf{
	private static final long serialVersionUID = 1L;
	private List<String> messages = new ArrayList<>();
	
	protected Serveur() throws RemoteException {}
	
	public static void main(String args[]) {
		System.setProperty("java.rmi.server.hostname","192.168.1.84");
		try {
			LocateRegistry.createRegistry(1099);
			
			Serveur serveur = new Serveur();
			Naming.rebind("//localhost/Rmiserver", serveur);
			
			System.out.println("Serveur démarré ! ");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> returnList() throws RemoteException {
		return messages;
	}
	public void envoyerMessage(String msg) throws RemoteException {
		this.messages.add(msg);
	}
}
