import java.util.*;

public class DFBnB {
    public static int counter=1;
    public static TilePuzzleNode goul;
    public static String dfbNb(TilePuzzleNode start,TilePuzzleNode end){
        Stack<TilePuzzleNode> stack = new Stack<>();
        Hashtable<TilePuzzleNode,TilePuzzleNode> open_list = new Hashtable<>();
        stack.push(start);
        open_list.put(start,start);
        String result ="";
        int t =Integer.MAX_VALUE;
        while (!stack.isEmpty()){
            TilePuzzleNode node = stack.pop();
            if(node.markAsOut)
                open_list.remove(node);
            else {
                node.markAsOut=true;
                ArrayList<TilePuzzleNode> arrayList = Operator.operator(node);
                arrayList.sort(TilePuzzleNode::compareTo);
                counter+=arrayList.size();
                for (int i = 0; i < arrayList.size(); i++) {
                    TilePuzzleNode temp= arrayList.get(i);
                    temp.father = node;
                    if(temp.function>=t){
                        arrayList.subList(i,arrayList.size()).clear();
                        break;
                    }
                    if (open_list.contains(temp) && open_list.contains(temp.markAsOut)){
                        arrayList.remove(i);
                        i--;
                    }else if (open_list.contains(temp) &&! (open_list.contains(temp))){
                        if(open_list.get(temp).function<=temp.function){
                            arrayList.remove(i);
                            i--;
                        }else{
                            open_list.remove(temp);
                            stack.remove(temp);
                        }
                    }else if(temp.equals(end)){
                        t=temp.function;
                        result = temp.path;
                        goul = temp;
                        arrayList.subList(i,arrayList.size()).clear();
                        break;
                    }

                }
                Collections.reverse(arrayList);
                for (TilePuzzleNode puzzleNode:arrayList) {
                    stack.push(puzzleNode);
                    open_list.put(puzzleNode, puzzleNode);
                }
            }
        }

        return result;
    }
}
