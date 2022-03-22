package comp533.mvc;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class RemoteClientObject extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements RemoteClientObjectInt {

	//Map<String, Integer> clientMap = null;
	
	@Override
	public Map<String, Integer> clientMapper(final List<KeyValue<String, Integer>> list) {
		traceRemoteList(list);
		final ReducerInt reducer = ReducerFactory.getReducer();
		final Map<String, Integer> clientMap = reducer.reduce(list);
		traceRemoteResult(clientMap);
		return clientMap;
	}
	@Override
	public synchronized void quitObject() throws RemoteException {
		traceQuit();
		this.synchronizedNotify();
		
	}
	@Override
	public synchronized void clientWait() throws RemoteException {
		try {
			this.synchronizedWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		return CLIENT;
	}
}
