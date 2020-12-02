package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.SeveurIntf;

public class Client {
	private List<String> messages = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	
	protected Client() throws RemoteException {}

	public static void main(String[] args) {
		try {
			SeveurIntf messageServer = (SeveurIntf)Naming.lookup("//localhost/Rmiserver");
			
			System.out.println("Bienvenue!");
			
			Client client = new Client();
			new EnvoiThread(client, messageServer).start();
			new ReceptionThread(client, messageServer).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void afficher(String msg) {
		System.out.println(msg);
	}
	
	public String input() {
		return sc.nextLine();
	}
	
	public List<String> returnList() {
		return messages;
	}

	public void stop() {
		System.exit(0);
	}
}
