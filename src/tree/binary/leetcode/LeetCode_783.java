package tree.binary.leetcode;

import java.util.*;

/**
 * 783. 二叉搜索树节点最小距离
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 * 差值是一个正数，其数值等于两值之差的绝对值
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 *
 */
public class LeetCode_783 {


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

        public int minDiffInBST(TreeNode root) {
            return inorderTraversalByIteration(root);
        }


        public int inorderTraversalByIteration(TreeNode root) {
            int preVal = -1;
            int minDiff = Integer.MAX_VALUE;
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
                if (preVal != -1){
                    minDiff = Math.min(minDiff, Math.abs(root.val - preVal));
                }
                preVal = root.val;
                //切换为right，继续模拟递归
                root = root.right;
            }
            return minDiff;
        }


    }


}
