package comp533.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TokenCounterModel extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements PropertyChangeListener, TokenCounterModelInt, RemoteModelInt {

	private String inputString;
	private Map<String, Integer> result = new HashMap<String, Integer>();
	private TokenCounterView view;
	private int numThreads;
	protected List<Thread> threads = new ArrayList<Thread>();
	private BlockingQueue<KeyValue<String, Integer>> keyValueQueue = new ArrayBlockingQueue<KeyValue<String, Integer>>(BUFFER_SIZE);
	private List<LinkedList<KeyValue<String, Integer>>> reductionQueueList = new ArrayList<LinkedList<KeyValue<String, Integer>>>();
	protected List<Slave> slaveList = new ArrayList<Slave>();
	private Joiner joiner;
	private Barrier barrier;
	protected List<Map<String, Integer>> reducedMap = new ArrayList<Map<String, Integer>>();
	protected Stack<RemoteClientObjectInt> remoteClientStack = new Stack<RemoteClientObjectInt>();
	protected Stack<Slave> idleSlaveStack = new Stack<Slave>();
	protected List<RemoteClientObjectInt> remoteClientList = new ArrayList<RemoteClientObjectInt>();
	protected Stack<RemoteClientObjectInt> idleClientStack = new Stack<RemoteClientObjectInt>();
	
	@Override
	public String getInputString() {
		return inputString;
	}
	@Override
	public void setInputString(final String inputString) {
		//joiner = new Joiner(slaveList.size());
		this.inputString = inputString;
		propertyChange(new PropertyChangeEvent(this, "InputString", null, inputString));
		
		// we must now add the following:
		// Initialize next round of token processing
		result.clear();
		for (LinkedList<KeyValue<String, Integer>> queue : reductionQueueList) {
			queue.clear();
		}
		
		// Possible unblocking of slave threads
		for (Slave slave: slaveList) {
			slave.notifySlave();
		}
		
		// Problem split
		final String[] tokenList = inputString.split(" ");
		final List<String> stringList = new ArrayList<String>();
		final MapperInt mapper = MapperFactory.getMapper();
		final ReducerInt reducer = Reducer.getReducer();
		for (String word: tokenList) {
			stringList.add(word);
		}
		final List<KeyValue<String, Integer>> keyValueList = mapper.map(stringList);
		
		// Bounded buffer production
		for (KeyValue<String, Integer> keyValue : keyValueList) {
			try {
				traceEnqueueRequest(keyValue);
				keyValueQueue.put(keyValue);
				traceEnqueue(keyValueQueue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// End of input enqueue
		final KeyValue<String, Integer> nullKeyValue = new KeyValue<String, Integer>(null, null);
		for (int i = 0; i < numThreads; i++) {
			try {
				traceEnqueueRequest(nullKeyValue);
				keyValueQueue.put(nullKeyValue);
				traceEnqueue(keyValueQueue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Join
		try {
			joiner.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Merge partitioned results
		// result = reducer2.reduce(keyValueList);
		for (LinkedList<KeyValue<String, Integer>> queue : reductionQueueList) {
			result.putAll(reducer.reduce(queue));;
			traceAddedToMap(result, result);
		}
		
		
		// Announce a change to the Result property of its observers
		propertyChange(new PropertyChangeEvent(this, "Result", null, result));
	}
	@Override
	public Map<String, Integer> getResult() {
		return result;
	}
	@Override
	public void addPropertyChangeListener(final TokenCounterView view) {
		this.view = view;
	}
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		view.propertyChange(evt);
	}
	@Override
	public String toString() {
		return MODEL;
	}
	@Override
	public int getNumThreads() {
		return numThreads;
	}
	@Override
	public void setNumThreads(final int numThreads) throws InterruptedException {
		this.numThreads = numThreads;
		barrier = new Barrier(numThreads);
		joiner = new Joiner(numThreads);
		propertyChange(new PropertyChangeEvent(this, "numThreads", null, numThreads));
		for (int i = 0; i < numThreads; i++) {
			final Slave slave = new Slave(i, this);
			final Thread thread = new Thread(slave);
			slaveList.add(slave);
			idleSlaveStack.push(slave);
			reductionQueueList.add(new LinkedList<KeyValue<String, Integer>>());
			thread.setName("Slave" + i);
			threads.add(thread);
			thread.start();
		}
		reduction();
	}
	@Override
	public List<Thread> getThreads() {
		return threads;
	}
	@Override
	public BlockingQueue<KeyValue<String, Integer>> getKeyValueQueue() {
		return keyValueQueue;
	}
	@Override
	public List<LinkedList<KeyValue<String, Integer>>> getReductionQueueList() {
		return reductionQueueList;
	}
	@Override
	public LinkedList<KeyValue<String, Integer>> getReductionList(final int num) {
		return reductionQueueList.get(num);
	}
	@Override
	public Barrier getBarrier() {
		return barrier;
	}
	@Override
	public Joiner getJoiner() {
		return joiner;
	}
	@Override
	public List<Slave> getSlaveList() {
		return slaveList;
	}
	@Override
	public void registerClient(RemoteClientObjectInt client) {
		remoteClientList.add(client);
		remoteClientStack.push(client);
		idleClientStack.push(client);
		traceRegister(client);
		reduction();
		
	}
	@Override
	public void reduction() {
		while (!idleSlaveStack.isEmpty() && !idleClientStack.isEmpty()) {
			final SlaveInt slave = idleSlaveStack.pop();
			final RemoteClientObjectInt remoteClient = idleClientStack.pop();
			//traceClientAssignment(remoteClient);
			slave.setClient(remoteClient);
		}
		
	}
	@Override
	public void terminate() throws RemoteException {
		for (Thread thread : threads) {
			thread.interrupt();
		}
		while (!remoteClientStack.isEmpty()) {
			try {
				remoteClientStack.pop().quitObject();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void anotherQuit() {
		while(!remoteClientStack.isEmpty()) {
			try {
				remoteClientStack.pop().quitObject();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
