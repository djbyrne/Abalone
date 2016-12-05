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
        directions = new int[9][9];

       
        // hard coded even directions
        evenDirections = new int[][]{ {0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,0}};
        // hard coded odd directions
        oddDirections = new int[][]{ {-1,-1},{0,-1},{1,0},{0,1},{-1,1},{-1,0}};
        
        // checking if x is even or odd
        if(x%2 == 0){
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
			CellControl[] selected = GameLogic.getSelected();
			CellControl first = selected[0];
			if(selected[0] != null && cell.getType() == 0)
			{
				//delete selected piece
				Board.setCell(first.getBoardX(), first.getBoardY(), 0);
				GameLogic.emptySelected();
				
				//place new piece
				cell.placePiece();
			}
			else
			{
				System.out.println("no piece selected");
			}
			//cell.placePiece();
			
			
			
			//right click
			if (((MouseEvent) event).getButton().equals(MouseButton.SECONDARY))
				rightClick();
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

//method to add the direction coordinates
public int[] checkNeighbour(int[] n){
	int x2 = boardX  + n[0];
	int y2 = boardY + n[1];
	
	int[] neighbour = new int[]{x2,y2};
	return neighbour;
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

}