package sort.common;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(maxIndex, begin) <= 0) {
                    maxIndex = begin;
                }
            }
            if (maxIndex != end) {
                swap(maxIndex, end);
            }
        }
    }
}
