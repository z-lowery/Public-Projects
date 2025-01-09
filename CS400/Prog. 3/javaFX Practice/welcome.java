import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.event.EventHandler;

import java.util.Random;


public class welcome extends Application {

    @Override
    public void start(final Stage stage){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal spacing between elements
        gridPane.setVgap(10); // Vertical spacing between elements 
        gridPane.setPadding(new javafx.geometry.Insets(20, 20, 20, 20)); // padding around gridPane elements
        
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        Button button = new Button("Enter"); 
        
        Text welcomeMessage = new Text("");
        button.setOnAction(e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            if(firstName.isEmpty() || firstName.isBlank() || lastName.isEmpty() || lastName.isBlank()){
                welcomeMessage.setText("First name and last name fields can not be blank!");
            } else {
                welcomeMessage.setText("Welcome, " + firstNameTextField.getText() + " " + lastNameTextField.getText());
            }
        });


        GridPane.setConstraints(firstNameLabel, 0, 0); // col, row
        GridPane.setConstraints(lastNameLabel, 0, 2);
        GridPane.setConstraints(firstNameTextField, 0, 1); 
        GridPane.setConstraints(lastNameTextField, 0, 3);
        GridPane.setConstraints(button, 0, 5);
        GridPane.setConstraints(welcomeMessage, 0, 6);
        GridPane.setColumnSpan(welcomeMessage, 2);

        gridPane.getChildren().addAll(firstNameLabel, lastNameLabel, 
        firstNameTextField, lastNameTextField, button, welcomeMessage);


        Group root = new Group(gridPane);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Welcome Form"); 

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}