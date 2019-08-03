package basicsort;

public class DirectSelectionSort {

    public static void directSelectionSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            //记录当前最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        directSelectionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
