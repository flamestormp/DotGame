package refactor;
/*
Dot class for the Dot object tracks consumption position toggle and team
*/
public class Dot {
	private int ConsumeCount;
	private int Xposition;
	private int Yposition;
	public int Team;
	private boolean toggle = false;

	//team 1 is black team 0 is white
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
	public void addConsumeCount(int consumeCount) {
		ConsumeCount += consumeCount;
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
		return 50*ConsumeCount;
	}
	// mov = (size of map / consume count)
	public int getMov(){
		return 1000/(2*ConsumeCount);
	}
	public boolean getToggle() {
		return toggle;
	}
	public void setToggle(boolean T){
		toggle = T;
	}
}
