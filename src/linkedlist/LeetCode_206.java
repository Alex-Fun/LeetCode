package linkedlist;

import java.util.List;

/**
 *
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 *
 */
public class LeetCode_206 {

    public ListNode reverseList(ListNode head) {
        //参考栈的反转
        ListNode newHead = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    public ListNode recursionReverseList(ListNode head) {
        //避免初试node为null
        if (head == null){
            return null;
        }
        //遍历到链表末尾边界
        if (head.next == null){
            return head;
        }
        ListNode newHead = recursionReverseList(head.next);
        head.next.next = head;
        //给反转后的链表设置末尾边界
        head.next = null;
        return newHead;
    }


}
