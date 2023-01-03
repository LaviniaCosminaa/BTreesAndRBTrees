package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FactorT {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextField valueT;


    //function to go back
    @FXML
    void userBack(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("main.fxml");
    }

    //function to go on the next page
    //and transfer the ramification value that we want
    @FXML
    void userNext(ActionEvent event) throws IOException {
        int value = Integer.parseInt(valueT.getText());

        FXMLLoader data = new FXMLLoader(getClass().getResource("BTree.fxml"));
        try {
            root = data.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        MainB mb = data.getController();
        mb.creare(value);
    }
}
