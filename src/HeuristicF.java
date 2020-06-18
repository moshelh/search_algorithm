

public class HeuristicF {
    /**
     *
     * @param n current node
     * @param i tail column potion
     * @param j tail row potion
     * @return calculate the M.D for this tail
     */
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
