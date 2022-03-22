package comp533.mvc;

public class ReducerFactory extends gradingTools.comp533s19.assignment0.AMapReduceTracer {

	private static Reducer reducer = Reducer.getReducer();
	
	public static Reducer getReducer() {
		return reducer;
	}
	public static void setReducer(final Reducer reduce) {
		traceSingletonChange(Reducer.class, reduce);
		reducer = reduce;
	}
	
}
