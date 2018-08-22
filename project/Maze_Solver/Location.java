public class Location implements Comparable<Location>{
    private int row,col,goalDistance,startDistance;
    private Location previous;
    private boolean aStar;
    
    public Location(int r, int c, Location previous, int distToStart, int distToGoal, boolean aStar){
	row = r;
	col = c;
	this.previous = previous;
	startDistance = distToStart;
	goalDistance = distToGoal;
	this.aStar = aStar;
    }
    
    public int compareTo(Location loc){
	if(aStar) return goalDistance+startDistance-(loc.goalDistance+loc.startDistance);
	else return goalDistance-loc.goalDistance;
    }
    
    public int getRow(){return row;}
    public int getCol(){return col;}
    public Location getPrevious(){return previous;}
    public int getDistanceToGoal(){return goalDistance;}
    public int getDistanceToStart(){return startDistance;}
}
