package quicksort;

public class ExcellentQuickSort {

    public static void quickSort(int[] arr){

        if (arr == null || arr.length < 2) {
            return;
        }
        //开启快速排序
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int l,int r){

        if(l<r){
            //加上这一行就是随机快排
            //每次都把r与l-r上的任意一个数交换
            //让被排序的数组尽可能均分
            swap(arr,l+(int)Math.random()*(r-l+1),r);
            int[] p=partition(arr,l,r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }

    }

    /**
     * 一趟排序，以r为基准数，l是遍历时走的索引
     * less是左边界，more是右边界
     * 最终返回的数组是等于arr[r]的左边界和右边界
     * 一次partition确定了所有等于r的位置
     * @param arr
     * @param l   当前的索引
     * @param r
     * @return
     */
    public static int[] partition(int[] arr,int l,int r){

        //相等区域左边界
        int less=l-1;
        ////相等区域右边界
        int more=r;
        while (l<more){
            //当前索引小于基准数，则将当前arr[l]与左边界的后一个数交换
            //这个if主要是将左边界右扩一个
            if(arr[r]>arr[l]){
                swap(arr,l++,++less);
            }else{
                //当前索引大于等于基准数，则将当前索引与右边界的前一个数交换
                swap(arr,--more,l);
            }
        }
        //最后把arr[r]放到正确位置
        swap(arr,more,r);
        return new int[]{less+1,more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr={4,3,6,9,1};
        quickSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
