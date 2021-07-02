package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 */
public class LeetCode_107 {

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
         * 层序遍历
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrderTraversal(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null){
                return list;
            }
            List<Integer> level = new ArrayList<>();
            Queue<TreeNode> stk = new LinkedList<>();
            Queue<TreeNode> leafStk = new LinkedList<>();
            Queue<TreeNode> tmpStk = null;
            TreeNode tmpRoot = root;
            stk.offer(tmpRoot);
            while (!stk.isEmpty()){
                tmpRoot = stk.poll();
                level.add(tmpRoot.val);
                if (tmpRoot.left != null){
                    leafStk.offer(tmpRoot.left);
                }
                if (tmpRoot.right != null){
                    leafStk.offer(tmpRoot.right);
                }
                if (stk.isEmpty()){
                    //当前行结束
                    list.add(level);
                    level = new ArrayList<>();
                    //切换为子节点行
                    tmpStk = stk;
                    stk = leafStk;
                    leafStk = tmpStk;
                }
            }
            return list;
        }

        public List<List<Integer>> levelOrderTraversal1(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null){
                return list;
            }
            Queue<TreeNode> stk = new LinkedList<>();
            TreeNode tmpRoot = root;
            stk.offer(tmpRoot);
            List<Integer> level = new ArrayList<>(stk.size());
            while (!stk.isEmpty()){
                //stk中所有元素即为当前层所有元素
                int size = stk.size();
                for (int i = 0; i < size; i++) {
                    tmpRoot = stk.poll();
                    level.add(tmpRoot.val);
                    if (tmpRoot.left != null){
                        stk.offer(tmpRoot.left);
                    }
                    if (tmpRoot.right != null){
                        stk.offer(tmpRoot.right);
                    }
                }
                list.add(level);
                level = new ArrayList<>(stk.size());
            }
            return list;
        }

        /**
         * 采用递归实现
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrderTraversalByRecursion(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null){
                return list;
            }
            levelOrderTraversalByRecursion(0, root, list);
            return list;
        }
        public void levelOrderTraversalByRecursion(int levelIndex, TreeNode root, List<List<Integer>> list) {
            if (levelIndex >= list.size()){
                list.add(new ArrayList<>());
            }
            List<Integer> level = list.get(levelIndex);
            level.add(root.val);
            if (root.left != null){
                levelOrderTraversalByRecursion(levelIndex+1, root.left, list);
            }
            if (root.right != null){
                levelOrderTraversalByRecursion(levelIndex+1, root.right, list);
            }
        }



    }


}
