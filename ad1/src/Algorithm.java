public class Algorithm {

    private static Red findRedNode(Node root){
        if (root instanceof Red){
            return (Red) root;
        }else if (root instanceof Black){
            return null;
        }else if (root instanceof Rod) {
            Rod rootRod = (Rod) root;
            if (rootRod.equalWeight()){
                Red l = findRedNode(rootRod.getLeft());
                if (l == null){
                    return findRedNode(rootRod.getRight());
                }else{
                    return l;
                }
            }else {
                return findRedNode(rootRod.getHeaviest());
            }
        } else {
            return null;
        }

    }

    private static Integer fixCurrentNode(Rod node){
        Integer switches = 0;
        int reds = node.getWeight();
        while (!node.isStable()) {
            Red red1 = findRedNode(node);
            Black black1 = findBlackNode(node.getRoot());
            Red red2 = findRedNode(node.getRoot());
            Black black2 = findBlackNode(node);
            if (black1 == null || red1 == null){
                if (black2 == null || red2 == null){
                    return null;
                }else{
                    switches += Node.swap(red2,black2);
                }
            }
            else if (black2 == null || red2 == null){
                switches += Node.swap(red1,black1);
            }else if (red2.nrFixes() + black2.nrFixes() > red1.nrFixes() + black1.nrFixes()){
                switches += Node.swap(red2,black2);
            }else{
                switches += Node.swap(red1,black1);
            }
            if (switches > reds){
                return null;
            }
        }
        return switches;
    }
    private static Black findBlackNode1(Node node){
        if (node instanceof Red){
            return null;
        }else if (node instanceof Black){
            Black b = (Black) node;
            if (b.doesFix()){
                return b;
            } else {
                return null;
            }
        }else if (node instanceof Rod){
            Rod r = (Rod) node;
            if(r.isBalanced()){
                 return null;
            } else{
                Black l;
                Node n = r.getLightest();
                if (n == null){
                    l = findBlackNode(r.getLeft());
                    if (l == null){
                        return findBlackNode(r.getRight());
                    }else {
                        return l;
                    }
                }else{
                   return findBlackNode(n);

                }
            }
        }else{
            return null;
        }
    }

    private static Black findBlackNode2(Node node){
        if (node instanceof Red){
            return null;
        }else if (node instanceof Black){
            return (Black) node;
        }else if (node instanceof Rod){
            Rod r = (Rod) node;
            Black l;
            Node n = r.getLightest();
            if (n == null){
                l = findBlackNode(r.getLeft());
                if (l == null){
                    return findBlackNode(r.getRight());
                }else {
                    return l;
                }
            }else{
                l = findBlackNode(n);
                if (l == null ){
                    return findBlackNode(r.getHeaviest());
                } else {
                    return l;
                }
            }
        }else{
            return null;
        }
    }
    private static Black findBlackNode(Node node){
        Black b = findBlackNode1(node);
        if (b == null||true){
            b = findBlackNode2(node);
        }
        return b;
    }
    public static String algorithm(Node  root){
        Integer r = algorithmRun(root);
        if (r== null){
            return "discard";
        }
        return r.toString();
    }
    public static Integer algorithmRun(Node root){
        if (root instanceof Leaf){
            return 0;
        }
        else{
            Rod rootRod = (Rod) root;
            Integer l;
            Integer r;
            Integer c;
            //if (root.isBalanced()) {
            //    return 0;
            //}
            if (root.isStable()){
                l = algorithmRun(rootRod.getLeft());
                r = algorithmRun(rootRod.getRight());
                c = 0;
            }else {
                c = fixCurrentNode(rootRod);
                l = algorithmRun(rootRod.getLeft());
                r = algorithmRun(rootRod.getRight());
            }
            if (l == null|| r == null||c==null){
                return null;
            }
            return l+r+c;
        }
    }
}
