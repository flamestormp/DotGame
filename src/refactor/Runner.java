package refactor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Runner {
	//white = 0, black = 1
	//loser is the one who hits 0 first
	static int white_count = 18, black_count = 18;
	static final int WIDTH = 1000, HEIGHT = 1000;
	static Map map = new Map();
	static int toggle = 0; // 0 = unselected, 1 = selected
	static int xpos;
    static int ypos;
    static int turn = 1;
    
	public static void main(String[] args){
	
		
			final int tol = 11;
			JFrame frame = new JFrame("DotGame");
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			Panel panel = new Panel();
			frame.add(panel);
			panel.drawing();
			panel.addMouseListener(new MouseListener(){
				Dot tempdot;
				public void mouseClicked(MouseEvent e){
					xpos = e.getX();
					ypos = e.getY();
			    	System.out.print(xpos + "," + ypos + ", ");
			    	if(toggle == 0){
			    		//tolerance 'tol' pixels on each side of click
			 			for(int xsearch = (xpos-tol); xsearch < (xpos+tol); xsearch++){
			 				for(int ysearch = (ypos-tol); ysearch < (ypos+tol); ysearch++){
			 					if(map.getMap()[xsearch][ysearch] != null){
			 						tempdot = map.selectObj(xsearch, ysearch, turn);
			 						if(tempdot != null){
			 						toggle = 1;
			 						System.out.println(", toggle: " + toggle);
			 						}
			 					}
			 				}
			 			}
			 		}
			 		else if(toggle == 1){
			 			//check to see if move is successful
			 			if(map.moveObj(xpos, ypos, tempdot) == true){
			 				toggle = 0;
			
			 				if(turn == 0) turn = 1;
			 				else turn = 0;
			 		
			 				System.out.println(", toggle: " + toggle);
			 			}
			 		}
			    	panel.drawing();
				}
				public void mouseEntered(MouseEvent arg0){
				}
				public void mouseExited(MouseEvent arg0){
				}
				public void mousePressed(MouseEvent arg0){
				}
				public void mouseReleased(MouseEvent arg0){
				}
			});
			
		//announce victor
		if(white_count == 0) 
			System.out.println("Black Wins!");
		else if(black_count == 0)
			System.out.println("White Wins!");
	}
}
