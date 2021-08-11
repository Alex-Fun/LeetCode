package sort.common;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    /**
     * 二分法查目标位置，插入
     */
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int index = search(begin);
            insert(begin, index);
        }
    }

    /**
     * 将source位置的元素插入到dest位置
     * @param source
     * @param dest
     */
    private void insert(int source, int dest) {
        T t = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i-1];
        }
        array[dest] = t;
    }

    /**
     * 利用二分搜索找到 index 位置元素的待插入位置
     * 已经排好序数组的区间范围是 [0, index)
     * @param index
     * @return
     */
    private int search(int index) {
        int left = 0;
        int right = index;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cmp = cmp(mid, index);
            if (cmp > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
