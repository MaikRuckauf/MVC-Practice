package comp533.mvc;

public class MapReduceSummationMain {

	public static void main(final String[] args) throws NumberFormatException, InterruptedException {
		
		final MapReduceSummation mapReduceSummation = MapReduceSummation.getMapReduceSummation();
		MapperFactory.setMapper(mapReduceSummation);
		
		final TokenCounterModel model = new TokenCounterModel();
		final TokenCounterController controller = new TokenCounterController(model);
		final TokenCounterView view = new TokenCounterView();
		
		model.addPropertyChangeListener(view);
		controller.tokenCounter();
	}
}
