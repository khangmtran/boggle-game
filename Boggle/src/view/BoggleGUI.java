package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Boggle;

/**
 * @author Khang Tran
 * GUI for Boggle Game
 */
public class BoggleGUI extends Application {

	public static void main(String[] args) {
		launch(args); //run application
	}

	//GUI components
	private Boggle game;
	private TextArea diceTrayArea = new TextArea();
	private TextArea resultArea = new TextArea();
	private TextArea userInputArea = new TextArea();
	private Label userLabel = new Label("Enter attempts below:");
	private Label resultLabel = new Label("Results:");
	private Button newGameButton = new Button("New Game");
	private Button endGameButton = new Button("End Game");
	private GridPane gridPane = new GridPane();

	@Override
	public void start(Stage stage) {
		// Set up the main stage
		stage.setTitle("Boggle");
		layoutGUI();
		registerListener();
		Scene scene = new Scene(gridPane, 640, 650);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Initialize the game model
	 * and load the game's dictionary
	 */
	private void setUpModel() {
		game = new Boggle();
		game.getDictionary();
	}

	/**
	 * Method sets up the entire GUI layout
	 */
	private void layoutGUI() {
		HBox buttonBox = new HBox(10); // 10 is the spacing between the buttons
		buttonBox.getChildren().addAll(newGameButton, endGameButton);
		// set up the grid
		gridPane.setHgap(12);
		gridPane.setVgap(12);
		gridPane.add(buttonBox, 1, 1);
		gridPane.add(userLabel, 2, 1);
		gridPane.add(resultLabel, 3, 1);

		// set up diceTrayArea
		Font font = Font.font("Courier New", FontWeight.BOLD, 20);
		diceTrayArea.setFont(font);
		diceTrayArea.setMaxSize(220, 270);
		diceTrayArea.setEditable(false);
		gridPane.add(diceTrayArea, 1, 2);
		
		// set up userInputArea
		font = Font.font("ChalkBoard", FontWeight.BOLD, 18);
		userInputArea.setFont(font);
		userInputArea.setMaxSize(380, 270);
		userInputArea.setEditable(true);
		userInputArea.setWrapText(true);
		gridPane.add(userInputArea, 2, 2);
		
		//set up resultArea
		BorderPane resultPane = new BorderPane();
		resultPane.setCenter(resultLabel);
		resultArea.setMinSize(600, 280);
		resultArea.setEditable(false);
		resultArea.setWrapText(true);
		resultPane.setBottom(resultArea);
		gridPane.add(resultPane, 1, 4);
		gridPane.setColumnSpan(resultPane, 2);

	}

	/**
	 * Set the action listeners for the buttons
	 */
	private void registerListener() {
		// TODO Auto-generated method stub
		newGameButton.setOnAction(new NewGameButtonListener());
		endGameButton.setOnAction(new EndGameButtonListener());
	}

	public class NewGameButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// Handler for the New Game button
			setUpModel();
			userInputArea.clear();
			resultArea.clear();
			userInputArea.setEditable(true);
			diceTrayArea.setText(game.playGame());
		}
	}

	public class EndGameButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String input = userInputArea.getText();
			game.addWord(input);	
			resultArea.setText("Your score: " + game.getScore() + "\n");
			resultArea.appendText("\n");
			resultArea.appendText("Words you found:\n");
			resultArea.appendText(game.getListOfCorrectAttempts() + "\n");
			resultArea.appendText("\n");
			resultArea.appendText("Incorrect words:\n");
			resultArea.appendText(game.getListofWrongAttempts() + "\n");
			resultArea.appendText("\n");
			resultArea.appendText("You could have found " + game.getNumberofExtraWords() + " more words:\n");
			resultArea.appendText(game.getListofExtraWords());
			userInputArea.setEditable(false);
			
		}
	}

}
