
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
				System.out.println(targets[i].getCell().getType());
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
		int checkMove = checkTypeOfMove();
		setTargets();
		//printArray();

		//check if user is trying to move sideways
		if(checkMove == 1 )fillSideMove();
		else if(checkMove == 0)fillTargets();
	}

	public static int findMoveDir(CellControl c)
	{
		selected[0].findNeighbors();
		return selected[0].isNeighbor(c);
	}

	//find out if it is a straight move or a side move
	// 1 = side move
	// 0 = straight move
	public static int checkTypeOfMove()
	{
		int direction = 6;
		int moveType = 2;
		System.out.println("move direction: "+moveDir);
		
		//check how many are selected
		if(selected[2] != null)
		{
			direction = selected[1].isNeighbor(selected[2]);
			System.out.println("direction: "+direction);
			direction = findOpp(direction);
			System.out.println("opposite direction: "+direction);
			
			if(direction == moveDir) moveType = 0;
			else{moveType = 1;}
		}
		else if(selected[1] != null)
		{
			direction = selected[0].isNeighbor(selected[1]);
			System.out.println("direction: "+direction);
			direction = findOpp(direction);
			System.out.println("opposite direction: "+direction);
			if(direction == moveDir) moveType = 0;
			else{moveType = 1;}
		}
		else moveType = 0;
		
		System.out.println("move type: "+moveType);
		return moveType;
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

	public static void fillTargets()
	{
		
		System.out.println("fill targets");
		//loop through selected array
		for(int i =0; i<targets.length;i++)
		{
			//check if it is a legal move
			//set the targets to fill
			if(targets[i] != null)
			{
				//set the targets to fill
				targets[i].getCell().placePiece();

				//set the selected to empty piece
				selected[i].getCell().setType(0);

				//unselect the last piece that was used
				selected[i].getCell().unsetSelected();
				selected [i] = null;
			}
	
		}
		emptyTargets();
		togglePlayer();
	}

	public static  void fillSideMove()
	{
		

		System.out.println("fill side move");
		if(checkSideMove())
		{
			System.out.println("it is a side move!");
			//loop through selected array
			for(int i =0; i<targets.length;i++)
			{
				//check if it is a legal move
				//set the targets to fill
				if(targets[i] != null && targets[i].getCell().getType() == 0)
				{
					//set the targets to fill
					targets[i].getCell().placePiece();

					//set the selected to empty piece
					selected[i].getCell().setType(0);

					//unselect the last piece that was used
					selected[i].getCell().unsetSelected();
					selected [i] = null;
				}
			}
			emptyTargets();
			togglePlayer();
		}
	}



	//checks if the targets neighbors are free
	public static boolean checkSideMove()
	{
		
		boolean valid = false;

		for(int i =0; i<targets.length;i++)
		{
			
			if(targets[i] != null && targets[i].getCell().getType() == 0)
				valid = true;
			else if(targets[i] != null && targets[i].getCell().getType() != 0)
			{
				System.out.println("check side: "+valid);
				return false;
			}
			else if(targets[i] == null)valid = true;
		}
		
		return valid;
	}


	public static int getPlayer(){return currentPlayer;}

	public static void togglePlayer()
	{
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
	private final static int EMPTY = 0; private final static int BLACK = 1; private final static int WHITE = 2;
	private static CellControl[] selected = {null,null,null};
	private static CellControl[] selectedNeighbours = new CellControl[6];
	private static CellControl[] targets = new CellControl[3];
	private static CellControl[] targetNeighbours = new CellControl[6];
	private static int[] tempSelected = new int[3];
	private static int[] tempTarget = new int[3];
	private static int currentPlayer = BLACK;
	private static int selDir = 6;
	private static int moveDir = 6;



}



