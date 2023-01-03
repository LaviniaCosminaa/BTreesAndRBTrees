package com.example.proiect;

public class RBNode {
    /**
     * attributes
     */
    RBNode leftChild, rightChild;
    RBNode parent;
    int value;
    int color;

    /**
     * constructor
     * @param value - value of the node
     */
    public RBNode(int value) { this( value, null, null, null ); }

    /**
     * constructor
     * @param value - value of the node
     * @param leftChild - the left child node
     * @param rightChild - the right child node
     * @param parent - the parent node
     */
    public RBNode(int value, RBNode leftChild, RBNode rightChild, RBNode parent) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        color = 1;
    }

    /**
     * function to print a node in the form -value:color-
     * @return value:R/B
     */
    public String nodeToString() {
        return "" + value + ((color == 0) ? ":R" : ":B") + "";
    }
}
