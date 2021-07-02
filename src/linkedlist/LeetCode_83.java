package linkedlist;

/**
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class LeetCode_83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode node = head;
        while (node.next != null){
            if (node.next.val == node.val){
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }
        return head;
    }
}
