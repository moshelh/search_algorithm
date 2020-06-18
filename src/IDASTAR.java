import java.util.*;

public class IDASTAR {
    public static int counter=1;
    public static int cost = 0;
    public static String[] order = {"left","up","right","down"};
    public static TilePuzzleNode idastar(TilePuzzleNode start, TilePuzzleNode end, boolean withOpen){
        Stack<TilePuzzleNode> stack = new Stack<>();
        Hashtable<TilePuzzleNode,TilePuzzleNode> open_list = new Hashtable<>();
        int t = Operator.calculateH(start);

        while (t != Integer.MAX_VALUE){
            int minF= Integer.MAX_VALUE;
            start.markAsOut=false;
            stack.push(start);
            open_list.put(start,start);
            while (!stack.isEmpty()){
                TilePuzzleNode node = stack.pop();
                 if(node.markAsOut){
                     if(withOpen) {
                         for (Map.Entry<TilePuzzleNode, TilePuzzleNode> puzzleNode : open_list.entrySet()) {
                             System.out.println(puzzleNode.getKey());
                         }
                     }
                     open_list.remove(node);
                 }else{
                     node.markAsOut=true;
                     stack.push(node);
                     for (String order:order) {
                         ArrayList<TilePuzzleNode> arrayList = Operator.operator(node,order);
                         for (TilePuzzleNode puzzleNode:arrayList) {
                             counter++;
                             puzzleNode.father = node;
                             if(puzzleNode.function>t){
                                 minF =Math.min(puzzleNode.function,minF);
                                 continue;
                             }
                             if(open_list.containsKey(puzzleNode) && open_list.get(puzzleNode).markAsOut) {
                                 continue;
                             }
                             if (open_list.containsKey(puzzleNode) &&(!(open_list.get(puzzleNode).markAsOut))){
                                 if(open_list.get(puzzleNode).function>puzzleNode.function){
                                     open_list.remove(puzzleNode);
                                     stack.remove(puzzleNode);
                                 }else {
                                     continue;
                                 }
                             }
                             cost = puzzleNode.cost;
                             if (puzzleNode.equals(end)) return puzzleNode;
                             stack.push(puzzleNode);
                             open_list.put(puzzleNode, puzzleNode);
                         }
                     }

                 }
            }
            t=minF;
        }
        return null;
    }
}
