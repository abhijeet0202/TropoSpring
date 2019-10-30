package org.abhibane.spring.learning.dependencyInjection.javaConfig.beanStudentSchool;

import org.springframework.stereotype.Component;

@Component
public class DAVPublic implements School {

	@Override
	public void register() {
		System.out.println("DAV Public School Registeration done");
	}
	
	@Override
	public String toString() {
		return " DAV Public School";
	}

}
