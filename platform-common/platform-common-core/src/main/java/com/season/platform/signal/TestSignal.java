package com.season.platform.signal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("restriction")
public class TestSignal implements SignalHandler {

	private void signalCallback(Signal sn) {
		System.out.println(sn.getName() + "is recevied.");
	}

	@Override
	public void handle(Signal signalName) {
		signalCallback(signalName);
	}

	public static void main(String[] args) throws InterruptedException {
		TestSignal testSignalHandler = new TestSignal();
		// install signals
		Signal.handle(new Signal("TERM"), testSignalHandler);
		/*Signal.handle(new Signal("USR1"), testSignalHandler);
		Signal.handle(new Signal("USR2"), testSignalHandler);*/
		for (;;) {
			Thread.sleep(3000);
			System.out.println("running......");
		}
	}

}