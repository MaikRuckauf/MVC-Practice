package comp533.mvc;

import java.util.ArrayList;
import java.util.List;

public class MapReduceSummation extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements MapperInt<String, Integer> {
	
	private static MapReduceSummation mapReduceSum = new MapReduceSummation();

	@Override
	public List<KeyValue<String, Integer>> map(final List<String> list) {
		// TODO Auto-generated method stub
		final List<KeyValue<String, Integer>> tokenList = new ArrayList<KeyValue<String, Integer>>();
		for (String word : list) {
			final KeyValue<String, Integer> keyVal = new KeyValue<String, Integer>("ResultKey", Integer.parseInt(word));
			tokenList.add(keyVal);
		}
		traceMap(list, tokenList);
		return tokenList;
	}
	public static MapReduceSummation getMapReduceSummation() {
		return mapReduceSum;
	}
	public static void setMapReduceSummation(final MapReduceSummation mapReduceSum) {
		MapReduceSummation.mapReduceSum = mapReduceSum;
		
	}
	@Override
	public String toString() {
		return INT_SUMMING_MAPPER;
	}
}
