package heap.leetcode;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * <p>
 * <p>
 * 扩展练习：
 * <p>
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-words
 */
public class LeetCode_692 {
    public List<String> topKFrequent(String[] words, int k) {
        return sortByHeap(words, k);
    }

    private Map.Entry<String, Integer>[] elements;
    private int size;

    private List<String> sortByHeap(String[] words, int k) {
        //count
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(words[i], count);
        }
        //heap
        elements = new Map.Entry[k];
        size = 0;
        int j = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (j < k) {
                elements[size++] = entry;
                j++;
            }
            if (j == k) {
                heapify();
                j++;
            } else if (j > k) {
                if (compare(elements[0], entry)) {
                    continue;
                }
                //repalce
                elements[0] = entry;
                siftDown(0);
            }
        }
        //poll heap
        LinkedList<String> list = new LinkedList();
        while (size > 0) {
            list.addFirst(elements[0].getKey());
            size--;
            elements[0] = elements[size];
            elements[size] = null;
            siftDown(0);
        }
        return list;
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {
        Map.Entry<String, Integer> entry = elements[index];
        while (index < size >> 1) {
            int childIndex = (index << 1) + 1;
            Map.Entry<String, Integer> child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size) {
                Map.Entry<String, Integer> rightChild = elements[rightIndex];
                if (compare(child, rightChild)) {
                    childIndex = rightIndex;
                    child = rightChild;
                }
            }
            if (compare(child, entry)) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = entry;
    }

    //return  entry1是否词频更高?
    private boolean compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
        if (entry1.getValue().equals(entry2.getValue())) {
            return entry1.getKey().compareTo(entry2.getKey()) < 0;
        } else {
            return entry1.getValue() - entry2.getValue() > 0;
        }
    }

    public static void main(String[] args) {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> list = new LeetCode_692().topKFrequent(words, 4);
        System.out.println(list);
    }

}
