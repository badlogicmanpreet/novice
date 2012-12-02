package com.jmon;

import com.jmon.client.MFrame;

public class MStarter {

	static MStarter starter;
	static MFrame frame;

	public MStarter() {
	}

	public static void main(String args[]) {
		starter = new MStarter();
		frame = new MFrame(true);
		frame.createFrame();
	}

}
