import java.util.List;
import java.util.TreeSet;

/**
 * Created by mattijn on 14-11-2016.
 */
public class Graph {
    Node root;
    List<Node> nodes_error;
    List<Node> nodes_1red_more;
    List<Node> nodes_1black_more;

    public Graph (Node root){
        this.root = root;
    }
}
