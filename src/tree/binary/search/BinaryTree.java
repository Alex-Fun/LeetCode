package tree.binary.search;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> {

    protected Node<E> root;
    protected int size;

    protected Node<E> root() {
        return this.root;
    }

    protected int size() {
        return this.size;
    }

    protected boolean isEmpty() {
        return size == 0;
    }

    protected void clear() {
        size = 0;
        root = null;
    }


    //preorderTraversal
    private void preorder(Visitor<E> visitor) {
        this.preorder(this.root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    //inorderTraversal
    public void inorder(Visitor<E> visitor) {
        this.inorder(this.root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    //postorderTraversal
    public void postorder(Visitor<E> visitor) {
        this.postorder(this.root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    //levelOrderTraversal
    public void levelOrder(Visitor<E> visitor) {
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node<E> top = queue.poll();
            visitor.visit(top.element);
            if (top.left != null) {
                queue.offer(top.left);
            }
            if (top.right != null) {
                queue.offer(top.right);
            }
        }
    }

    //predecessor 前驱节点
    public Node<E> predecessor(Node<E> node){
        if (node == null){
            return null;
        }
        if (node.left != null){
            node = node.left;
            while (node.right != null){
                node = node.right;
            }
            return node;
        }
        Node<E> parent = node.parent;
       /* while (parent != null){
            node = parent;
            parent = node.parent;
            if ( node == parent.right){
                return parent;
            }
        }
        return null;*/
        while (parent != null && node == parent.left){
            node = parent;
            parent = node.parent;
        }
        return parent;


    }

    //successor 后继节点
    public Node<E> successor(Node<E> node){
        if (node == null){
            return null;
        }
        if (node.right != null){
            node = node.right;
            while (node.left != null){
                node = node.left;
            }
            return node;
        }
        Node<E> parent = node.parent;
        /*while (parent != null){
            if (parent.left == node){
               return parent;
            }
            node = parent;
            parent = node.parent;
        }
        return null;*/
        while (parent != null && parent.left != node){
            parent = node.parent;
        }
        return parent;
    }

    //isComplete 是否为完全二叉树
    public boolean isComplete() {
        if (this.root == null) {
            return false;
        }
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.add(this.root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> top = queue.poll();

            if (top.left != null) {
                if (leaf) {
                    return false;
                }
                queue.offer(top.left);
            } else {
                leaf = true;
            }

            if (top.right != null) {
                if (leaf) {
                    return false;
                }
                queue.offer(top.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    public boolean isComplete2() {
        if (this.root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                // 后面遍历的节点都必须是叶子节点
                leaf = true;
            }

        }
        return true;

    }

    //height  height()
    public int height() {
        return height(this.root);
    }

    public int height(Node<E> node) {
        int height = 0;
        Queue<Node<E>> queue = new LinkedList();
        queue.offer(node);
        int levelSize = queue.size();

        while (!queue.isEmpty()) {
            Node<E> top = queue.poll();
            levelSize--;
            if (top.left != null) {
                queue.offer(top.left);
            }
            if (top.right != null) {
                queue.offer(top.right);
            }
            if (levelSize == 0) {
                // 意味着即将要访问下一层
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    public int height2() {
        return heightByRecursion(this.root);
    }

    public int heightByRecursion(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightByRecursion(node.left), heightByRecursion(node.right));
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, parent);
    }


    protected static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        public boolean isLeftChild(){
            return parent != null && this == this.parent.left;
        }
        public boolean isRightChild(){
            return parent != null && this == this.parent.right;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }

    public static interface Visitor<E> {
        void visit(E element);
    }
}
