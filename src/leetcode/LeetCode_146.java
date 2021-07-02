package leetcode;


import java.util.*;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 3000
 * 0 <= value <= 104
 * 最多调用 3 * 104 次 get 和 put
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 */
public class LeetCode_146 {

    static class DoubleLinkedNode<K, V>{
        K key;
        V value;
        DoubleLinkedNode<K, V> prev;
        DoubleLinkedNode<K, V> next;

        public DoubleLinkedNode() {
        }

        public DoubleLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DoubleLinkedNode<?, ?> that = (DoubleLinkedNode<?, ?>) o;
            return Objects.equals(key, that.key);
        }

    }
    static class LRUCache {
        // >=1
        private int capacity;
        private DoubleLinkedNode<Integer, Integer> head;
        private DoubleLinkedNode<Integer, Integer> tail;
        private Map<Integer, DoubleLinkedNode<Integer, Integer>> valueMap;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.valueMap = new HashMap<>(capacity);
            this.head = new DoubleLinkedNode<>();
            this.tail = new DoubleLinkedNode<>();
        }

        public int get(int key) {
            DoubleLinkedNode<Integer, Integer> node = valueMap.get(key);
            if (node == null){
                return -1;
            }
            moveToTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            DoubleLinkedNode<Integer, Integer> node = valueMap.get(key);
            if (node != null){
                node.value = value;
                moveToTail(node);
                return;
            }
            node = new DoubleLinkedNode<>(key, value);
            valueMap.put(key, node);
            addNode(node);
            if (valueMap.size() > capacity){
                valueMap.remove(head.next.key);
                removeHead();
            }
            return;
        }

        private void moveToTail(DoubleLinkedNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addNode(node);
        }

        private void removeHead() {
            head.next = head.next.next;
            head.next.prev = head;
        }

        private void addNode(DoubleLinkedNode<Integer, Integer> node){
            if (head.next == null){
                head.next = node;
                node.prev = head;
                tail.prev = node;
                node.next = tail;
                return;
            }
            tail.prev.next = node;
            node.prev = tail.prev;

            node.next = tail;
            tail.prev = node;
            return;
        }

    }


    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = super.get(key);
            return value == null ? -1:value;
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


    public static void main(String[] args) {
//["LRUCache","put","put","put","put","get","get"]
//[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(2));
    }
}
