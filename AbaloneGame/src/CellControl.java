// implementation of a custom control for javafx
// imports for the class
import javafx.event.EventHandler; 
import javafx.scene.control.Control; 
import javafx.scene.input.KeyCode; 
import javafx.scene.input.KeyEvent; 
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
	
	
	setOnMouseClicked(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event)
		{
			cell.placePiece();
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

public Cell getCell()
{
	return cell;
}


      // private fields of the class
private Cell cell;
private int boardX;
private int boardY;
}