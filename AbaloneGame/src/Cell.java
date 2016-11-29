// class definition for an X  or O piece
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Translate;
class Cell extends Group {
// constructor for the class 
	public Cell(int type,int side) {
		//create new hexagon
        hex = new Polygon(new Hexagon(side).getPoints());
        root = new HBox( hex);
        getChildren().addAll(root);
        
	}
      // overridden version of the resize method
@Override
public void resize(double width, double height) { // call the super class method
super.resize(width, height); 

}
      // overridden version of the relocate method
@Override
public void relocate(double x, double y) { }
// private fields of the class

public void placePiece()
{
	this.setOpacity(.5);
}

private int type;
private Translate pos; // translate that set the position of this piece
private Polygon hex;
private HBox root;

// lines for drawing the X piece
      // ellipse for rendering the O piece
// maintain a copy of the piece type we have
}