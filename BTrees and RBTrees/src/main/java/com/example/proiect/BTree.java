package com.example.proiect;

import java.io.IOException;

public class BTree {
    /**
     * attributes
     */
    int T;

    /**
     * @param t - ramification factor
     */
    public BTree(int t) {
        T = t;
        root = new BNode(t);
        root.n = 0;
        root.leaf = true;
    }

    public BNode root;

    /**
     *
     * @param x - the starting node
     * @param key - the key we're looking for
     * @return the node where the key is
     */
    private BNode Search(BNode x, int key) {
        int i = 0;
        if (x == null)
            return x;
        for (i = 0; i < x.n; i++) {
            if (key < x.key[i]) {
                break;
            }
            if (key == x.key[i]) {
                return x;
            }
        }
        if (x.leaf) {
            return null;
        } else {
            return Search(x.child[i], key);
        }
    }

    /**
     *
     * @param k - the key we're looking for
     * @return true or false values if it contains the respective key
     */
    public boolean Contain(int k) {
        if (this.Search(root, k) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * the split function (separation)
     * @param x
     * @param pos
     * @param y
     */
    private void Split(BNode x, int pos, BNode y) {
        BNode z = new BNode(T);
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.leaf) {
            for (int j = 0; j < T; j++) {
                z.child[j] = y.child[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            x.child[j + 1] = x.child[j];
        }
        x.child[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.n = x.n + 1;
    }

    /**
     * insert function
     * @param key - value
     */
    public void Insert(final int key) {
        BNode r = root;
        if (r.n == 2 * T - 1) {
            BNode s = new BNode(T);
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            Split(s, 0, r);
            insertValue(s, key);
        } else {
            insertValue(r, key);
        }
    }

    /**
     * inseration the value in a specific node
     * @param x
     * @param k
     */
    final void insertValue(BNode x, int k) {

        if (x.leaf) {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
                x.key[i + 1] = x.key[i];
            }
            x.key[i + 1] = k;
            x.n = x.n + 1;
        } else {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
            }
            i++;
            BNode tmp = x.child[i];
            if (tmp.n == 2 * T - 1) {
                Split(x, i, tmp);
                if (k > x.key[i]) {
                    i++;
                }
            }
            insertValue(x.child[i], k);
        }
    }

    /**
     * the display function
     * @param x
     * @param indent
     * @return a String that represents the BTree
     */
    public String display(BNode x, int indent){
        String t = "";
        boolean b = x.leaf;
        if(x!=null){
            for(int i=0;i<x.n;i++){
                if(!b)
                    t+=display(x.child[i], indent + 2);
                for(int j=0;j<indent;j++){
                    t+="   ";
                }
                t+=x.key[i];
                t+="\n";
            }
            if(!b)
                t+=display(x.child[x.n], indent + 2);
        }
        return t;
    }

    /**
     * the function that "shows" the drawing
     * (we're using the display function to do that)
     */
    public String Show(){
        String tree = "";
        if(root == null){
            tree +="null\n";
        }
        else{
            tree += display(root, 0);
        }

        return tree;
    }

    /**
     * a function that displays the elements in inorder
     * @param x
     * @return a String with the elements in inorder
     */
    public String inorderDisplay(BNode x){
        String treeInorderDisplay = "";
        boolean b = x.leaf;
        for(int i=0;i<x.n;i++){
            if(!b)
                treeInorderDisplay += inorderDisplay(x.child[i]);
            treeInorderDisplay += x.key[i] + " ";
        }
        if(!b)
            treeInorderDisplay += inorderDisplay(x.child[x.n]);
        return treeInorderDisplay;
    }

    /**
     * the delete function
     * @param x
     * @param key
     */
    private void Remove(BNode x, int key) {
        int pos = x.Find(key);
        if (pos != -1) {
            if (x.leaf) {
                int i = 0;
                for (i = 0; i < x.n && x.key[i] != key; i++) {
                }
                ;
                for (; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        x.key[i] = x.key[i + 1];
                    }
                }
                x.n--;
                return;
            }
            if (!x.leaf) {

                BNode pred = x.child[pos];
                int predKey = 0;
                if (pred.n >= T) {
                    for (;;) {
                        if (pred.leaf) {
                            System.out.println(pred.n);
                            predKey = pred.key[pred.n - 1];
                            break;
                        } else {
                            pred = pred.child[pred.n];
                        }
                    }
                    Remove(pred, predKey);
                    x.key[pos] = predKey;
                    return;
                }

                BNode nextNode = x.child[pos + 1];
                if (nextNode.n >= T) {
                    int nextKey = nextNode.key[0];
                    if (!nextNode.leaf) {
                        nextNode = nextNode.child[0];
                        for (;;) {
                            if (nextNode.leaf) {
                                nextKey = nextNode.key[nextNode.n - 1];
                                break;
                            } else {
                                nextNode = nextNode.child[nextNode.n];
                            }
                        }
                    }
                    Remove(nextNode, nextKey);
                    x.key[pos] = nextKey;
                    return;
                }

                int temp = pred.n + 1;
                pred.key[pred.n++] = x.key[pos];
                for (int i = 0, j = pred.n; i < nextNode.n; i++) {
                    pred.key[j++] = nextNode.key[i];
                    pred.n++;
                }
                for (int i = 0; i < nextNode.n + 1; i++) {
                    pred.child[temp++] = nextNode.child[i];
                }

                x.child[pos] = pred;
                for (int i = pos; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        x.key[i] = x.key[i + 1];
                    }
                }
                for (int i = pos + 1; i < x.n + 1; i++) {
                    if (i != 2 * T - 1) {
                        x.child[i] = x.child[i + 1];
                    }
                }
                x.n--;
                if (x.n == 0) {
                    if (x == root) {
                        root = x.child[0];
                    }
                    x = x.child[0];
                }
                Remove(pred, key);
                return;
            }
        } else {
            for (pos = 0; pos < x.n; pos++) {
                if (x.key[pos] > key) {
                    break;
                }
            }
            BNode tmp = x.child[pos];
            if (tmp.n >= T) {
                Remove(tmp, key);
                return;
            }
            if (true) {
                BNode nb = null;
                int devider = -1;

                if (pos != x.n && x.child[pos + 1].n >= T) {
                    devider = x.key[pos];
                    nb = x.child[pos + 1];
                    x.key[pos] = nb.key[0];
                    tmp.key[tmp.n++] = devider;
                    tmp.child[tmp.n] = nb.child[0];
                    for (int i = 1; i < nb.n; i++) {
                        nb.key[i - 1] = nb.key[i];
                    }
                    for (int i = 1; i <= nb.n; i++) {
                        nb.child[i - 1] = nb.child[i];
                    }
                    nb.n--;
                    Remove(tmp, key);
                    return;
                } else if (pos != 0 && x.child[pos - 1].n >= T) {

                    devider = x.key[pos - 1];
                    nb = x.child[pos - 1];
                    x.key[pos - 1] = nb.key[nb.n - 1];
                    BNode child = nb.child[nb.n];
                    nb.n--;

                    for (int i = tmp.n; i > 0; i--) {
                        tmp.key[i] = tmp.key[i - 1];
                    }
                    tmp.key[0] = devider;
                    for (int i = tmp.n + 1; i > 0; i--) {
                        tmp.child[i] = tmp.child[i - 1];
                    }
                    tmp.child[0] = child;
                    tmp.n++;
                    Remove(tmp, key);
                    return;
                } else {
                    BNode lt = null;
                    BNode rt = null;
                    boolean last = false;
                    if (pos != x.n) {
                        devider = x.key[pos];
                        lt = x.child[pos];
                        rt = x.child[pos + 1];
                    } else {
                        devider = x.key[pos - 1];
                        rt = x.child[pos];
                        lt = x.child[pos - 1];
                        last = true;
                        pos--;
                    }
                    for (int i = pos; i < x.n - 1; i++) {
                        x.key[i] = x.key[i + 1];
                    }
                    for (int i = pos + 1; i < x.n; i++) {
                        x.child[i] = x.child[i + 1];
                    }
                    x.n--;
                    lt.key[lt.n++] = devider;

                    for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
                        if (i < rt.n) {
                            lt.key[j] = rt.key[i];
                        }
                        lt.child[j] = rt.child[i];
                    }
                    lt.n += rt.n;
                    if (x.n == 0) {
                        if (x == root) {
                            root = x.child[0];
                        }
                        x = x.child[0];
                    }
                    Remove(lt, key);
                    return;
                }
            }
        }
    }

    /**
     * the MAIN delete function
     * (we look for the node where the key is)
     * @param key
     */
    public void Remove(int key) {
        BNode x = Search(root, key);
        if (x == null) {
            System.out.println("Nu exista");
            return;
        }
        Remove(root, key);
    }
}
