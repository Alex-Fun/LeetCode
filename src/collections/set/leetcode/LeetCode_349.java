package collections.set.leetcode;

import demo.P1;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 */
public class LeetCode_349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int i = 0;i < nums1.length; i++){
            set1.add(nums1[i]);
        }
        Set<Integer> set2 = new HashSet();
        for(int i = 0;i < nums2.length; i++){
            set2.add(nums2[i]);
        }
        Set<Integer> set = new HashSet<>();
        Iterator<Integer> it1 = set1.iterator();
        while (it1.hasNext()){
            Integer next = it1.next();
            if (set2.contains(next)) {
                set.add(next);
            }
        }
        int[] ints = new int[set.size()];
        int i =0;
        for (Integer item : set) {
            ints[i++]=item;
        }
        return ints;
    }

    public static void main(String[] args) {

        // 3、定义一个缓冲区
        ByteBuffer buf = ByteBuffer.allocate(10);
        String n = "itheima12";
        buf.put(n.getBytes());
        System.out.println(buf.position()); // 2
        System.out.println(buf.limit());    // 7
        System.out.println(buf.capacity()); // 10
        System.out.println("---------------------");
        buf.flip();

        // 读取数据
        byte[] b = new byte[2];
        buf.get(b);
        String rs = new String(b);
        System.out.println(rs);

        System.out.println(buf.position()); // 2
        System.out.println(buf.limit());    // 7
        System.out.println(buf.capacity()); // 10
        System.out.println("---------------------");
        buf.mark(); // 标记此刻这个位置！ 2

        byte[] b2 = new byte[3];
        buf.get(b2);
        System.out.println(new String(b2));
        System.out.println(buf.position()); // 5
        System.out.println(buf.limit());    // 7
        System.out.println(buf.capacity()); // 10

        System.out.println("---------------------");
        buf.reset(); // 回到标记位置
        buf.reset(); // 回到标记位置
        if(buf.hasRemaining()){
            System.out.println(buf.remaining());
            System.out.println(buf.position()); // 5
            System.out.println(buf.limit());    // 7
            System.out.println(buf.capacity()); // 10
        }
    }




}
