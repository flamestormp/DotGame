package refactor;
/*
 * Contains the array of dots and methods to deal with dots
 */
public class Map {
	
	private Dot[][] map = new Dot[1000][1000];
	
	public Map(){
		for(int y = 100; y <= 200; y+=100){
			for(int x = 100; x < 1000; x += 100){
				map[x][y] = new Dot(1,x,y,0);
			}
		}
		for(int y = 900; y >= 800; y-=100){
			for(int x = 100; x < 1000; x += 100){
				map[x][y] = new Dot(1,x,y,1);
			}
		}
	}
	
	public Dot[][] getMap(){
		return map;
	}
	
	public Dot selectObj(int x, int y, int turn){
		// just make sure you don't select nonexistent dot
		while(true){
			if(map[x][y] == null || map[x][y].Team != turn){
				System.out.print("error");
				return null;
			}
			else{
				System.out.print("in the green");
				break; // kills loop
			}
		}
		return map[x][y];
	}
	
	//function moves dot, consumes, and returns a success/fail message 
	//in form of boolean
	public boolean moveObj(int x, int y, Dot dot){
		if(dot != null){
			int mov = dot.getMov();
			//absolute values for absolute differences
			int xdiff = Math.abs(dot.getXposition() - x);
			int ydiff = Math.abs(dot.getYposition() - y);
		
			if(xdiff <= mov && ydiff <= mov){
				map[x][y] = dot;
				map[dot.getXposition()][dot.getYposition()] = null;
				dot.setXposition(x);
				dot.setYposition(y);
				consume(dot);
				return true;
			}
			return false;
		}
		return false;
	}
	
	//misleading name. dot is not consumed, but the dots around it within range.
	private void consume(Dot dot){
		int blacktemp = 0, whitetemp = 0;
		int consumeCountPool = 0;
		int xpos = dot.getXposition(), ypos = dot.getYposition();
		
		//following code block is meant to prevent array going out of bounds
		int xmin = xpos-dot.getRange();
		int xmax = xpos+dot.getRange();
		int ymin = ypos-dot.getRange();
		int ymax = ypos+dot.getRange();
		if(xmin < 0) xmin = 0;
		if(xmax > 999) xmax = 999;
		if(ymin < 0) ymin = 0;
		if(ymax > 999) ymax = 999;
		
		//search systematically like a box
		for(int xsearch = xmin; xsearch < xmax; xsearch++){
			for(int ysearch = ymin; ysearch < ymax; ysearch++){
				//check for two things: 
				//1. if array value is empty (can't operate on empty)
				//2. determine if coordinate is the same (can't consume yourself)
				if((map[xsearch][ysearch] != null) && 
						(xsearch != xpos && ysearch != ypos)){
					
					if(map[xsearch][ysearch].Team == 0){
						whitetemp++;
					}
					else{
						blacktemp++;
					}
					consumeCountPool += map[xsearch][ysearch].getConsumeCount();
					map[xsearch][ysearch] = null;
				}
			}
		}
		
		dot.addConsumeCount(consumeCountPool);
		Runner.black_count -= blacktemp;
		Runner.white_count -= whitetemp;
	}
}
