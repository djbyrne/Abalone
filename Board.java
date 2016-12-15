// an implementation of the XO board and the game logic
// imports necessary for this class
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle; 
import javafx.scene.transform.Translate;
// class definition for drawing a game board
class Board extends Pane {
// constructor for the class 
	public Board() {
		// initialise the boards 
		
		board = new int[][]{
		//	 0 1 2 3 4 5 6 7 8
			{3,3,3,3,3,3,3,3,3,3,3},
		    {3,3,3,3,3,0,3,3,3,3,3}, //0
		    {3,3,3,0,0,0,0,0,3,3,3}, //1
		    {3,1,1,0,0,0,0,0,2,2,3}, //2
		    {3,1,1,1,0,0,0,2,2,2,3}, //3
		    {3,1,1,1,0,0,0,2,2,2,3}, //4
		    {3,1,1,1,0,0,0,2,2,2,3}, //5
		    {3,1,1,0,0,0,0,0,2,2,3}, //6
		    {3,3,1,0,0,0,0,0,2,3,3}, //7
		    {3,3,3,3,0,0,0,3,3,3,3},
		    {3,3,3,3,3,3,3,3,3,3,3}//8*/
		  };
			   
		renders = new CellControl[11][11]; 
		
		
		
		
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 11; j++) { 
				//board[i][j] = EMPTY;
				renders[i][j] = new CellControl(i,j,board[i][j]); 
				
				if(board[i][j]!=3)
				{
					if(j %2 != 0)
					{
						renders[i][j].setLayoutX((i*50)+25);
					}
					else
					{
						renders[i][j].setLayoutX(i*(50));
					}
					
					renders[i][j].setLayoutY(j*(40));
					getChildren().addAll(renders[i][j]);
				}
				//startingPositions(i,j);
				
			}				
		}
		
		current_player = BLACK;
	}
// we have to override resizing behaviour to make our view appear properly 
@Override
public void resize(double width, double height) {
            // call the superclass method first
	super.resize(width, height); 
	cell_width = width / 9.0;
    cell_height = height / 9.0;
}

public static CellControl getCell(int x, int y)
{
	return renders[x][y];
}

public static void setCell(int x, int y, int t)
{
	board[x][y] = t;
	renders[x][y].getCell().setType(t);
	
	//change the opacity to 1
	renders[x][y].getCell().setOpacity(1);
}


      // public method for resetting the game
public void resetGame() {
}


      // public method that tries to place a piece
public void placePiece(final double x, final double y) {
	
}
// private fields of the class
private static int[][] board; // array that holds all pieces
private static CellControl[][] renders; // array that holds all the render pieces
private int[][] redPieces;
private int[][] bluePieces;
private Rectangle back; // background of the board
private Line h1, h2, v1, v2; // horizontal and vertical grid lines 
private double cell_width, cell_height; 
// width and height of a cell
// translation of {one, two} cell {width, height}
private Translate ch_one, ch_two, cw_one, cw_two;
private int current_player;
private int rowCounter,cellCounter,maxCounter;


      // constants for the class
private final int EMPTY = 0; private final int BLACK = 1; private final int WHITE = 2;
}