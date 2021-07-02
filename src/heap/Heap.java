package heap;

public interface Heap<E> {

    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 清空
    void clear();

    // 添加元素
    void add(E element);

    // 获得堆顶元素
    E get();

    // 删除堆顶元素
    E remove();

    E replace(E element);

}
