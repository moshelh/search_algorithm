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
                TilePuzzleNode node =BFS.bfs(start, end);
                System.out.println(node.cost);
                node.path=node.path.substring(1);
                System.out.println(node.path);
                System.out.println(BFS.counter);
            } else if (fileReader.algorithmName.equals("DFID")) {
                DFID.dfid(start, end);
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
