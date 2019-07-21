package DependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

interface MediaPlayer{
	
}
@Component
public class CDPlayer implements MediaPlayer{
	
	private CompactDisc cd;
	
	@Autowired
	public CDPlayer(CompactDisc myCD) {
		cd = myCD;
	}
	
	public void play() {
		cd.play();
	}

}
