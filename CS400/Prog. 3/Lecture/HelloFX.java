import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/* 
Compiling:
javac --module-path ../javafx/lib/ --add-modules javafx.controls HelloFX.java
*/

/* 
Running: 
java --module-path ../javafx/lib/ --add-modules javafx.controls HelloFX
*/

public class HelloFX extends Application {

	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage){
		BorderPane root = new BorderPane();

		Label output = new Label("0");
		BorderPane.setAlignment(output,Pos.CENTER);
		//output.setStyle("-fx-background-color: orange;");
		//output.setAlignment(Pos.CENTER);
		root.setTop(output);


		GridPane keypad = new GridPane();
		keypad.setAlignment(Pos.CENTER);
		for(int i = 0; i<9;i++){
			Button button = new Button(""+(i+1)); //label[i]
			keypad.getChildren().add(button);
			GridPane.setRowIndex(button, i/3 ); // rowIndexes[i]
			GridPane.setColumnIndex(button, i%3); // columnindexes[i]
		}
		root.setCenter(keypad);
		keypad.addEventHandler(ActionEvent.ACTION, event -> {
			Button target = (Button)event.getTarget();
			String buttonLabel = target.getText();
			String oldLabel = output.getText();
			output.setText( oldLabel + buttonLabel);
		});

		HBox panel = new HBox();
		panel.setAlignment(Pos.CENTER);
		//BorderPane.setAlignment(panel,Pos.CENTER);

		root.setBottom(panel);
		Button x2Button = new Button("x2");
		Button quitButton = new Button("Quit");
		panel.getChildren().add(x2Button);
		panel.getChildren().add(quitButton);

		x2Button.setOnAction( e -> {
			int value = Integer.parseInt(output.getText());
			value *= 2;
			output.setText(""+value); 
		});
		quitButton.setOnAction( e -> {
			Platform.exit();
		} );
		

		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.setTitle("HelloFX");
		stage.show();
	}

/*	
	@Override
	public void start(Stage stage){
		TextField leftInput = new TextField(); 
		leftInput.setPrefWidth(64);
		
		Label plusLabel = new Label("+");
		//plusLabel.setLayoutX(300);
		
		TextField rightInput = new TextField();
		rightInput.setPrefWidth(64);
		
		Button sumButton = new Button("=");
		Label sumLabel = new Label("???");
		
		sumButton.setOnAction( event -> {
			System.out.println("Button was clicked...");
			int left = Integer.parseInt(leftInput.getText()); 
			int right = Integer.parseInt(rightInput.getText()); 
			int sum = left + right;
			sumLabel.setText(""+sum); // the "" makes sure it passes as a string and not an integer 
		});

		HBox root = new Group(leftInput, plusLabel, rightInput, sumButton, sumLabel);
		Scene scene = new Scene(root, 400, 300); // root of tree with all our controls. 400x300 is the default size of the scene
		stage.setScene(scene); 
		stage.show();
	}
	
	public static void main(String[] args){
		Applications.launch();
	}*/
}
