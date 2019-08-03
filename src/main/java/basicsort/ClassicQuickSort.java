package basicsort;

/**
 * 经典快排
 */
public class ClassicQuickSort {

    public static void quickSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //开启快速排序
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int begin, int end) {

        if (begin < end) {
            //开启一趟排序，返回基准数所在的索引
            int cur = partition(arr, begin, end);
            //排基准数左边的
            quickSort(arr, begin, cur - 1);
            //排基准数右边的
            quickSort(arr, cur + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {

        //取基准数
        int pivot = arr[begin];
        while (begin < end) {
            //基准数在左边，从右开始找
            while (begin < end && arr[end] > pivot) {
                end--;
            }
            if (begin < end) {
                arr[begin++] = arr[end];
            }

            while (begin < end && arr[begin] < pivot) {
                begin++;
            }
            if (begin < end) {
                arr[end--] = arr[begin];
            }
        }
        arr[begin] = pivot;
        return begin;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
