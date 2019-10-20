package org.abhibane.spring.learning.dependencyInjection.autowiring.cdPlayerProgram;

import static org.junit.Assert.*;

import org.abhibane.spring.learning.dependencyInjection.autowiring.cdPlayerProgram.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // It tells Spring application context, to automatically created when the test starts.
@ContextConfiguration(classes=CDPlayerConfig.class) // ContextConfiguration annotation tells to load its configuration from ?classes?
public class CDPlayerTest {
	
	@Autowired
	private CompactDisk cd;
	
	@Test
	public void cdShouldNotBeNull() {
		assertNotNull(cd);
		cd.play();
	}
	
	/*
	 * @Test public void play() { //md.play(); }
	 */

}
