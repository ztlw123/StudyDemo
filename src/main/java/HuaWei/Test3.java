package HuaWei;

import java.util.Scanner;

/**
 * @Author zjh
 * @Date 2019/08/14,18:50
 */
public class Test3 {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];

        int result = 1;

        for(int i=0; i<N; i++) {
            arr[i] = scanner.nextInt();

            if(i>0) {
                if(arr[i] > arr[i-1])
                    result++;
            }
        }

        System.out.println(result);

    }
}
