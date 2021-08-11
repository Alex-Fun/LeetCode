package sort.common;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    private int heapSize;

    @Override
    protected void sort() {
        //heapify 大顶堆
        heapSize = array.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 0) {
            //swap 0,size-1
            //size--
            swap(0, --heapSize);
            //siftDown(0)
            siftDown(0);
        }

    }

    private void siftDown(int i) {
        T element = array[i];
        // 缩小范围，仅比较非叶子节点与其子节点关系
        while (i < heapSize >> 1) {
            //左子节点
            int childIndex = (i << 1) + 1;
            T child = array[childIndex];
            //右子节点位置
            int rightIndex = childIndex + 1;
            //选出最大子节点
            if (rightIndex < heapSize && cmp(childIndex, rightIndex) < 0) {
                childIndex = rightIndex;
                child = array[childIndex];
            }
            //判断父子节点谁最大
            if (cmp(element, child) >= 0) {
                break;
            }
            //子节点大于父节点，交换
            array[i] = child;
            i = childIndex;
        }
        array[i] = element;
    }


}
