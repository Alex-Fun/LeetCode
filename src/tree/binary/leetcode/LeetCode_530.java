package tree.binary.leetcode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 *
 */
public class LeetCode_530 {


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


        public int getMinimumDifference(TreeNode root) {
            return getByInorder(root);
        }


        int minDiff = Integer.MAX_VALUE;
        int preVal = Integer.MAX_VALUE;

        public int getByInorder(TreeNode root){
            if (root == null){
                return Integer.MAX_VALUE;
            }
            int leftDiff = getByInorder(root.left);
            int tmpDiff = root.val > preVal ? root.val-preVal : preVal-root.val;
            preVal = root.val;
            tmpDiff = tmpDiff < leftDiff ? tmpDiff : leftDiff;
            int rightDiff = getByInorder(root.right);
            tmpDiff = tmpDiff < rightDiff ? tmpDiff : rightDiff;
            return tmpDiff;

        }

    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);
    }


}
