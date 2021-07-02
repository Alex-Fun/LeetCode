package tree.redblack.entity;

import tree.avl.entity.BalancedBinarySearchTree;

import javax.xml.soap.Node;
import java.util.Comparator;

public class RedBlackTree<E> extends BalancedBinarySearchTree<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

//    @Override
    protected void afterRemove2(Node<E> node){

        Node<E> parent = node.parent;
        if (parent == null){
//            black(node);
            return;
        }
        if (isRed(node)){
            return;
        }
        if (node.left != null){
            black(node.left);
        }else if (node.right != null){
            black(node.right);
        }else {
            //node为black叶子节点
            //下溢出

            /*
            1.实际被删的一定是叶子节点，首次调用afterRemove()情况下node是已经被删节点，
            所以parent.left或right定有一个为null，为null的位置则是node原来的位置
            （因为正常的RBTree叶子节点的父节点一定是度为2）。
            2.在递归调用afterRemove()情况下，node就变为实际被删节点的祖先节点（伴随着下溢情况出现），
            此时的node的parent实际不缺少左右子节点，所以需要通过node.isLeftChild()判断
             */
            boolean left = parent.left == null || node.isLeftChild();
            Node<E> sibling = left ? parent.right : parent.left;
            if (isBlack(sibling)){
                if (sibling.left != null){
                    rotateRight(parent);
                    black(sibling.left);
                    color(sibling, colorOf(parent));
                    black(parent);
                }else if (sibling.right != null){
                    rotateLeft(sibling);
                    rotateRight(parent);
                    color(sibling.right, colorOf(parent));
                    black(parent);
                }else {
                    //parent降级
                    boolean parentBlack = isBlack(parent);
                    red(sibling);
                    black(parent);
                    if (parentBlack){
                        afterRemove(parent);
                    }
                }
                return;

            }else {
                //兄弟节点为Red
                if (!left){
                    rotateRight(parent);
                    sibling = node.sibling();
                    if (sibling.isLeaf()){
                        red(sibling);
                    }else {
                        if (sibling.right != null){
                            rotateLeft(sibling);
                        }
                        rotateRight(parent);
                        red(parent.parent);
                        black(parent.sibling());

                    }
                }else {

                }


            }


        }


    }


    private Node<E> color(Node<E> node, boolean color){
        if (node == null){
            return node;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node){
        return this.color(node, RED);
    }

    private Node<E> black(Node<E> node){
        return this.color(node, BLACK);
    }

    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isRed(Node<E> node){
        return this.colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node){
        return this.colorOf(node) == BLACK;
    }


    private static class RBNode<E> extends Node<E>{
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
