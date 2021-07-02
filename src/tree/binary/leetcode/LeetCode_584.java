package tree.binary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 *
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 * 进阶：
 *
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 示例 1：
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 * 提示：
 *
 * N 叉树的高度小于或等于 1000
 * 节点总数在范围 [0, 10^4] 内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 */
public class LeetCode_584 {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    class Solution {

        private List<Integer> list;

        public List<Integer> preorder(Node root) {
//            return preorderByRecursion(root);
            return preorderByIteration(root);
        }

        public List<Integer> preorderByRecursion(Node root){
            list = new ArrayList<>();
            if (root != null){
                doRecursion(root);
            }
            return list;
        }

        public void doRecursion(Node tmpRoot){
            if (tmpRoot == null){
                return;
            }
            list.add(tmpRoot.val);
            List<Node> children = tmpRoot.children;
            if (children == null){
                return ;
            }
            for (int i = 0; i < children.size(); i++) {
                doRecursion(children.get(i));
            }
        }

        public List<Integer> preorderByIteration(Node root){
            this.list = new ArrayList<>();
            if (root == null){
                return list;
            }
            Stack<Node> stk = new Stack();
            stk.push(root);
            while (!stk.isEmpty()){
                root = stk.pop();
                list.add(root.val);
                List<Node> children = root.children;
                if (children == null && children.isEmpty()){
                    continue;
                }
                for (int i = children.size() - 1; i >= 0; i--) {
                    stk.push(children.get(i));
                }
            }
            return list;
        }




    }


}
