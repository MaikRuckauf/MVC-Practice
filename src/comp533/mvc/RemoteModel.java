package comp533.mvc;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class RemoteModel extends TokenCounterModel implements RemoteModelInt, PropertyChangeListener  {

	@Override
	public void registerClient(final RemoteClientObjectInt remoteClient) {
		remoteClientList.add(remoteClient);
		remoteClientStack.push(remoteClient);
		idleClientStack.push(remoteClient);
		traceRegister(remoteClient);
		reduction();
	}
	@Override
	public void reduction() {
		while (!idleSlaveStack.isEmpty() && !idleClientStack.isEmpty()) {
			final SlaveInt slave = idleSlaveStack.pop();
			final RemoteClientObjectInt remoteClient = idleClientStack.pop();
			traceClientAssignment(remoteClient);
			slave.setClient(remoteClient);
		}
	}
	@Override
	public void terminate() {
		for (Slave slave : slaveList) {
			slave.traceQuit();
		}
		if (!remoteClientList.isEmpty()) {
			for (RemoteClientObjectInt word : remoteClientList) {
				try {
					word.quitObject();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
