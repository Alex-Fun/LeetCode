package sort.common;

public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            for (int cur = begin; cur > 0 && cmp(cur, cur-1) < 0; cur--) {
                swap(cur, cur-1);
            }

        }
    }
}
