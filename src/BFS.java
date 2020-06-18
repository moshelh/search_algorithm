import java.util.*;

public class BFS {
    public  static int counter=1;
    public static int cost=0;
    public static TilePuzzleNode bfs(TilePuzzleNode start, TilePuzzleNode end, boolean withOpen){
        Queue<TilePuzzleNode> queue= new LinkedList<>();
        HashSet<TilePuzzleNode> open_list = new HashSet<>();
        queue.add(start);
        open_list.add(start);
        HashSet<TilePuzzleNode> close_list = new HashSet<>();

        while (!queue.isEmpty()){
            TilePuzzleNode node = queue.poll();
            if(withOpen){
                System.out.println("-------------------------------------");
                for (TilePuzzleNode puzzleNode:open_list) {
                    System.out.println(puzzleNode.toString());
                }
                System.out.println("-------------------------------------");
            }
            open_list.remove(node);
            close_list.add(node);
            ArrayList<TilePuzzleNode> arrayList = Operator.operator(node,"all");

            for (TilePuzzleNode puzzleNode:arrayList) {
                counter++;
                puzzleNode.father=node;
                if((!(open_list.contains(puzzleNode))&& !(close_list.contains(puzzleNode)))){
                    cost=puzzleNode.cost;
                    if(puzzleNode.equals(end)) return puzzleNode;
                    queue.add(puzzleNode);
                    open_list.add(puzzleNode);
                }
            }
        }
        return null;

    }
}
