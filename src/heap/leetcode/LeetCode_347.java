package heap.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class LeetCode_347 {

    public int[] topKFrequent(int[] nums, int k) {
        return countByHeap(nums, k);
    }


    private Map.Entry<Integer, Integer>[] elements;
    private int size;

    private int[] countByHeap(int[] nums, int k) {
        //利用hashmap统计元素次数
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(nums[i], count);
        }

        //heap
        elements = new Map.Entry[k];
        size = k;
        int j = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (j < k) {
                elements[j++] = entry;
            }
            if (j == k) {
                j++;
                heapify();
            } else if (j > k) {
                //replace
                if (elements[0].getValue() >= entry.getValue()) {
                    continue;
                }
                elements[0] = entry;
                siftDown(0);
            }
        }

        int[] topk = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            topk[i] = elements[0].getKey();
            elements[0] = elements[size - 1];
            elements[size - 1] = null;
            size--;
            siftDown(0);
        }
        return topk;
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        Map.Entry<Integer, Integer> element = elements[index];
        //循环下滤
        while (index < size >> 1) {
            //左子节点
            int childIndex = (index << 1) + 1;
            Map.Entry<Integer, Integer> child = elements[childIndex];

            //可能有右子节点
            int rightIndex = childIndex + 1;
            if (rightIndex < size && elements[rightIndex].getValue() < child.getValue()) {
                childIndex = rightIndex;
                child = elements[childIndex];
            }
            if (element.getValue() <= child.getValue()) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        int[] ints = new LeetCode_347().topKFrequent(nums, k);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

}
