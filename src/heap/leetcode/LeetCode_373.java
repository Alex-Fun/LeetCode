package heap.leetcode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 373. 查找和最小的K对数字
 * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
 * <p>
 * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums
 */
public class LeetCode_373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return sortByHeap(nums1, nums2, k);
    }

    private int size;
    private int[][] elements;
    private int[] matrixCount;
    private int[][] replace;

    /**
     * 纯数组方式实现过于复杂，若改用jdk优先级队列，动态数组，链表，会更简化
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private List<List<Integer>> sortByHeap(int[] nums1, int[]nums2, int k){
        if(k > nums1.length * nums2.length){
            k = nums1.length*nums2.length;
        }
        elements = new int[k][2];
        size = 0;
        int left = nums1[0] + nums2[0];
        int right = nums1[nums1.length-1] + nums2[nums2.length-1];
        int mid;
        while(left < right){
            mid = (left+right) >> 1;
            int check = count(nums1, nums2, k, mid);
            if( check == 0){
                //恰好有k组sum<mid
                break;
            }else if(check < 0){
                //少于k组，上移mid
                left = mid + 1;
            }else{
                //超过k组，下移mid
                right = mid;
            }
        }
        mid = (left+right) >> 1;
        count(nums1, nums2, k, mid);
        if (size > k){
            replace = new int[size-k][2];
            size = k;
        }
        //init elements
        initElements(nums1, nums2);
        //heapify
        heapify();
        //replace
        if (replace != null){
            for (int i = 0; i < replace.length; i++) {
                if (replace[i][0]+replace[i][1] < elements[0][0]+elements[0][1]) {
                    elements[0] = replace[0];
                    siftDown(0);
                }
            }
        }
        //poll heap && 前k组elements 排序 -> list
        LinkedList<List<Integer>> list = new LinkedList();
        while(size>0){
            List<Integer> row = new ArrayList(2);
            row.add(elements[0][0]);
            row.add(elements[0][1]);
            list.addFirst(row);
            elements[0] = elements[--size];
            elements[size] = null;
            siftDown(0);
        }
        return list;
    }

    private int count(int[] nums1, int[] nums2, int k, int mid){
        int m = nums1.length -1 ;
        int n = 0;
        size = 0;
        matrixCount = new int[nums2.length];
        while(m>=0 && n < nums2.length){
            if(nums1[m]+nums2[n]<= mid){
                size += m+1;
                matrixCount[n] = m+1;
                n++;
                if(size > k){
                    return 1;
                }
            }else{
                m--;
            }
        }
        return size - k;
    }

    private void initElements(int[] nums1, int[]nums2){
        int tmpIndex=size-1;
        int replaceIndex= 0;
        //遍历matrixCount记录的所有组合
        for(int i = 0;i<matrixCount.length;i++){
            int count = matrixCount[i];
            for(int j=0;j<count ;j++){
                if (tmpIndex >= 0){
                    elements[tmpIndex--]= new int[]{nums1[j], nums2[i]};
                }else {
                    replace[replaceIndex++] = new int[]{nums1[j], nums2[i]};
                }
            }
        }
    }

    private void heapify(){
        for(int i=(size>>1)-1; i>=0;i--){
            siftDown(i);
        }
    }

    private void siftDown(int index){
        int[] pair= elements[index];
        while(index < size >>1){
            int childIndex = (index<<1)+1;
            int[] child = elements[childIndex];
            int rightIndex = childIndex +1;
            if(rightIndex < size && elements[rightIndex][0] + elements[rightIndex][1] > child[0]+child[1]){
                childIndex = rightIndex;
                child = elements[childIndex];
            }
            if(pair[0]+pair[1] >= child[0] + child[1]){
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = pair;
    }


    public static void main(String[] args) {
        int[] nums1 = {-10,-4,0,0,6};
        int[] nums2 = {3,5,6,7,8,100};
        int k = 10;
        List<List<Integer>> lists = new LeetCode_373().kSmallestPairs(nums1, nums2, k);
        System.out.println(lists);
        System.out.println("[[-10,3],[-10,5],[-10,6],[-10,7],[-10,8],[-4,3],[-4,5],[-4,6],[0,3],[0,3]]");
    }
}
