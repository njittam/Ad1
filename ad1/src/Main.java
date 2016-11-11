import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static int findClosing(String input){
        int count = 0;
        int index = -1;
        do{
            index++;
            char c = input.charAt(index);
            if ( c == '('){
                count++;
            } else if (c == ')'){
                count--;
            }
        } while (count != 0 && index < input.length());
        return index;
    }

    private static Node createTree(String input) throws Exception {
        Node left = null;
        Node right = null;
        input = input.substring(1, input.length()-1); //remove surrounding ()
        switch (input.charAt(0)){
            case 'R':
                left = new Node(Node.NodeType.Red);
                input = input.substring(1, input.length());
                break;
            case 'B':
                left = new Node(Node.NodeType.Black);
                input = input.substring(1, input.length());
                break;
            case '(':
                int indexClosing = findClosing(input);
                if (indexClosing == input.length()){
                    throw new Exception();
                }
                String subString = input.substring(0,indexClosing +1);
                left = createTree(subString);
                input = input.substring(indexClosing + 1, input.length());
                break;
            default:
                throw new Exception();
        }

        switch (input.charAt(0)){
            case 'R':
                right = new Node(Node.NodeType.Red);
                break;
            case 'B':
                right = new Node(Node.NodeType.Black);
                break;
            case '(':
                int indexClosing = findClosing(input);
                if (indexClosing == input.length()){
                    throw new Exception();
                }
                String subString = input.substring(0,indexClosing +1);
                right = createTree(subString);
                break;
            default:
                throw new Exception();
        }

        return new Node(left, right);
    }
    public static int test(Node root){
        switch (root.getType()){
            case Black:
            case Red:
                return 0;
            case Rod:
                
        }
        return 0;
    }
    public static void main(String[] arg) {
        BufferedReader br = null;
        String input;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            //input = br.readLine();
            input = "((BB)(RR))";
            Node root = createTree(input);
            System.out.print(root);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
