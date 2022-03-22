package comp533.mvc;

import java.util.List;
import java.util.Map;

public interface ReducerInt<KeyType, ValueType> {
	
	public Map<KeyType, ValueType> reduce(List<KeyValue<KeyType, ValueType>> list);

}
