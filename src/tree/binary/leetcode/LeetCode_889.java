package tree.binary.leetcode;

import javax.swing.tree.TreeNode;
import java.util.Arrays;

/**
 * 889. 根据前序和后序遍历构造二叉树
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 *
 *  pre 和 post 遍历中的值是不同的正整数。
 *
 * 示例：
 *
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *
 * 提示：
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 */
public class LeetCode_889 {

    /*Definition for a binary tree node.*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class Solution {
        //增加两个成员变量，配合使用坐标指针法
        private int[] pre;
        private int[] post;

        /**
         * [a, b, c, d, e, f, g]
         * @param pre [a,  b,d,e,  c,f,g]
         * @param post [d,e,b,  f,g,c,  a]
         * @return
         */
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            this.pre = pre;
            this.post = post;
            return rebuild(0, 0, pre.length);
        }

        /**
         * @param iPre pre子区间起始位在原pre中的索引
         * @param iPost post子区间起始位在原post中的索引
         * @param N 子区间的长度
         * @return
         */
        private TreeNode rebuild(int iPre, int iPost, int N){
            if (N <= 0){
                return null;
            }
            //pre[0]==post[length-1],此两个为tmpRoot
            int tmpVal = pre[iPre];
            TreeNode tmpRoot = new TreeNode(tmpVal);
            if (N == 1){
                return tmpRoot;
            }
            //假设pre为有左右两子树（若缺失左子树或右子树，不影响实际规律，只是描述会变）
            //pre[1]为pre左子树的tmpRoot， 通过pre[1]锚定post左子树的tmpRoot。
            //同理，通过post[length-2]锚定pre右子树的tmpRoot
            int l = 1;//左子树长度
            for (; l < N; l++) {
                if (post[iPost+l-1]==pre[iPre+1]){
                    break;
                }
            }
            tmpRoot.left = rebuild(iPre+1, iPost, l);
            tmpRoot.right = rebuild(iPre+1+l, iPost+l, N-1-l);
            return tmpRoot;
        }
    }

}
