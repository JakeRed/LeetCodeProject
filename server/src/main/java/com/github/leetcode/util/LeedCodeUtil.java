package com.github.leetcode.util;

import com.github.leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 刷题util
 */
public class LeedCodeUtil {
    /**
     * 无重复字符的最长子串
     * @param s
     * @return https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 75.29%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 5.51%
     * 的用户
     */
    public static int lengthOfLongestSubstring(String s){
        StringBuilder result = new StringBuilder();
        int maxLength = 0;
        for(int i = 0;i < s.length();i++){
            int index = result.indexOf(s.substring(i,i+1));
            if (index >= 0){
                result.delete(0,index+1).append(s, i, i+1);
            } else {
                result.append(s, i, i+1);
            }
            if (maxLength < result.length()){
                maxLength = result.length();
            }
        }
        return maxLength;
    }

    /**
     * 最长回文子串
     * @param s
     * @return https://leetcode-cn.com/problems/longest-palindromic-substring/
     * 执行用时：
     * 30 ms
     * , 在所有 Java 提交中击败了
     * 86.11%
     * 的用户
     * 内存消耗：
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 54.83%
     * 的用户
     */
    public static String longestPalindrome(String s){
        char[] res = s.toCharArray();
        int[] range = new int[2];
        for (int i = 0;i < res.length;i++){
            int left = i,right = i;
            while (right < res.length - 1 && res[right + 1]==res[left]) {
                right++;
                i = right;
            }
            while(left-1 >= 0 && right+1 < res.length && res[left-1]==res[right+1]){
                left -- ;
                right ++;
            }
            if (range[1] - range[0] < (right - left + 1)){
                range[0] = left;
                range[1] = right + 1;
            }
        }

        return s.substring(range[0],range[1]);
    }

    /**
     * https://leetcode-cn.com/problems/3sum/submissions/
     * 执行用时：
     * 19 ms
     * , 在所有 Java 提交中击败了
     * 99.26%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 95.64%
     * 的用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3){
            return result;
        }
        Arrays.sort(nums);
        // 找出负数,问题变为两个数相加等于一个正数
        for (int i = 0; i < nums.length && nums[i] <= 0; i++){
            if (i-1 < 0 || nums[i] != nums[i-1]){
                // 再找一个正数
                int maxIndex = nums.length - 1;
                int minIndex = i + 1;
                // 左右寻找
                while(minIndex < maxIndex){
                    int sum = nums[i] + nums[minIndex] + nums[maxIndex];
                    // 表示满足条件
                    if (sum == 0){
                        result.add(Arrays.asList(nums[i],nums[minIndex],nums[maxIndex]));
                        while (minIndex < maxIndex && nums[minIndex + 1] == nums[minIndex]) {
                            minIndex++;
                        }
                        while (minIndex < maxIndex && nums[maxIndex - 1] == nums[maxIndex]) {
                            maxIndex--;
                        }
                        maxIndex --;
                        minIndex ++;
                    } else if (sum > 0){
                        maxIndex --;
                    } else {
                        minIndex ++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/roman-to-integer/
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int result = 0;
        char[] res = s.toCharArray();
        for (int i = res.length -1; i >=0; i--){
            switch (res[i]){
                case 'I':
                    if (i+1 < res.length && res[i+1] != 'I'){
                        result -= 1;
                    } else {
                        result += 1;
                    }
                    break;

                case 'V':
                    if (i+1 < res.length && (res[i+1] != 'V' && res[i+1] != 'I')){
                        result -= 5;
                    } else {
                        result += 5;
                    }
                    break;

                case 'X':
                    if (i+1 < res.length && (res[i+1] != 'V' && res[i+1] != 'I' && res[i+1] != 'X')){
                        result -= 10;
                    } else {
                        result += 10;
                    }
                    break;

                case 'L':
                    if (i+1 < res.length && (res[i+1] != 'L' && res[i+1] != 'V' && res[i+1] != 'I' && res[i+1] != 'X')){
                        result -= 50;
                    } else {
                        result += 50;
                    }
                    break;

                case 'C':
                    if (i+1 < res.length && (res[i+1] != 'C' && res[i+1] != 'L' && res[i+1] != 'V' && res[i+1] != 'I' && res[i+1] != 'X')){
                        result -= 100;
                    } else {
                        result += 100;
                    }
                    break;

                case 'D':
                    if (i+1 < res.length && (res[i+1] != 'D' && res[i+1] != 'C' && res[i+1] != 'L' && res[i+1] != 'V' && res[i+1] != 'I' && res[i+1] != 'X')){
                        result -= 500;
                    } else {
                        result += 500;
                    }
                    break;

                case 'M':
                    result += 1000;
                    break;

            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/string-to-integer-atoi/
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        int result = 0;
        s = s.trim();
        if (s.length() > 0){
            char[] res = s.toCharArray();
            StringBuilder str = new StringBuilder();
            boolean isPositive = res[0] != '-';
            int i = (res[0] == '-' || res[0] == '+')?1:0;
            boolean notzero = true;
            for (; i < res.length && str.length() <= 11;i++){
                if (Character.isDigit(res[i])){
                    if (notzero){
                        notzero = res[i] == '0';
                    }
                    if (!notzero)
                        str.append(res[i]);
                } else {
                    break;
                }
            }
            if (str .length() > 0 && str.length() < 10){
                result = isPositive?Integer.parseInt(String.valueOf(str)):-Integer.parseInt(String.valueOf(str));
            } else if (str.length() == 10){
                long temp = isPositive?Long.parseLong(String.valueOf(str)):-Long.parseLong(String.valueOf(str));
                result = (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE)?(isPositive?Integer.MAX_VALUE:Integer.MIN_VALUE): (int) temp;
            } else if (str.length() > 10){
                result = isPositive?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/container-with-most-water/
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (left < right){
            result = Math.max(result,Math.min(height[left],height[right])*(right-left));
            if (height[left] > height[right]){
                right --;
            } else {
                left ++;
            }
        }
        return result;
    }


    /**
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.3 MB
     * , 在所有 Java 提交中击败了
     * 76.16%
     * 的用户
     * @param head
     * @param n
     * @return
     */
    public static int cur = 0;
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        head.next = removeNthFromEnd(head.next,n);
        cur ++;
        if(n==cur) return head.next;
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode result = head;
        List<ListNode> listNodes = new ArrayList<>();
        listNodes.add(result);
        ListNode temp = head;
        while (temp != null && temp.next != null){
            listNodes.add(temp.next);
            temp = temp.next;
        }
        if (listNodes.size() - n - 1 >= 0){
            listNodes.get(listNodes.size() - n - 1).next =  n>1?listNodes.get(listNodes.size() - n + 1):null;
        } else if (listNodes.size() == n){
            result = result.next;
        } else {
            result = null;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/zigzag-conversion/
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        StringBuilder result = new StringBuilder();
        char[] list = s.toCharArray();
        HashMap<Integer,List<Character>> hashMap = new HashMap<>();
        int i = 0;
        boolean positive_direction = true;
        for (Character item: list){
            if (hashMap.get(i) == null){
                hashMap.put(i,new ArrayList<>());
            }
            hashMap.get(i).add(item);
            if (!positive_direction){
                if (i > 0){
                    i--;
                } else {
                    i++;
                    positive_direction = true;
                }
            } else if (i + 1 < numRows){
                i++;
            } else if (i > 0){
                i--;
                positive_direction = false;
            }
        }

        for (int a = 0; a < numRows; a++){
            if (hashMap.get(a) == null){
                break;
            }
            for (Character character:hashMap.get(a)){
                result.append(character);
            }
        }
        return result.toString();
    }

    /**
     * 对于=numRows行的, s中的第i个字符，j为第j行，满足
     * i%(2n-2) == j & 2n-2-j ----> rowJ
     * @param s
     * @param numRows
     * @return
     */
    public static String convertBackup(String s, int numRows){
        char[] list = s.toCharArray();
        if (numRows <= 1){
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int j = 0;
        boolean positive_direction = true;
        int[] arr = new int[numRows];
        for (int i = 0; i < list.length; i++){
            while (i%(2*(numRows-1)) == j || i%(2*(numRows-1)) == 2*(numRows-1) - j){
                int offset = 0;
                for (int k = 0; k <= j;k++){
                    offset += arr[k];
                }
                stringBuilder.insert(offset,list[i]);
                arr[j]++;
                if (!positive_direction){
                    if (j > 0){
                        j--;
                    } else {
                        j++;
                        positive_direction = true;
                    }
                } else if (j + 1 < numRows){
                    j++;
                } else if (j > 0){
                    j--;
                    positive_direction = false;
                }
            }
        }
        return stringBuilder.toString();
    }
}
