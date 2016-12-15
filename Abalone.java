// imports
import javafx.scene.Scene;
import javafx.scene.layout.StackPane; 
import javafx.stage.Stage;
// class definition
public class Abalone { // overridden init method
// initialise the stack pane and add a custom control to it
	public Abalone(){
		sp_mainlayout = new StackPane();
		cc_custom = new CustomControl(); 
		sp_mainlayout.getChildren().addAll(cc_custom);
		
		
		Stage gameStage = new Stage();
		
			gameStage.setTitle("Abalone"); 
			gameStage.setScene(new Scene(sp_mainlayout, 380, 170)); 
			
			}
	public StackPane getStackPane(){return sp_mainlayout;}
      // private fields for this class
private  StackPane sp_mainlayout; 
private CustomControl cc_custom;
}