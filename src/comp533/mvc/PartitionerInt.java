package comp533.mvc;

public interface PartitionerInt<K, V> {

	abstract int partition(K key, V value, int numPartitions);
	public String toString();
	
}
