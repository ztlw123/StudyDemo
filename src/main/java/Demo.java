import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Demo {

    public static void main(String[] args) {
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3l;
//        System.out.println(c==d);   //true
//        System.out.println(e==f);   //false;
//        System.out.println(c==(a+b));   //true;
//        System.out.println(c.equals(a+b));  //true;
//        System.out.println(g==(a+b));  //true;
//        System.out.println(g.equals(a+b));  //false
//

        Integer a =1;
        Integer b = Integer.valueOf(1);
        Integer c = new Integer(1);
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(a == c);
        int[] arr = {8,7,6,5,4,3,2,1};
//        int[] arr = new int[100000];
//        for(int i=100000; i>0; i--) {
//            arr[arr.length-i] = i;
//        }
//        pupple(arr);
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println((int)new Random().nextInt(100));
        System.out.println(new Date());

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.push(2);
        list.addLast(3);
        System.out.println(list.toString());
    }

    public static void quickSort0722(int[] a, int left, int right) {
        if(right <= left)
            return;

        int randomPos = new Random().nextInt(right-left) + left;
        int temp = a[left];
        a[left] = a[randomPos];
        a[randomPos] = temp;

        int l = left;
        int r = right;
        int key = a[left];

        while (l<r) {
            while (l<r && a[r]>=key)
                r--;
            a[l] = a[r];

            while (l<r && a[l]<=key)
                l++;
            a[r] = a[l];
        }
        a[r] = key;

        quickSort0722(a, left, r-1);
        quickSort0722(a, r+1, right);
    }

    public static void quickSort(int[] a, int left, int right) {
        if(right<=left)
            return;

        int l = left;
        int r = right;
        int key = a[l];

        while (l<r) {
            while (key<=a[r] && l<r)
                r--;
            a[l] = a[r];

            while (key>=a[l] && l<r)
                l++;
            a[r] = a[l];
        }
        a[r] = key;

        quickSort(a, left                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        , r-1);
        quickSort(a, r+1, right);
    }

    public static void pupple(int[] a) {
        for(int i=0; i<a.length; i++) {
            for (int j=0; j<a.length-i-1; j++) {
                if(a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

}

