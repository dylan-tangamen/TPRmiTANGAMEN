package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SeveurIntf extends Remote {
	List<String> returnList() throws RemoteException;
	void envoyerMessage(String msg) throws RemoteException;
}
