package sort.union;

/**
 * Union Find Quick Union by Rank Optimized by Path Halving - 基于路径减半的优化
 */
public class UnionFindQURPH extends UnionFindQUR {

    public UnionFindQURPH(int capacity) {
        super(capacity);
    }

    /**
     * 路径减半：使路径上每隔一个节点就指向其祖父节点（parent的parent）
     * 路径分裂、路径减半的效率差不多，但都比路径压缩要好
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
