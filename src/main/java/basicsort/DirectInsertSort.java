package basicsort;

public class DirectInsertSort {

    public static void directInsertSort(int arr[]) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //从第i个及第i个以后开始比较
        for (int i = 1; i < arr.length; i++) {
            //先拿到i-1的那个数字，并从那个位置开始比较
            for (int j = i - 1; j >= 0; j--) {
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
        directInsertSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
