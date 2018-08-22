import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
public class MazeApp{

    public static void main(String[] args) throws IOException{
	    
	MazeApp app = new MazeApp(new Maze("default.dat"));
	app.run();
				  }
    private Maze maze;
    private String FileAt;
    private MazeDisplay display;
    private String[] buttons;
    private String[] filenames;

    public MazeApp(Maze starter){
	maze = starter;
	FileAt = "default.dat";
	buttons = new String[6];
	File[] FileArray = new File("Mazes").listFiles();
	filenames = new String[FileArray.length];
	for(int i = 0; i < FileArray.length; i++){
	    filenames[i] = FileArray[i].getName();
	}
	buttons[0] = "Prims-Generator";
	buttons[1] = "Solve";
	buttons[2] = "SolveStack";
	buttons[3] = "SolveA*";
	buttons[4] = "SolveQueue";
	buttons[5] = "Reset";
	display = new MazeDisplay("MazeSolve", maze.getRows(), maze.getCols(), buttons,filenames);
    }
    public void removeExtra(Maze maze){
	for(int r = 0; r < maze.getRows(); r++){
	    for(int c = 0; c < maze.getCols(); c++){
		if (maze.maze[r][c].getType().equals("?") || maze.maze[r][c].getType().equals("."))
		    maze.maze[r][c] = new Square(" ");
		    }
	}

    }
    public int countPath(Maze maze){
	int count = 0;
	for(int r = 0; r < maze.getRows(); r++){
	    for(int c = 0; c < maze.getCols(); c++){
		if (maze.maze[r][c].getType().equals("*"))
		    count++;
	    }
	
	}
	return count;
    }
    public void runCommand(String tool) throws IOException{
	if (tool.indexOf("Solve") != -1){
	    Coordinate start = new Coordinate(0,0);
	    Coordinate end = new Coordinate(0,0);
	    for(int r = 0; r < maze.getRows(); r++){
		for(int c = 0; c < maze.getCols(); c++){
		    if (maze.maze[r][c].getType().equals("S")){
			start.setX(r);
			start.setY(c);
		    }
		    if (maze.maze[r][c].getType().equals("E")){
			end.setX(r);
			end.setY(c);
		    }
		}
	    }
	    System.out.println(tool.substring(tool.indexOf("e") + 1));
	    if(tool.substring(tool.indexOf("e") + 1).equals("")){
		MazeSolver.solve(maze,end,start.getX(),start.getY(),display);
	    }
	    if(tool.substring(tool.indexOf("e") + 1).equals("Stack")){
		MazeSolver.solveA(maze,0,start,end,display);
	    }
	    if(tool.substring(tool.indexOf("e") + 1).equals("Queue")){
		MazeSolver.solveA(maze,1,start,end,display);
	    }
	    if(tool.substring(tool.indexOf("e") + 1).equals("A*")){
		MazeSolver.solveA(maze,3,start,end,display);
	    }
	    removeExtra(maze);
	    maze.maze[start.getX()][start.getY()] = new Square("S");
	    maze.maze[end.getX()][end.getY()] = new Square("E");
	    System.out.println(countPath(maze));
	}
	
	if(tool.equals("Reset")){
	    maze = new Maze("Mazes/" + FileAt);
	}
	if(tool.equals("Prims-Generator")){
	    String dims = display.getDims();
	    int rows = Integer.parseInt(dims.substring(0,dims.indexOf(",")));
	    int cols = Integer.parseInt(dims.substring(dims.indexOf(",") + 1));
	    PrimsGenerator prim = new PrimsGenerator(rows,cols);
	    System.out.println("DONE");
	}
	if(tool.indexOf(".dat") > 0){
	    maze = new Maze("Mazes/" + tool);
	    FileAt = tool;
	    display.cellSize = Math.max(1, 600 / Math.max(maze.getRows(),maze.getCols()));
	    display.numRows = maze.getRows();
	    display.numCols = maze.getCols();
	    display.setPreferredSize(new Dimension(display.numCols * display.cellSize, display.numRows * display.cellSize));
	    display.image = new BufferedImage(display.numCols * display.cellSize, display.numRows * display.cellSize, BufferedImage.TYPE_INT_RGB);
	}
    }

    

    public void run() throws IOException{
	while (true){
	    display.updateDisplay(maze);
	    display.repaint();
	    display.pause(1);
	    if (!display.getOperation().equals("Nothing")){
		runCommand(display.getOperation());
		display.setOperation("Nothing");
	    }
	}
    }
}
