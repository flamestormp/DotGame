package refactor;
import javax.swing.JPanel;
import java.awt.*;

/*
 * Draws: background, turn bar, dots, movement range indicator
 */
public class Panel extends JPanel{
	
	private static final long serialVersionUID = 1L; //something eclipse told me to do
	Dot[][] mapper = Runner.map.getMap();
	
	public void drawing(){
		repaint();
	}
	
	public void paintComponent(Graphics disp){
		super.paintComponent(disp);
		//background
		disp.setColor(Color.LIGHT_GRAY);
		disp.fillRect(0, 0, 1000, 1000);
		//turn bar
		Color c = null;
		if(Runner.turn == 1){ c = Color.BLACK; }
		else{ c = Color.WHITE; }
		disp.setColor(c);
		disp.fillRect(0, 0, 10, 1000);
		//vars
		int xpos, ypos, mov;
		
		for(int x = 0; x < 1000; x++){
			for(int y = 0; y < 1000; y++){
				
				if(mapper[x][y] != null){
					//init vars
					xpos = mapper[x][y].getXposition();
					ypos = mapper[x][y].getYposition();
					mov = mapper[x][y].getMov();
					//draw the dots
					if(mapper[x][y].Team == 0){
						disp.setColor(Color.WHITE);
						disp.fillRect(xpos,ypos,10,10);
					}
					else{
						disp.setColor(Color.BLACK);
						disp.fillRect(xpos,ypos,10,10);
					}
					//display movement range
					if(mapper[x][y].getToggle() == true){
						disp.setColor(new Color(15,15,25,10));
						disp.fillRect(xpos - mov, ypos - mov, 2*mov, 2*mov);
					}
				}
			}
		}
	}
}
