package DependencyInjection;

import org.springframework.stereotype.Component;

interface CompactDisc{
	void play();
}

@Component
public class SgtPeppers implements CompactDisc{

	@Override
	public void play() {
		System.out.println("Inside SgtPeppers::play()");
		
	}
	
}
