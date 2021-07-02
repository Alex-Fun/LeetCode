package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 */
public class LeetCode_450 {

    /*Definition for a binary tree node.*/
    public static class TreeNode {
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

    }
    static class Solution {

        public TreeNode deleteNode(TreeNode root, int key) {
            return deleteByReversal(root, key);

        }

        public TreeNode deleteByReversal(TreeNode root, int key){
            if (root == null){
                return null;
            }
            TreeNode tmp = null;
            if (root.val > key){
                tmp = deleteByReversal(root.left, key);
                if (tmp == null){
                    root.left = null;
                }
                return root;
            }else if (root.val < key){
                tmp = deleteByReversal(root.right, key);
                if (tmp == null){
                    root.right = null;
                }
                return root;
            }

            if (root.right != null){
                TreeNode successor = this.successor(root);
                root.val = successor.val;
            }else if (root.left != null){
                root.val = root.left.val;
                root.right = root.left.right;
                root.left = root.left.left;
            }else {
                root = null;
            }
            return root;
        }

        //successor 后继子节点
        public TreeNode successor(TreeNode node){
            TreeNode pre = node.right;
            int val;
            if (node.right.left == null){
                node.right = node.right.right;
                return pre;
            }
            node = node.right;
            while (node.left != null){
                pre = node;
                node = node.left;
            }
            pre.left = node.right;
            return node;
        }



    }

    public static void main(String[] args) {

        Integer[] array = {5,3,6,2,4,null,7};
        System.out.println();
        TreeNode root = LeetCodeTools.rebuildByIntArray(array);
        Solution solution = new Solution();
        TreeNode treeNode = solution.deleteNode(root, 7);
        System.out.println(treeNode);
    }

}
