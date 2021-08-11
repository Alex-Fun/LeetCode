package sort.union;

/**
 * Union Find Quick Find
 */
public class UnionFindQF extends UnionFind {
    public UnionFindQF(int capacity) {
        super(capacity);
    }

    /**
     * 父节点就是根节点
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 将v1所在集合的所有元素，都嫁接到v2的父节点上
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == parent1) {
                parents[i] = parent2;
            }
        }
    }
}
