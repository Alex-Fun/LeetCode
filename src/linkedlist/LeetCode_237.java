package linkedlist;

/**
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class LeetCode_237 {

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val){
            head = head.next;
        }
        ListNode node = head;
        ListNode prev = null;
        while (node !=null){
            if (node.val == val){
                if (node.next != null){
                    node.val = node.next.val;
                    node.next = node.next.next;
                }else {
                    //链接末尾
                    prev.next = null;
                    break;
                }
            }else {
                prev = node;
                node = node.next;
            }
        }
        return head;
    }
}
