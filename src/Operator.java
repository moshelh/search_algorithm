import java.util.ArrayList;

public class Operator {

    public static ArrayList<TilePuzzleNode> operator(TilePuzzleNode node,String order){
        ArrayList<TilePuzzleNode> nodes = new ArrayList<>();
        int row = node.tailPuzzle.length-1;
        int col = node.tailPuzzle[0].length-1;

        int rowBlank=node.blankTile.getKey();
        int colBlank=node.blankTile.getValue();
        //move left
        if (node.blankTile.getValue()!=col && !(node.tailPuzzle[rowBlank][colBlank+1].color.equals("black"))){
            if(order.equals("left") || order.equals("all")) {
                String move = "L";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank, colBlank + 1, move,1); //move left
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move up
        if(node.blankTile.getKey() !=row  && !(node.tailPuzzle[rowBlank+1][colBlank].equals("black"))){
            if(order.equals("up") || order.equals("all")) {
                String move = "U";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank + 1, colBlank, move,2); //move left
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move right
        if(node.blankTile.getValue() !=0  && !(node.tailPuzzle[rowBlank][colBlank-1].equals("black"))){
            if(order.equals("right") || order.equals("all")) {
                String move = "R";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank, colBlank - 1, move,3); //move left
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move down
        if(node.blankTile.getKey() !=0  && !(node.tailPuzzle[rowBlank-1][colBlank].equals("black"))){
            if(order.equals("down") || order.equals("all")) {
                String move = "D";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank - 1, colBlank, move,4); //move left
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }

        return nodes;
    }

    public static int  calculateH(TilePuzzleNode node){
        int h=0;
        for (int i = 0; i <node.tailPuzzle.length; i++) {
            for (int j = 0; j <node.tailPuzzle[0].length; j++) {
                h+=HeuristicF.heuristicF(node,i,j);
            }
        }
        return h;
    }
    private static TilePuzzleNode generateSuccessor(TilePuzzleNode node,int i, int j,String move,int from){
        TilePuzzleNode temp = new TilePuzzleNode(node); //move left
        temp.swapTile(i,j);
        if(!(temp.equals(node.father))) {
            temp.path=temp.path+"-"+node.tailPuzzle[i][j].val+move;
            if(node.tailPuzzle[i][j].color.equals("red")){
                temp.cost+=30;
            }else{
                temp.cost+=1;
            }
            int h=calculateH(temp);
            temp.function = temp.cost+h;
            temp.iterationNum++;
            temp.from=from;
            return temp;
        }
        return null;
    }

}
