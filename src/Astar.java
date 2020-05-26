import java.util.*;

public class Astar {
    public  static int counter=1;
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
            ArrayList<TilePuzzleNode> arrayList = Operator.operator(tempNode);
            counter+=arrayList.size();
            for (TilePuzzleNode successor:arrayList) {
              successor.father=tempNode;
               if (open_list.containsKey(successor) && open_list.get(successor)<=successor.function) continue;
               if (close_list.containsKey(successor)&& close_list.get(successor)<=successor.function)
                   continue;
               else{
                   queue.add(successor);
                   open_list.put(successor,successor.function);
               }

            }
            close_list.put(tempNode,tempNode.function);
        }
      return null;
    }
}
