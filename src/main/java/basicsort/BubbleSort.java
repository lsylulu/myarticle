package basicsort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void bubbleSort(int arr[]) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //外层循环表示当前数字
        for (int i = 0; i < arr.length; i++) {
            //内层循环限制具体每次的趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
