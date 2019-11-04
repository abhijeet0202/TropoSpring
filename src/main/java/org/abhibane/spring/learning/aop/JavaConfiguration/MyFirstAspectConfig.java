package org.abhibane.spring.learning.aop.JavaConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.abhibane.spring.learning.aop")
public class MyFirstAspectConfig {

}
