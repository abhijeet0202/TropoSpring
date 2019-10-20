package org.abhibane.spring.learning.dependencyInjection.javaConfig.knightWithAnnotation;

import org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML.Knight;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnightMain {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
		Knight knight = context.getBean(Knight.class);
		knight.embarkOnQuest();
		context.close();
		
	}

}
