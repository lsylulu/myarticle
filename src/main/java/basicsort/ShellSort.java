package basicsort;

import java.util.Arrays;

/**
 * 希尔排序
 * 是根据插入排序改出来的，插入有两种方式，一种是直接交换；一种是右移腾位置
 * 时间复杂度 O(n^(1.3—2))
 */
public class ShellSort {

    public static void shellSort(int[] arr) {
//        int n = arr.length;
//        int gap = n / 2;
//        //一次while就是一趟希尔排序
//        while (gap >= 1) {
//            //每次比较i之前的所有与之对应的子序列
//            for (int i = gap; i < arr.length; i++) {
//                int j = 0;
//                int temp = arr[i];
//                //遍历每一个子序列
//                for (j = i - gap; j >= 0 && temp < arr[j]; j = j - gap) {
//                    arr[j + gap] = arr[j];
//                }
//                arr[j + gap] = temp;
//            }
//            gap = gap / 2;
//        }
        if (arr == null || arr.length < 2) {
            return;
        }
        int gap = arr.length / 2;

        while(gap>=1){
            //从第i个及第i个以后开始比较
            for (int i = gap; i < arr.length; i++) {
                //先拿到i-1的那个数字，并从那个位置开始比较
                for (int j = i - gap; j >= 0; j=j-gap) {
                    if (arr[j] > arr[j + gap]) {
                        swap(arr, j, j + gap);
                    }
                }
            }
            gap = gap / 2;
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {1, 26, 89, 23, 15, 12, 36};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

