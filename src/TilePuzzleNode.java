import javafx.util.Pair;

import java.util.Arrays;

public class TilePuzzleNode {
   public Tile[][] tailPuzzle ;
   public TilePuzzleNode father;
   public Pair<Integer,Integer> blankTile;
   public String path;
   public int cost =0;
    public TilePuzzleNode(int row, int column){
            tailPuzzle = new Tile[row][column];
            path="";
    }
    public TilePuzzleNode(TilePuzzleNode other){
        this.tailPuzzle= new Tile[other.tailPuzzle.length][other.tailPuzzle[0].length];
        for (int i = 0; i <tailPuzzle.length ; i++) {
            for (int j = 0; j < tailPuzzle[0].length; j++) {
               tailPuzzle[i][j]=new Tile(other.tailPuzzle[i][j]);
            }
        }
        blankTile=new Pair<>(other.blankTile.getKey(),other.blankTile.getValue());
        path=other.path;
        cost=other.cost;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TilePuzzleNode that = (TilePuzzleNode) o;
        for (int i = 0; i < tailPuzzle.length; i++) {
            for (int j = 0; j < tailPuzzle[0].length; j++) {
                if(!tailPuzzle[i][j].equals(that.tailPuzzle[i][j])) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tailPuzzle);
    }

    public void swapTile(int i,int j){
        Tile tile = new Tile(tailPuzzle[i][j]);
        tailPuzzle[i][j].color="white";
        tailPuzzle[i][j].val =-1;
        tailPuzzle[blankTile.getKey()][blankTile.getValue()].color=tile.color;
        tailPuzzle[blankTile.getKey()][blankTile.getValue()].val=tile.val;
        blankTile=new Pair<>(i,j);
    }

    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < tailPuzzle.length; i++) {
            s+=Arrays.toString(tailPuzzle[i]);
        }
        return s;
    }
}