import java.util.ArrayList;

public class Operator {

    /**
     *
     * @param node current bord
     * @param order create the successor
     * @return arraylist of all legal successor
     */

    public static ArrayList<TilePuzzleNode> operator(TilePuzzleNode node,String order){
        ArrayList<TilePuzzleNode> nodes = new ArrayList<>();
        int row = node.tailPuzzle.length-1;
        int col = node.tailPuzzle[0].length-1;

        int rowBlank=node.blankTile[0];
        int colBlank=node.blankTile[1];
        //move left
        if (node.blankTile[1]!=col && !(node.tailPuzzle[rowBlank][colBlank+1].color.equals("black"))){
            if(order.equals("left") || order.equals("all")) {
                String move = "L";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank, colBlank + 1, move,1);
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move up
        if(node.blankTile[0] !=row  && !(node.tailPuzzle[rowBlank+1][colBlank].equals("black"))){
            if(order.equals("up") || order.equals("all")) {
                String move = "U";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank + 1, colBlank, move,2);
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move right
        if(node.blankTile[1] !=0  && !(node.tailPuzzle[rowBlank][colBlank-1].equals("black"))){
            if(order.equals("right") || order.equals("all")) {
                String move = "R";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank, colBlank - 1, move,3);
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }
        //move down
        if(node.blankTile[0] !=0  && !(node.tailPuzzle[rowBlank-1][colBlank].equals("black"))){
            if(order.equals("down") || order.equals("all")) {
                String move = "D";
                TilePuzzleNode temp = generateSuccessor(node, rowBlank - 1, colBlank, move,4);
                if (temp != null) {
                    nodes.add(temp);
                }
            }
        }

        return nodes;
    }

    /**
     *
     * @param node current bord
     * @return heuristic function for this bord
     */
    public static int  calculateH(TilePuzzleNode node){
        int h=0;
        for (int i = 0; i <node.tailPuzzle.length; i++) {
            for (int j = 0; j <node.tailPuzzle[0].length; j++) {
                h+=HeuristicF.heuristicF(node,i,j);
            }
        }
        return h;
    }

    /**
     *
     * @param node current bord
     * @param i tail column potion
     * @param j tail row potion
     * @param move string represent the move direction.
     * @param from give direction a numeric value .
     * @return new node if it legal move otherwise null .
     */
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
