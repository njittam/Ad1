/**
 * Created by mattijn on 15-11-2016.
 */
public class Black extends Leaf {
    public Black(){
        super();
        weight = 0;
    }
    public boolean doesFix(){
        Node current = this;
        Rod p = this.getParent();
        while (p != null){
            if (current.isLeftChild()){
                if (p.getLeft().getWeight() - p.getRight().getWeight()  + 1< 0){
                    return true;
                }
            }else {
                if (p.getRight().getWeight() - p.getLeft().getWeight() -1 > 0){
                    return true;
                }
            }
            p = p.getParent();
        }
        return false;
    }

    public int nrFixes() {
        Node current = this;
        Rod p = this.getParent();
        int fixes = 0;
        while (p != null){
            if (current.isLeftChild()){
                if (p.getLeft().getWeight() - p.getRight().getWeight()  + 1 < 0){
                    fixes++;
                }
            }else {
                if (p.getRight().getWeight() - p.getLeft().getWeight() -1 > 0){
                    fixes++;
                }
            }
            p = p.getParent();
        }
        return fixes;
    }
}
