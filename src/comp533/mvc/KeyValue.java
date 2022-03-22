package comp533.mvc;

public class KeyValue<KeyType, ValueType> extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements KeyValueInt<KeyType, ValueType> {

	private KeyType key;
	private ValueType value;
	
	public KeyValue(final KeyType key, final ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public KeyType getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	@Override
	public ValueType getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	@Override
	public void setValue(final ValueType value) {
		// TODO Auto-generated method stub
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + key + "," + value + ")";
	}
}