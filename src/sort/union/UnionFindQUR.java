package sort.union;

/**
 * Union Find Quick Union Optimized by Rank - 基于Rank优化
 */
public class UnionFindQUR extends UnionFindQU {

    private int[] ranks;

    public UnionFindQUR(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    /**
     * 层数矮的树 嫁接到 层数高的树
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return;
        }

        if (ranks[parent1] < ranks[parent2]) {
            parents[parent1] = parent2;
        } else if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
            ranks[parent1] += 1;
        }

    }
}
