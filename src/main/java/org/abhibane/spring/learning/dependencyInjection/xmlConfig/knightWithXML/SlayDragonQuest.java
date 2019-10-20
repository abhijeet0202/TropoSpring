package org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML;

import java.io.PrintStream;

public class SlayDragonQuest implements Quest {

	private PrintStream stream;
	
	public SlayDragonQuest(PrintStream stream){
		this.stream = stream;
	}
	@Override
	public void embark() {
		stream.println("Embarking On Quest To Slay The Dragon");

	}

}
