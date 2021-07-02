package tree.binary.search;

import tree.binary.leetcode.LeetCode_108;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
        if (this.root == null) {
            this.root = createNode(element, null);
            // 新添加节点之后的处理
            afterAdd(root);
            size++;
            return;
        }
        Node<E> parent = this.root;
        Node<E> current = this.root;
        int compare = 0;
        while (current != null) {
            compare = compare(current.element, element);
            parent = current;
            if (compare > 0) {
                current = current.right;
            } else if (compare < 0) {
                //root值 < node值
                current = current.left;
            } else {
                //root值 == node值
                this.root.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        // 新添加节点之后的处理
        afterAdd(root);
        size++;
    }

    /**
     * 添加node之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {
    }

    Node<E> node(E element) {
        Node<E> current = root;
        int compare = 0;
        while (current != null) {
            compare = this.compare(current.element, element);
            if (compare > 0) {
                current = current.right;
            } else if (compare < 0) {
                current = current.left;
            } else {
                return current;
            }
        }
        return null;
    }

    void remove(E element) {
        this.remove(this.node(element));
    }

    void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        if (node.hasTwoChildren()) {
            // 度为2的节点
            // 找到后继节点
            Node<E> successor = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = successor.element;
            // 删除后继节点
            node = successor;
        }
        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为1
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) {
                // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(node);
        } else if (node.parent != null) {
            //度为0 且 非root节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        } else {
            //度为0 且为root节点
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    /**
     * 删除node之后的调整
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node){
    }

    public Node<E> rebuildFromPreIn(E[] preorder, E[] inorder) {
/*        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
            return null;
        }
        E tmpRootElement = preorder[0];
        Node<E> tmpRoot = new Node<E>(tmpRootElement, null);
        int inorderRootIndex = indexOfInorder(inorder, tmpRootElement);

        int preorderLeftEnd = inorderRootIndex+1;

        //left
        tmpRoot.left = constructFromPreIn(Arrays.copyOfRange(preorder, 1,preorderLeftEnd), Arrays.copyOfRange(inorder, 0, inorderRootIndex));
        tmpRoot.left.parent = tmpRoot;

        //right
        tmpRoot.right = constructFromPreIn(Arrays.copyOfRange(preorder, preorderLeftEnd, preorder.length), Arrays.copyOfRange(inorder, inorderRootIndex+1, inorder.length));
        tmpRoot.left.parent = tmpRoot;
        return tmpRoot;*/
        return rebuildFromPreIn(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private Node<E> rebuildFromPreIn(E[] preorder, int pBegin, int pEnd, E[] inorder, int iBegin, int iEnd){
        if (preorder == null || pEnd < pBegin || inorder == null || iEnd < iBegin){
            return null;
        }
        E tmpRootElement = preorder[pBegin];
        Node<E> tmpRoot = new Node<E>(tmpRootElement, null);
        for (int i = iBegin; i <= iEnd; i++) {
            if (inorder[i] == tmpRootElement){
                //inorder有效区间内第j个，即left长度为j。(inorder[i]为tmpRoot，没有算入)
                int j = i-iBegin;
                tmpRoot.left = rebuildFromPreIn(preorder, pBegin+1, pBegin+j, inorder, iBegin, i-1);
                tmpRoot.right = rebuildFromPreIn(preorder, pBegin+j+1, pEnd, inorder, i+1, iEnd);
                break;
            }
        }
        return tmpRoot;
    }


    public Node<E> rebuildByInPost(E[] inorder, E[] postorder) {
        return rebuildByInPost(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private Node<E> rebuildByInPost(E[] inorder, int iBegin, int iEnd, E[] postorder, int pBegin, int pEnd){
        if (inorder == null || iEnd < iBegin || postorder == null || pEnd < pBegin){
            return null;
        }
        E tmpRootElement = postorder[pEnd];
        Node<E> tmpRoot = new Node<>(tmpRootElement, null);
        for (int i = iBegin; i <= iEnd; i++) {
            if (inorder[i] == tmpRootElement){
                int j = i-iBegin;
                tmpRoot.left = rebuildByInPost(inorder, iBegin, i-1, postorder, pBegin, pBegin+j-1);
                tmpRoot.right = rebuildByInPost(inorder, i+1, iEnd, postorder, pBegin+j, pEnd-1);
                break;
            }
        }
        return tmpRoot;
    }



    boolean contains(E element) {
        return this.node(element) != null;
    }

    /**
     * @return 等效e1-e2的值，即0, e1 = e2; >0, e1 > e2; <0, e1 < e2;
     */
    private int compare(E e1, E e2) {
        if (this.comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }


}
