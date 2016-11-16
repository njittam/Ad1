import java.io.*;
import java.net.URL;


public class Main {
    private static int getdepth(Node root){
        if (root instanceof Leaf){
            return 1;
        }else{
            Rod rootRod = (Rod) root;
            int depth_left  = getdepth(rootRod.getLeft());
            int depth_right = getdepth(rootRod.getRight());
            if (depth_left > depth_right){
                return depth_left + 1;
            }
            else return depth_right + 1;
        }
    }

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

    private static Rod createTree(String input) throws Exception {
        Node left;
        Node right;
        input = input.substring(1, input.length()-1); //remove surrounding ()
        switch (input.charAt(0)){
            case 'R':
                left = new Red();
                input = input.substring(1, input.length());
                break;
            case 'B':
                left = new Black();
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
                right = new Red();
                break;
            case 'B':
                right = new Black();
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
        Rod r = new Rod(left,right);
        left.setParent(r);
        right.setParent(r);
        return r;
    }

    public static void run(int i ,String input, String out) throws Exception {
        Rod root = createTree(input);
        //root.print("", true);
        System.out.println(Integer.toString(i) +" "+ Algorithm.algorithm(root)+ " " + out)  ;
        //System.out.println(Algorithm.algorithm(root) +'\n')  ;
        //root.print("", true);
    }
    static class Al extends Thread{
        int i;
        String input;
        String out;
        Al(int i ,String input, String out){
            this.i = i;
            this.input = input;
            this.out = out;
        }
        public void run(){
            try {
                Main.run(i,input,out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] arg){
        BufferedReader br = null;
        String input;
        BufferedReader br2 = null;
        try {
            URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
            System.out.println(location.getFile());
            for (int i = 1; i <= 20;  i++) {
                String fn = "/C:/Users/mattijn/IdeaProjects/Ad1/out/production/ad1/"+Integer.toString(i);
                br2 = new BufferedReader(new FileReader(fn+ ".out"));
                String out = br2.readLine();

                System.setIn(new FileInputStream(fn+".in"));
                br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();
                Thread t = new Al(i,input,out);
                t.run();
            }
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
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main2(String[] arg) {
        BufferedReader br = null;
        String input;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
            Thread t = new Al(0,input,"");
            t.start();
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
