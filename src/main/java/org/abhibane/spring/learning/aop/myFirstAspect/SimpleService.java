package org.abhibane.spring.learning.aop.myFirstAspect;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	public void doSomething() {

	}

	public void doException() {
		throw new RuntimeException();
	}
}
