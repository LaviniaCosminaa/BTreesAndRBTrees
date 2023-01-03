package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class MainB {
    //B tree to use
    BTree obj;

    @FXML
    private Button backButton;

    @FXML
    private Label messageResult;

    @FXML
    private Button removeAll;

    @FXML
    private Label tree;

    @FXML
    private TextField valueDelete;

    @FXML
    private TextField valueInsert;

    @FXML
    private TextField valueSearch;

    // the ramification factor
    int Tvalue;

    //function to print the tree
    void printTree(){
        String treePrint = obj.Show();
        System.out.println(treePrint);
        tree.setText(treePrint);
    }

    //function to go back
    @FXML void userBack(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("FactorT.fxml");
    }

    //function to remove all the nodes from OBJ
    @FXML void removeAll(ActionEvent event) {
        obj = null;
        obj = new BTree(Tvalue);
        printTree();

    }

    //function to insert in the OBJ tree - valueInsert
    @FXML void insertValue(ActionEvent event) {
        int value = Integer.parseInt(valueInsert.getText());
        valueInsert.clear();
        obj.Insert(value);
        System.out.println("\n");
        messageResult.setText("The value was inserted!");
        printTree();
    }

    //function to delete from the OBJ tree - valueDelete
    @FXML void deleteValue(ActionEvent event) {
        int value = Integer.parseInt(valueDelete.getText());
        valueDelete.clear();
        obj.Remove(value);
        messageResult.setText("The node was deleted!");
        printTree();
    }

    //function to search in the OBJ tree - valueSearch
    @FXML void searchValue(ActionEvent event) {
        int value = Integer.parseInt(valueSearch.getText());
        valueSearch.clear();
        if(obj.Contain(value)==true){
            messageResult.setText("The node is in the tree!");
        }
        else{
            messageResult.setText("The node is not in the tree!");
        }
    }

    //function to display in inorder
    @FXML void inorderDisplay(ActionEvent event) {
        String text = "The tree in inorder display is: ";
        String result = obj.inorderDisplay(obj.root);
        if(result.equals("")){
            text+="empty";}
        else{
            text+=result;}
        text+=".";
        messageResult.setText(text);

    }


    //function to find the maximum of the OBJ tree
    @FXML
    void maximum(ActionEvent event) {
        String text = "Maximum is: ";
        String sir = obj.inorderDisplay(obj.root);
        String[] nr = sir.split(" ");
        if(sir == ""){
            messageResult.setText("No values found.");
        }
        else{
            text+=nr[nr.length-1];
            text+=".";
            messageResult.setText(text);
        }

    }


    //function to find the minimum of the OBJ tree
    @FXML
    void minimum(ActionEvent event) {
        String text = "Minimum is: ";
        String sir = obj.inorderDisplay(obj.root);
        String[] nr = sir.split(" ");
        if(sir == ""){
            messageResult.setText("No values found.");
        }
        else {
            text += nr[0];
            text += ".";
            messageResult.setText(text);
        }
    }

    //the create function (works like an initialize function)
    public void creare(int t){
        obj = new BTree(t);
        Tvalue = t;
    }

}
