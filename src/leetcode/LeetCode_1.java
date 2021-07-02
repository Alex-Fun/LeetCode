package leetcode;

import java.util.*;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n^2) 的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 */
public class LeetCode_1 {

    public int[] twoSum(int[] nums, int target) {
//        return sum0(nums, target);
//        return sum1(nums,target);
        return sum2(nums,target);
    }

    public int[] sum2(int[] nums, int target){
        int[] index = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null){
                index[0]=i;
                index[1]=j;
                break;
            }
            map.put(nums[i], i);
        }
        return index;
    }


    public int[] sum0(int[] nums, int target) {
        int[] index = new int[2];
        int num;
        for (int i = 0; i < nums.length-1; i++) {
            num = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] + num == target){
                    index[0]=i;
                    index[1]=j;
                    break;
                }
            }
        }
        return index;
    }

    public int[] sum1(int[] nums, int target) {
        int[] index = null;
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list);
        int i=0,j=list.size()-1;
        while (i >=0 && i<list.size() && j>=0 && j<list.size()){
            int iint = list.get(i);
            int jint = list.get(j);
            int sum = iint + jint;
            if (sum == target){
                index=indexOf(iint, jint, nums);
                break;
            }else if (sum > target){
                j--;
            }else if (sum < target){
                i++;
            }
        }
        return index;
    }

    private int[] indexOf(int num1, int num2, int[] ints){
        int[] index=new int[]{-1,-1};
        for (int i = 0; i < ints.length; i++) {
            if (index[0]==-1 && ints[i] == num1){
                index[0]=i;
            }else if (index[1] == -1 && ints[i] == num2){
                index[1]=i;
            }
            if (index[0] >= 0 && index[1] >=0){
                break;
            }
        }
        return index;
    }


}
