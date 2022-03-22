package comp533.mvc;

import java.io.Serializable;

public interface KeyValueInt<KeyType, ValueType> extends Serializable{
	
	public KeyType getKey();
	public ValueType getValue();
	void setValue(ValueType value);
	public String toString();
}
