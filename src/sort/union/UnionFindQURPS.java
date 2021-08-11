package sort.union;

/**
 * Union Find Quick Union by Rank Optimized by Path Spliting - 基于路径分裂的优化
 */
public class UnionFindQURPS extends UnionFindQUR{
    public UnionFindQURPS(int capacity) {
        super(capacity);
    }

    /**
     * 路径分裂：使路径上的每个节点都指向其祖父节点（parent的parent）
     * 路径分裂、路径减半的效率差不多，但都比路径压缩要好
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v){
            int parent = parents[v];
            parents[v] = parents[parent];
            v = parent;
        }
        return v;
    }
}
