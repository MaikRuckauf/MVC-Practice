package comp533.mvc;

import java.util.ArrayList;
import java.util.List;

public class Mapper extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements MapperInt<String, Integer> {
	
	private static Mapper mapper = new Mapper();

	@Override
	public List<KeyValue<String, Integer>> map(List<String> list) {
		// TODO Auto-generated method stub
		final List<KeyValue<String, Integer>> tokenList = new ArrayList<KeyValue<String, Integer>>();
		for (String word : list) {
			final KeyValue<String, Integer> keyVal = new KeyValue<String, Integer>(word, 1);
			tokenList.add(keyVal);
		}
		traceMap(list, tokenList);
		return tokenList;
	}
	public static Mapper getMap() {
		// TODO Auto-generated method stub
		return mapper;
	}
	public static void setMap(final Mapper mapper) {
		// TODO Auto-generated method stub
		Mapper.mapper = mapper;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return TOKEN_COUNTING_MAPPER;
	}
}
