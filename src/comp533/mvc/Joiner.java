package comp533.mvc;

public class Joiner extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements JoinerInt {
	
	int joinerCount;
	int callCount;
	boolean lock;

	public Joiner(final int joinerCount) {
		this.joinerCount = joinerCount;
		this.callCount = 1;
		this.lock = false;
		traceJoinerCreated(this, joinerCount);
	}

	@Override
	public synchronized void finished() {
		traceJoinerFinishedTask(this, joinerCount, callCount);
		if (callCount < joinerCount) {
			callCount += 1;
			lock = false;
		}
		else {
			lock = true;
			callCount = 1;
			this.notify();
		}	
	}

	@Override
	public synchronized void join() throws InterruptedException {
		if (!lock) {
			traceJoinerWaitStart(this, joinerCount, callCount);
			this.wait();
			traceJoinerWaitEnd(this, joinerCount, callCount);
		}
		traceJoinerRelease(this, joinerCount, callCount);
		lock = false;
	}
	@Override
	public String toString() {
		return JOINER;
	}
	
}
