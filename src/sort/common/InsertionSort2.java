package sort.common;

public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

    /**
     * 先定位，后移位
     */
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            T t = array[cur];
            while (cur > 0 && cmp(array[cur - 1], t) > 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = t;
        }
    }

}
