/**
 * Created by mattijn on 15-11-2016.
 */
public class Rod extends Node {
    private Node left;
    private Node right;

    public Rod  (Node left, Node right){
        if (left.nrDesendats() < right.nrDesendats()){
            this.left = right;
            this.right = left;
        }else{
            this.left = left;
            this.right = right;
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public boolean equalWeight() {
        return right.getWeight() == left.getWeight();
    }

    public Node getHeaviest() {
        if (left.getWeight() > right.getWeight()) return left;
        else if (left.getWeight() < right.getWeight()) return right;
        else return null;
    }
    public Node getLightest() {
        if (left.getWeight() > right.getWeight()) return right;
        else if (left.getWeight() < right.getWeight()) return left;
        else return null;
    }
    public void setLeft(Leaf left) {
        this.left = left;
    }

    public void setRight(Leaf right) {
        this.right = right;
    }
}
