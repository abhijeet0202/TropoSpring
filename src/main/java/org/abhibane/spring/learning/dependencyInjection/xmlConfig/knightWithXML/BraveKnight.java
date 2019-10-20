package org.abhibane.spring.learning.dependencyInjection.xmlConfig.knightWithXML;

public class BraveKnight implements Knight {

	private Quest quest;
	
	public BraveKnight(Quest quest) {
		this.quest= quest;
	}

	@Override
	public void embarkOnQuest() {
		quest.embark();

	}

}
