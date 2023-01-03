package com.example.proiect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * app class
 */
public class App extends Application {
    //stage
    private static Stage stg;
    //function to run the scene on the stage
    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("RB/B TREES");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,1000,700));
        primaryStage.show();
    }
    //scene changer
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    //launcher
    public static void main(String[] args) {
        launch();
    }
}