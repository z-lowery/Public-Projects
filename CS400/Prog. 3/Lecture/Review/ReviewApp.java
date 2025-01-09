package Review;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


public class ReviewApp extends Application {
    private String turn = "X";
    private Button[] gameButtonList = new Button[9];

    private boolean checkWin() {
        // check rows
        for (int i = 0; i < 9; i+=3) {
            if (!gameButtonList[i].getText().equals("-") && 
                gameButtonList[i].getText().equals(gameButtonList[i + 1].getText()) &&
                gameButtonList[i].getText().equals(gameButtonList[i + 2].getText())) {
                return true;
            }
            System.out.println("checked");

        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (!gameButtonList[i].getText().equals("-") && 
                gameButtonList[i].getText().equals(gameButtonList[i + 3].getText()) &&
                gameButtonList[i].getText().equals(gameButtonList[i + 6].getText())) {
                return true;
            }
        }

        // check diagonals
        // top left to right diagonal
        if(!gameButtonList[4].getText().equals("-")){
        if(gameButtonList[0].getText().equals(gameButtonList[4].getText()) &&
            gameButtonList[0].getText().equals(gameButtonList[8].getText())){
                return true;
        }
        // bottom left to right diagonal
        if(gameButtonList[6].getText().equals(gameButtonList[4].getText()) &&
            gameButtonList[6].getText().equals(gameButtonList[2].getText())){
                return true;
        }
        }
        return false;
    }

    @Override
    public void start(Stage stage){

        int windowWidth = 400;
        int windowHeight = 300; 
        
        // define root for use below: define a layout of 3x3 buttons

        // for the root, we need a layout class to hold all the buttons
        GridPane root = new GridPane();


        for (int i = 0; i < 9; i++) {
            Button button = new Button("-");
            gameButtonList[i] = button;
            button.setPrefSize(windowWidth/3, windowHeight/3); // scale buttons with window size

            button.addEventHandler(ActionEvent.ACTION, handler -> { // using .addEventFilter activates before .addEventHandler
                button.setText(turn);
                if(checkWin()) {
                    // 1. Add a label displaying who won the screen
                    Label label = new Label("\'" + turn + "\' WON THE GAME!");
                    label.setFont(Font.font(20));
                    root.getChildren().add(label);

                    GridPane.setRowIndex(label, 3); // have label appear in the fourth row of the GridPan
                    GridPane.setColumnSpan(label, 3); // allow the label to span all 3 columns
                    // 2. prevent buttons from responding to clicks
                    root.addEventFilter( ActionEvent.ACTION, e -> {
                        e.consume(); // prevents from reaching handlers 
                    });

                }

                if(turn.equals("X")) turn = "O";
                else if(turn.equals("O")) turn = "X";
            });
                
            root.getChildren().add(button); // this works for all but BorderPanes
            GridPane.setColumnIndex(button, i%3); // set location in the GridPane
            GridPane.setRowIndex(button, i/3); //
        }
        

        Scene scene = new Scene(root, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}