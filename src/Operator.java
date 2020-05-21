import java.util.ArrayList;

public class Operator {
    public static ArrayList<TilePuzzleNode> operator(TilePuzzleNode node){
        ArrayList<TilePuzzleNode> nodes = new ArrayList<>();
        int row = node.tailPuzzle.length-1;
        int col = node.tailPuzzle[0].length-1;

        int rowBlank=node.blankTile.getKey();
        int colBlank=node.blankTile.getValue();
        //move left
        if (node.blankTile.getValue()!=col && !(node.tailPuzzle[rowBlank][colBlank+1].color.equals("black"))){
            TilePuzzleNode temp = new TilePuzzleNode(node); //move left
            temp.swapTile(rowBlank,colBlank+1);
            if(!(temp.equals(node.father))) {
                temp.path=temp.path+"-"+node.tailPuzzle[rowBlank][colBlank+1].val+"L";
                if(node.tailPuzzle[rowBlank][colBlank+1].color.equals("red")){
                    temp.cost+=30;
                }else{
                    temp.cost+=1;
                }
                nodes.add(temp);
            }
        }

        //move up
        if(node.blankTile.getKey() !=row  && !(node.tailPuzzle[rowBlank+1][colBlank].equals("black"))){
            TilePuzzleNode temp = new TilePuzzleNode(node);
            temp.swapTile(rowBlank+1,colBlank);
            if(!(temp.equals(node.father))) {
                temp.path=temp.path+"-"+node.tailPuzzle[rowBlank+1][colBlank].val+"U";
                if(node.tailPuzzle[rowBlank+1][colBlank].color.equals("red")){
                    temp.cost+=30;
                }else{
                    temp.cost+=1;
                }
                nodes.add(temp);
            }
        }
        //move right
        if(node.blankTile.getValue() !=0  && !(node.tailPuzzle[rowBlank][colBlank-1].equals("black"))){
            TilePuzzleNode temp = new TilePuzzleNode(node); //move left
            temp.swapTile(rowBlank,colBlank-1);
            if(!(temp.equals(node.father))) {
                temp.path=temp.path+"-"+node.tailPuzzle[rowBlank][colBlank-1].val+"R";
                if(node.tailPuzzle[rowBlank][colBlank-1].color.equals("red")){
                    temp.cost+=30;
                }else{
                    temp.cost+=1;
                }
                nodes.add(temp);
            }
        }
        //move down
        if(node.blankTile.getKey() !=0  && !(node.tailPuzzle[rowBlank-1][colBlank].equals("black"))){
            TilePuzzleNode temp = new TilePuzzleNode(node); //move left
            temp.swapTile(rowBlank-1,colBlank);
            if(!(temp.equals(node.father))) {
                temp.path=temp.path+"-"+node.tailPuzzle[rowBlank-1][colBlank].val+"D";
                if(node.tailPuzzle[rowBlank-1][colBlank].color.equals("red")){
                    temp.cost+=30;
                }else{
                    temp.cost+=1;
                }
                nodes.add(temp);
            }
        }

        return nodes;
    }

}
