package quicksort;

public class ClassicalQuickSort {

    public static void quickSort(int[] arr){

        if (arr == null || arr.length < 2) {
            return;
        }
        //开启快速排序
        quickSort(arr,0,arr.length-1);
    }

    public static void quickSort(int[] arr,int l,int r){

        if(l<r){
            int cur=partition(arr,l,r);
            //排序cur左边的
            quickSort(arr,l,cur-1);
            //排序cur右边的
            quickSort(arr,cur+1,r);
        }

    }

    /**
     * 一趟排序，以r为基准数，给l-r上的数排序
     * 小于等于r的方左边；大于r的放右边
     * 保证more和more的右边的数大于arr[r]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition(int[] arr,int l,int r){

        //当前数组的右边界
        int more=r+1;
        int num=arr[r];
        while (l<more){

            if(arr[l]<=num){
                l++;
            }else{
                //--more，保证每一次排序，从more开始一定是大于num的
                //交换后并不知道arr[l]>x还是arr[l]<=x
                //因此l还是在原地
                swap(arr,l,--more);
            }
        }
//        if(num>=arr[more]){
//            return r;
//        }
//        swap(arr,more-1,r);
        //more-1必然是最终基准数所在的索引
        return more-1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr={1,3,4,6,9};
        quickSort(arr);
        System.out.println();
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
