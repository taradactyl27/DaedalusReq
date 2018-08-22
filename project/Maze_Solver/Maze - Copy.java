import java.util.Scanner;

public class Maze{
	private String[][] maze;
	private int row, col;
	Scanner input;

	public Maze(){
		input = new Scanner(System.in);
		
		// read in # of rows & cols
		row = intInput("Enter number of rows: ");
		col = intInput("Enter number of columns: ");
		
		System.out.println(row + " " + col);
		// create maze array based on input
		maze = new String[row][col];
		
		userCreateMaze(input);
	}
	
	private int intInput(String question){
		int ans;
		while (true){
			System.out.print(question);
			try {
				ans = input.nextInt();
				
				return ans;
			}
			catch (java.util.InputMismatchException e){
				System.out.println("You can only enter an integer");
			}
			finally {
				input.nextLine();
			}
		}
	}
	
	private void userCreateMaze(Scanner input){ // takes in Scanner object from constructor
	  // give choice to input row x row or as copy paste
		input.nextLine();
		System.out.println("Would you like to enter manually or load from a file?");
		System.out.println("Choose \"manual\" or \"file\"");
		String choice = input.nextLine();
		
		if (choice.equals("manual")){		// enter manually
			System.out.println("Copy and paste your maze: ");
			
			int currRow = 1;
			while (currRow <= row){
			    String mazeRow = input.nextLine();
			    enterArrayRow(currRow - 1, mazeRow);
			    currRow++;
			}
		}
		
		else if (choice.equals("file")){
		    System.out.println("Enter the maze filename: ");
		    
		    String file = input.nextLine();
		    //readFromFile(file);
		}
		
		else {
			throw new IllegalArgumentException("You can only choose \"manual\" or \"file\"");
		}
		
	}
	
	// precondition: entry.length() = col
	private void enterArrayRow(int row, String entry){
	    for (int c = 0; c < col; c++){
	        maze[row][c] = entry.substring(c, c + 1);
	    }
	}
	/*
	public void readFromFile(String filename){
	    try { 
	        Scanner test = new Scanner (new File(filename));   
	    }
	    catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }
        
        
        
        
        int down = 1;
        int across = 0;
        while (test.hasNextLine()){
	        down++;
	        if (test.nextLine().length() > across)
	            across = test.nextLine().length();
        }
        
        Scanner sc = new Scanner (new File(filename));

        for (int row = 0;sc.hasNextLine(); row++){
	        String line = sc.nextLine();
	        //System.out.println(line);
	        char[] arr = line.toCharArray();
	        //System.out.println(arr[0]);
	        int col = 0;
	        for (char i: arr){
	            System.out.println(i);
	            maze[row][col++]=i;
	            System.out.println(col);
	        }
	        //System.out.println();
        }
        System.out.println(sc.hasNextLine());
    }
	*/
	public String toString(){
		String output = "";
		for(String[] r : maze){
			for(String c : r){
				output += (c + "");
			}
			output += "\n";
		}
		return output;
	}
}
