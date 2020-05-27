import javafx.util.Pair;

import java.util.Arrays;

public class TilePuzzleNode implements Comparable<TilePuzzleNode> {
   public Tile[][] tailPuzzle ;
   public TilePuzzleNode father;
   public Pair<Integer,Integer> blankTile;
   public String path;
   public int cost ;
   public int function ;
   public int iterationNum;
   public int from;
   public boolean markAsOut;

    public TilePuzzleNode(int row, int column){
            tailPuzzle = new Tile[row][column];
            path="";
            function = 0;
            iterationNum=0;
            cost=0;
            from=0;
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
        this.iterationNum=other.iterationNum;
        from=0;
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
        int hashCode=0;
        for (int i = 0; i <tailPuzzle.length ; i++) {
            for (int j = 0; j < tailPuzzle[i].length; j++) {
                hashCode+=Math.pow(tailPuzzle[i][j].val*(j+1),2);
            }
        }
        return hashCode;
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

    @Override
    public int compareTo(TilePuzzleNode o) {
        if(this.function-o.function != 0) return Integer.compare(this.function,o.function);
        if(this.iterationNum - o.iterationNum != 0) return Integer.compare(this.iterationNum , o.iterationNum);
        return Integer.compare(this.from,o.from);
    }
}
