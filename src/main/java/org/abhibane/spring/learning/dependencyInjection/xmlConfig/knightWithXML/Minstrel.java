package org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML;

import java.io.PrintStream;

public class Minstrel {
	
	private PrintStream stream;
	
	public Minstrel(PrintStream stream) {
		this.stream = stream;
	}
	
	public void singBeforeQuest() {
		stream.println("Before");
	}
	
	public void singAfterQuest() {
		stream.println("After");
	}

}
