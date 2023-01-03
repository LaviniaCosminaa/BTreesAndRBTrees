package com.example.proiect;

public class BNode {
    /**
     * attributes
     */
    public int T;
    int n;
    int key[];
    BNode child[];
    boolean leaf;

    public BNode(int t) {
        T = t;
        key= new int[2 * T - 1];
        child = new BNode[2 * T];
        leaf = true;
    }

    /**
     * @param k - value of the node
     * @return
     */


    public int Find(int k) {
        for (int i = 0; i < this.n; i++) {
            if (this.key[i] == k) {
                return i;
            }
        }
        return -1;
    }

}
