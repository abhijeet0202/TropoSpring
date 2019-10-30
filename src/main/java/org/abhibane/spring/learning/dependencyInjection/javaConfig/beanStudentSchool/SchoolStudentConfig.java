package org.abhibane.spring.learning.dependencyInjection.javaConfig.beanStudentSchool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolStudentConfig {

	@Bean
	public School school() {
		return new DAVPublic();
	}

	// Style 1:
	@Bean
	public Student student() {
		return new AbhijeetStudent(school());
	}

	// Style 2:
	@Bean
	public Student student1(School school) {
		return new AbhijeetStudent(school);
	}

}
