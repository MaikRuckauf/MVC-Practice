package comp533.mvc;

import java.rmi.RemoteException;

public interface TokenCounterControllerInt {
	
	public void inputPrompt();
	public void setInputString(String inputString) throws InterruptedException;
	public void tokenCounter() throws NumberFormatException, InterruptedException, RemoteException;
	

}
