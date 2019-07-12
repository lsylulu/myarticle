package quicksort;

public class ClassicalQuickSort {

    public static void quickSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        //开启快速排序
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //基准数的索引
            int cur = partition(arr, left, right);
            //排序cur左边的
            quickSort(arr, left, cur - 1);
            //排序cur右边的
            quickSort(arr, cur + 1, right);
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
        int pivot = arr[right];
        while (left < right) {

            //缩小左边界：基准数在右边，必须从左边开始找
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //左边的大数顶掉右边的小数
            if (left < right) {
                arr[right--] = arr[left];
            }

            //缩小右边界
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            //右边的小数顶掉左边的大数
            if (left < right) {
                arr[left++] = arr[right];
            }

        }
        //最终left的所在被基准数顶替，返回基准数
        arr[right] = pivot;
        return right;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        quickSort(arr);
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
