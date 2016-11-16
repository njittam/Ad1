import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by mattijn on 10-11-2016.
 */
public class Node {
    private Rod parent;
    public int weight;
    public static final boolean debug = false;
    private boolean balanced = false;

    public Node(){}

    public Rod getParent(){
        return this.parent;
    }

    public Node getRoot(){
        if (this.parent == null){
            return this;
        }else{
            return parent.getRoot();
        }
    }

    public boolean isStable() {
        if (this instanceof Rod){
            Rod thisRod = (Rod) this;
            return Math.abs(thisRod.getLeft().getWeight() - thisRod.getRight().getWeight()) <= 1;
        } else {
            return this instanceof Leaf;
        }
    }
    public boolean isBalanced(){
        if (this.balanced){
            return true;
        }
        if (!this.isStable()){
            return false;
        }
        if (this instanceof Rod){
            Rod thisRod = (Rod) this;
            boolean isBalanced  = thisRod.getRight().isBalanced() && thisRod.getRight().isBalanced();
            if (isBalanced){
                balanced = true;
            }
            return isBalanced;
        } else {
            return this instanceof Leaf;
        }
    }

    public int nrDesendats(){
        if (this instanceof Leaf){
            return 0;
        }else if (this instanceof Rod){
            Rod r = (Rod) this;
            return r.getRight().nrDesendats() + r.getLeft().nrDesendats();
        }else {
            return 0;
        }
    }
    public void setParent(Rod parent){
        this.parent = parent;
        if (parent!=null){
            parent.fixWeight();
        }
    }

    public void fixWeight(){
        if (parent == null){
            return;
        }
        parent.weight = parent.getRight().weight + parent.getLeft().weight;
        parent.fixWeight();
    }

    public void print(String prefix, boolean isTail) {
        String name;
        if (this instanceof Rod){
            name = ".";
        }
        else if (this instanceof Black){
            name = "B";
        }else if (this instanceof Red){
            name = "R";
        } else {
            name = "x";
        }
        System.out.println(prefix + (isTail ? "└── " : "├── ") + name);
        if (this instanceof Rod) {
            Node left = ((Rod) this).getLeft();
            Node right = ((Rod) this).getRight();
            left.print(prefix + (isTail ? "    " : "│   "), false);
            right.print(prefix + (isTail ? "    " : "│   "), true);
        }
    }
    public boolean isLeftChild(){
        if (parent == null) return false;
        return this == this.getParent().getLeft();
    }
    public static int swap (Leaf a, Leaf b){
       // System.out.println(a  + ", " + b);
        //System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       // a.getRoot().print("",false);
        boolean aisleft = a.isLeftChild();
        boolean bisleft = b.isLeftChild();
        if (aisleft){
            a.getParent().setLeft(b);
        }else {
            a.getParent().setRight(b);
        }
        if (bisleft){
            b.getParent().setLeft(a);
        }else {
            b.getParent().setRight(a);
        }
        Rod t = a.getParent();
        a.setParent(b.getParent());
        b.setParent(t);
        a.fixWeight();
        b.fixWeight();
        //System.out.println("-------------------------------------------------------------");
       // a.getRoot().print("", true);
      //  System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if (a.swapped && b.swapped){
            return -1;
        }else if(a.swapped || b.swapped) {
            return 0;
        }
        return 1;
    }
    public int getBlacks() {//todo fix weights.
        if (this instanceof Red){
            return 0;
        }
        if (this instanceof Black){
            return 1;
        }
        if (this instanceof Rod){
            Rod r = (Rod) this;
            return r.getLeft().getBlacks() + r.getRight().getBlacks();
        }
        return weight;
    }
    public int getWeight() {//todo fix weights.
        if (this instanceof Red){
            return 1;
        }
        if (this instanceof Black){
            return 0;
        }
        if (this instanceof Rod){
            Rod r = (Rod) this;
            return r.getLeft().getWeight() + r.getRight().getWeight();
        }
        return weight;
    }
}
