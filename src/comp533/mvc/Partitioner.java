package comp533.mvc;

public class Partitioner extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements PartitionerInt<String, Integer> {
	
	@Override
	public int partition(final String key, final Integer value, final int numPartitions) {
		if (!Character.isLetter(key.charAt(0))) {
			tracePartitionAssigned(key, value, 0, numPartitions);
			return 0;
		}
		final double max = Math.ceil(('z' - 'a' + 1) / (double) numPartitions);
		final double partition = Math.floor((key.toLowerCase().charAt(0) - 'a' + 1) / (double) max);
		tracePartitionAssigned(key, value, (int)partition, numPartitions);
		return (int)partition;
	}

	@Override
	public String toString() {
		return PARTITIONER;
	}

}
