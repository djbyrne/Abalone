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
        if(y%2 != 0){
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
				if(neighbors[0] == null){findNeighbors();}
				rightClick();
				
			}
			//left click
			else{
				
				//check if its an odd or even cell
				if (boardY%2 != 0){
					//System.out.println("even");
			    }
			    else {
			    	//System.out.println("odd");
			    }
				//check if it is an opponent cell
				if(getCell().getType() != GameLogic.getPlayer() && getCell().getType() !=0 )
				{
					//pushing opponents piece
					//check if it is a sideways move
					if(GameLogic.checkTypeOfMove() == 1)push();
					else if(GameLogic.checkTypeOfMove() == 0)System.out.println("CAN not push sideways");
				}
				else
				{
					//check if this is already selected
					if(isSelected)
					{
						//empty the selected array
						isSelected = false;
						GameLogic.emptySelected();
						GameLogic.emptyTargets();
					}
					else
					{
						isSelected= true;
						if(neighbors[0] == null){findNeighbors();}
						CellControl[] selected = GameLogic.getSelected();
						CellControl first = selected[0];
						
						//check if the piece is empty
						if(selected[0] != null && cell.getType() == 0)
						{
							//check if this piece is a neighbor of the currently selected piece
							CellControl[] selectedCells = GameLogic.getSelected();
							placePiece(selected);
						}
						else
						{
							System.out.println("no piece selected");
						}
						
					}
				}
				
				
			}
			
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

public void push()
{
	//print push
	System.out.println("push");
	GameLogic.setMoveDir(GameLogic.findMoveDir(this));
	
	//highlight the cells behind the pushed cell
	
	//get selected weight
	System.out.println("weight = "+GameLogic.getSelectedWeight());
	
	//find weight of the toPushArray
	System.out.println("push weight = "+GameLogic.setPushPieces(this));
	
	
	//compare the weights
	if(GameLogic.getSelectedWeight() > GameLogic.setPushPieces(this))
	{
		//System.out.println("push succesful");
		
		GameLogic.setPushSelected();
	}
}

//populates neighbor array
public void findNeighbors()
{
	//System.out.println("ping");
	CellControl cc;
	for(int i=0; i<directions.length;i++)
	{
		cc = Board.getCell(boardX+directions[i][0], boardY+directions[i][1]);
		neighbors[i] = cc;
	}
}

public void placePiece(CellControl[] selected){
	//set the move direction
	GameLogic.setMoveDir(GameLogic.findMoveDir(this));
	System.out.println("set move direction to"+GameLogic.findMoveDir(this));
	boolean place = false;
	CellControl[] n = GameLogic.getSelectedNeighbors();
	
	
	for(int i=0;i<n.length;i++)
	{
		if(n[i].getBoardX() == this.getBoardX() && n[i].getBoardY() == this.getBoardY())
		{
			place = true;
		}
	}
	
	if(place)
	{
		GameLogic.placePiece();
	}
	else
	{
		System.out.println("invalid move");
	}
	
}

public CellControl[] getNieghbors()
{
	findNeighbors();
	return neighbors;
}

//check if a cell is a neighbour of this cell and returns the direction
public int isNeighbor(CellControl c)
{
	//loop through array and check if c is contained in the array
	CellControl cellIt;
	for(int i=0;i<neighbors.length;i++)
	{
		cellIt = neighbors[i];
		if(cellIt.getBoardX() == c.getBoardX() &&cellIt.getBoardY() == c.getBoardY() )
			return i;
	}
	return 6; //6 means that it is not a neighbor
}

public CellControl getNeighbor(int direction)
{
	
	return neighbors[direction];
}

public void rightClick()
{
	//check if the cell clicked is the correct player
	findNeighbors();
	if(GameLogic.getPlayer() == cell.getType())
	{
		GameLogic.setSelected(this);
		isSelected = true;
	}
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
private boolean isSelected;

}