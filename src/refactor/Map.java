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
	
	Dot[][] getMap(){
		return map;
	}
	
	public Dot selectObj(int x, int y, int turn){
		// just make sure you don't select nonexistent dot
		while(true){
			if(map[x][y] == null || map[x][y].Team == turn){
				return null;
			}
			else{
				break; // kills loop
			}
		}
		return map[x][y];
	}
	
	public void moveObj(int x, int y, Dot dot){
		int mov = dot.getMov();
		//absolute values for absolute differences
		int xdiff = Math.abs(dot.getXposition() - x);
		int ydiff = Math.abs(dot.getYposition() - y);
		
		if(xdiff <= mov && ydiff <= mov){
			map[dot.getXposition()][dot.getYposition()] = map[x][y];
			map[dot.getXposition()][dot.getYposition()] = null;
		
			consume(dot);
		}
	}
	
	private void consume(Dot dot){
		//change the dot counts after loop to avoid changing range
		//while in the process of consumption
		int blacktemp = 0, whitetemp = 0;
		
		//search systematically like a box
		for(int xmin = dot.getXposition()-dot.getRange(); 
				xmin < dot.getXposition()+dot.getRange(); xmin++){
			for(int ymin = dot.getYposition()-dot.getRange();
					ymin < dot.getYposition()+dot.getRange(); ymin++){
				if(map[xmin][ymin] != null){
					if(map[xmin][ymin].Team == 0)
						whitetemp++;
					else
						blacktemp++;
					map[xmin][ymin] = null;
				}
			}
		}
		Runner.black_count -= blacktemp;
		Runner.white_count -= whitetemp;
	}
}
