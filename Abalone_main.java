import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class Abalone_main extends Application { // overridden init method
	@Override
	public void init() {
	
		
	
	}
	     
	public void start(Stage primaryStage) {
		
		
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp,800,500);	
		
		optionsMenu.getItems().addAll(resetItem,rulesItem,howToPlayItem,closeItem);
		// adding menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		bp.setTop(menuBar);
		
		// adding sidebar to the game
/*		if(GameLogic.getPlayer()==1){
			t1 = new Text("Blue turn");
		}
		else if(GameLogic.getPlayer()== 2){
			t1 = new Text("Red turn");
		}*/
		
		
		gp.addRow(0,t1,turnLabel);
		gp.addRow(1,t2, scoreLabel);
		gp.addRow(2, errorLabel);
		gp.setStyle("-fx-background-color: #b5b4ba");
		gp.setHgap(15);
		gp.setVgap(15);
		gp.setPadding(new Insets(30,30,30,30));
		
		bp.setLeft(gp);
		
		//adding the game to the center of the borderpane
		StackPane boardStackPane = abalone_board.getStackPane();
		
		hb.getChildren().add(boardStackPane);
		bp.setCenter(hb);
		
	// set a size, title and a scene on the main window. show it when // ready
	primaryStage.setScene(scene);	
	primaryStage.setTitle("Abalone"); 
	 
	primaryStage.show();
	
	 menuBar.getMenus().addAll(optionsMenu);
	
	 // closes the game if close game option is clicked
	 closeItem.setOnAction(actionEvent -> Platform.exit());
	 
	 //opens new window with the rules explained
	 rulesItem.setOnAction(actionEvent -> {
		 	final Stage rules = new Stage();
		 	rules.initModality(Modality.APPLICATION_MODAL);
		 	rules.initOwner(primaryStage);
		 	rules.setTitle("Abalone Rules explained");
		 	
		 	VBox vb = new VBox(20);
		 	vb.getChildren().add(rulesText);
		 	vb.setStyle("-fx-background-color: #f2f7ff; -fx-padding : 10");
		 	rulesText.setStyle("-fx-font-size: 17");
		 	
		 	Scene rulesScene = new Scene(vb,700,350);
		 	rules.setScene(rulesScene);
		 	rules.show();
		 	
		 });
	 howToPlayItem.setOnAction(actionEvent -> {
		 	
		 	final Stage howToPlay = new Stage();
		 	howToPlay.initModality(Modality.APPLICATION_MODAL);
		 	howToPlay.initOwner(primaryStage);
		 	howToPlay.setTitle("How to play the Game");
		 	
		 	VBox vb = new VBox(20);
		 	vb.getChildren().add(howToPlayText);
		 	vb.setStyle("-fx-background-color: #f2f7ff; -fx-padding : 10");
		 	howToPlayText.setStyle("-fx-font-size: 17");
		 	
		 	Scene howToPlayScene = new Scene(vb,700,350);
		 	howToPlay.setScene(howToPlayScene);
		 	howToPlay.show();
		 	
		 });
	
	}
	
	      // overridden stop method
	public void stop() {
	}
	      // entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
	launch(args); 
	}
	public static void setPlayer(int p){
		if(p == 1){
			turnLabel.setText("Blues Turn");
		}
		else if(p == 2){
			turnLabel.setText("Reds Turn");
		}
	}
	public static void setScore(int s){
		score = s;
	}
	public static void setError(String s){
		errorLabel.setText(s);
	}
	      // private fields for this class

	
	private Abalone abalone_board = new Abalone();
	private HBox hb = new HBox();
	private Text rulesText = new Text("Abalone Rules explanation:"
			+ "\n"
			+ "\n"
			+ "There are two available colours in the game, Red and Blue"
			+ "\n"
			+ "\n"
			+ "Play alternates between Red and Blue"
			+ "\n"
			+ "\n"
			+ "A player must select from 1-3 pieces and move them all in the same direction"
			+ "\n"
			+ "\n"
			+ "You may push an opponents piece if you have more pieces in a row E.g. 2 pieces vs 1 piece"
			+ "\n"
			+ "\n"
			+ "The Aim of the game is to push the opponents pieces in the 'gutter'"
			+ "\n"
			+ "\n"
			+ "You win when you have pushed 3 of the opponents marbles off the board");
	private Text howToPlayText = new Text("How to Play the game:"
			+ "\n"
			+ "\n"
			+ "To select your piece right click on it"
			+ "\n"
			+ "\n"
			+ "If you wish to add more pieces to your selection right click more (up to 3 pieces)"
			+ "\n"
			+ "\n"
			+ "You then can move by left clicking onto an empty position that you wish to move to"
			+ "\n"
			+ "\n"
			+ "To push an opponents pieces left click your selected pieces onto their piece");
	//private variables
	GridPane gp = new GridPane();
	
	
	private Text t1 = new Text("Player turn: ");
	private Text t2 = new Text("Player Score: ");
	private static Label turnLabel = new Label("Reds turn");
	
	private static int score;
	private static Label scoreLabel = new Label(""+score);
	
	private static Label errorLabel = new Label("");
	
	
	
	
	// adding menu options
	Menu optionsMenu = new Menu("Options");
	MenuItem resetItem = new MenuItem("Reset Game");
	MenuItem rulesItem = new MenuItem("Abalone Rules");
	MenuItem howToPlayItem = new MenuItem("How to play");
	MenuItem closeItem = new MenuItem("Close game");
	
	
	}