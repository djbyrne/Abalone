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
		board = new int[9][9]; 
		renders = new CellControl[9][9]; 
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++) { 
				board[i][j] = EMPTY;
				renders[i][j] = new CellControl(i,j); 
				
					//set the position of renders[i][j]
					//indent every odd row
					if(j==0 && i<2 || j==0 && i>6 )
					{
						//do nothing
					}
					else if(j==1 && i<2 || j==1 && i>7 )
					{
						//do nothing
					}
					else if(j==2 && i<1 || j==2 && i>7 )
					{
						//do nothing
					}
					else if(j==3 && i<1 || j==3 && i>8 )
					{
						//do nothing
					}
					else if(j==5 && i<1 || j==5 && i>8 )
					{
						//do nothing
					}
					else if(j==6 && i<1 || j==6 && i>7 )
					{
						//do nothing
					}
					else if(j==7 && i<2 || j==7 && i>7 )
					{
						//do nothing
					}
					else if(j==8 && i<2 || j==8 && i>6 )
					{
						//do nothing
					}
					else
					{
						if(j %2 == 0)
						{
							renders[i][j].setLayoutX((i*38.88)+19.44);
						}
						else
						{
							renders[i][j].setLayoutX(i*(38.88));
						}
						
						renders[i][j].setLayoutY(j*(17));
						getChildren().addAll(renders[i][j]);
					}					
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

      // public method for resetting the game
public void resetGame() {
}

public void assignNeighbours()
{
	
}
      // public method that tries to place a piece
public void placePiece(final double x, final double y) {
	// translate the x, y coordinates into cell indexes
	int indexx = (int) (x / cell_width); 
	int indexy = (int) (y/cell_height);
	
	
	
	if(indexy%2==0)
	{
		System.out.println("even line");
		indexx = (int) (x - 17);
		indexx = (int) (indexx / cell_width);
	}
	else
	{
		System.out.println("odd line");
		
	}
	
	renders[indexx][indexy].setOpacity(.5);
}
// private fields of the class
private int[][] board; // array that holds all pieces
private CellControl[][] renders; // array that holds all the render pieces
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