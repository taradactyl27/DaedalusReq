import java.util.LinkedList;
import java.util.Random;

import java.io.PrintWriter;
import java.io.IOException;

public class pg {
    public static final char PASSAGE_CHAR = ' ';
    public static final char WALL_CHAR = '#';
    public static final boolean WALL    = false;
    public static final boolean PASSAGE = !WALL;

    private final boolean map[][];
    private final int width;
    private final int height;

    public pg( final int width, final int height ) throws IOException{
        this.width = width;
        this.height = height;
        this.map = new boolean[width][height];

        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        frontiers.add(new int[]{x,y,x,y});

        while ( !frontiers.isEmpty() ){
            final int[] f = frontiers.remove( random.nextInt( frontiers.size() ) );
            x = f[2];
            y = f[3];
            if ( map[x][y] == WALL )
            {
                map[f[0]][f[1]] = map[x][y] = PASSAGE;
                if ( x >= 2 && map[x-2][y] == WALL )
                    frontiers.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && map[x][y-2] == WALL )
                    frontiers.add( new int[]{x,y-1,x,y-2} );
                if ( x < width-2 && map[x+2][y] == WALL )
                    frontiers.add( new int[]{x+1,y,x+2,y} );
                if ( y < height-2 && map[x][y+2] == WALL )
                    frontiers.add( new int[]{x,y+1,x,y+2} );
            }
        }
				writeToFile();
    }

    //Override
    public String toString(){
        final StringBuffer b = new StringBuffer();
        for ( int x = 0; x < width + 2; x++ )
            b.append( WALL_CHAR );
        b.append( '\n' );
        for ( int y = 0; y < height; y++ ){
            b.append( WALL_CHAR );
            for ( int x = 0; x < width; x++ )
                b.append( map[x][y] == WALL ? WALL_CHAR : PASSAGE_CHAR );
            b.append( WALL_CHAR );
            b.append( '\n' );
        }
        for ( int x = 0; x < width + 2; x++ )
            b.append( WALL_CHAR );
        b.append( '\n' );
        return b.toString();
    }
		
			private void writeToFile() throws IOException{
		try {
				PrintWriter writer = new PrintWriter("Mazes/generatedfile.dat", "UTF-8");
				writer.println(this.toString());
				writer.close();
		}
		catch (IOException e){
			throw new IOException("unable to write to file");
		}
	}
		public static void main(String[] args) throws IOException{
			pg p = new pg(1000,1000);
			
		}
}
