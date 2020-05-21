import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileReader {
    String algorithmName;
    boolean withTime;
    boolean withOpen;
    ArrayList<Integer> black;
    ArrayList<Integer> red;
    String[] num;

    public FileReader() {
        black = new ArrayList<>();
        red = new ArrayList<>();
    }

    public static List<String> fileReader(String path) throws IOException {
        BufferedReader in
                = new BufferedReader(new java.io.FileReader(path));

        List<String> allStrings = new ArrayList<String>();
        String str = "";
        while (true) {
            String tmp = in.readLine();
            if (tmp == null) {
                break;
            }
            if (tmp.isEmpty()) {
                if (!str.isEmpty()) {
                    allStrings.add(str);
                }
                str = "";
            } else if (tmp == null) {
                break;
            } else {
                if (str.isEmpty()) {
                    str = tmp;
                } else {
                    str += "\\n" + tmp;
                }
            }
        }
        allStrings.add(str);
        return allStrings;
    }

    public TilePuzzleNode createStartNode() throws IOException {
        List<String> text = fileReader("E:\\search_project\\src\\input.txt");
        String arr[] = text.get(0).trim().split(Pattern.quote("\\n"));
        this.algorithmName = arr[0];
        if (arr[1].equals("with time")) {
            withTime = true;
        }
        if (arr[2].equals("with open")) {
            withOpen = true;
        }
        this.num = arr[3].split("x");
        TilePuzzleNode tailPuzzle = new TilePuzzleNode(Integer.parseInt(num[0]), Integer.parseInt(num[1]));
        int endBlack = arr[4].indexOf(':');
        arr[4] = arr[4].substring(endBlack + 1);
        if (arr[4].length() != 0) {
            arr[4] = arr[4].replaceAll(" ", "");
            String[] blackNum = arr[4].split(",");
            for (String s : blackNum) {
                black.add(Integer.parseInt(s));
            }
        }
        int endRed = arr[5].indexOf(':');
        arr[5] = arr[5].substring(endRed + 1);
        arr[5] = arr[5].replaceAll(" ", "");
        if (arr[5].length() != 0) {
            String[] redNum = arr[5].split(",");
            for (String s : redNum) {
                red.add(Integer.parseInt(s));
            }
        }
        int row = 0;
        int col = 0;
        int curNum = 0;

        for (int i = 6; i < arr.length; i++) {
            String[] temp = arr[i].split(",");
            for (String num : temp) {

                if (num.equals("_")) {
                    curNum = -1;
                    tailPuzzle.tailPuzzle[row][col] = new Tile("white", curNum);
                    tailPuzzle.blankTile=new Pair<>(row,col);
                } else {
                    curNum = Integer.parseInt(num);
                    if (red.contains(curNum)) {
                        tailPuzzle.tailPuzzle[row][col] = new Tile("red", curNum);
                    } else if (black.contains(curNum)) {
                        tailPuzzle.tailPuzzle[row][col] = new Tile("black", curNum);
                    } else {
                        tailPuzzle.tailPuzzle[row][col] = new Tile("green", curNum);
                    }
                }

                col++;
            }
            col = 0;
            row++;
        }

        return tailPuzzle;
    }
    public TilePuzzleNode createEndNode(){
        int row=Integer.parseInt(num[0]);
        int col =Integer.parseInt(num[1]);
        TilePuzzleNode tilePuzzleNode = new TilePuzzleNode(row,col);
        int num=1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j <col; j++) {
                tilePuzzleNode.tailPuzzle[i][j] = new Tile("",num);
                num++;
            }
        }
        tilePuzzleNode.tailPuzzle[row-1][col-1].val=-1;
        return tilePuzzleNode;
    }


//    public static void main(String[] args) {
//        try {
//            FileReader fileReader = new FileReader();
//
//            TilePuzzleNode tailPuzzle1 = fileReader.createStartNode();
//            TilePuzzleNode tailPuzzle = fileReader.createEndNode();
//        }catch (Exception e){
//
//        }
//    }
}
