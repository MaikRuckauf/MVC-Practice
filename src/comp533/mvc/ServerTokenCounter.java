package comp533.mvc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerTokenCounter extends gradingTools.comp533s19.assignment0.AMapReduceTracer {
	
	public static void main(final String[] args) {
		try {
			
			Registry registry = LocateRegistry.createRegistry(1099);
			final TokenCounterModel model = new TokenCounterModel();
			UnicastRemoteObject.exportObject(model, 0);
			registry.rebind("model", model);
			final TokenCounterView view = new TokenCounterView();
			final TokenCounterController controller = new TokenCounterController(model);
			model.addPropertyChangeListener(view);
			controller.tokenCounter();
			traceExit(ServerTokenCounter.class);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
