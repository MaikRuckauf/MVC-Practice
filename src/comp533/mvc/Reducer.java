package comp533.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reducer extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements ReducerInt<String, Integer> {
	
	
	private static Reducer reducer = new Reducer();

	@Override
	public Map<String, Integer> reduce(final List<KeyValue<String, Integer>> list) {
		// TODO Auto-generated method stub
		final Map<String, Integer> tokenMap = new HashMap<String, Integer>();
		for (KeyValue<String, Integer> word : list) {
			tokenMap.put(word.getKey(), tokenMap.getOrDefault(word.getKey(), 0) + word.getValue());
		}
		traceReduce(list, tokenMap);
		return tokenMap;
	}
	public static Reducer getReducer() {
		return reducer;
	}
	public static void setReducer(final Reducer reducer) {
		Reducer.reducer = reducer;
	}
	@Override
	public String toString() {
		return REDUCER;
	}
}
