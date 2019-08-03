package basicsort;

public class MergeSort {

    public static void mergeSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int begin, int end) {

        //说明此时，子问题已经不可拆分了
        if (begin == end) {
            return;
        }
        //类似于(begin+end)/2，只不过不会出现越界的问题
        int mid = begin + ((end - begin) >> 1);
        mergeSort(arr, begin, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    /**
     * 给数组begin-end这一段数据排序
     *
     * @param arr
     * @param begin
     * @param mid
     * @param end
     */
    public static void merge(int[] arr, int begin, int mid, int end) {

        int[] help = new int[end - begin + 1];
        int p1 = begin;
        int p2 = mid + 1;
        int i = 0;
        //归并的过程，始终把小的那个数先放到help中
        while (p1 <= mid && p2 <= end) {
            if (arr[p1] > arr[p2]) {
                help[i++] = arr[p2++];
            } else {
                help[i++] = arr[p1++];
            }
        }

        //以下while必然只发生一个
        while (p1 <= mid) {
            //说明p1到头了，将p2后面的全添加到help中
            help[i++] = arr[p1++];
        }
        while (p2 <= end) {
            help[i++] = arr[p2++];
        }

        //将排好序的这段begin-end赋值到arr中
        for (int j = 0; j < end - begin + 1; j++) {
            arr[begin + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
