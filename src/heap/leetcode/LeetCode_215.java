package heap.leetcode;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 */
public class LeetCode_215 {

    public int findKthLargest(int[] nums, int k) {
        // TODO: 2021/7/1/0001 再用快排实现一次
//        return findKth(nums, k);
        return findKthBySelf(nums, k);



    }

    /**
     * 利用jdk优先级队列，堆排序实现
     *
     * @param nums
     * @param k
     * @return
     */
    private int findKth(int[] nums, int k) {
        List<Integer> list = Arrays.stream(Arrays.copyOf(nums, k)).boxed().collect(Collectors.toList());
        //jdk的优先级队列，默认排序是 compare(e1, e2) > 0时，e2靠前，默认是按照数值升序排列
        //所以可以认为jdk中，默认数值小的优先级更高
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(list);
//        for (int i = 0; i < k; i++) {
//            queue.offer(nums[i]);
//        }

        for (int i = k; i < nums.length; i++) {
            if (queue.peek() < nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }

    private int[] elements;

    private int findKthBySelf(int[] nums, int k) {
        elements = new int[k];
        for (int i = 0; i < k; i++) {
            elements[i] = nums[i];
        }
        //heapify
        for (int i = (elements.length >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > elements[0]) {
                //replace
                elements[0] = nums[i];
                siftDown(0);
            }
        }
        return elements[0];
    }

    private void siftDown(int index) {
        int element = elements[index];
        while (index < elements.length >> 1) {
            int childIndex = (index << 1) + 1;
            int child = elements[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < elements.length && elements[rightIndex] < child){
                childIndex = rightIndex;
                child = elements[childIndex];
            }
            if (element <= child){
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }



}
