package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 *
 *
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 *
 *
 * 提示：
 *
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 *
 */
public class LeetCode_99 {

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

        public void recoverTree(TreeNode root) {
//            recoverByInorder(root);
            recoverByInorder2(root);
        }

        TreeNode x = null;
        TreeNode y = null;
        TreeNode prevNode = null;

        public void recoverByInorder2(TreeNode root){
            inorder2(root);
            int val = x.val;
            x.val = y.val;
            y.val = val;
        }

        public boolean inorder2(TreeNode root) {
            if (root == null) {
                return false;
            }
            if (inorder2(root.left)) {
                return true;
            }
            if (prevNode != null && prevNode.val > root.val){
                y = root;
                if (x == null){
                    x = prevNode;
                }else {
                    return true;
                }
            }
            prevNode = root;
            if (inorder2(root.right)) {
                return true;
            }
            return false;
        }

        public void recoverByInorder(TreeNode root){
            List<TreeNode> nodes = new ArrayList<>();
            inorder(root, nodes);
            swapTwo(nodes);
        }

        public void inorder(TreeNode root, List<TreeNode> nodes) {
            if (root == null) {
                return;
            }
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }

        public void swapTwo(List<TreeNode> nodes) {
            int n = nodes.size();
            TreeNode x = null, y = null;
            for (int i = 0; i < n - 1; ++i) {
                if (nodes.get(i + 1).val < nodes.get(i).val) {
                    y = nodes.get(i + 1);
                    if (x == null) {
                        x = nodes.get(i);
                    } else {
                        break;
                    }
                }
            }
            int val = x.val;
            x.val = y.val;
            y.val = val;
        }


    }


}
