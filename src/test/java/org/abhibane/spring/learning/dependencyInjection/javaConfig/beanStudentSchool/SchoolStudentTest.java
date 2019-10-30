package org.abhibane.spring.learning.dependencyInjection.javaConfig.beanStudentSchool;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // It tells Spring application context, to automatically created when the test starts.
@ContextConfiguration(classes = SchoolStudentConfig.class) // ContextConfiguration annotation tells to load its configuration from ?classes?
public class SchoolStudentTest {
	
	@Autowired
	School school;
	
	@Autowired
	Student student;
	
	@Test
	public void schoolShouldNotBeNull() {
		assertNotNull(school);
		school.register();
		System.out.println(school);
	}
	
	@Test
	public void studentStudyinWhochSchool() {
		student.whichSchool();
	}
}
