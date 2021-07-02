package tree.avl.entity;

import tree.binary.search.BinarySearchTree;

import java.util.Comparator;

public class AVLTree<E> extends BalancedBinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                rebalance(node);
                //整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node){
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //更新高度
                updateHeight(node);
            } else {
                //恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> node) {
        return new AVLNode<E>(element, (AVLNode<E>) node);
    }

    private void rebalance(Node<E> grand) {
        AVLNode<E> parent = ((AVLNode<E>) grand).tallerChild();
        AVLNode<E> node = parent.tallerChild();
        if (parent.isLeftChild()) {
            // L
            if (node.isLeftChild()) {
                // LL-对Grand右旋
                rotateRight(grand);
            } else {
                // LR-先对Parent左旋，后对Grand右旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // R
            if (node.isLeftChild()) {
                // RL-先对Parent右旋，后对Grand左旋
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR-对Grand左旋
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡
     * 此方法作为思路的补充
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        super.afterRotate(grand, parent, child);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f){
        super.rotate(r, b, c, d, e, f);
        // 更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }



    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;

    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, AVLNode<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            return Math.abs(leftHeight - rightHeight);
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            this.height = 1 + Math.max(leftHeight, rightHeight);
        }

        public AVLNode<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return (AVLNode<E>) left;
            }
            if (leftHeight < rightHeight) {
                return (AVLNode<E>) right;
            }
            return isLeftChild() ? (AVLNode<E>) left : (AVLNode<E>) right;
        }


    }


}
