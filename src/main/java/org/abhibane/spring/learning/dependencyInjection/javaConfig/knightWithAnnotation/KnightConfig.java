package org.abhibane.spring.learning.dependencyInjection.javaConfig.knightWithAnnotation;

import org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML.BraveKnight;
import org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML.Knight;
import org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML.Quest;
import org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {
	
	@Bean
	public Knight knight() {
		return new BraveKnight(quest());
	}
	
	@Bean
	public Quest quest() {
		return new SlayDragonQuest(System.out);
	}

}
