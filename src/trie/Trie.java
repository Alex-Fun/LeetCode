package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie<V> {

    private Node<V> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V get(String key){
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    public V add(String key, V value) {
        checkKey(key);
        if (this.root == null){
            this.root = new Node<>(null, null);
        }
        Node<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            node.childMap = node.childMap == null ? new HashMap<>() : node.childMap;
            if (node.childMap.get(c) == null) {
                node.childMap.put(c, new Node<>(node, c));
            }
            node = node.childMap.get(c);
        }
        //已存在相同key
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        //新增key
        size++;
        node.value = value;
        node.word = true;
        return null;
    }

    public V remove(String key) {
        Node<V> node = node(key);
        if (node == null || !node.word) {
            return null;
        }
        size--;
        V value = node.value;
        node.value = null;
        node.word = false;
        //检查是否有子节点
        if (node.childMap != null && !node.childMap.isEmpty()){
            return value;
        }

        //检查父节点是否可删
        Node<V> parent = node.parent;
        while (parent != null) {
            parent.childMap.remove(node.key);
            if (parent.word || !parent.childMap.isEmpty()){
                return value;
            }
            node = parent;
            parent = node.parent;
        }
        return value;
    }

    public boolean startsWith(String prefix) {
        return this.node(prefix) != null ;
    }

    public Node<V> node(String str) {
        checkKey(str);
        Node<V> node = this.root;
        for (int i = 0; i < str.length(); i++) {
            if (node == null || node.childMap == null || node.childMap.isEmpty()) {
                return null;
            }
            char c = str.charAt(i);
            node = node.childMap.get(c);
        }
        return node;
    }

    private void checkKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    class Node<V> {
        Node<V> parent;
        Map<Character, Node<V>> childMap;
        Character key;
        V value;
        boolean word;

//        public Node(Node<V> parent) {
//            this.parent = parent;
//        }

        public Node(Node<V> parent, Character key) {
            this.parent = parent;
            this.key = key;
        }
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("小码哥", 5);
        test(trie.size() == 5);
        test(trie.startsWith("do"));
        test(trie.startsWith("c"));
        test(trie.startsWith("ca"));
        test(trie.startsWith("cat"));
        test(trie.startsWith("cata"));
        test(!trie.startsWith("hehe"));
        test(trie.get("小码哥") == 5);
        test(trie.remove("cat") == 1);
        test(trie.remove("catalog") == 3);
        test(trie.remove("cast") == 4);
        test(trie.size() == 2);
        test(trie.startsWith("小"));
        test(trie.startsWith("do"));
        test(!trie.startsWith("c"));
    }

    public static void test(boolean value) {
        try {
            if (!value) {
                throw new Exception("测试未通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
