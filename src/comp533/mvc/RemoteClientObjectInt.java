package comp533.mvc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface RemoteClientObjectInt extends Remote {
	public Map<String, Integer> clientMapper(List<KeyValue<String, Integer>> list) throws RemoteException;
	public void quitObject() throws RemoteException;
	public void clientWait() throws RemoteException;
}
