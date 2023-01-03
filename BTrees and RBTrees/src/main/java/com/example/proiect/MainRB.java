package com.example.proiect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainRB implements Initializable {
    //RB tree to use
    RBTree obj;
    @FXML private Label messageResult;//label to print results
    @FXML private Label tree;//label to print the tree
    /**
     * TextFields to get values from user
     */
    @FXML private TextField valueInsert;
    @FXML private TextField valueDelete;
    @FXML private TextField valueSearch;
    @FXML private TextField valueSuccessor;
    @FXML private TextField valuePredecessor;

    //function to print the tree
    void printTree(){
        String treePrint = obj.indentedDisplay();
        tree.setText(treePrint);
    }
    //function to insert a node in the OBJ tree - valueInsert
    @FXML void insertNode(ActionEvent event) {
        //get value from valueInsert
        int value = Integer.parseInt(valueInsert.getText());
        valueInsert.clear();
        if(obj.insertNewNode(value)==0){
            messageResult.setText("The node was inserted!");
        }
        else{
            messageResult.setText("The node is already in the tree!");
        }
        printTree();
    }
    //function to delete a node from the OBJ tree - valueDelete
    @FXML void deleteNode(ActionEvent event) {
        //get value from valueDelete
        int value = Integer.parseInt(valueDelete.getText());
        valueDelete.clear();
        obj.deleteNode(value);
        messageResult.setText("The node was deleted!");
        printTree();
    }
    //function to search a node in the OBJ tree - valueSearch
    @FXML void searchNode(ActionEvent event) {
        //get value from valueSearch
        int value = Integer.parseInt(valueSearch.getText());
        valueSearch.clear();
        if(obj.searchNode(value)==true){
            messageResult.setText("The node is in the tree!");
        }
        else{
            messageResult.setText("The node is not in the tree!");
        }
    }
    //function to find the successor of a node in OBJ tree - valueSuccessor
    @FXML void successorNode(ActionEvent event){
        //get value from valueInsert
        String text = "The successor of the node ";
        text+=valueSuccessor.getText();
        text+=" is: ";
        int value = Integer.parseInt(valueSuccessor.getText());
        valueSuccessor.clear();
        text+=obj.successorFind(value).value;
        messageResult.setText(text);
        printTree();
    }
    //function to find the predecessor of a node in OBJ tree - valuePredecessor
    @FXML void predecessorNode(ActionEvent event){
        //get value from valueInsert
        String text = "The predecessor of the node ";
        text+=valuePredecessor.getText();
        text+=" is: ";
        int value = Integer.parseInt(valuePredecessor.getText());
        valuePredecessor.clear();
        text+=obj.predecessorFind(value).value;
        messageResult.setText(text);
        printTree();
    }
    //function to find the minimum of the OBJ tree
    @FXML void minim(ActionEvent event){
        String good = "Minimum is: ";
        RBNode rbn = obj.minimumFromTree(obj);
        if (rbn==null || rbn.value==-2147483648) messageResult.setText("Minimum not found.");
        else  {
            good+= rbn.nodeToString();
            good+=".";
            messageResult.setText(good);
        }
    }
    //function to find the maximum of the OBJ tree
    @FXML void maxim(ActionEvent event){
        String good = "Maximum is: ";
        RBNode rbn = obj.maximumFromTree(obj);
        if (rbn==null || rbn.value==-2147483648) messageResult.setText("Maximum not found.");
        else  {
            good+= rbn.nodeToString();
            good+=".";
            messageResult.setText(good);
        }
    }
    //function to find the maximum key of a black node of the OBJ tree
    @FXML void MaxBKey(ActionEvent event){
        String text = "The maximum key of black nodes is: ";
        text+=obj.maxKeyBlack();
        text+=".";
        messageResult.setText(text);
    }
    //function to find the maximum key of a red node of the OBJ tree
    @FXML void MaxRKey(ActionEvent event){
        String text = "The maximum key of red nodes is: ";
        text+=obj.maxKeyRed();
        text+=".";
        messageResult.setText(text);
    }
    //function to find the minimum key of a black node of the OBJ tree
    @FXML void MinBKey(ActionEvent event){
        String text = "The minimum key of black nodes is: ";
        text+=obj.minKeyBlack();
        text+=".";
        messageResult.setText(text);
    }
    //function to find the minimum key of a red node of the OBJ tree
    @FXML void MinRKey(ActionEvent event){
        String text = "The minimum key of red nodes is: ";
        text+=obj.minKeyRed();
        text+=".";
        messageResult.setText(text);
    }
    //function to find the height of the OBJ tree
    @FXML void height(ActionEvent event){
        String text = "The height of the tree is: ";
        text+=obj.heightTree();
        text+=".";
        messageResult.setText(text);
    }
    //function to find the black height of the OBJ tree
    @FXML void blackHeight(ActionEvent event){
        String text = "The black height of the tree is: ";
        text+=obj.blackHeightTree();
        text+=".";
        messageResult.setText(text);
    }
    //function to print the OBJ tree in inorder
    @FXML void inorderTree(ActionEvent event){
        String text = "The tree in inorder is: ";
        String result = obj.inorder();
        if(result.equals("-2147483648B")){
            text+="empty";}
        else{
            text+=result;}
        text+=".";
        messageResult.setText(text);
    }
    //function to print the OBJ tree in preorder
    @FXML void preorderTree(ActionEvent event){
        String text = "The tree in preorder is: ";
        String result = obj.preorder();
        if(result.equals("-2147483648B")){
            text+="empty";}
        else{
            text+=result;}
        text+=".";
        messageResult.setText(text);
    }
    //function to print the OBJ tree in postorder
    @FXML void postorderTree(ActionEvent event){
        String text = "The tree in postorder is: ";
        String result = obj.postorder();
        if(result.equals("-2147483648B")){
            text+="empty";}
        else{
            text+=result;}
        text+=".";
        messageResult.setText(text);
    }
    //function to go back
    @FXML void userBack(ActionEvent event) throws IOException {
        App m = new App();
        m.changeScene("main.fxml");
    }
    //function to remove all the nodes from OBJ
    @FXML void removeAll(ActionEvent event) throws IOException {
        obj.removeAll();
        obj.inorder();
        printTree();
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        obj = new RBTree(Integer.MIN_VALUE);
    }
}
