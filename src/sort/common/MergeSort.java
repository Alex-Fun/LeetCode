package sort.common;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (begin + 1 >= end) {
            return;
        }
        //divide
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        //merge
        merge(begin, mid, end);

    }

    /**
     * merge [begin, mid), [mid, end)
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        //原数组待赋值位置
        int position = begin;
        //leftArray待赋值索引
        int li = 0;
        //leftArray逻辑长度
        int leftLength = mid - begin;
        //rightArray待赋值索引
        int ri = mid;

        //方便merge，独立出左边Array
        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = array[begin + i];
        }

/*
        while (li < leftLength && ri < end){
            if (cmp(leftArray[li], array[ri]) <= 0){
                array[position++] = leftArray[li++];
            }else {
                array[position++] = array[ri++];
            }
        }
        //若rightArray先merge完，则leftArray剩余部分直接赋值进array
        if (ri == end){
            while (li < leftLength){
                array[position++] = leftArray[li++];
            }
        }
        //若leftArray先merge完(li == leftLength)，则rightArray剩余部分不用处理，直接完成
        return;
*/

        // 如果左边还没有结束
        while (li < leftLength) {
            if (ri < end && cmp(leftArray[li], array[ri]) > 0) {
                array[position++] = array[ri++];
            } else {
                array[position++] = leftArray[li++];
            }

        }


    }
}
