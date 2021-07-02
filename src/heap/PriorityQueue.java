package heap;

import java.util.Comparator;

public class PriorityQueue<E> {

    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        this(null, comparator);
    }

    public PriorityQueue(E[] elements, Comparator<E> comparator) {
        this.heap = new BinaryHeap<>(elements, comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    public void clear(){
        heap.clear();
    }

    public void offer(E element){
        heap.add(element);
    }

    public E poll(){
        return heap.remove();
    }

    public E peek(){
        return heap.get();
    }


}
