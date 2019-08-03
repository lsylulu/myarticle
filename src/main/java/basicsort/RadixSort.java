package basicsort;

/**
 * 基数排序
 * 时间复杂度为O(nlog(r)m)
 * r为所采取的基数，而m为堆数
 */
public class RadixSort {


    public static void radixSort(int[] arr) {

        int max = getMax(arr);
        //控制每次根据个位还是十位还是百位来排
        for (int i = 1; max / i > 0; i = i * 10) {

            int[][] buckets = new int[arr.length][10];
            //分配：获取每一位数字(个、十、百、千位...分配到桶子里)
            for (int j = 0; j < arr.length; j++) {
                //获取当前位数上的这个数
                int num = (arr[j] / i) % 10;
                //将其放入桶子里
                buckets[j][num] = arr[j];
            }

            //回收：回收桶子里的元素
            int k = 0;
            //有10个桶子
            for (int j = 0; j < 10; j++) {
                //对每个桶子里的元素进行回收
                for (int l = 0; l < arr.length ; l++) {
                    //如果桶子里面有元素就回收(数据初始化会为0)
                    if (buckets[l][j] != 0) {
                        arr[k++] = buckets[l][j];
                    }
                }
            }
        }
    }

    public static int getMax(int[] arr) {

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {21,56,88,195,3546,1,35,12,6,7};
        radixSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}