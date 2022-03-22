package comp533.mvc;

public class Barrier extends gradingTools.comp533s19.assignment0.AMapReduceTracer implements BarrierInt {

	int barrierCount;
	int callCount;
	
	public Barrier (final int barrierCount) {
		this.barrierCount = barrierCount;
		callCount = 1;
		// traceBarrierCreated(this, barrierCount);
	}
	
	@Override 
	public synchronized void barrier() throws InterruptedException {
		if (callCount < barrierCount) {
			callCount += 1;
			traceBarrierWaitStart(this, barrierCount, callCount);
			this.wait();
			traceBarrierWaitEnd(this, barrierCount, callCount);
		}
		else {
			traceBarrierReleaseAll(this, barrierCount, callCount);
			callCount = 1;
			this.notifyAll();
		}
	}
	@Override
	public String toString() {
		return BARRIER;
	}
}
