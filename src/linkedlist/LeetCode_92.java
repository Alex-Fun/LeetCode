package linkedlist;

/**
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
public class LeetCode_92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        m = m-1;
        n = n-1;
        //参考206反转
        ListNode newHead = null;
        ListNode firstNode = head;
        ListNode mNode = head;
        ListNode nNode = null;
        for (int i = 0; head != null; i++) {
            if (i < m || i > n){
                if (i == m-1){
                    mNode = head;
                }else if (i == n+1){
                    nNode.next = head;
                }
                head = head.next;
                continue;
            }
            if (i == m ){
                nNode = head;
            }

            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;

            head = temp;
        }
        mNode.next = newHead;

        return firstNode;
    }
}
