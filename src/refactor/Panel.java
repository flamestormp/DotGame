package refactor;
import javax.swing.JPanel;
import java.awt.*;

public class Panel extends JPanel{

	Map map = new Map();
	Dot[][] mapper = map.getMap();
	
	public void drawing(){
		repaint();
	}

	public void paintComponent(Graphics disp){
		super.paintComponent(disp);
		//background
		disp.setColor(Color.LIGHT_GRAY);
		disp.fillRect(0, 0, 1000, 1000);
		//then dots
		for(int x = 0; x < 1000; x++){
			for(int y = 0; y < 1000; y++){
				if(mapper[x][y] != null){
					if(mapper[x][y].Team == 0){
						disp.setColor(Color.WHITE);
						disp.fillRect(mapper[x][y].getXposition(),
							mapper[x][y].getYposition(),10,10);
					}
					else{
						disp.setColor(Color.BLACK);
						disp.fillRect(mapper[x][y].getXposition(),
							mapper[x][y].getYposition(),10,10);
					}
				}
			}	
		}
	}
}
