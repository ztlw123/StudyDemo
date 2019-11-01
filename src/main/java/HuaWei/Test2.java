package HuaWei;


import java.util.*;

public class Test2{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] map = new int[m][n];
        System.out.println(getPath(map, m-1, n-1));
    }

    public static int getPath(int[][] map, int lastRow, int lastCol) {
        if(lastRow == 0 && lastCol == 0)
            return map[0][0];

        if(lastRow == 0)
            return (map[0][lastCol]+getPath(map, lastRow, lastCol-1));
        if(lastCol == 0)
            return (map[lastRow][0]+getPath(map, lastRow-1, lastCol));

        int sum = map[lastRow][lastCol];

        return Math.min(sum+getPath(map, lastRow-1, lastCol), sum+getPath(map, lastRow, lastCol-1));
    }
}

