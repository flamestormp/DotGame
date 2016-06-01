package refactor;

/*
 * Class is for Dot. A dot can be on one of two teams.
 * A dot has a position on an x,y grid. A dot has a count
 * of the # of other dots consumed. A dot also has range
 * of movement and range of consumption.
 */

public class Dot {
public int ConsumeCount;
public int Xposition;
public int Yposition;
public int Team;

final int RANGE = 50;

	public Dot(int consumeCount, int xposition, int yposition, int team) {
	super();
	ConsumeCount = consumeCount;
	Xposition = xposition;
	Yposition = yposition;
	Team = team;
}
	public int getConsumeCount() {
		return ConsumeCount;
	}
	public void setConsumeCount(int consumeCount) {
		ConsumeCount = consumeCount;
	}
	public int getXposition() {
		return Xposition;
	}
	public void setXposition(int xposition) {
		Xposition = xposition;
	}
	public int getYposition() {
		return Yposition;
	}
	public void setYposition(int yposition) {
		Yposition = yposition;
	}

	// range = size of map - mov
	public int getRange(){
		return 1000 - getMov();
	}
	
	// mov = size of map / consume count
	public int getMov(){
		return 1000/ConsumeCount;
	}
	
}
