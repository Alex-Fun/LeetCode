package heap;

import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null) {
            size = 0;
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            this.elements = (E[]) new Object[Math.max(elements.length, DEFAULT_CAPACITY)];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    /**
     * 由下向上进行下滤
     */
    private void heapify() {
        /*//由上向下进行上滤，效率较低，O(nlgn)
        for (int i = 0; i < size; i++) {
            siftUp(i);
        }*/

        //由下向上进行下滤，效率较高，O(n)
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            //主动清空数组，节省内存空间
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        //判断参数是否为空
        elementNotNullCheck(element);

        //判断容量，是否需要扩容
        ensureCapacity(size + 1);

        elements[size] = element;
        siftUp(size++);
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            //1.5*elements.length
            int newLength = this.elements.length + this.elements.length >> 1;
            E[] newElements = (E[]) new Object[newLength];

//            newElements = Arrays.copyOf(this.elements, newLength);

            for (int i = 0; i < this.elements.length; i++) {
                newElements[i] = elements[i];
            }
            this.elements = newElements;
        }

    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }


    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        elements[0] = elements[size - 1];
        elements[--size] = null;
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        if (isEmpty()) {
            elements[0] = element;
            size++;
            return null;
        }
        E root = elements[0];
        elements[0] = element;
        siftDown(0);
        return root;
    }

    private void siftUp(int index) {
        E element = elements[index];
        int parentIndex;
        while (index > 0) {
            //计算父节点坐标
            parentIndex = (index - 1) >> 1;
            if (super.compare(elements[index], elements[parentIndex]) <= 0) {
                break;
            }
            elements[index] = elements[parentIndex];
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void siftDown(int index) {
        E element = elements[index];
        //有size/2个非叶子节点
        while (index < size >> 1) {
            //必有左子节点
            int childIndex = index << 1 + 1;
            E child = elements[childIndex];
            //若 右节点存在 且 右节点大/优先于左节点
            int rightIndex = childIndex + 1;
            if (rightIndex < size && super.compare(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[childIndex];
            }
            if (super.compare(element, child) >= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }


    private void emptyCheck() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("elements must not be null");
        }
    }
}
