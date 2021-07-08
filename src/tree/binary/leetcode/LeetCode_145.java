package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 */
public class LeetCode_145 {

    /*Definition for a binary tree node.*/
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

        /**
         * 后序遍历
         *
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            ArrayList<Integer> list = new ArrayList<>();
//            postorderTraversalByRecursion(list, root);
            postorderTraversalByIteration(list, root);
            return list;
        }

        private void postorderTraversalByRecursion(List<Integer> list, TreeNode tmpRoot) {
            if (tmpRoot == null) {
                return;
            }
            postorderTraversalByRecursion(list, tmpRoot.left);
            postorderTraversalByRecursion(list, tmpRoot.right);
            list.add(tmpRoot.val);
        }

        /**
         * 此法缺点，仅适用于二叉树，不适用N叉树
         *
         * @param list
         * @param root
         */
        private void postorderTraversalByIteration(List<Integer> list, TreeNode root) {
            Stack<TreeNode> stk = new Stack<TreeNode>();
            // 通过prevDoneNode记录上一个被记录的node，以判断当前节点的右子节点是否已记录完成
            //      若是则意味着该节点的子节点后续遍历已完成，该记录当前节点了
            TreeNode prevDoneNode = null;
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
                //切换为right，继续模拟递归
                if (root.right == null || root.right == prevDoneNode) {
                    list.add(root.val);
                    prevDoneNode = root;
                    root = null;
                } else {
                    stk.push(root);
                    root = root.right;
                }
            }
            return;
        }

        private void postorderTraversalByIteration1(List<Integer> list, TreeNode root) {
            Stack<TreeNode> stk = new Stack<TreeNode>();
            TreeNode prev = null;
            stk.push(root);
            while (!stk.isEmpty()) {
                TreeNode peek = stk.peek();
                boolean isLeaf = peek.left == null && peek.right == null;
                boolean prevIsPeekChild = prev != null && (prev == peek.left || prev == peek.right);
                if (isLeaf || prevIsPeekChild) {
                    prev = stk.pop();
                    list.add(prev.val);
                } else {
                    if (peek.right != null) {
                        stk.push(peek.right);
                    }
                    if (peek.left != null) {
                        stk.push(peek.left);
                    }
                }

            }
        }


    }

}
