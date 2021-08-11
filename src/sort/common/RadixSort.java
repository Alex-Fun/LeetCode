package sort.common;

public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //max,min  通过-min 可以避免负数干扰索引位置
        int max = 0;
        int min = max;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }

        // 个位数: array[i] / 1 % 10 = 3
        // 十位数：array[i] / 10 % 10 = 9
        // 百位数：array[i] / 100 % 10 = 5
        // 千位数：array[i] / 1000 % 10 = ...
        for (int divider = 1; divider <= max - min; divider *= 10) {
            //开辟内存空间，存储次数 0-9
            int[] count = new int[10];
            // 统计每个整数出现的次数
            for (int i = 0; i < array.length; i++) {
                count[(array[i] - min) / divider % 10]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            //sort array
            int[] sort = new int[array.length];
            for (int i = array.length - 1; i >= 0; i--) {
                int i1 = (array[i] - min) / divider % 10;
                sort[--count[i1]] = array[i];
            }

            for (int i = 0; i < sort.length; i++) {
                array[i] = sort[i];
            }
        }

        System.out.println(array);


    }

    public static void main(String[] args) {
        int a = (91231 - 12000) / 10 % 10;
        System.out.println(a);
    }

}
