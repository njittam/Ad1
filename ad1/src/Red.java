/**
 * Created by mattijn on 15-11-2016.
 */
public class Red extends Leaf {
    public Red(){
        super();
        weight = 1;
    }
    public int nrFixes() {
        Node current = this;
        Rod p = this.getParent();
        int fixes = 0;
        while (p != null) {
            if (current.isLeftChild()) {
                if (p.getLeft().getWeight() - p.getRight().getWeight() - 1 > 0) {
                    fixes++;
                }
            } else {
                if (p.getRight().getWeight() - p.getLeft().getWeight() + 1 < 0) {
                    fixes++;
                }
            }
            p = p.getParent();
        }
        return fixes;
    }
}
