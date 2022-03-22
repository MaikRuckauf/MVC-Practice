package comp533.mvc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteModelInt extends Remote {
	
	void registerClient(RemoteClientObjectInt remoteClient) throws RemoteException;
	void reduction() throws RemoteException;
	void terminate() throws RemoteException;
	void anotherQuit() throws RemoteException;

}
