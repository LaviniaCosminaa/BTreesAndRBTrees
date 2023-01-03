package com.example.proiect;
import java.io.IOException;
public class RBTree {
    /**
     * attributes
     */
    private static RBNode nullNode;
    private RBNode current;
    private RBNode root;
    static final int RED   = 0;
    static final int BLACK = 1;
    // using static initializer for initializing null Node
    static {
        nullNode = new RBNode(0);
        nullNode.leftChild = nullNode;
        nullNode.rightChild = nullNode;
        nullNode.parent = nullNode;
    }
    /**
     * constructor for root
     * @param root
     */
    public RBTree(int root) {
        this.root = new RBNode(root);
        this.root.leftChild = nullNode;
        this.root.rightChild = nullNode;
        this.root.parent = nullNode;
    }
    /**
     * function to remove all the values from a tree
     */
    public void removeAll() throws IOException {
        root = nullNode;
    }
    /**
     * function to insert an element
     * @param newElement - the value to be inserted
     * @return
     */
    public int insertNewNode(int newElement) {
        RBNode last = nullNode;
        RBNode newNode = new RBNode(newElement, null, null, null);
        if(searchNode(root, newElement) == true){
            return 1;
        }
        else{
            if(root.value == Integer.MIN_VALUE){
                root = nullNode;
            }
            current = root; //x
            while(current != nullNode){
                last = current;
                current = (newNode.value < current.value) ? current.leftChild : current.rightChild;
            }
            newNode.parent = last;
            if(last == nullNode){
                root = newNode;
            }
            else{
                if(newNode.value < last.value){
                    last.leftChild = newNode;
                }
                else{
                    last.rightChild = newNode;
                }
            }
            newNode.leftChild = newNode.rightChild = nullNode;
            newNode.color = 0;
            InsertFixup(newNode);
        }
        return 0;
    }
    /**
     * function to fixup the tree after insertion
     * @param newNode - the inserted node
     */
    private void InsertFixup(RBNode newNode) {
        while(newNode.parent.color == 0){
            if(newNode.parent == newNode.parent.parent.leftChild){
                RBNode rightTree = newNode.parent.parent.rightChild;
                if(rightTree.color == 0){
                    newNode.parent.color = 1;
                    rightTree.color = 1;
                    newNode.parent.parent.color = 0;
                }
                else{
                    if(newNode == newNode.parent.rightChild){
                        newNode = newNode.parent;
                        LeftRotate(newNode);
                    }
                    newNode.parent.color = 1;
                    newNode.parent.parent.color = 0;
                    RightRotate(newNode.parent.parent);
                }
            }
            else{
                RBNode leftTree = newNode.parent.parent.leftChild;
                if(leftTree.color == 0){
                    newNode.parent.color = 1;
                    leftTree.color = 1;
                    newNode.parent.parent.color = 0;
                }
                else{
                    if(newNode == newNode.parent.leftChild){
                        newNode = newNode.parent;
                        RightRotate(newNode);
                    }
                    newNode.parent.color = 1;
                    newNode.parent.parent.color = 0;
                    LeftRotate(newNode.parent.parent);
                }
            }
        }
        root.color = 1;
    }
    /**
     * function to delete a node from tree
     * @param element - the value of the node to be deleted
     * @return - the deleted node
     */
    public RBNode deleteNode(int element) {
        RBNode nodeDel = searchNodeElement(root, element);
        //System.out.printf("Nodul de sters are valoarea %d ( element = %d )\n",nodeDel.value, element);
        RBNode y, x;
        if(nodeDel.leftChild==nullNode || nodeDel.rightChild==nullNode){
            y = nodeDel;
        }
        else{
            y = successor(nodeDel);
        }
        if(y.leftChild!=nullNode){
            x = y.leftChild;
        }
        else{
            x = y.rightChild;
        }
        x.parent = y.parent;
        if(y.parent == nullNode){
            root = x;
        }
        else{
            if (y == y.parent.leftChild)
                y.parent.leftChild = x;
            else
                y.parent.rightChild = x;
        }
        if (y != nodeDel)
        {
            nodeDel.value = y.value;
        }
        if (y.color == 1)
            DeleteFixup(x);
        return y;
    }
    /**
     * function to fixup the tree after delete
     * @param x - the node to apply the function to
     */
    private void DeleteFixup(RBNode x) {
        RBNode w;
        while ((x != root) && (x.color == 1))
        {
            if (x == x.parent.leftChild)
            {
                w = x.parent.rightChild;
                if(w.color == 0)
                {
                    w.color = 1;
                    x.parent.color = 0;
                    LeftRotate(x.parent);
                    w = x.parent.rightChild;
                }
                if((w.leftChild.color == 1) && (w.rightChild.color == 1))
                {
                    w.color = 0;
                    x = x.parent;
                }
                else
                {
                    if(w.rightChild.color == 1)
                    {
                        w.leftChild.color = 1;
                        w.color = 0;
                        RightRotate(w);
                        w = x.parent.rightChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 1;
                    w.rightChild.color = 1;
                    LeftRotate(x.parent);
                    x = root;
                }
            }
            else
            {
                // same as 'then' clause with 'right' and 'left' exchanged
                w = x.parent.leftChild;
                if(w.color == 0)
                {
                    w.color = 1;
                    x.parent.color = 0;
                    RightRotate(x.parent);
                    w = x.parent.leftChild;
                }
                if((w.leftChild.color == 1) && (w.rightChild.color == 1))
                {
                    w.color = 0;
                    x = x.parent;
                }
                else
                {
                    if(w.leftChild.color == 1)
                    {
                        w.rightChild.color = 1;
                        w.color = 0;
                        LeftRotate(w);
                        w = x.parent.leftChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = 1;
                    w.leftChild.color = 1;
                    RightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 1;
    }
    /**
     * function to search an element
     * @param node - the node
     * @param element
     */
    private RBNode searchNodeElement(RBNode node, int element) {
        while((node!=nullNode) && (node.value!=element)){
            if (element < node.value){
                node = node.leftChild;
            }
            else {
                if (element > node.value){
                    node = node.rightChild;
                }
            }
        }
        return node;
    }
    /**
     * function to search an element
     * @param value - value to search
     */
    public boolean searchNode(int value) {
        return searchNode(root, value);
    }
    /**
     * function to search an element
     * @param node - the node to start from
     * @param value - the value to search
     * @return
     */
    private boolean searchNode(RBNode node, int value) {
        boolean check = false;
        while ((node != nullNode) && check != true)
        {
            int nodeValue = node.value;
            if (value < nodeValue)
                node = node.leftChild;
            else if (value > nodeValue)
                node = node.rightChild;
            else
            {
                check = true;
                break;
            }
            check = searchNode(node, value);
        }
        return check;
    }
    /**
     * function to find the succesor of a node
     */
    public RBNode successorFind(int value){
        RBNode elem = searchNodeElement(root, value);
        RBNode succ = successor(elem);
        return succ;
    }
    private RBNode successor(RBNode w) {
        if(w==nullNode) return w;
        RBNode x = w;
        if (x.rightChild!=nullNode)
            return minimum(x.rightChild);
        RBNode y = x.parent;
        while (y!=nullNode && x == y.rightChild)
        {
            x = y;
            y = x.parent;
        }
        return y;
    }
    /**
     * function to find the predecessor of a node
     * @return
     */
    public RBNode predecessorFind(int value){
        RBNode succ = predecessor(searchNodeElement(root, value));
        return succ;
    }
    private RBNode predecessor(RBNode w) {
        if(w==nullNode) return w;
        RBNode x = w;
        if (x.leftChild!=nullNode)
            return maximum(x.leftChild);
        RBNode y = x.parent;
        while (y!=nullNode && x == y.leftChild)
        {
            x = y;
            y = x.parent;
        }
        return y;
    }
    /**
     * function to get the minimum from a tree
     * @param w - node to start
     * @return
     */
    private RBNode minimum(RBNode w) {
        RBNode x = w;
        while (x.leftChild!=nullNode)
            x = x.leftChild;
        return x;
    }
    /**
     * function to get the maximum from a tree
     * @param w - node to start
     * @return
     */
    private RBNode maximum(RBNode w) {
        RBNode x = w;
        while (x.rightChild!=nullNode)
            x = x.rightChild;
        return x;
    }
    /**
     * function to get height from a tree
     * @param x - node to start
     * @return
     */
    private int depth(RBNode x) {
        if (x==nullNode) {
            return -1;
        }
        else
        {
            int lDepth = depth(x.leftChild);
            int rDepth = depth(x.rightChild);
            return (lDepth<rDepth ? rDepth : lDepth)+1;
        }
    }
    /**
     * minimum from the tree
     * @param obj - the tree
     * @return
     */
    public RBNode minimumFromTree(RBTree obj) {
        return minimum(obj.root);
    }
    /**
     * maximum from the tree
     * @param obj - the tree
     * @return
     */
    public RBNode maximumFromTree(RBTree obj) {
        return maximum(obj.root);
    }
    /**
     * functions to get min/max red/black node from tree
     */
    public int maxKeyBlack(){
        RBNode aux = root;
        int maxim = 0;
        if(aux==nullNode)
            return -1000;
        while(aux!=nullNode){
            if((aux.value > maxim) && (aux.color == 1))
                maxim = aux.value;
            aux = aux.rightChild;
        }
        return maxim;
    }
    public int maxKeyRed(){
        RBNode aux = root;
        int maxim = 0;
        if(aux==nullNode)
            return -1000;
        while(aux!=nullNode){
            if((aux.value > maxim) && (aux.color == 0))
                maxim = aux.value;
            aux = aux.rightChild;
        }
        return maxim;
    }
    public int minKeyBlack(){
        RBNode aux = root;
        int minim = 200000000;
        if(aux==nullNode)
            return -1000;
        while(aux!=nullNode){
            if((aux.value < minim) && (aux.color == 1))
                minim = aux.value;
            aux = aux.leftChild;
        }
        if(minim==200000000 || minim==-2147483648)
            return 0;
        else
            return minim;
    }
    public int minKeyRed(){
        String inorder = inorder();
        String[] result = inorder.split("\\s");
        int minim = 200000000;
        for(String s: result){
            char last = s.charAt(s.length() -1);
            if(last=='R'){
                String ss = s.substring(0,s.length() -1);
                minim = Integer.parseInt(ss);
                break;
            }
        }
        if(minim==200000000)
            return 0;
        else
            return minim;
    }
    /**
     * functions to get tree (black) height
     */
    public int heightTree() {
        return depth(root);
    }
    public int blackHeightTree() {
        int height = 0;
        RBNode x = root;
        if(x==nullNode)
            return 0;
        while(x!=nullNode){
            if(x.color == 1)
                height++;
            x=x.leftChild;
        }
        return height;
    }
    /**
     * rotations
     */
    private void RightRotate(RBNode newNode) {
        RBNode y = newNode.leftChild;
        newNode.leftChild = y.rightChild;
        if (y.rightChild != nullNode)
            y.rightChild.parent = newNode;
        y.parent = newNode.parent;
        if (newNode.parent == nullNode) {
            root = y;
        }
        else {
            if (newNode == newNode.parent.leftChild){
                newNode.parent.leftChild = y;
            }
            else {
                newNode.parent.rightChild = y;
            }
        }
        y.rightChild =newNode;
        newNode.parent = y;
    }
    private void LeftRotate(RBNode newNode) {
        RBNode y = newNode.rightChild;
        newNode.rightChild = y.leftChild;
        if ( y.leftChild != nullNode)
            y.leftChild.parent = newNode;
        y.parent = newNode.parent;
        if (newNode.parent == nullNode){
            root = y;
        }
        else {
            if (newNode == newNode.parent.leftChild){
                newNode.parent.leftChild = y;
            }
            else{
                newNode.parent.rightChild = y;
            }
        }
        y.leftChild = newNode;
        newNode.parent = y;
    }
    /**
     * traversal
     */
    public String inorder() {
        String res = inorder(root);
        return res;
    }
    private String inorder(RBNode node) {
        String result = "";
        if (node != nullNode)
        {
            result+=inorder(node.leftChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            result+=node.value;
            result+=c;
            result+=" ";
            //System.out.print(node.value +""+c+" ");
            result+=inorder(node.rightChild);
        }
        return result;
    }
    public String preorder() {
        String res = preorder(root);
        return res;
    }
    private String preorder(RBNode node) {
        String result = "";
        if (node != nullNode)
        {
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            result+=node.value;
            result+=c;
            result+=" ";
            result+=preorder(node.leftChild);
            result+=preorder(node.rightChild);
        }
        return result;
    }
    public String postorder() {
        String res = postorder(root);
        return res;
    }
    private String postorder(RBNode node) {
        String result = "";
        if (node != nullNode)
        {
            result+=postorder(node.leftChild);
            result+=postorder(node.rightChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            result+=node.value;
            result+=c;
            result+=" ";
            //System.out.print(node.value +""+c+" ");
        }
        return result;
    }
    /**
     * function to display the tree
     * @param w - node to start
     * @param indent - spaces
     * @return
     */
    String display(RBNode w, int indent) {
        String t = "";
        if (w!=nullNode)
        {
            t+=display(w.rightChild, indent + 2);
            for (int i = 0; i < indent; i++) {
                t+="\t";
            }
            t+=w.nodeToString();
            t+="\n";
            t+=display(w.leftChild, indent + 2);
        }
        return t;
    }
    String indentedDisplay() {
        String tree = "";
        if (root == null){
            tree+="null\n";
        }
        else {
            tree+=display(root, 0);
        }
        return tree;
    }
}
