package tree.binary.leetcode;

/**
 * 701. 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * 解释：另一个满足题目要求可以通过的树是：
 *
 * 示例 2：
 *
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 * 示例 3：
 *
 * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 *
 *
 *
 *
 * 提示：
 *
 * 给定的树上的节点数介于 0 和 10^4 之间
 * 每个节点都有一个唯一整数值，取值范围从 0 到 10^8
 * -10^8 <= val <= 10^8
 * 新值和原始二叉搜索树中的任意节点值都不同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 *
 */
public class LeetCode_701 {

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

        public TreeNode insertIntoBST(TreeNode root, int val) {
//            return insertByRecursion(root, val);
            return insertByIteration(root, val);
        }

        public TreeNode insertByRecursion(TreeNode root, int val){
            if (root == null) {
                root = new TreeNode(val);
                return root;
            }
            if (root.val > val) {
                if (root.left == null){
                    root.left = new TreeNode(val);
                }else {
                    insertByRecursion(root.left, val);
                }
            } else {
                if (root.right == null){
                    root.right = new TreeNode(val);
                }else {
                    insertByRecursion(root.right, val);
                }
            }
            return root;

        }

        public TreeNode insertByIteration(TreeNode root, int val){
            TreeNode newNode = new TreeNode(val);
            if (root == null){
                root = newNode;
                return root;
            }
            TreeNode tmpRoot = root;
            while (true){
                if (tmpRoot.val > val){
                    if (tmpRoot.left == null){
                        tmpRoot.left = newNode;
                        break;
                    }else {
                        tmpRoot = tmpRoot.left;
                    }
                }else {
                    if (tmpRoot.right == null){
                        tmpRoot.right = newNode;
                        break;
                    }else {
                        tmpRoot = tmpRoot.right;
                    }
                }
            }
            return root;

        }





    }


}
