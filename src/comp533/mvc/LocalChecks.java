package comp533.mvc;

import grader.basics.execution.BasicProjectExecution;
import gradingTools.comp533s22.assignment3.S22Assignment3Suite;
import trace.grader.basics.GraderBasicsTraceUtility;
// import util.trace.Tracer;

public class LocalChecks {
	public static void main(final String[] args) {
		final int first = 600;
		final int second = 2000;
		final int third = 5;
		// if you set this to false, grader steps will not be traced
		GraderBasicsTraceUtility.setTracerShowInfo(true);	
		// if you set this to false, all grader steps will be traced,
		// not just the ones that failed		
		GraderBasicsTraceUtility.setBufferTracedMessages(true);
		// Change this number if a test trace gets longer than 600 and is clipped
		GraderBasicsTraceUtility.setMaxPrintedTraces(first);
		// Change this number if all traces together are longer than 600
		GraderBasicsTraceUtility.setMaxTraces(second);
		// Change this number if your process times out prematurely
		BasicProjectExecution.setProcessTimeOut(third);
		// You need to always call such a method
		S22Assignment3Suite.main(args);
	}
}
