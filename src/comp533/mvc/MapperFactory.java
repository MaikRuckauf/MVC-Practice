package comp533.mvc;

public class MapperFactory extends gradingTools.comp533s19.assignment0.AMapReduceTracer {
	
	private static MapperInt mapper = Mapper.getMap();
	
	public static MapperInt getMapper() {
		return mapper;
	}
	public static void setMapper(final MapperInt newMapper) {
		traceSingletonChange(MapperFactory.class, newMapper);
		mapper = newMapper;
	}
}
