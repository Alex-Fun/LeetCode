package tree.binary.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeTools {

    public static LeetCode_450.TreeNode rebuildByIntArray(Integer[] array) {
        Queue<LeetCode_450.TreeNode> treeQueue = new LinkedList<>();
        if (array[0] == null) {
            return null;
        }
        LeetCode_450.TreeNode root = new LeetCode_450.TreeNode(array[0]);
        treeQueue.offer(root);
        for (int i = 1; i < array.length; ) {
            LeetCode_450.TreeNode node = treeQueue.poll();
            if (node == null) {
                continue;
            }
            node.left = array[i] == null ? null : new LeetCode_450.TreeNode(array[i]);
            node.right = array[i + 1] == null ? null : new LeetCode_450.TreeNode(array[i + 1]);
            treeQueue.offer(node.left);
            treeQueue.offer(node.right);
            i = i + 2;
        }
        return root;
    }


    public static class TreeNode {
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
}
