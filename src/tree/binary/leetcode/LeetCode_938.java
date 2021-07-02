package tree.binary.leetcode;

import java.util.Stack;

/**
 * 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * 示例 2：
 *
 *
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 *
 */
public class LeetCode_938 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {

        public int rangeSumBST(TreeNode root, int low, int high) {
            return getSumByInorder(root, low, high);

        }

        public int getSumByInorder(TreeNode root, int low, int high){
            int sum = 0;
            if (root.left != null && root.val >= low){
                sum += getSumByInorder(root.left, low, high);
            }
            if (root.val >= low && root.val <= high){
                sum += root.val;
            }
            if (root.right != null && root.val <= high){
                sum += getSumByInorder(root.right, low, high);
            }
            return sum;
        }


    }


}