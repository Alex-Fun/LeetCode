package tree.binary.leetcode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 */
public class LeetCode_144 {

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

        /**
         * 前序遍历
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> list = new ArrayList<>();
//            preorderTraversalByRecursion(list, root);
            preorderTraversalByIteration(list, root);
            return list;
        }

        private void preorderTraversalByRecursion(List<Integer> list, TreeNode tmpRoot) {
            if (tmpRoot == null){
                return ;
            }
            list.add(tmpRoot.val);
            preorderTraversalByRecursion(list, tmpRoot.left);
            preorderTraversalByRecursion(list, tmpRoot.right);
        }

        /**
         * 基于递归逻辑改写的迭代逻辑（不一定是最简方案），思路较为通用，首推通用逻辑
         * @param list
         * @param root
         */
        private void preorderTraversalByIteration(List<Integer> list, TreeNode root){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode tmpRoot = root;
            while (!stack.isEmpty() || tmpRoot != null) {
                while (tmpRoot != null) {
                    list.add(tmpRoot.val);
                    stack.push(tmpRoot);
                    tmpRoot = tmpRoot.left;
                }
                tmpRoot = stack.pop();
                tmpRoot = tmpRoot.right;
            }
        }


        /**
         * 想到的初始方案，方案能实现，虽然相对简单，但思路不够通用
         * @param list
         * @param root
         */
        private void preorderTraversalByIteration0(List<Integer> list, TreeNode root){
            LinkedList<TreeNode> stack = new LinkedList<>();
            TreeNode tmpRoot = root;
            while (tmpRoot != null ){
                list.add(tmpRoot.val);
                if (tmpRoot.right != null){
                    stack.push(tmpRoot.right);
                }
                //为了避免stack.isEmpty时pop报错，使用poll代替
                tmpRoot = tmpRoot.left != null ? tmpRoot.left : stack.poll();
            }
        }

        /**
         * 新迭代方案，仿写递归得到的
         * @param list
         * @param root
         */
        private void flattenByIteration1(List<Integer> list, TreeNode root) {
            Stack<TreeNode> stk = new Stack<>();
            TreeNode tmpRoot = root;
            while ( tmpRoot != null){
                list.add(tmpRoot.val);
                if (tmpRoot.right != null){
                    stk.push(tmpRoot.right);
                }
                if (tmpRoot.left != null){
                    stk.push(tmpRoot.left);
                }
                tmpRoot = stk.isEmpty() ? null : stk.pop();
            }

        }



    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        List<Integer> integers = new Solution().preorderTraversal(root);
        System.out.println(integers);
//        LinkedList<Object> list = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            list.push(i);
//        }
//        System.out.println(list);
//        System.out.println(list.pop());
//        System.out.println(list.poll());
    }

}
