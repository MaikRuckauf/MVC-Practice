package comp533.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public interface TokenCounterModelInt {

	public String getInputString();
	public void setInputString(String inputString) throws InterruptedException;
	public Map<String, Integer> getResult();
	void addPropertyChangeListener(TokenCounterView view);
	public int getNumThreads();
	public void setNumThreads(int numThreads) throws InterruptedException;
	public List<Thread> getThreads();

	public BlockingQueue<KeyValue<String, Integer>> getKeyValueQueue();
	public List<LinkedList<KeyValue<String, Integer>>> getReductionQueueList(); // something about a linkedList
	public Joiner joiner = new Joiner(0); // this needs to be an interface
	public Barrier barrier = new Barrier(0);
	public Barrier getBarrier();
	public Joiner getJoiner();
	public List<Slave> getSlaveList();
	public LinkedList<KeyValue<String, Integer>> getReductionList(int num);
}
