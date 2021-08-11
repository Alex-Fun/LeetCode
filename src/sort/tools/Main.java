package sort.tools;

import sort.common.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] array = {7, 3, 25, 8, 62, 7, 42, 5, 17, 33, 5, 48, 36, 17, 4, 52};
//        array = Integers.random(100000, -100000, Integer.MAX_VALUE);
        array = Integers.random(1000, -100000, 100000);
//        array = Integers.random(1000, 1000, 100000);

        testSorts(array,
				new RadixSort(),
//				new InsertionSort1(),
//				new InsertionSort2(),
                new InsertionSort3(),
                new SelectionSort(),
                new HeapSort(),
                new MergeSort(),
//                new BubbleSort3(),
                new QuickSort(),
//                new ShellSort()
                new CountSort()
        );
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }
}
