
public class GameLogic {
	
	public GameLogic()
	{
	
	}
	
	public static CellControl[] getSelected()
	{
		CellControl[] x = selected;
		return x;
	}
	
	public static void setSelected(CellControl c)
	{
		
		//System.out.println(c);
		if(c.getCell().getType() != 0)
		{
			if(selected[0] == null)
			{
				selected[0] = c;
				
				selected[0].getCell().setSelected();
				setSelectedNeighbors();
				
			}
			//make sure you can select this cell
			else if(selected[1] == null && getPlayer() == c.getCell().getType()&&c.isNeighbor(selected[0])!=7)
			{
				selected[1] = c;
				c.getCell().setSelected();
				
				if(c.isNeighbor(selected[0])!=7)System.out.println("true"+c.isNeighbor(selected[0]));
				else System.out.println("false");
			}
			else if(selected[2] == null && getPlayer() == c.getCell().getType()
					&& c.isNeighbor(selected[1]) == selected[1].isNeighbor(selected[0]))
			{
				//System.out.println("true"+c.isNeighbor(selected[1]));
				selected[2] = c;
				c.getCell().setSelected();
				
			}
		}	
	}
	
	public static void setTargets()
	{
		//loop through selected array
		for(int i =0; i<selected.length;i++)
		{
			if(selected[i] != null)
			{
				targets[i] = selected[i].getNieghbors()[getMoveDir()];
			}
		}	
	}
	
	public static void fillTargets()
	{
		//loop through selected array
		for(int i =0; i<targets.length;i++)
		{
			//set the targets to fill
			if(targets[i] != null)
			{
				//set the targets to fill
				targets[i].getCell().placePiece();
				
				//set the selected to empty piece
				selected[i].getCell().setType(0);
				
			}
		}
	}
	
	public static void emptyTargets()
	{
		for(int i =0; i<selected.length;i++)
		{
			
				targets[i] = null;
			
		}
	}
	
	public static void placePiece()
	{
		setTargets();
		printArray();
		fillTargets();
		emptySelected();
		emptyTargets();
		togglePlayer();
	}
	
	public static int findMoveDir(CellControl c)
	{
		selected[0].findNeighbors();
		return selected[0].isNeighbor(c);
	}
	
	//emtpy the selected array
	public static void emptySelected()
	{
		//check how many pieces were selected
		for(int i = 0; i<selected.length; i++)
		{
			if(selected[i]!=null)
			{
				selected[i].getCell().unsetSelected();
				selected [i] = null;
			}
			
		}
		
	}
	
	public static void setSelectedNeighbors()
	{
		selectedNeighbours = selected[0].getNieghbors();
	}
	
	public static CellControl[] getSelectedNeighbors()
	{
		return selectedNeighbours;
	}
	
	public static void printArray()
	{
		/*for(int i =0; i<selected.length; i++)
		{
			System.out.println("S:"+selected[i].getBoardX()+":"+selected[i].getBoardX());
		}*/
		
		for(int i =0; i<targets.length; i++)
		{
			if(targets[i]!= null){
				System.out.println("T:"+targets[i].getBoardX()+":"+targets[i].getBoardY());
			}
			
		}
	}
	
	public static int getPlayer(){return currentPlayer;}
	
	public static void togglePlayer()
	{
		Abalone_main.setPlayer(currentPlayer);
		if(currentPlayer == RED){
			Abalone_main.setScore(scoreRed);	
		}
		else if(currentPlayer == BLUE){
			Abalone_main.setScore(scoreBlue);
		}
		
		if(currentPlayer == 1)currentPlayer = 2;
		else if(currentPlayer == 2)currentPlayer = 1;
	}
	
	public static int getMoveDir(){return moveDir;}
	public static void setMoveDir(int x){moveDir = x;}
	
	public static int findOpp(int x)
	{
		if(x>2){return x-3;}
		else{return x+3;}
			
	}
	
	
	
	//variables
	private int gameState = 0;
	private final static int EMPTY = 0; private final static int RED = 1; private final static int BLUE = 2;
	private static CellControl[] selected = {null,null,null};
	private static CellControl[] selectedNeighbours = new CellControl[6];
	private static CellControl[] targets = new CellControl[3];
	private static int currentPlayer = RED;
	private static int selDir = 6;
	private static int moveDir = 6;
	private static int scoreRed;
	private static int scoreBlue;

	

}



