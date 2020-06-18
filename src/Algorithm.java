import java.io.*;
import java.nio.charset.StandardCharsets;

public class Algorithm {
    FileReader fileReader;
    public Algorithm(){
        fileReader = new FileReader();
    }
    public void startAlgorithm() throws IOException {
        TilePuzzleNode start = this.fileReader.createStartNode();
        TilePuzzleNode end = this.fileReader.createEndNode();
        long startTime =0;
        TilePuzzleNode node;
        String path="";
        String cost="Cost: ";
        String num ="Num: ";
        String ans="";
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.txt"), StandardCharsets.UTF_8));

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        if(possible(start,end)) {

            if (fileReader.withTime) {
                 startTime = System.currentTimeMillis();
            }
            switch (fileReader.algorithmName) {
                case "BFS":
                    node = BFS.bfs(start, end, fileReader.withOpen);
                    if (node != null) {
                        cost += node.cost + "";
                        node.path = node.path.substring(1);
                        path = node.path;
                    } else {
                        cost += BFS.cost + "";
                        path = "no path";

                    }
                    num += BFS.counter + "";
                    break;
                case "DFID":
                    path = DFID.dfid(start, end, fileReader.withOpen);
                    if (!path.equals("fail")) {
                        path = path.substring(1);
                        cost += DFID.save.cost + "";
                    } else {
                        cost += DFID.cost + "";
                        path = "no path";
                    }
                    num += DFID.counter + "";
                    break;
                case "A*":
                    node = Astar.aStar(start, end, fileReader.withOpen);
                    if (node != null) {
                        node.path = node.path.substring(1);
                        path = node.path;
                        cost += node.cost + "";
                    } else {
                        cost += Astar.cost + "";
                        path = "no path";
                    }
                    num += Astar.counter + "";
                    break;
                case "IDA*":
                    node = IDASTAR.idastar(start, end, fileReader.withOpen);
                    if (node != null) {
                        node.path = node.path.substring(1);
                        path = node.path;
                        cost += node.cost + "";
                    } else {
                        cost += IDASTAR.cost + "";
                        path = "no path";
                    }
                    num += IDASTAR.counter + "";
                    break;
                default:
                    int n = Math.min(Integer.MAX_VALUE,factorial(fileReader.numOfTail));
                    path = DFBnB.dfbNb(start, end, fileReader.withOpen ,n);
                    if (!path.equals("")) {
                        path = path.substring(1);
                        node = DFBnB.goul;

                        cost = node.cost + "";
                    } else {
                        cost += DFBnB.cost + "";
                        path = "no path";
                    }
                    num = IDASTAR.counter + "";

                    break;
            }
            if (fileReader.withTime) {
                double time = (double) (System.currentTimeMillis() - startTime) / 1000;
                ans = path+"\n"+num+"\n"+cost+"\n"+time;

            }else{
                ans = path+"\n"+num+"\n"+cost;
            }
        }else {
            ans = "no path" + "\n" + "Num: " + 1;
        }
        assert writer !=null;
        writer.write(ans);
        writer.close();
    }

    /**
     *
     * @param start - Start board
     * @param end - End board
     * @return true if there is no tail that is black and is not in its place ,Otherwise it returns false
     */
    private boolean possible(TilePuzzleNode start, TilePuzzleNode end) {
        for (int i = 0; i <start.tailPuzzle.length ; i++) {
            for (int j = 0; j <end.tailPuzzle[0].length ; j++) {
                if ((!start.tailPuzzle[i][j].equals(end.tailPuzzle[i][j]))&&start.tailPuzzle[i][j].color.equals("black")){
                    return false;
                }
            }
        }
        return true;

    }
    private static int factorial(int number) {
        int result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
