package sort.common;

public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            //如果单轮冒泡都没有交换，则已经整体有序，可提前结束
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin - 1, begin) > 0) {
                    swap(begin - 1, begin);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
