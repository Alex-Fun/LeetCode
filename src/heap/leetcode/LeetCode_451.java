package heap.leetcode;


import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 */
public class LeetCode_451 {

    public String frequencySort(String s) {
        return sortByHeap(s);
//        return sortBy(s);
    }

    private String sortBy(String s){
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            Integer count = map.get(key);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(key, count);
        }
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue()-o1.getValue());
        StringBuilder builder = new StringBuilder(s.length());
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Character, Integer> entry = list.get(i);
            for (Integer j = 0; j < entry.getValue(); j++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();

    }

    private String sortByHeap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            Integer count = map.get(key);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(key, count);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            Map.Entry<Character, Integer> top = queue.remove();
            for (Integer i = 0; i < top.getValue(); i++) {
                builder.append(top.getKey());
            }
        }
        return builder.toString();
    }



    public static void main(String[] args) {
        String tree = new LeetCode_451().frequencySort("tree");
        System.out.println(tree);
    }

}
