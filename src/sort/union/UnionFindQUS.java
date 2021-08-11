package sort.union;

/**
 * Union Find Quick Union Optimized by Size - 基于size的优化
 */
public class UnionFindQUS extends UnionFindQU {

    private int[] sizes;

    public UnionFindQUS(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    /**
     * 元素少的树 嫁接到 元素多的树
     */
    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) {
            return;
        }
        if (sizes[parent1] < sizes[parent2]) {
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        } else {
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        }
    }
}
