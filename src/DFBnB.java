import java.util.*;

public class DFBnB {
    public static int counter=1;
    public static TilePuzzleNode goul;
    public static int cost=0;
    public static String dfbNb(TilePuzzleNode start, TilePuzzleNode end, boolean withOpen,int n){
        Stack<TilePuzzleNode> stack = new Stack<>();
        Hashtable<TilePuzzleNode,TilePuzzleNode> open_list = new Hashtable<>();
        stack.push(start);
        open_list.put(start,start);
        String result ="";
        int t =n;
        while (!stack.isEmpty()){
            TilePuzzleNode node = stack.pop();
            if(node.markAsOut)
                open_list.remove(node);
            else {
                node.markAsOut=true;
                stack.push(node);
                ArrayList<TilePuzzleNode> arrayList = Operator.operator(node,"all");
                arrayList.sort(TilePuzzleNode::compareTo);
                counter+=arrayList.size();
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).father = node;
                    cost = arrayList.get(i).cost;
                    if(arrayList.get(i).function>=t){
                        arrayList.subList(i,arrayList.size()).clear();
                        break;
                    }
                    if (open_list.containsKey(arrayList.get(i)) && open_list.get(arrayList.get(i)).markAsOut  ){
                        arrayList.remove(arrayList.get(i));
                        i--;
                    }else if (open_list.containsKey(arrayList.get(i)) &&! (open_list.get(arrayList.get(i)).markAsOut)){
                        if(open_list.get(arrayList.get(i)).function<=arrayList.get(i).function){
                            arrayList.remove(i);
                            i--;
                        }else{
                            if(withOpen) {
                                for (Map.Entry<TilePuzzleNode, TilePuzzleNode> puzzleNode : open_list.entrySet()) {
                                    System.out.println(puzzleNode.getKey());
                                }
                            }
                            open_list.remove(arrayList.get(i));
                            stack.remove(arrayList.get(i));
                        }
                    }else if(arrayList.get(i).equals(end)){
                        t=arrayList.get(i).function;
                        result = arrayList.get(i).path;
                        goul = arrayList.get(i);
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
