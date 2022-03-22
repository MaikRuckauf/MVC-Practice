package comp533.mvc;

public interface SlaveInt extends Runnable {

	public void run();
	public void notifySlave();
	public void setClient(RemoteClientObjectInt remoteClient);
	public void waits() throws Exception;
	public String toString();
	
}
