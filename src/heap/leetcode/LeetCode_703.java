package heap.leetcode;


import java.util.*;

/**
 * 703. 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * <p>
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * <p>
 * <p>
 * 提示：
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 */
public class LeetCode_703 {
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
    class KthLargest {
        Integer[] elements;
        int size;

        public KthLargest(int k, int[] nums) {
            size = 0;
            elements = new Integer[k];
            k = Math.min(k, nums.length);
            for (int i = 0; i < k; i++) {
                elements[i] = nums[i];
                size++;
            }
            heapify();
            for (int i = k; i < nums.length; i++) {
                add(nums[i]);
            }
        }


        public int add(int val) {
            if (size < elements.length){
                elements[size] = val;
                siftUp(size);
                size++;
                return elements[0];
            }
            if (elements[0] >= val) {
                return elements[0];
            }
            //replace
            elements[0] = val;
            siftDown(0);
            return elements[0];
        }

        private void heapify() {
            for (int i = (size >> 1) - 1; i >= 0; i--) {
                siftDown(i);
            }
        }

        private void siftUp(int index) {
            Integer element = elements[index];
            int parentIndex;
            while (index > 0) {
                //计算父节点坐标
                parentIndex = (index - 1) >> 1;
                if (elements[index] >= elements[parentIndex]) {
                    break;
                }
                elements[index] = elements[parentIndex];
                index = parentIndex;
            }
            elements[index] = element;
        }

        private void siftDown(int index) {
            int element = elements[index];
            while (index < size >> 1) {
                int childIndex = (index << 1) + 1;
                int child = elements[childIndex];
                int rightIndex = childIndex + 1;
                if (rightIndex < size && elements[rightIndex] < child) {
                    childIndex = rightIndex;
                    child = elements[childIndex];
                }

                if (element <= child) {
                    break;
                }
                elements[index] = child;
                index = childIndex;
            }
            elements[index] = element;
        }


    }

    public static void main(String[] args) {
        int[] ints = new int[5];
        System.out.println(ints[0]);
        for (int i = 1; i < 1; i++) {
            System.out.println(1);
        }
    }


}

