package sort.leetcode;


/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class LeetCode_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //merge1(nums1, m, nums2, n);
        merge2(nums1, m, nums2, n);
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] left = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = nums1[i];
        }
        int position = 0;
        int li = 0;
        int ri = 0;
        while (li < m && ri < n) {
            if (left[li] <= nums2[ri]) {
                nums1[position++] = left[li++];
            } else {
                nums1[position++] = nums2[ri++];
            }
        }
        if (li == m) {
            while (ri < n) {
                nums1[position++] = nums2[ri++];
            }
        } else {
            while (li < m) {
                nums1[position++] = left[li++];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int position = nums1.length - 1;
        int li = m - 1;
        int ri = n - 1;
        while (li >= 0 || ri >= 0) {
            if (li < 0) {
                nums1[position--] = nums2[ri--];
            } else if (ri < 0) {
                nums1[position--] = nums1[li--];
            } else if (nums1[li] <= nums2[ri]) {
                nums1[position--] = nums2[ri--];
            } else {
                nums1[position--] = nums1[li--];
            }
        }
    }
}
