package org.abhibane.spring.learning.dependencyInjection.javaConfig.beanStudentSchool;

import org.springframework.stereotype.Component;

@Component
public class AbhijeetStudent implements Student {
	
	private School mySchool;
	public AbhijeetStudent(School schoolRegistration) {
		mySchool = schoolRegistration;
	}

	@Override
	public void whichSchool() {
		System.out.println("I am studying in"+mySchool);

	}

}
