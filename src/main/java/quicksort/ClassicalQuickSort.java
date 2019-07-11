package quicksort;

public class ClassicalQuickSort {

    public static void quickSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //开启快速排序
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //基准数的索引
            int cur = partition(arr, start, end);
            //排序cur左边的
            quickSort(arr, start, cur - 1);
            //排序cur右边的
            quickSort(arr, cur + 1, end);
        }
    }

    /**
     * 一趟排序，以arr[left]为基准数，小于等于它的放左边，大于等于它的放右边
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left, int right) {

        //记录基准数
        int pivot = arr[left];
        while (left < right) {

            //缩小右边界
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            //右边的小数顶掉左边的大数
            if (left < right) {
                arr[left++] = arr[right];
            }

            //缩小左边界
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //左边的大数顶掉右边的小数
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        //最终left的所在被基准数顶替，返回基准数
        arr[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 77, 5, 34, 0, 8};
        quickSort(arr);
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
