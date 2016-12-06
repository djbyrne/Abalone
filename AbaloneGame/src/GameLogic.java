
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
				c.getCell().setSelected();
				//setPlayer(c.getCell().getType());
				//System.out.println("selected piece at: "+c.getBoardX()+":"+c.getBoardY());
			}
			/*else if(selected[1] == null && getPlayer() == c.getCell().getType())
			{
				selected[1] = c;
				c.getCell().setSelected();
			}
			else if(selected[2] == null && getPlayer() == c.getCell().getType())
			{
				selected[2] = c;
				c.getCell().setSelected();
			}*/
		}
		
		
			
	}
	
	public static void emptySelected()
	{
		//selected [0].getCell().setOpacity(1);
		selected [0] = null;
		//selected [1].getCell().setOpacity(1);
		//selected [1] = null;
		//selected [2].getCell().setOpacity(1);
		//selected [2] = null;
		System.out.println("emtpied");
	}
	
	public static void movePiece()
	{
		//change to empty at the selected position	
	}
	
	
	
	public static void printArray()
	{
		for(int i =0; i<selected.length; i++)
		{
			System.out.println(selected[i]);
		}
	}
	
	public static int getPlayer()
	{
		return currentPlayer;
	}
	
	public static void togglePlayer()
	{
		if(currentPlayer == 1)currentPlayer = 2;
		else if(currentPlayer == 2)currentPlayer = 1;
	}
	
	
	
	//variables
	private int gameState = 0;
	private final static int EMPTY = 0; private final static int BLACK = 1; private final static int WHITE = 2;
	private static CellControl[] selected = {null};
	private static int currentPlayer = BLACK;
	

}



