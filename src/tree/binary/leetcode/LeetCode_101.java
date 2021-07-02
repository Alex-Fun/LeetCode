package tree.binary.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 */
public class LeetCode_101 {

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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
    static class Solution {

        public boolean isSymmetric(TreeNode root) {
            // TODO: 2021/4/16/0016
            //层序遍历，每一层判断该层size是否偶数，元素是否对称，否则return false
//            return this.judgeByIteration(root);
            return this.check(root, root);
        }

        public boolean checkByIteration(TreeNode root) {
            if (root == null){
                return true;
            }
            TreeNode u = root.left;
            TreeNode v = root.right;

            Queue<TreeNode> q = new LinkedList<TreeNode>();
            q.offer(u);
            q.offer(v);
            while (!q.isEmpty()) {
                u = q.poll();
                v = q.poll();
                if (u == null && v == null) {
                    continue;
                }
                if ((u == null || v == null) || (u.val != v.val)) {
                    return false;
                }

                q.offer(u.left);
                q.offer(v.right);

                q.offer(u.right);
                q.offer(v.left);
            }
            return true;
        }


        public boolean judgeByIteration(TreeNode root){
            if (root == null){
                return true;
            }
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left == null && right == null){
                return true;
            }else if (left == null || right == null){
                return false;
            }else if (left.val != right.val){
                return false;
            }
            List<TreeNode> leftQueue = new LinkedList<>();
            List<TreeNode> rightQueue = new LinkedList<>();
            leftQueue.add(left);
            rightQueue.add(right);
            while (!leftQueue.isEmpty() && !rightQueue.isEmpty()){
                int size = leftQueue.size();
//                int size = leftQueue.size();
                for (int i = 0; i < size; i++) {
                    left = leftQueue.remove(0);
                    right = rightQueue.remove(0);
                    if (left.left == null && right.right == null){
                    }else if (left.left == null || right.right == null){
                        return false;
                    }else if (left.left.val != right.right.val){
                        return false;
                    }else {
                        leftQueue.add(left.left);
                        rightQueue.add(right.right);
                    }
                    if (left.right == null && right.left == null){
                    }else if (left.right == null || right.left == null){
                        return false;
                    }else if (left.right.val != right.left.val){
                        return false;
                    }else {
                        leftQueue.add(left.right);
                        rightQueue.add(right.left);
                    }
                }

            }
            return true;
        }



        public boolean check(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }

    }




}
