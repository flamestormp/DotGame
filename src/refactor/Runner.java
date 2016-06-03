package refactor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

/*
 *The runner class allows for the other classes to interact via static variables 
 * the runner produces the data for the graphics which are displayed by the panel
 */
public class Runner {
	//white = 0, black = 1
	//loser is the one who hits 0 first 18 represents dots per team
	static int white_count = 18, black_count = 18;
	static final int WIDTH = 1000, HEIGHT = 1000;
	static Map map = new Map();
	static int toggle = 0; // 0 = unselected, 1 = selected
	static int xpos;
    static int ypos;
    static int turn = 1;
    
	public static void main(String[] args){
		final int tol = 30;
		File file = new File("results.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			JFrame frame = new JFrame("DotGame");
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			Panel panel = new Panel();
			frame.add(panel);
			panel.drawing();
			//mouse input is used to interact with runner which changes the array positions and dot values
			panel.addMouseListener(new MouseListener(){
				Dot tempdot;
				public void mouseClicked(MouseEvent e){
					xpos = e.getX();
					ypos = e.getY();
			    	System.out.print(xpos + "," + ypos + ", ");
			    	if(white_count > 1 || black_count > 1){
			    		if(toggle == 0){
			    			//tolerance 'tol' pixels on each side of click
			    			for(int xsearch = (xpos-tol); xsearch < (xpos+tol); xsearch++){
			 					for(int ysearch = (ypos-tol); ysearch < (ypos+tol); ysearch++){
			 						if(xsearch < 1000 && ysearch < 1000 &&
			 							map.getMap()[xsearch][ysearch] != null){
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
			 					// change turn after successful move
			 					if(turn == 0) turn = 1;
			 					else turn = 0;
			 					System.out.println(", toggle: " + toggle);
			 				}
			 			}
			    		panel.drawing();
			    	} else {
			    		if(black_count == 0){ 
			    			try {
								bw.write("Winner:" + " White");
								bw.newLine();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			    		}
			    		else if(white_count == 0){
			    			try {
								bw.write("Winner:" + " Black");
								bw.newLine();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			    		}
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
}
