import java.util.ArrayList;
import java.util.HashSet;

public class DFID {
    public static int counter=1;
    public static TilePuzzleNode save;
    public static String dfid(TilePuzzleNode start,TilePuzzleNode end){
        String result="";
        for (int i = 1; i <Integer.MAX_VALUE ; i++) {
            HashSet<TilePuzzleNode> hash = new HashSet<>();
            result = limited_DFS(start,end ,i,hash);
         if(!result.equals("cutoff")) return result;
        }
        return result;
    }
    private static String limited_DFS(TilePuzzleNode start,TilePuzzleNode end,int limit,HashSet h){
        String result="";
        if(start.equals(end)){
            save=start;
            return start.path;
        }
        else if (limit==0) return "cutoff";
        else{
            h.add(start);
            boolean isCutoff = false;
            ArrayList<TilePuzzleNode> arrayList = Operator.operator(start);
            for (TilePuzzleNode puzzleNode:arrayList) {
                counter++;
                puzzleNode.father=start;
                if (!h.contains(puzzleNode)){
                    result = limited_DFS(puzzleNode,end,limit-1,h);
                    if(result.equals("cutoff"))
                        isCutoff=true;
                    else if (!result.equals("fail")){
                        return result;
                    }
                }
            }
            h.remove(start);
            if(isCutoff)
                return "cutoff";
            else return "fail";
        }
    }
}
