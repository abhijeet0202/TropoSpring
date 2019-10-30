package org.abhibane.spring.learning.dependencyInjection.autowiring.cdPlayerProgram;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer implements MediaPlayer {
	
	private CompactDisk compactDisk;
	
	public CDPlayer(CompactDisk argCompactDisk) {
		compactDisk = argCompactDisk;
	}
	
	
	public void play() {
		compactDisk.play();
	}

}
