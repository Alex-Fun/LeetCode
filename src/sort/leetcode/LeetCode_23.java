package sort.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class LeetCode_23 {


    public ListNode mergeKLists(ListNode[] lists) {
        // return mergeByHeap1(lists);
        //return mergeByHeap2(lists);
//        return mergeByMerge2(lists);
        return mergeByBinaryMerge(lists, 0, lists.length - 1);
    }

    public ListNode mergeByBinaryMerge(ListNode[] lists, int begin, int end){
        // 防止lists = []的情况
        if(begin > end){
            return null;
        }
        if(begin == end){
            return lists[begin];
        }
        int mid = begin + end >> 1;
        return mergeTwoListNode(mergeByBinaryMerge(lists, begin, mid), mergeByBinaryMerge(lists, mid+1, end));
    }



    /**
     * 单个测试中能通过，但速度最慢，在LeetCode上提交会超时
     *
     * @param lists
     * @return
     */
    private ListNode mergeByMerge2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        List<ListNode> nodes = new LinkedList();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                nodes.add(lists[i]);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        ListNode min = null;
        while (!nodes.isEmpty()) {
            //min
            ListNode node;
            for (int i = 0; i < nodes.size(); i++) {
                node = nodes.get(i);

                min = min == null || min.val > node.val ? node : min;
            }
            //add to tail
            if (min == null) {
                break;
            }
            tail.next = min;
            tail = tail.next;
            //move to next
            nodes.remove(min);
            if (min.next != null) {
                nodes.add(min.next);
            }
            min = null;
        }
        return head.next;
    }

    /**
     * @param lists
     * @return
     */
    private ListNode mergeByMerge1(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }

        ListNode l1 = lists[0];
        ListNode l2 = null;
        for(int i = 1; i < lists.length; i++){
            l2 = lists[i];
            l1 = mergeTwoListNode(l1, l2);
        }
        return l1;
    }

    private ListNode mergeTwoListNode(ListNode l1, ListNode l2){
        ListNode head = new ListNode();
        ListNode tail = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public static void main(String[] args) {
        //[[1,4,5],[1,3,4],[2,6]]
        sort.leetcode.LeetCode_23.ListNode[] lists = {new sort.leetcode.LeetCode_23.ListNode(1, new sort.leetcode.LeetCode_23.ListNode(4, new sort.leetcode.LeetCode_23.ListNode(5))),
                new sort.leetcode.LeetCode_23.ListNode(1, new sort.leetcode.LeetCode_23.ListNode(3, new sort.leetcode.LeetCode_23.ListNode(4))),
                new sort.leetcode.LeetCode_23.ListNode(2, new sort.leetcode.LeetCode_23.ListNode(6))};
        ;
        sort.leetcode.LeetCode_23.ListNode listNode = new sort.leetcode.LeetCode_23().mergeKLists(lists);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
