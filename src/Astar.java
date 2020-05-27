import java.util.*;

public class Astar {
    public  static int counter=1;
    public static String[] order = {"left","up","right","down"};
    public static TilePuzzleNode aStar(TilePuzzleNode start,TilePuzzleNode end){
        PriorityQueue<TilePuzzleNode> queue= new PriorityQueue<>();
        Hashtable<TilePuzzleNode,Integer> open_list = new Hashtable<>();
        Hashtable<TilePuzzleNode,Integer> close_list = new Hashtable<>();

        queue.add(start);
        open_list.put(start,0);
        while (!queue.isEmpty()){
            TilePuzzleNode tempNode =queue.poll();
            open_list.remove(tempNode);
            if(tempNode.equals(end)) return tempNode;
            close_list.put(tempNode,tempNode.function);
            for (String order:order) {
                ArrayList<TilePuzzleNode> arrayList = Operator.operator(tempNode, order);
                counter += arrayList.size();
                for (TilePuzzleNode successor : arrayList) {
                    successor.father = tempNode;
                    if (!open_list.containsKey(successor) && !close_list.containsKey(successor)) {
                        queue.add(successor);
                        open_list.put(successor, successor.function);
                    } else if (open_list.containsKey(successor) && open_list.get(successor) > successor.function) {
                        queue.remove(successor);
                        open_list.remove(successor);
                        queue.add(successor);
                        open_list.put(successor, successor.function);
                    }
                }
            }
        }
        return null;
    }
}
