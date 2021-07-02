package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class LeetCode_114 {

    /*Definition for a binary tree node.*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
    class Solution {

        public void flatten(TreeNode root) {
//            flattenByRecursion(root);
            flattenByIteration(root);
        }

        private TreeNode flattenByRecursion(TreeNode root) {
            if (root == null){
                return root;
            }
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode tail = null;
            if (left != null){
                tail = flattenByRecursion(left);
                root.right = left;
                root.left = null;
            }
            if (tail != null){
                tail.right = right;
            }else {
                tail = root;
            }
            if (right != null){
                return flattenByRecursion(right);
            }else {
                return tail;
            }

        }

        private void flattenByIteration(TreeNode root) {
            Stack<TreeNode> stk = new Stack<>();
            TreeNode tmpRoot = root;
            while ( tmpRoot != null){
                TreeNode right = tmpRoot.right;
                TreeNode left = tmpRoot.left;

                if (right != null){
                    stk.push(right);
                }
                if (left != null){
                    stk.push(left);
                    tmpRoot.left = null;
                    tmpRoot.right = left;
                }else if (right == null){
                    tmpRoot.right = stk.isEmpty() ? null : stk.pop();;
                    tmpRoot = tmpRoot.right;
                    continue;
                }

                tmpRoot = stk.isEmpty() ? null : stk.pop();
            }

        }

        /**
         * 采用原地算法--额外空间占用为O(1)
         * @param root
         */
        public void flattenByIterationO1(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.left != null) {
                    TreeNode next = curr.left;
                    TreeNode predecessor = next;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    predecessor.right = curr.right;
                    curr.left = null;
                    curr.right = next;
                }
                curr = curr.right;
            }
        }


    }


}
