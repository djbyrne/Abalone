import java.util.ArrayList;

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
				//System.out.println(targets[i].getCell().getType());
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
		//System.out.println("move direction: "+moveDir);
		
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
			System.out.println("move direction: "+moveDir);
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
				//System.out.println("T:"+targets[i].getBoardX()+":"+targets[i].getBoardY());
			}

		}
	}

	public static void fillTargets()
	{
		
		//System.out.println("fill targets");
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
		

		//System.out.println("fill side move");
		if(checkSideMove())
		{
			//System.out.println("it is a side move!");
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
				//System.out.println("check side: "+valid);
				return false;
			}
			else if(targets[i] == null)valid = true;
		}
		
		return valid;
	}


	public static int getPlayer(){return currentPlayer;}

	public static int getMoveDir(){return moveDir;}
	public static void setMoveDir(int x){moveDir = x;}

	public static int findOpp(int x)
	{
		if(x>2){return x-3;}
		else{return x+3;}

	}
	
	public static int getSelectedWeight()
	{
		int weight = 0;
		for(int i = 0; i<selected.length;i++)
		{
			if(selected[i] != null)
			{
				weight++;
			}
		}
		
		return weight;
	}
	
	public static int setPushPieces(CellControl c)
	{
		//set index 0
		System.out.println("set push pieces started");
		int weight = 0;
	    pushPieces[0]= c;
	    weight++;
	    pushPieces[0].getCell().setSelected();
	    
	    //System.out.println(c.getNeighbor(findOpp(moveDir)).getCell().getType());
	    //System.out.println(c.getCell().getType());
	    //System.out.println("cell type: "+pushPieces[0].getCell().getType());
	    //System.out.println("cell neighbor type: "+pushPieces[0].getNeighbor(moveDir).getCell().getType());
	    
		    //find the next cell in the move direction for index 1
		    if(pushPieces[0] != null && pushPieces[0].getNeighbor(moveDir).getCell().getType() == getOppent())
		    {
		    	System.out.println("piece added to pos 1");
		    	pushPieces[1] = c.getNeighbor(moveDir);
		    	 pushPieces[1].getCell().setSelected();
		    	 weight++;
		    	 //return weight;
		    }
		    else System.out.println("no neighbor");
	    
	    	if(pushPieces[1] != null && pushPieces[1].getNeighbor(moveDir).getCell().getType() == getOppent())
		    {
	    		System.out.println("piece added to pos 2");
		    	pushPieces[2] = pushPieces[1].getNeighbor(moveDir);
		    	 pushPieces[2].getCell().setSelected();
		    	 weight++;
		    }
	    
	    //print the push pieces array
	    for(int i =0; i<pushPieces.length; i++)
		{
			if(pushPieces[i]!= null){
				//System.out.println("T:"+pushPieces[i].getBoardX()+":"+pushPieces[i].getBoardY());
			}
		}
		
		return weight;
	}
	
	public static void setPushSelected()
	{
		//add the weight of both opponents
		if(pushPieces[2] != null)pushSelected.add(pushPieces[2]);
		else System.out.println("push pieces 2 is null");
		if(pushPieces[1] != null)pushSelected.add(pushPieces[1]);
		else System.out.println("push pieces 1 is null");
		if(pushPieces[0] != null)pushSelected.add(pushPieces[0]);
		else System.out.println("push pieces 0 is null");
		if(selected[0] != null)pushSelected.add(selected[0]);
		else System.out.println("selected pieces 0 is null");
		if(selected[1] != null)pushSelected.add(selected[1]);
		else System.out.println("selected pieces 1 is null");
		if(selected[2] != null)pushSelected.add(selected[2]);
		else System.out.println("selected pieces 2 is null");
		
		//System.out.println(pushSelected);
		setPushTargets();
	}
	
	public static void setPushTargets()
	{
		for(int i = 0; i<pushSelected.size();i++)
		{
			CellControl  c = (CellControl) pushSelected.get(i);
			pushTargets.add(c.getNeighbor(moveDir));
		}
		//System.out.println(pushTargets);
		fillPushTargets();
	}
	
	public static void fillPushTargets()
	{
		//System.out.println("fill push targets");
		//loop through selected array
		for(int i =0; i<pushTargets.size();i++)
		{
			//check if it is a legal move
			
			//set the targets to fill
			CellControl c = (CellControl) pushTargets.get(i);
			CellControl cs = (CellControl) pushSelected.get(i);
			if(c.getCell().getType() == 3)
			{
				System.out.println("gutter");
				//increment score by 1
				incScore();
				
			}
			c.getCell().fillCell(cs);
			cs.getCell().setType(0);
			
			//unselect the last piece that was used
			cs.getCell().unsetSelected();
			cs = null;

		}
		emptyPushTargets();
		togglePlayer();
		
	}
	
	public static void incScore()
	{
		if(currentPlayer == 1)redScore++;
		else if(currentPlayer == 2)blueScore++;
		
		System.out.println("red: "+redScore+" blue: "+blueScore);
		
		if(redScore >=3 ||blueScore >=3 )
		{
			System.out.println("game over");
		}
	}
	
	public static void emptyPushTargets()
	{
		pushTargets.clear();
	}
	
	public static int getOppent()
	{
		if(currentPlayer == 1)return 2;
		else return 1;
	}
	
	
	public static void togglePlayer()
	{
		//System.out.println("toggle player");
		if(currentPlayer == 1)currentPlayer = 2;
		else if(currentPlayer == 2)currentPlayer = 1;
		
		//empty all arrays
		selected[0] = null;
		selected[1] = null;
		selected[2] = null;
		
		targets[0] = null;
		targets[1] = null;
		targets[2] = null;
		
		pushPieces[0] = null;
		pushPieces[1] = null;
		pushPieces[2] = null;
		
		selectedNeighbours = new CellControl[6];
		targetNeighbours = new CellControl[6];
		
		pushSelected.clear();
		pushTargets.clear();
		
		tempSelected = new int[3];
		tempTarget = new int[3];
		
		
	}


	//variables
	private int gameState = 0;
	private final static int EMPTY = 0; private final static int BLACK = 1; private final static int WHITE = 2;
	private static CellControl[] selected = {null,null,null};
	private static CellControl[] selectedNeighbours = new CellControl[6];
	private static CellControl[] targets = new CellControl[3];
	private static CellControl[] targetNeighbours = new CellControl[6];
	private static CellControl[] pushPieces = new CellControl[3];
	private static ArrayList pushSelected = new ArrayList();
	private static ArrayList pushTargets = new ArrayList();
	private static int[] tempSelected = new int[3];
	private static int[] tempTarget = new int[3];
	private static int currentPlayer = BLACK;
	private static int selDir = 6;
	private static int moveDir = 6;
	private static int redScore = 0;
	private static int blueScore = 0;



}



