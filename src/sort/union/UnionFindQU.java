package sort.union;

/**
 * Union Find Quick Union
 */
public class UnionFindQU extends UnionFind {
    public UnionFindQU(int capacity) {
        super(capacity);
    }

    /**
     * 通过parent链条不断地向上找，直到找到根节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 将v1的根节点嫁接到v2的根节点上
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return;
        }
        parents[parent1] = parent2;
    }
}
