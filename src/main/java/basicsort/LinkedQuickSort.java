package basicsort;

/**
 * 使用链表实现快排
 */
public class LinkedQuickSort {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void quickSort(ListNode head) {

        quickSort(head, null);
    }

    public static void quickSort(ListNode head, ListNode tail) {

        if (head == tail || head.next == tail) {
            return;
        }

        ListNode cur = partition(head, tail);
        quickSort(head, cur);
        quickSort(cur.next, tail);
    }

    public static ListNode partition(ListNode head, ListNode tail) {

        //left是当前链表对于次基准数来说的左边界，left及left左边的必然比head小
        ListNode left = head, cur = head.next;
        int pivot = head.val;
        while (cur != tail) {
            if (cur.val < pivot) {
                left = left.next;
                swap(left, cur);
            }
            cur = cur.next;
        }
        swap(head, left);
        return left;
    }

    public static void swap(ListNode left, ListNode right) {
        int tmp = left.val;
        left.val = right.val;
        right.val = tmp;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(2);
        head.next=new ListNode(1);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(6);
        head.next.next.next.next=new ListNode(5);
        quickSort(head);
        ListNode cur=head;
        while (cur!=null){
            if(cur.next==null){
                System.out.print(cur.val);
                return;
            }
            System.out.print(cur.val+"->");
            cur=cur.next;
        }
    }
}
