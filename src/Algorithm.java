import java.io.IOException;

public class Algorithm {
    FileReader fileReader;
    public Algorithm(){
        fileReader = new FileReader();
    }
    public void startAlgorithm() throws IOException {
        TilePuzzleNode start = this.fileReader.createStartNode();
        TilePuzzleNode end = this.fileReader.createEndNode();

        //check if possible to do algorithm;
        if(possible(start,end)) {
            if (fileReader.algorithmName.equals("BFS")) {
                long startTime = System.currentTimeMillis();
                TilePuzzleNode node =BFS.bfs(start, end);
                double time =(double)(System.currentTimeMillis() - startTime)/1000 ;
                System.out.println(time);
                if(node != null) {
                    System.out.println(node.cost);
                    node.path = node.path.substring(1);
                    System.out.println(node.path);
                    System.out.println(BFS.counter);
                }
            } else if (fileReader.algorithmName.equals("DFID")) {
                if(!DFID.dfid(start, end).equals("fail")) {
                    System.out.println(DFID.dfid(start, end));
                    System.out.println(DFID.counter);
                    System.out.println(DFID.save.cost);
                }
            }else if(fileReader.algorithmName.equals("A*")){
                TilePuzzleNode node = Astar.aStar(start,end);
                if(node != null){
                    System.out.println(node.path);
                    System.out.println(node.cost);
                    System.out.println(Astar.counter);
                }
            }else if(fileReader.algorithmName.equals("IDA*")){
                TilePuzzleNode node = IDASTAR.idastar(start,end);
                if (node != null){
                    System.out.println(node.path);
                    System.out.println(node.cost);
                    System.out.println(IDASTAR.counter);
                }
            }
        }
    }

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

    public static void main(String[] args) throws IOException {
        Algorithm algorithm = new Algorithm();
        algorithm.startAlgorithm();
    }


}
