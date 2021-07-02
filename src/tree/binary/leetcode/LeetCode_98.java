package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 *
 */
public class LeetCode_98 {

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

    }
    class Solution {

        public boolean isValidBST(TreeNode root) {
            return checkValidByReversal(root, null, null);
//            return checkValidByInOrder(root);
        }

        public boolean checkValidByReversal(TreeNode root, TreeNode leftPre, TreeNode rightPre){
            if (root == null){
                return true;
            }
            if (root.left != null){
                if (root.left.val >= root.val || (leftPre != null && leftPre.val >= root.left.val)){
                    return false;
                }
            }
            if (root.right != null){
                if (root.right.val <= root.val || (rightPre != null && rightPre.val <= root.right.val)){
                    return false;
                }
            }
            return checkValidByReversal(root.left, leftPre, root) && checkValidByReversal(root.right, root, rightPre);
        }

        private Integer prevVal;

        /**
         * 套用中序遍历，BST的中序遍历结果是递增的
         * @param root
         * @return
         */
        public boolean checkValidByInOrder(TreeNode root){
            if (root == null){
                return true;
            }
            if (!checkValidByInOrder(root.left)){
                return false;
            }
            if (prevVal == null || prevVal < root.val){
                prevVal = root.val;
            }else {
                return false;
            }
            if (!checkValidByInOrder(root.right)){
                return false;
            }
            return true;
        }





    }


}
