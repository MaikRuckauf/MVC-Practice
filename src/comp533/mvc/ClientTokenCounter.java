package comp533.mvc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientTokenCounter extends gradingTools.comp533s19.assignment0.AMapReduceTracer{

	public static void main(final String[] args) {
		try {
			final Registry registry = LocateRegistry.getRegistry(1099);
			final RemoteClientObjectInt remoteClient = new RemoteClientObject();
			UnicastRemoteObject.exportObject(remoteClient, 0);
			final RemoteModelInt model = (RemoteModelInt) registry.lookup("model");
			model.registerClient(remoteClient);
			remoteClient.clientWait();
			traceExit(ClientTokenCounter.class);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
