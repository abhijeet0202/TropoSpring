package org.abhibane.spring.learning.aop.myFirstAspect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.abhibane.spring.learning.aop.JavaConfiguration.MyFirstAspectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyFirstAspectConfig.class)
public class TestSimpleAspectJavaConfig {
	
	@Autowired
	TracingAspect tracingAspect;
	
	@Autowired
	SimpleService simpleService;
	
	@Test
	public void aspectIsCalled() {
		tracingAspect.isEntered =false;//reset
		assertFalse(tracingAspect.isEnteringCalled());
		simpleService.doSomething();
		assertTrue(tracingAspect.isEnteringCalled());
	}

	@Test
	public void aspectIsExitCalled() {
		tracingAspect.isExit =false;
		assertFalse(tracingAspect.isExitCalled());
		simpleService.doSomething();
		assertTrue(tracingAspect.isExitCalled());
	}

	@Test
	public void aspectIsSuccesfulReturnedCalled() {
		tracingAspect.isSuccessfulReturned =false;
		assertFalse(tracingAspect.isSuccessfulReturnedCalled());
		simpleService.doSomething();
		assertTrue(tracingAspect.isSuccessfulReturnedCalled());
	}

	@Test(expected = RuntimeException.class)
	public void aspectisThrowingReturnedCalled() {
		tracingAspect.isThrowingReturned =false;
		assertFalse(tracingAspect.isThrowingReturnedCalled());
		simpleService.doException();
		assertTrue(tracingAspect.isThrowingReturnedCalled());
	}

}
