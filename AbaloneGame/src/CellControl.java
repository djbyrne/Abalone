// implementation of a custom control for javafx
// imports for the class
import javafx.event.EventHandler; 
import javafx.scene.control.Control; 
import javafx.scene.input.KeyCode; 
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

// class definition for a custom control
class CellControl extends Control { // constructor for the class 
	public CellControl(int x, int y,int t) {
		// set a default skin and generate a game board
		setSkin(new CellSkin(this));
		cell = new Cell(t,10); 
		getChildren().add(cell);
		boardX = x;
		boardY = y;
		
		//initialising the directions array to values 0-5
        directions = new int[6][2];
        // hard coded even directions
        evenDirections = new int[][]{ {0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,0}};
        // hard coded odd directions
        oddDirections = new int[][]{ {-1,-1},{0,-1},{1,0},{0,1},{-1,1},{-1,0}};
        //emtpy neighbor array
        neighbors = new CellControl[6];
        
        // checking if x is even or odd
        if(y%2 == 0){
        	directions = evenDirections;
        }
        else {
        	directions = oddDirections;
        }
	
	
	setOnMouseClicked(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event)
		{
			
			
			//System.out.println(Board.getCell(boardX, boardY).getCell().getType());
			//check if piece is selected
			//right click
			if (((MouseEvent) event).getButton().equals(MouseButton.SECONDARY))
			{
				rightClick();
				
			}
			else{
				CellControl[] selected = GameLogic.getSelected();
				CellControl first = selected[0];
				if(selected[0] != null && cell.getType() == 0)
				{
					//delete selected piece
					Board.setCell(first.getBoardX(), first.getBoardY(), 0);
					GameLogic.emptySelected();
					
					//check if this piece is a neighbor of the currently selected piece
					CellControl[] selectedCells = GameLogic.getSelected();
					/*if(isNeighbor(selectedCells[0]))
					{
						//place new piece
						cell.placePiece();
					}
					else
						System.out.println("not a neighbor of selected piece");	*/
					
					cell.placePiece();
					
				}
				else
				{
					System.out.println("no piece selected");
				}
				//cell.placePiece();
			}
			//System.out.println("x:"+boardX+" y:"+boardY);	
		}
	});
}
      // override the resize method
@Override
public void resize(double width, double height) {
	// update the size of the rectangle
	super.resize(width, height); 
	cell.resize(width, height);
}

//populates neighbor array
public void findNeighbors()
{
	CellControl cc;
	for(int i=0; i<directions.length;i++)
	{
		cc = Board.getCell(boardX+directions[i][0], boardY+directions[i][1]);
		neighbors[i] = cc;
	}
	
	//highlight all neighbours
	/*for(int i=0;i<neighbors.length;i++)
	{
		neighbors[i].setOpacity(.5);
	}*/
	
}

//method to add the direction coordinates
/*public boolean checkNeighbour(CellControl c1){
	CellControl[] c2 = GameLogic.getSelected();
	if
}*/

//check if a cell is a neighbour of this cell
public boolean isNeighbor(CellControl c)
{
	//loop through array and check if c is contained in the array
	CellControl cellIt;
	for(int i=0;i<neighbors.length;i++)
	{
		cellIt = neighbors[i];
		if(cellIt.getBoardX() == c.getBoardX() &&cellIt.getBoardY() == c.getBoardY() )
			return true;
	}
	return false;
}

public void rightClick()
{
	//check if the cell clicked is the correct player
	if(GameLogic.getPlayer() == cell.getType())GameLogic.setSelected(this);
	else System.out.println("not your turn");
}

public Cell getCell()
{
	return cell;
}

public int getBoardX(){return boardX;}
public int getBoardY(){return boardY;}


      // private fields of the class
private Cell cell;
private int boardX;
private int boardY;
private int[][] directions;
private int[][] evenDirections;
private int[][] oddDirections;
private CellControl[] neighbors;

}