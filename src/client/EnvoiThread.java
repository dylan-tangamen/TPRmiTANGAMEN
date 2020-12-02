package client;

import java.rmi.RemoteException;

import common.SeveurIntf;

public class EnvoiThread extends Thread{
	private Client utilisateur;
	private SeveurIntf serveur;
	
	public EnvoiThread(Client utilisateur, SeveurIntf serveur) {
		this.utilisateur = utilisateur;
		this.serveur = serveur;
	}
	
	public void run() {
		this.utilisateur.afficher("Client ok");
		while(true) {
			try {
				String msg = this.utilisateur.input();
				if(!(msg.equals("/quitter"))) {
					serveur.envoyerMessage(msg);
				}
				else {
					this.utilisateur.stop();
				}
			} catch (RemoteException e) {}
		}
	}
}
