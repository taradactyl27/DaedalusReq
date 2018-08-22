public class MazeSolver{

    
    public static boolean solve(Maze maze,Coordinate ending, int x, int y, MazeDisplay display){
	maze.maze[x][y] = new Square("*");
	if(display.check.isSelected()){
	    display.updateDisplay(maze);
	    display.pause(display.getSpeed());
	    display.repaint();
	}
	
	if (x == ending.getX() && y == ending.getY()){
	    return true;
	}
	if (y > 0 && (maze.maze[x][y - 1].getType().equals(" ") || maze.maze[x][y - 1].getType().equals("E")) && solve(maze,ending,x,y - 1,display))
    {
        return true;
    }
	if (y < maze.getCols() && (maze.maze[x][y + 1].getType().equals(" ") || maze.maze[x][y + 1].getType().equals("E")) && solve(maze,ending,x, y + 1,display))
	    {
		return true;
	    }
	if (x > 0 && (maze.maze[x - 1][y].getType().equals(" ")|| maze.maze[x - 1][y].getType().equals("E")) && solve(maze,ending,x - 1,y,display))
	    {
		return true;
	    }
	if (x < maze.getRows() && (maze.maze[x + 1][y].getType().equals(" ") || maze.maze[x + 1][y].getType().equals("E")) && solve(maze,ending,x + 1, y,display))
	    {
		return true;
	    }
	maze.maze[x][y] = new Square(" ");
	if(display.check.isSelected()){
	    display.updateDisplay(maze);
	    display.pause(display.getSpeed());
	    display.repaint();
	}
	
	return false;
    }
    public static void solveA(Maze maze,int solveStyle, Coordinate starting, Coordinate ending,MazeDisplay display){
	Agenda locations;
	boolean aStar = false;
	if(solveStyle == 0) locations = new MyStack();
	else if(solveStyle == 1) locations = new MyQueue();
	else locations = new MyPriorityQueue();
	if(solveStyle == 3) aStar = true;
	Location start = new Location(starting.getX(),starting.getY(),null,0,(Math.abs(ending.getX() - starting.getX()) + Math.abs(starting.getY() - ending.getY())),false);
	Location end = new Location(ending.getX(),ending.getY(),null,0,0,false);
	locations.add(start);
	Location current = start;
	
	while(current != end && locations.size() != 0){
	    current = locations.next();
	    if(current.getDistanceToGoal() == 0){
		while(current != null){
		    maze.maze[current.getRow()][current.getCol()] = new Square("*");
		    if(display.check.isSelected()){
				display.updateDisplay(maze);
				display.pause(display.getSpeed());
				display.repaint(); 
		    }
		    current = current.getPrevious();
		}
		break;
	    }
	    else{
		maze.maze[current.getRow()][current.getCol()] = new Square(".");
		if(display.check.isSelected()){
		    display.updateDisplay(maze);
		    display.pause(display.getSpeed());
		    display.repaint();
		}
		int[][] moves = {{1,0},{0,1},{-1,0},{0,-1}};
		for(int i=0; i<moves.length; i++){
		    int r = current.getRow() + moves[i][0];
		    int c = current.getCol() + moves[i][1];
		    if(maze.maze[r][c].getType().equals(" ") || maze.maze[r][c].getType().equals("E") ){
			int distToStart = Math.abs(r-starting.getX()) + Math.abs(c-starting.getY());
			       int distToGoal = Math.abs(r-ending.getX()) + Math.abs(c-ending.getY());
			       locations.add(new Location(r, c, current, distToStart, distToGoal, aStar));
			       maze.maze[r][c] = new Square("?");
			       
		    }
		}
	    }
	    // System.out.println(locations.size());
	}
    }
}


