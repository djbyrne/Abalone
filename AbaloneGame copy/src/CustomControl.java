// implementation of a custom control for javafx
// imports for the class
import javafx.event.EventHandler; 
import javafx.scene.control.Control; 
import javafx.scene.input.KeyCode; 
import javafx.scene.input.KeyEvent; 
import javafx.scene.input.MouseEvent;

// class definition for a custom control
class CustomControl extends Control { // constructor for the class 
	public CustomControl() {
		// set a default skin and generate a game board
		setSkin(new CustomControlSkin(this)); 
		board = new Board(); 
		getChildren().add(board);
	
	
	setOnMouseClicked(new EventHandler<MouseEvent>(){
		
		@Override
		public void handle(MouseEvent event)
		{
			//board.placePiece(event.getX(), event.getY());
			//System.out.println(event.getX()+":"+ event.getY());
		}
	});
}
      // override the resize method
@Override
public void resize(double width, double height) {
	// update the size of the rectangle
	super.resize(width, height); 
	board.resize(width, height);
}
      // private fields of the class
private Board board; 
}