package comp533.mvc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerIntegerSummer {

	public static void main(final String[] args) {
		try {
			final MapReduceSummation mapReduceSum = MapReduceSummation.getMapReduceSummation();
			MapperFactory.setMapper(mapReduceSum);
			Registry registry = LocateRegistry.createRegistry(1099);
			final RemoteModel model = new RemoteModel();
			UnicastRemoteObject.exportObject(model, 0);
			registry.rebind("model", model);
			final TokenCounterView view = new TokenCounterView();
			final TokenCounterController controller = new TokenCounterController(model);
			model.addPropertyChangeListener(view);
			controller.tokenCounter();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
}
