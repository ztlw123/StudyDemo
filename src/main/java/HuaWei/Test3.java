package HuaWei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author zjh
 * @Date 2019/08/14,18:50
 */
public class Test3 {
    public static void main(String[] args){
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        int[][] result = matrix(matrix(matrix(A, B), B), B);
        int[][] res = matrix(A, matrix(matrix(B, B), B));
        for(int i=0; i<3; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        for(int i=0; i<3; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }

    public static int[][] matrix(int[][] A, int[][] B) {
        int[][] result = new int[3][3];
        for(int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                result[i][j] = A[i][0]*B[0][j] + A[i][1]*B[1][j] + A[i][2]*B[2][j];
            }
        }
        return result;
    }

    public static void count(int n) {
        int s_next = 0;
        int count = 0;
        for(int i=1; i<=n; i++) {
            if((i-1)%2 == 0) {
                s_next += 2;
            }
            else {
                s_next += 1;
            }
            count += s_next;
        }

        System.out.println(count);
    }
}
