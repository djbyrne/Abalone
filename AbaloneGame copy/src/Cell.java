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
	public Cell(int t,int side) {
		//create new hexagon
		super();
        hex = new Polygon(new Hexagon(side).getPoints());
        root = new HBox( hex);
        type = t;
        getChildren().addAll(root);
        if(type == 1)hex.setFill(Color.RED);
        else if(type == 2)hex.setFill(Color.BLUE);
        else hex.setFill(Color.BLACK);
        	
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
	
	setType(GameLogic.getPlayer());
}

public void setSelected()
{
	this.setOpacity(.5);
}

public void unsetSelected()
{
	this.setOpacity(1);
}

public void setType(int x)
{
	type = x;
	if(type == 1)hex.setFill(Color.RED);
    else if(type == 2)hex.setFill(Color.BLUE);
    else hex.setFill(Color.BLACK);
}

public int getType()
{
	return type;
}

private int type;
private Translate pos; // translate that set the position of this piece
private Polygon hex;
private HBox root;
private CellControl cellControl;
}