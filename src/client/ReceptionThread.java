package client;

import java.rmi.RemoteException;

import common.SeveurIntf;

public class ReceptionThread extends Thread{
	private Client client;
	private SeveurIntf serveur;
	
	public ReceptionThread(Client client, SeveurIntf serveur) {
		this.client = client;
		this.serveur = serveur;
	}
	
	public void run() {
		this.client.afficher("");
		try {
			for (String message : serveur.returnList()) {
				client.returnList().add(message);
			}
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				if(client.returnList().size()<serveur.returnList().size()) {
					for(int i = client.returnList().size(); i<serveur.returnList().size(); i++) {
						this.client.afficher(serveur.returnList().get(i));
						this.client.returnList().add(serveur.returnList().get(i));
					}
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
