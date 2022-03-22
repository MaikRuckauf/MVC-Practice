package comp533.mvc;

public class TokenCounterMain {

	public static void main(final String[] args) throws NumberFormatException, InterruptedException {
		final TokenCounterModel model = new TokenCounterModel();
		final TokenCounterView view = new TokenCounterView();
		final TokenCounterController controller = new TokenCounterController(model);
		model.addPropertyChangeListener(view);
		controller.tokenCounter();
	}
}
