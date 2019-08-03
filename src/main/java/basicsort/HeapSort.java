package basicsort;

/**
 * 堆排序
 */
public class HeapSort {

    public static void heapSort(int[] arr) {

        if(arr==null||arr.length<2){
            return;
        }
        //构建大根堆结构
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    /**
     * 往上冒的过程
     * @param arr
     * @param i
     */
    public static void heapInsert(int[] arr, int i) {

        while (arr[(i - 1) / 2] < arr[i]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     * 往下沉的过程
     * @param arr
     * @param i
     * @param size
     */
    public static void heapify(int[] arr, int i, int size) {

        int left = i * 2 + 1;
        while (left < size) {
            //较孩子的值的索引
            int largestChild = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largestChild = arr[largestChild] > arr[i] ? largestChild : i;
            //说明当前节点已经是最大的了，不往下沉
            if (largestChild == i) {
                break;
            }
            swap(arr, i, largestChild);
            i = largestChild;
            left = i * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 6, 5, 0, 7, 9};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
