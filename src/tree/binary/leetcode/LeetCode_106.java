package tree.binary.leetcode;

import java.util.HashMap;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class LeetCode_106 {

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
        //增加两个成员变量，配合使用坐标指针法
        private int[] in;
        private int[] post;
        // <element, index>
        private HashMap<Integer, Integer> inorderElementMap;


        /**
         * [a, b, c, d, e, f, g]
         * @param inorder [d,b,e,  a,  f,c,g]
         * @param postorder [d,e,b,  f,g,c,  a]
         * @return
         */
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.in = inorder;
            this.post = postorder;
            this.calculateMap();
            return rebuild(0, 0, in.length);
        }

        /**
         * @param iIn inorder子区间起始位在原pre中的索引
         * @param iPost postorder子区间起始位在原post中的索引
         * @param N 子区间的长度
         * @return
         */
        private TreeNode rebuild(int iIn, int iPost, int N){
            if (N < 1){
                return null;
            }
            TreeNode tmpRoot = new TreeNode(this.post[iPost + N - 1]);
            if (N == 1){
                return tmpRoot;
            }
            Integer tmpRootIndex = this.inorderElementMap.get(tmpRoot.val);
            tmpRoot.left = rebuild(iIn, iPost, tmpRootIndex-iIn);
            tmpRoot.right = rebuild(tmpRootIndex+1, iPost+tmpRootIndex-iIn, iIn+N-1-tmpRootIndex);
            return tmpRoot;
        }

        private void calculateMap(){
            inorderElementMap = new HashMap<>(this.in.length);
            for (int i = 0; i < this.in.length; i++) {
                inorderElementMap.put(this.in[i], i);
            }
        }

    }

}
