package heap.leetcode;


/**
 * 378. 有序矩阵中第 K 小的元素
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 * <p>
 * 输入：matrix = [[-5]], k = 1
 * 输出：-5
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == matrix.length
 * n == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 * 1 <= k <= n2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 */
public class LeetCode_378 {

    public int kthSmallest(int[][] matrix, int k) {
        return sortByBinary(matrix, k);
        // TODO: 2021/7/1/0001 利用归并排序+堆求解
//        return sortByHeap(matrix, k);
    }

    private int sortByBinary(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = right + left >> 1;
            boolean check = check(matrix, mid, k);
            if (check) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int[][] matrix, int val, int k) {
        int count = 0;
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j < matrix[i].length && count < k){
            if (matrix[i][j] > val){
                i--;
            }else {
                j++;
                count = count + i + 1;
            }
        }
        return count >= k;
    }


    private int sortByHeap(int[][] matrix, int k) {
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3,5,7},{9,11,13},{13,15,17}};
        int k = 3;
        int i = new LeetCode_378().kthSmallest(matrix, k);
        System.out.println(i);

    }


    /*public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        int mid = (int)(Math.sqrt(8*k+1)-0.5);
        while (left < right) {

            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = left + ((right - left) >> 1);
        }
        return left;
    }*/
}

