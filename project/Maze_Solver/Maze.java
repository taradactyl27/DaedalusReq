import java.util.Scanner;
import java.io.*;

public class Maze{
	public Square[][] maze;
	private int row, col;

	// CONSTRUCTORS
	
	// default constructor
	// 10 x 10 maze
	public Maze(){
		row = col = 10;
		maze = new Square[row][col];
	}
	
	public Maze(String manualMaze, int i){	// arbitrary int parameter to separate manual from file constructor
		row = findNumRows(manualMaze);
		col = findNumCols(manualMaze);
		// System.out.println(row + " " + col);
		
		maze = new Square[row][col];
		populateMaze(manualMaze);
	}
	
	public Maze(String filename){	// creates maze by loading from file
		String mazeContent = readFile(filename);
		row = findNumRows(mazeContent);
		col = findNumCols(mazeContent);
		// System.out.println(row + " " + col);
		
		maze = new Square[row][col];
		populateMaze(mazeContent);
	}
	
	// returns contents from file as string
	private static String readFile(String filename){
		String content = "";
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new File(filename));
			while (fileScanner.hasNextLine()){
				content += fileScanner.nextLine() + "\n";
			}
			content = content.substring(0, content.length() - 1);	// remove final \n
			fileScanner.close();
			return content;
		}
		catch (FileNotFoundException e){
			System.out.println("No such file found");
		}
		return "";
	}
	
	// returns # of rows in a maze string
	private int findNumRows(String m){
		int numNewlines = 1; // starts at 1 to account for first row
		while (m.indexOf("\n") != -1){
			numNewlines++;
			m = m.substring(m.indexOf("\n") + 1);
		}
		return numNewlines;
	}
	
	// returns # of columns in a maze string
	private int findNumCols(String m){	// assumes rectangular maze, not jagged
		int newlinePos = m.indexOf("\n");	// \n means end of column
		return newlinePos;
	}
	
	// fills maze array with Squares based on String content
	private void populateMaze(String content){
		int contentPos = 0;
		for (int r = 0; r < row; r++){
			for (int c = 0; c < col; c++){
				maze[r][c] = new Square(content.substring(contentPos, contentPos + 1));
				contentPos++;
			}
			contentPos++; // to skip newline character
		}
	}
	
	// ACCESSOR METHODS
	public int getRows(){
		return row;
	}
	
	public int getCols(){
		return col;
	}
	
	// returns Square at location [row][col]
	public Square getSquare(int row, int col){
		return maze[row][col];
	}
	
	// returns type of Square at location [row][col]
	public String getSquareType(int row, int col){
		return getSquare(row, col).getType();
	}
	
	// returns color of Square at location [row][col]
	public int[] getSquareColor(int row, int col){
		return getSquare(row, col).getColor();
	}
	
	public String toString(){
		String output = "";
		for (Square[] row : maze){
			for (Square s : row){
				output += s.getType();
			}
			output += "\n";
		}
		output = output.substring(0, output.length() - 1);	// remove final \n
		return output;
	}
}
