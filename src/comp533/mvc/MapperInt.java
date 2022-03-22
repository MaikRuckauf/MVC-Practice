package comp533.mvc;

import java.util.List;

public interface MapperInt<KeyType, ValueType> {
	
	public List<KeyValue<KeyType, ValueType>> map(List<String> list);
	public String toString();
}
