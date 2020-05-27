import java.util.*;

public class BFS {
    public  static int counter=1;
    public static TilePuzzleNode bfs(TilePuzzleNode start,TilePuzzleNode end){
        Queue<TilePuzzleNode> queue= new LinkedList<>();
        HashSet<TilePuzzleNode> queueHash = new HashSet<>();
        queue.add(start);
        queueHash.add(start);
        HashSet<TilePuzzleNode> nodes = new HashSet<>();

        while (!queue.isEmpty()){
            TilePuzzleNode node = queue.poll();
            queueHash.remove(node);
            nodes.add(node);
            ArrayList<TilePuzzleNode> arrayList = Operator.operator(node,"all");

            for (TilePuzzleNode puzzleNode:arrayList) {
                counter++;
                puzzleNode.father=node;
                if((!(queueHash.contains(puzzleNode))&& !(nodes.contains(puzzleNode)))){
                    if(puzzleNode.equals(end)) return puzzleNode;
                    queue.add(puzzleNode);
                    queueHash.add(puzzleNode);
                }
            }
        }
        return null;

    }
}
