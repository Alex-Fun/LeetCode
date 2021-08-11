package sort.common;

import java.util.LinkedList;
import java.util.List;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        //生成stepList
        List<Integer> stepList = getStep();
        //按照不同step形成矩阵
        for (Integer step : stepList) {
            //形成矩阵
            for (Integer begin = 0; begin < step; begin++) {
                //矩阵内部轮询每列进行排序
                //insertSort
                for (int current = begin + step; current < array.length; current += step) {
                    int cur = current;
                    for (int j = cur - step; j >= 0 && cmp(array[cur], array[j]) < 0; j -= step) {
                        swap(cur, cur - step);
                    }
                }

            }
        }
        //整体完成sort
    }


    private List<Integer> getStep() {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i < array.length; i <<= 1) {
            list.add(0, i);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        System.out.println(a[1]);
        System.out.println(a[1]++);
        System.out.println(a[1]);
        System.out.println(a[1]);
        Integer integer = Integer.valueOf(1);
        integer++;
        System.out.println(integer);
    }
}
