package tree.binary.leetcode;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 *
 * 例如，
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和值: 2
 * 你应该返回如下子树:
 *
 *       2
 *      / \
 *     1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
 *
 */
public class LeetCode_700 {

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

        public TreeNode searchBST(TreeNode root, int val) {
//            return searchByRecursion(root, val);
            return searchByIteration(root, val);

        }

        public TreeNode searchByRecursion(TreeNode root, int val){
            if (root == null || root.val == val){
                return root;
            }
            return root.val > val ? searchByRecursion(root.left, val) : searchByRecursion(root.right, val);
        }

        public TreeNode searchByIteration(TreeNode root, int val){
            if (root == null || root.val == val){
                return root;
            }
            while (root.val != val){
                if (root.val > val){
                    root = root.left;
                }else {
                    root = root.right;
                }
                if (root == null){
                    return null;
                }
            }
            return root;

        }




    }


}
