import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class IDASTAR {
    public static int counter=1;
    public static TilePuzzleNode idastar(TilePuzzleNode start,TilePuzzleNode end){
        Stack<TilePuzzleNode> stack = new Stack<>();
        Hashtable<TilePuzzleNode,TilePuzzleNode> open_list = new Hashtable<>();
        int t = Operator.calculateH(start);

        while (t != Integer.MAX_VALUE){
            int minF= Integer.MAX_VALUE;
            stack.push(start);
            open_list.put(start,start);//check
            while (!stack.isEmpty()){
                TilePuzzleNode node = stack.pop();
                 if(node.markAsOut){
                     open_list.remove(node);
                 }else{
                     node.markAsOut=true;
                     ArrayList<TilePuzzleNode> arrayList = Operator.operator(node);
                     for (TilePuzzleNode puzzleNode:arrayList) {
                         counter++;
                         puzzleNode.father = node;
                         if(puzzleNode.function>t){
                             minF =Math.min(puzzleNode.function,minF);
                             continue;
                         }
                         if(open_list.contains(puzzleNode) && open_list.get(puzzleNode).markAsOut)
                             continue;
                         if (open_list.contains(puzzleNode) &&!(open_list.get(puzzleNode).markAsOut)){
                             if(open_list.get(puzzleNode).function>puzzleNode.function){
                                 open_list.remove(puzzleNode);
                                 stack.remove(puzzleNode);
                             }else {
                                 continue;
                             }
                         }
                         if (puzzleNode.equals(end)) return puzzleNode;
                         stack.push(puzzleNode);
                         open_list.put(puzzleNode, puzzleNode);
                     }
                 }
            }
            t=minF;
        }
        return null;
    }
}
