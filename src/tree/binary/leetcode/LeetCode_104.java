package tree.binary.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
public class LeetCode_104 {

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

        /**
         * 最大深度
         * @param root
         * @return
         */
        public int maxDepth(TreeNode root) {
//            return maxDepthByBFSIteration(root);
            return maxDepthByRecursion(root);
        }

        public int maxDepthByBFSIteration(TreeNode root){
            if (root == null){
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode tmpRoot = null;
            int size = 0;
            int maxDepth = 0;
            queue.offer(root);
            while (!queue.isEmpty()){
                size = queue.size();
                for (int i = 0; i < size; i++) {
                    tmpRoot = queue.poll();
                    if (tmpRoot.left != null){
                        queue.offer(tmpRoot.left);
                    }
                    if (tmpRoot.right != null){
                        queue.offer(tmpRoot.right);
                    }
                }
                maxDepth++;
            }
            return maxDepth;
        }


        public int maxDepthByRecursion(TreeNode root){
            if (root == null){
                return 0;
            }
            return 1+ Math.max(maxDepthByRecursion(root.left) , maxDepthByRecursion(root.right));
        }




    }


}
