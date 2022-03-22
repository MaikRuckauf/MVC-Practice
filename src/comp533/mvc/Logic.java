package comp533.mvc;

public class Logic implements gradingTools.comp533s21.assignment1.interfaces.MapReduceConfiguration {

	@Override
	public Object getBarrier(final int aNumThreads) {
		// TODO Auto-generated method stub
		return new Barrier(aNumThreads);
	}

	@Override
	public Class getBarrierClass() {
		// TODO Auto-generated method stub
		return Barrier.class;
	}

	@Override
	public Class getClientTokenCounter() {
		// TODO Auto-generated method stub
		return ClientTokenCounter.class;
	}

	@Override
	public Class getControllerClass() {
		// TODO Auto-generated method stub
		return TokenCounterController.class;
	}

	@Override
	public Object getIntSummingMapper() {
		// TODO Auto-generated method stub
		return MapReduceSummation.getMapReduceSummation();
	}

	@Override
	public Class getIntSummingMapperClass() {
		// TODO Auto-generated method stub
		return MapReduceSummation.class;
	}

	@Override
	public Object getJoiner(final int aNumThreads) {
		// TODO Auto-generated method stub
		return new Joiner(aNumThreads);
	}

	@Override
	public Class getJoinerClass() {
		// TODO Auto-generated method stub
		return Joiner.class;
	}

	@Override
	public Class getKeyValueClass() {
		// TODO Auto-generated method stub
		return KeyValue.class;
	}

	@Override
	public Class getMapperFactory() {
		// TODO Auto-generated method stub
		return MapperFactory.class;
	}

	@Override
	public Class getModelClass() {
		// TODO Auto-generated method stub
		return TokenCounterModel.class;
	}

	@Override
	public Object getPartitioner() {
		// TODO Auto-generated method stub
		return PartitionerFactory.getPartitioner();
	}

	@Override
	public Class getPartitionerClass() {
		// TODO Auto-generated method stub
		return Partitioner.class;
	}

	@Override
	public Class getPartitionerFactory() {
		// TODO Auto-generated method stub
		return PartitionerFactory.class;
	}

	@Override
	public Object getReducer() {
		// TODO Auto-generated method stub
		return ReducerFactory.getReducer();
	}

	@Override
	public Class getReducerClass() {
		// TODO Auto-generated method stub
		return Reducer.class;
	}

	@Override
	public Class getReducerFactory() {
		// TODO Auto-generated method stub
		return ReducerFactory.class;
	}

	@Override
	public Class getRemoteClientFacebookMapReduce() {
		// TODO Auto-generated method stub
		return RemoteClientFacebookMapReduce.class;
	}

	@Override
	public Class getRemoteClientObjectClass() {
		// TODO Auto-generated method stub
		return RemoteClientObject.class;
	}

	@Override
	public Class getRemoteClientObjectInterface() {
		// TODO Auto-generated method stub
		return RemoteClientObjectInt.class;
	}

	@Override
	public Class getRemoteModelInterface() {
		// TODO Auto-generated method stub
		return RemoteModelInt.class;
	}

	@Override
	public Class getServerFacebookMapReduce() {
		// TODO Auto-generated method stub
		return ServerFacebookMapReduce.class;
	}

	@Override
	public Class getServerIntegerSummer() {
		// TODO Auto-generated method stub
		return ServerIntegerSummer.class;
	}

	@Override
	public Class getServerTokenCounter() {
		// TODO Auto-generated method stub
		return ServerTokenCounter.class;
	}

	@Override
	public Class getSlaveClass() {
		// TODO Auto-generated method stub
		return Slave.class;
	}

	@Override
	public Class getStandAloneFacebookMapReduce() {
		// TODO Auto-generated method stub
		return StandAloneFacebookMapReduce.class;
	}

	@Override
	public Class getStandAloneIntegerSummer() {
		// TODO Auto-generated method stub
		return MapReduceSummationMain.class;
	}

	@Override
	public Class getStandAloneTokenCounter() {
		// TODO Auto-generated method stub
		return TokenCounterMain.class;
	}

	@Override
	public Object getTokenCountingMapper() {
		// TODO Auto-generated method stub
		return MapperFactory.getMapper();
	}

	@Override
	public Class getTokenCountingMapperClass() {
		// TODO Auto-generated method stub
		return Mapper.class;
	}

	@Override
	public Class getViewClass() {
		// TODO Auto-generated method stub
		return TokenCounterView.class;
	}

}
