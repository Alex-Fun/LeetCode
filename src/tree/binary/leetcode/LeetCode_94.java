package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 */
public class LeetCode_94 {

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
         * 中序遍历
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> list = new ArrayList<>();
//            inorderTraversalByRecursion(list, root);
            inorderTraversalByIteration(list, root);
            return list;
        }

        private void inorderTraversalByRecursion(List<Integer> list, TreeNode tmpRoot) {
            if (tmpRoot == null){
                return ;
            }
            inorderTraversalByRecursion(list, tmpRoot.left);
            list.add(tmpRoot.val);
            inorderTraversalByRecursion(list, tmpRoot.right);
        }

        /**
         * 基于递归逻辑改写的迭代逻辑（不一定是最简方案），思路较为通用，首推通用逻辑
         * @param list
         * @param root
         * @return
         */
        public void inorderTraversalByIteration(List<Integer> list, TreeNode root) {
            Stack<TreeNode> stk = new Stack<TreeNode>();
            //若root == null && stk.isEmpty() 意味着当前轮递归停止且 递归方法栈无待处理的方法任务，整体结束
            while (root != null || !stk.isEmpty()) {
                //此子循环模拟对left的递归 inorderTraversalByRecursion(list, tmpRoot.left);
                //root != null 即为 递归停止并return 的判断条件
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                //stk.pop方法等效模拟了方法栈返回的结果
                root = stk.pop();
                list.add(root.val);
                //切换为right，继续模拟递归
                root = root.right;
            }
        }


        /**
         * 想到的初始方案，方案能实现，但是过于复杂，且思路不够通用
         * @param list
         * @param root
         */
        private void inorderTraversalByIteration0(List<Integer> list, TreeNode root){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode tmpRoot = root;
            while (tmpRoot != null){
                //模拟递归嵌套
                while (true){
                    stack.push(tmpRoot);
                    if (tmpRoot.left == null){
                        break;
                    }
                    tmpRoot = tmpRoot.left;
                }
                //当前轮递归停止后处理逻辑
                list.add(tmpRoot.val);
                //切换下一轮递归
                if (tmpRoot.right != null){
                    tmpRoot = tmpRoot.right;
                }else {
                    tmpRoot = stack.isEmpty() ? null : stack.pop();
                }
            }
        }

        private void inorder(List<Integer> list, TreeNode root){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode tmpRoot = root;
            while (tmpRoot != null || !stack.isEmpty()){
                stack.push(tmpRoot);
                tmpRoot = tmpRoot.left;

                if (tmpRoot == null){
                    tmpRoot = stack.pop();
                    list.add(tmpRoot.val);
                    tmpRoot = tmpRoot.right;
                }

            }
        }



    }


}
