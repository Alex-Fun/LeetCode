package tree.binary.leetcode;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
public class LeetCode_102 {

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
        public List<List<Integer>> levelOrderTraversal0(TreeNode root) {
            List<List<Integer>> list = new LinkedList<>();
            if (root == null){
                return list;
            }
            Queue<TreeNode> stk = new LinkedList<>();
            stk.offer(root);
            int levelSize = stk.size();
            List<Integer> level = new ArrayList<>(levelSize);
            while (!stk.isEmpty()){
                root = stk.poll();
                level.add(root.val);
                levelSize--;
                if (root.left != null){
                    stk.offer(root.left);
                }
                if (root.right != null){
                    stk.offer(root.right);
                }
                if (levelSize == 0){
                    //当前层遍历结束，开始下一层
                    list.add(level);
                    level = new ArrayList<>(stk.size());
                }
            }
            return list;
        }

        public List<List<Integer>> levelOrderTraversal1(TreeNode root) {
            List<List<Integer>> list = new LinkedList<>();
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
                list.add(0, level);
                level = new ArrayList<>(stk.size());
            }
            return list;
        }


        public List<List<Integer>> levelOrderTraversalByRecursion(TreeNode root) {
            List<List<Integer>> list = new LinkedList<>();
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
            level.add(0, root.val);
            if (root.left != null){
                levelOrderTraversalByRecursion(levelIndex+1, root.left, list);
            }
            if (root.right != null){
                levelOrderTraversalByRecursion(levelIndex+1, root.right, list);
            }
        }



    }


}
