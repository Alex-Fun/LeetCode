package sort.common;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (begin + 1 >= end) {
            return;
        }
        // 确定轴点位置 O(n)
        int pivotIndex = pivotIndex(begin, end);
        sort(begin, pivotIndex);
        sort(pivotIndex + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        // 备份轴点元素
        T pivot = array[begin];
        // end指向最后一个元素
        end--;

        //选取pivot轴点
        while (begin < end) {
            // 根据轴点分配区域内部元素
            // 左右交替轮询元素
            while (begin < end) {
                if (cmp(array[end], pivot) <= 0) {
                    // 轮到右边，若右边<=轴点值，则右边值挪到左边
                    array[begin++] = array[end];
                    // 轮询方向对换，跳出小循环
                    break;
                } else {
                    end--;
                }
            }

            while (begin < end) {
                if (cmp(array[begin], pivot) >= 0) {
                    // 轮到左边，若左边>=轴点值，则左边挪到右边
                    array[end--] = array[begin];
                    // 轮询方向对换，跳出小循环
                    break;
                } else {
                    begin++;
                }
            }
        }
        // 将轴点元素挪到对相应位置
        array[begin] = pivot;
        return begin;
    }

}
