import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Platform;
import java.util.Random;

public class ButtonGame extends Application {

    private int score = 0; // private score field

    @SuppressWarnings("unused")
    @Override
    public void start(final Stage stage){
        // root object
        BorderPane borderPane = new BorderPane();

        // add label that will show the user score
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);

        // add button that will exit the program on click
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> { 
            Platform.exit(); // exits SPECIFICALLY the javaFX application while keeping non-JavaFX operations intact
            //System.exit(0);
        });
        borderPane.setBottom(exitButton);

        // add center pane that will contain all buttons
        Pane centerPane = new Pane();
        borderPane.setCenter(centerPane);
        Button[] buttonArray = new Button[9]; // button array to store 9 buttons
        
        // add 8 buttons with "Click me?" label to buttonArray
        for (int i = 0; i < 8; i++) {
            Button questionButton = new Button("Click me?");
            questionButton.setOnAction(e -> { // de-increment score on click
                scrambleButtons(new Random(), buttonArray);
                scoreLabel.setText("Score " + --score);
                exitButton.requestFocus(); // set focus to the exit button
            });
            buttonArray[i] = questionButton;
            centerPane.getChildren().add(questionButton); // add all the new buttons to centerPane
        }

        // add button with "Click me!" label to buttonArray
        buttonArray[8] = new Button("Click me!");
        buttonArray[8].setOnAction(e -> { // increment score on click
            scrambleButtons(new Random(), buttonArray);
            scoreLabel.setText("Score " + ++score);
            exitButton.requestFocus(); // set focus to the exit button
        });
        centerPane.getChildren().add(buttonArray[8]); // add button to centerPane

        Group root = new Group(borderPane);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("ButtonGame"); 

        exitButton.requestFocus(); // set focus to the exit button on start
        scrambleButtons(new Random(), buttonArray); // scramble buttons on launch
        stage.setScene(scene);
        stage.show();
    }

    private static void scrambleButtons(Random random, Button[] buttonArray){
        for (Button button : buttonArray) {
            button.setLayoutX(random.nextInt(500) + 1); // random X between 0-500 (inclusive)
            button.setLayoutY(random.nextInt(400) + 1); // randome Y between 0-400 (inclusive)
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}