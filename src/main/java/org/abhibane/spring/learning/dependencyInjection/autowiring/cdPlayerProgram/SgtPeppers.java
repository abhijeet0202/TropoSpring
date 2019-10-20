package org.abhibane.spring.learning.dependencyInjection.autowiring.cdPlayerProgram;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisk{

	@Override
	public void play() {
		System.out.println("Inside SgtPeppers::play()");
		
	}
	
}
