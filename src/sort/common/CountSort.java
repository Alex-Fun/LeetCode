package sort.common;

public class CountSort extends Sort<Integer> {

    @Override
    //stable写法
    protected void sort() {
        //确定array的max和min
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        //以array值为索引，将元素放入count数组
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        //形成sorted数组
        int[] sorted = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sorted[--counts[array[i] - min]] = array[i];
        }
        //将sorted数组放入array
        for (int i = 0; i < array.length; i++) {
            array[i] = sorted[i];
        }
    }


    //非stable写法
    protected void sort0() {
        //确定array的max和min
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        //以array值为索引，将元素放入count数组
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }
        //按照count数组索引顺序将有效值放入array，排序完成
        int ai = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                counts[i]--;
                array[ai++] = i + min;
            }
        }
    }
}
