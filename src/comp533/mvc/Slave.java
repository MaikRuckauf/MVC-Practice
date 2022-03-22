package comp533.mvc;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Slave extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements SlaveInt {
	
	private int number;
	private RemoteClientObjectInt remoteClient;
	private TokenCounterModel model;
	
	public Slave (final int number, final TokenCounterModelInt model) {
		
		this.model = (TokenCounterModel) model;
		this.number = number;
		this.remoteClient = null;
		
	}
	
	@Override
	public void run() {
		try {
			for(;;) {
				
				List<KeyValue<String, Integer>> slaveList = new ArrayList<KeyValue<String, Integer>>();
				Map<String, Integer> clientMap = null;
				BlockingQueue<KeyValue<String, Integer>> buffer = model.getKeyValueQueue();
				while (slaveList.size() == 0 || (slaveList.get(slaveList.size()-1).getKey() != null)) {
					traceDequeueRequest(buffer);
					slaveList.add((KeyValue<String, Integer>) buffer.take());
					traceDequeue(slaveList.get(slaveList.size()-1));
				}
				slaveList.remove(slaveList.size()-1);
				if (remoteClient == null) {
					final ReducerInt reduce = ReducerFactory.getReducer();
					traceReduce(slaveList, reduce);
					clientMap = reduce.reduce(slaveList);
				}
				else {
					try {
						traceRemoteList(slaveList);
						clientMap = remoteClient.clientMapper(slaveList);
						traceRemoteResult(clientMap);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				final PartitionerInt<String, Integer> partitioner = PartitionerFactory.getPartitioner();
				if (clientMap != null) {
					for (String word : clientMap.keySet()) {
						final int value = clientMap.get(word);
						final int index = partitioner.partition(word, value, model.getNumThreads());
						model.getReductionQueueList().get(index).add(new KeyValue<String, Integer>(word, value));
						tracePartitionAssigned(word, value, index, model.getNumThreads());
					}
				}
				final BarrierInt barrier = model.getBarrier();
				barrier.barrier();
				slaveList = model.getReductionList(number);
				traceSplitAfterBarrier(number, slaveList);
				if (remoteClient == null) {
					final ReducerInt reduce = ReducerFactory.getReducer();
					traceReduce(slaveList, reduce);
					clientMap = reduce.reduce(slaveList);
				}
				else {
					try {
						traceRemoteList(slaveList);
						clientMap = remoteClient.clientMapper(slaveList);
						traceRemoteResult(clientMap);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				model.getReductionList(number).clear();
				if (clientMap != null) {
					for (String word : clientMap.keySet()) {
						model.getReductionList(number).add(new KeyValue<String, Integer>(word, clientMap.get(word)));
					}
				}
				final JoinerInt joiner = model.getJoiner();
				joiner.finished();
				try {
					waits();
				} catch (Exception e) {
					this.traceQuit();
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			this.traceQuit();
		}
	}
	
	@Override
	public synchronized void notifySlave() {
		trace(NOTIFY);
		this.notify(); 
	}
	@Override
	protected void traceQuit() {
		trace (QUITTING);
	}
	@Override
	public void setClient(final RemoteClientObjectInt remoteClient) {
		traceClientAssignment(remoteClient);
		this.remoteClient = remoteClient;
	}
	@Override 
	public synchronized void waits() throws Exception {
		traceWait();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
			traceQuit();
			throw new Exception();
		}
	}
	@Override
	public String toString() {
		return SLAVE;
	}
}
