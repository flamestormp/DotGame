package refactor;
import javax.swing.JPanel;
import java.awt.*;

/*
 * Draws: background, turn bar, dots and movement range indicator just a box centered on all toggled dots
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
		if(Runner.white_count > 1 || Runner.black_count > 1){
			disp.setColor(Color.LIGHT_GRAY);
			disp.fillRect(0, 0, 1000, 1000);
			//turn bar
			Color c = null;
			if(Runner.turn == 1){ c = Color.BLACK; }
			else{ c = Color.WHITE; }
			disp.setColor(c);
			disp.fillRect(0, 0, 20, 1000);
			//vars
			int xpos, ypos, mov, consume;
		
			for(int x = 0; x < 1000; x++){
				for(int y = 0; y < 1000; y++){
					if(mapper[x][y] != null){
						//init vars
						xpos = mapper[x][y].getXposition();
						ypos = mapper[x][y].getYposition();
						mov = mapper[x][y].getMov();
						consume = mapper[x][y].getConsumeCount();
						//draw the dots
						if(mapper[x][y].Team == 0){
							disp.setColor(Color.WHITE);
							disp.fillRect(xpos-(5+(consume/2)),ypos-(5+(consume/2)),
									(consume-1)+10,(consume-1)+10);
						}
						else{
							disp.setColor(Color.BLACK);
							disp.fillRect(xpos-(5+(consume/2)),ypos-(5+(consume/2)),
									(consume-1)+10,(consume-1)+10);
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
		else if(Runner.white_count == 0){
			disp.setColor(Color.LIGHT_GRAY);
			disp.fillRect(0, 0, 1000, 1000);
			disp.setColor(Color.BLACK);
			disp.fillRect(0, 400, 1000, 100);
			disp.setColor(Color.WHITE);
		}
		else if(Runner.black_count == 0){
			disp.setColor(Color.LIGHT_GRAY);
			disp.fillRect(0, 0, 1000, 1000);
			disp.setColor(Color.WHITE);
			disp.fillRect(0, 400, 1000, 100);
		}
	}
}
