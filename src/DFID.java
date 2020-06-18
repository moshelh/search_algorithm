import java.util.ArrayList;
import java.util.HashSet;

public class DFID {
    public static int counter=1;
    public static TilePuzzleNode save;
    public static String[] order = {"left","up","right","down"};
    public static int cost=0;
    public static String dfid(TilePuzzleNode start, TilePuzzleNode end, boolean withOpen){
        String result="";
        for (int i = 1; i <Integer.MAX_VALUE ; i++) {
            HashSet<TilePuzzleNode> hash = new HashSet<>();
            result = limited_DFS(start,end ,i,hash,withOpen);
         if(!result.equals("cutoff")) return result;
        }
        return result;
    }
    private static String limited_DFS(TilePuzzleNode start, TilePuzzleNode end, int limit, HashSet<TilePuzzleNode> h, boolean withOpen){
        String result="";
        cost=start.cost;
        if(start.equals(end)){
            save = new TilePuzzleNode(start);
            return start.path;
        }
        else if (limit==0) return "cutoff";
        else{
            h.add(start);
            boolean isCutoff = false;
            for (String order:order) {
                ArrayList<TilePuzzleNode> arrayList = Operator.operator(start,order);
                for (TilePuzzleNode puzzleNode:arrayList) {
                    counter++;
                    puzzleNode.father=start;
                    if (!h.contains(puzzleNode)){
                        result = limited_DFS(puzzleNode,end,limit-1,h, withOpen);
                        if(result.equals("cutoff"))
                            isCutoff=true;
                        else if (!result.equals("fail")){
                            return result;
                        }
                    }
                }
            }
            if(withOpen){
                System.out.println("-------------------------------------");
                for (TilePuzzleNode puzzleNode:h) {
                    System.out.println(puzzleNode.toString());
                }
                System.out.println("-------------------------------------");
            }
            h.remove(start);
            if(isCutoff)
                return "cutoff";
            else return "fail";
        }
    }
}
