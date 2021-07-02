package tree.binary.leetcode;

import java.util.*;

/**
 * 590. N 叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的 后序遍历 。
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
 * 输出：[5,6,3,2,4,1]
 * 示例 2：
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 *
 * 提示：
 *
 * N 叉树的高度小于或等于 1000
 * 节点总数在范围 [0, 10^4] 内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 */
public class LeetCode_590 {

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

        public List<Integer> postorder(Node root) {
//            return postorderByRecursion(root);
            return postorderByIteration(root);
        }

        public List<Integer> postorderByRecursion(Node tmpRoot){
            list = new ArrayList<>();
            this.doRecursion(tmpRoot);
            return list;
        }

        private void doRecursion(Node tmpRoot) {
            if (tmpRoot == null){
                return;
            }
            List<Node> children = tmpRoot.children;
            if (children != null && !children.isEmpty()){
                for (int i = 0; i < children.size(); i++) {
                    doRecursion(children.get(i));
                }
            }
            list.add(tmpRoot.val);
        }

        /**
         * 结合二叉树后续遍历迭代法思路形成的N叉树后续遍历迭代法思路，但较为复杂
         * @param tmpRoot
         * @return
         */
        public List<Integer> postorderByIteration(Node tmpRoot){
            list = new ArrayList<>();
            if (tmpRoot == null){
                return list;
            }
            Stack<Node> stk = new Stack<>();
            stk.push(tmpRoot);
            Stack<Node> lastStk = new Stack<>();
            Node last = null;
            while (!stk.isEmpty()){
                tmpRoot = stk.pop();
                List<Node> children = tmpRoot.children;
                if (children.isEmpty()){
                    list.add(tmpRoot.val);
                    continue;
                }else if (last == tmpRoot){
                    if (!lastStk.isEmpty()){
                        last = lastStk.pop();
                    }
                    list.add(tmpRoot.val);
                    continue;
                }else {
                    stk.push(tmpRoot);
                    if (last != null){
                        lastStk.push(last);
                    }
                    last = tmpRoot;
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stk.push(children.get(i));
                    }
                }
            }
            LinkedList list = null;
            return list;
        }

        /**
         * LeetCode官方解答给出的迭代法思路，较为巧妙。可依据这个思路改造二叉树遍历思路
         * 来源链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/solution/ncha-shu-de-hou-xu-bian-li-by-leetcode/
         * @param root
         * @return
         */
        public List<Integer> postorderByIteration1(Node root){
            LinkedList<Node> stack = new LinkedList<>();
            LinkedList<Integer> output = new LinkedList<>();
            if (root == null) {
                return output;
            }

            stack.add(root);
            while (!stack.isEmpty()) {
                Node node = stack.pollLast();
                output.addFirst(node.val);
                for (Node item : node.children) {
                    if (item != null) {
                        stack.add(item);
                    }
                }
            }
            return output;
        }





    }


}
