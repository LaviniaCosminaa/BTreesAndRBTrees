package com.example.proiect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
public class Main {
    //the scene for the B tree
    @FXML void bScene(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("FactorT.fxml");
    }
    //the scene for the RB tree
    @FXML void rbScene(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("RBTree.fxml");
    }
}
