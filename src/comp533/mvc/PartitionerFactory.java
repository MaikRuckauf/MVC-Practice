package comp533.mvc;

public class PartitionerFactory extends gradingTools.comp533s19.assignment0.AMapReduceTracer {

	static Partitioner partition = new Partitioner();
	
	public static Partitioner getPartitioner() {
		return partition;
	}
	public static void setPartitioner(final Partitioner partitioner) {
		traceSingletonChange(Partitioner.class, partitioner);
		partition = partitioner;
	}
	
}
