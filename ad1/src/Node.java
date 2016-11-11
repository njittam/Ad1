/**
 * Created by mattijn on 10-11-2016.
 */
public class Node {
    public enum NodeType {
        Rod, Black, Red
    }

    private Node left;
    private Node right;
    private Node parent;
    private NodeType type;
    private int  reds = 0;
    private int blacks = 0;

    public Node(NodeType type) throws Exception {
        if (type == NodeType.Rod){
            throw new Exception();
        }
        this.type = type;
        if (this.type == NodeType.Black){
            this.blacks = 1;
        }
        if (this.type == NodeType.Red){
            this.reds = 1;
        }
    }

    public Node(Node left, Node right){
        this.type = NodeType.Rod;
        this.left = left;
        this.right = right;
        this.blacks = left.blacks + right.blacks;
        this.reds = left.reds + right.reds;
    }
    public NodeType getType(){
        return this.type;
    }
    public void setParent(Node parent){
        this.parent = parent;
    }

    public String toString(){
        switch (this.type) {
            case Rod:
                return "(" + left.toString() + right.toString() + ")";
            case Black:
                return "R";
            case Red:
                return "B";
            default:
                return ".";
        }

    }
}
