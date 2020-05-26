import javafx.util.Pair;

public class HeuristicF {
    public static int heuristicF(TilePuzzleNode n,int i ,int j){
        int val = n.tailPuzzle[i][j].val;
        if(val != -1) {
            int col = n.tailPuzzle[0].length;
            int valOfColor=1;
            if (n.tailPuzzle[i][j].color.equals("red")) valOfColor = 30;
            int expI = (val - 1) / col;
            int expJ = (val - 1) % col;

            return (Math.abs(i - expI) + Math.abs(j - expJ))*valOfColor;
        }
        return 0;
    }
}
