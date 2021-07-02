package tree.binary.leetcode;

import java.util.*;

/**
 * 559. N 叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 *
 *
 * 提示：
 *
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 104] 之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 */
public class LeetCode_559 {

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


        public int maxDepth(Node root) {
//            return maxDepthByRecursion(root);
            return maxDepthByIteration(root);
        }

        private int maxDepthByRecursion(Node root) {
            return this.doRecursion(root);
        }

        private int doRecursion(Node root){
            int depth = 0;
            if (root == null){
                return depth;
            }
            depth++;
            List<Node> children = root.children;
            if (children == null || children.isEmpty()){
                return depth;
            }
            for (int i = 0; i < children.size(); i++) {
                depth = Math.max(depth, doRecursion(children.get(i)));
            }
            return depth + 1;
        }

        private int maxDepthByIteration(Node root) {
            int maxDepth = 0;
            if (root == null){
                return maxDepth;
            }
            Queue<Node> queue =new LinkedList<>();
            queue.offer(root);
            int size;
            while (!queue.isEmpty()){
                size = queue.size();
                for (int i = size; i > 0; i--) {
                    Node tmpRoot = queue.poll();
                    List<Node> children = tmpRoot.children;
                    if (children == null || children.isEmpty()){
                        continue;
                    }
                    for (int j = 0; j < children.size(); j++) {
                        Node child = children.get(j);
                        if (child != null){
                            queue.offer(child);
                        }
                    }

                }
                maxDepth++;
            }
            return maxDepth;
        }


    }


}
