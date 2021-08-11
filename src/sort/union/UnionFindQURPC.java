package sort.union;

/**
 * Union Find Quick Union by Rank Optimized by Path Compress - 基于路径压缩的优化
 */
public class UnionFindQURPC extends UnionFindQUR{

    public UnionFindQURPC(int capacity) {
        super(capacity);
    }

    /**
     * find时路径压缩，路径压缩使路径上的所有节点都指向根节点，所以单次执行find实现成本稍高
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v] != v){
            parents[v] = find(v);
        }
        return parents[v];
    }

}
