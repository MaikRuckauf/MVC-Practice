package comp533.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class TokenCounterView extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements PropertyChangeListener, TokenCounterViewInt{
	
	@Override
	public void printTokenCounterDetails(final String inputString, final Map<String, Integer> result) {
	}
	@Override
	public void printTokenMap(final Map<String, Integer> result) {
		traceMap(result.values(), result.keySet());
	}
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		tracePropertyChange(evt);
	}
	@Override
	public String toString() {
		return VIEW;
	}
}
