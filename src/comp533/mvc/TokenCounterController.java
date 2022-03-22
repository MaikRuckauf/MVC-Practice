package comp533.mvc;

import java.rmi.RemoteException;
import java.util.Scanner;

public class TokenCounterController extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements TokenCounterControllerInt {

	private TokenCounterModelInt model;
	Scanner scanner;
	
	public TokenCounterController(final TokenCounterModelInt model) {
		this.model = model;
		this.scanner = new Scanner(System.in);
	}
	@Override
	public void inputPrompt() {
		traceNumbersPrompt();
	}
	@Override
	public void setInputString(final String inputString) throws InterruptedException {
		model.setInputString(inputString);
	}
	@Override
	public void tokenCounter() throws NumberFormatException, InterruptedException {
		//final String test = "quit";
		traceThreadPrompt();
		final String threadCount = scanner.nextLine();
		model.setNumThreads(Integer.parseInt(threadCount));
		while(true) {
			inputPrompt();
			final String inputString = scanner.nextLine();
			if (inputString.equals("quit")) {
				traceQuit();
				try {
					((TokenCounterModel) model).terminate();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			setInputString(inputString);
			model.getResult();
		}
	}
	protected void traceQuit() {
		trace (QUITTING);
	}
	@Override
	public String toString() {
		return CONTROLLER;
	}
}
