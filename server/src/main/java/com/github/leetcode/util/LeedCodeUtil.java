package com.github.leetcode.util;

import com.github.leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 刷题util
 */
public class LeedCodeUtil {

    private LeedCodeUtil(){

    }

    /**
     * 单例
     * @return
     */
    public static LeedCodeUtil getInstance(){
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static final LeedCodeUtil INSTANCE = new LeedCodeUtil();
    }

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
    public int lengthOfLongestSubstring(String s){
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
    public String longestPalindrome(String s){
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
    public List<List<Integer>> threeSum(int[] nums) {
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
    public int romanToInt(String s) {
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
    public int myAtoi(String s) {
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
    public int maxArea(int[] height) {
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
    public int cur = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
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
    public ListNode removeNthFromEndV2(ListNode head, int n) {
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
    public String convert(String s, int numRows) {
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
    public String convertBackup(String s, int numRows){
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

    /**
     * https://leetcode-cn.com/problems/longest-common-prefix/
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 80.99%
     * 的用户
     * 内存消耗：
     * 36.2 MB
     * , 在所有 Java 提交中击败了
     * 93.08%
     * 的用户
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length ==0){
            return "";
        }
        StringBuilder result = new StringBuilder();
        int length = strs[0].length();
        int index = 0;
        while(index < length && strs[0].length() > 0){
            char temp = strs[0].charAt(index);
            for (int i = 1; i < strs.length;i++){
                if (index == 0){
                    length = Math.min(length,strs[i].length());
                }
                if (length == 0 || temp != strs[i].charAt(index)){
                    return result.toString();
                }
            }
            result.append(temp);
            index ++;
        }
        return result.toString();
    }

    /**
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * @param digits
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 21.80%
     * 的用户
     * 内存消耗：
     * 38.6 MB
     * , 在所有 Java 提交中击败了
     * 19.06%
     * 的用户
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return new ArrayList<>();
        }
        char[] arrs = digits.toCharArray();
        List<String> result = new ArrayList<>();
        List<char[]> tempChar = new ArrayList<>();
        for (int i = 0; i < arrs.length; i++){
            switch (arrs[i]){
                case '2':
                    tempChar.add((new char[]{'a','b','c'}));
                     break;
                case '3':
                    tempChar.add((new char[]{'d','e','f'}));
                    break;
                case '4':
                    tempChar.add((new char[]{'g','h','i'}));
                    break;
                case '5':
                    tempChar.add((new char[]{'j','k','l'}));
                    break;
                case '6':
                    tempChar.add((new char[]{'m','n','o'}));
                    break;
                case '7':
                    tempChar.add((new char[]{'p','q','r','s'}));
                    break;
                case '8':
                    tempChar.add((new char[]{'t','u','v'}));
                    break;
                case '9':
                    tempChar.add((new char[]{'w','x','y','z'}));
                    break;
            }
        }
        for (Character s: tempChar.get(0)){
            result.add(String.valueOf(s));
        }
        int index = 1;
        while (index < tempChar.size()){
            result = getSubString(result, tempChar.get(index));
            index ++;
        }
        return result;
    }

    public List<String> getSubString(List<String> stringBuilderList, char[] list){
        List<String> stringBuilders = new ArrayList<>();
        for (int i = 0; i < stringBuilderList.size(); i++){
            for (int j = 0; j < list.length; j++){
                String s = stringBuilderList.get(i);
                s += list[j];
                stringBuilders.add(s);
            }
        }
        return stringBuilders;
    }

    /**
     * 20. 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() <= 0){
            return true;
        }
        char[] chars = s.toCharArray();
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (Character character : chars){
            if (character == '{' || character == '[' || character == '('){
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (topChar != character - 1 && topChar != character - 2){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 括号生成
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n <= 1){
            return Collections.singletonList("()");
        }
        List<String> returnString = new ArrayList<>();
        for (String s:generateParenthesis(--n)){
            for (int i = 0; i <= s.length(); i++){
                String temp = new StringBuilder(s).insert(i,"()").toString();
                if (!returnString.contains(temp)){
                    returnString.add(temp);
                }
            }
        }
        return returnString;
    }

    /**
     * 28. 实现 strStr()
     * https://leetcode-cn.com/problems/implement-strstr/
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        char[] res = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        if (res.length < needle.length()) return -1;
        Loop:for (int i = 0; i < res.length; i++){
            if (res[i] == needles[0] && i + needles.length <= res.length){
                for (int j = 1; j < needles.length; j++){
                    if (res[i + j] != needles[j]){
                        continue Loop;
                    }
                }
                return i;
            }
        }
        return -1;
    }

    /**
     * 16. 最接近的三数之和
     * https://leetcode-cn.com/problems/3sum-closest/
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        boolean flag = true;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            // 再找一个正数
            int maxIndex = nums.length - 1;
            int minIndex = i + 1;
            // 左右寻找
            while(minIndex < maxIndex){
                int sum = nums[i] + nums[minIndex] + nums[maxIndex];
                if (flag || Math.abs(target - sum) < Math.abs(result - target)){
                    flag = false;
                    result = sum;
                    if (result == target) return result;
                }
                if (sum > target){
                    maxIndex --;
                } else {
                    minIndex ++;
                }
            }
        }
        return result;
    }

    /**
     * 下一个排列
     * https://leetcode-cn.com/problems/next-permutation/
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int maxIndex = nums.length - 1;
        while (maxIndex > 0 && nums[maxIndex] <= nums[maxIndex - 1]){
            maxIndex--;
        }
        if (maxIndex != 0){
            Arrays.sort(nums,maxIndex,nums.length);
            maxIndex--;
            for (int i = maxIndex + 1;i < nums.length; i++){
             if (nums[i] > nums[maxIndex] && nums[i - 1] <= nums[maxIndex]){
                 int temp = nums[maxIndex];
                 nums[maxIndex] = nums[i];
                 nums[i] = temp;
                 break;
             }
            }
        } else {
            Arrays.sort(nums);
        }
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums,int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4){
            return result;
        }
        Arrays.sort(nums);
        for (int index = 0; index < nums.length - 3; index++) {
            int targetTemp = target - nums[index];
            for (int i = index + 1; i < nums.length; i++) {
                int maxIndex = nums.length - 1;
                int minIndex = i + 1;
                // 左右寻找
                while (minIndex < maxIndex) {
                    int sum = nums[i] + nums[minIndex] + nums[maxIndex];
                    // 表示满足条件
                    if (sum == targetTemp) {
                        List<Integer> tmpList = Arrays.asList(nums[index], nums[i], nums[minIndex], nums[maxIndex]);
                        if (!result.contains(tmpList)) {
                            result.add(tmpList);
                        }
                        while (minIndex < maxIndex && nums[minIndex + 1] == nums[minIndex]) {
                            minIndex++;
                        }
                        while (minIndex < maxIndex && nums[maxIndex - 1] == nums[maxIndex]) {
                            maxIndex--;
                        }
                        maxIndex--;
                        minIndex++;
                    } else if (sum > targetTemp) {
                        maxIndex--;
                    } else {
                        minIndex++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/count-and-say/
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        int i = 1;
        String result = "1";
        while (i < n){
            char[] attrs = result.toCharArray();
            StringBuilder builder = new StringBuilder();
            int count = 0;
            for (int j = 0; j < attrs.length;j++){
                if (j-1 >= 0 && attrs[j] != attrs[j-1]){
                    builder.append(count).append(attrs[j-1]);
                    count = 0;
                }
                count ++;
            }
            builder.append(count).append(attrs[attrs.length - 1]);
            result = builder.toString();
            i++;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/add-strings/
     * @param num1
     * @param num2
     * @return
     * */
    public String addStrings(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0){
            return num1.length() == 0?num2:num1;
        }
        Map<Character,Integer> POWER = new HashMap<>();
        POWER.put('0',0);
        POWER.put('1',1);
        POWER.put('2',2);
        POWER.put('3',3);
        POWER.put('4',4);
        POWER.put('5',5);
        POWER.put('6',6);
        POWER.put('7',7);
        POWER.put('8',8);
        POWER.put('9',9);
        char[] num1s = num1.toCharArray();
        char[] num2s = num2.toCharArray();
        int num1sCount = num1s.length-1;
        int num2sCount = num2s.length-1;
        int addOne = 0;
        StringBuilder result = new StringBuilder();
        while (num1sCount >= 0 && num2sCount >= 0){
            int count = POWER.get(num1s[num1sCount]) + POWER.get(num2s[num2sCount]) + addOne;
            addOne = count >= 10?1:0;
            result.insert(0,count%10);
            num1sCount --;
            num2sCount --;
        }
        while (num1sCount>=0){
            int count = POWER.get(num1s[num1sCount])  + addOne;
            addOne = count >= 10?1:0;
            result.insert(0,count%10);
            num1sCount --;
        }
        while (num2sCount>=0){
            int count = POWER.get(num2s[num2sCount])  + addOne;
            addOne = count >= 10?1:0;
            result.insert(0,count%10);
            num2sCount --;
        }
        if (addOne > 0){
            result.insert(0,addOne);
        }
        return result.toString();
    }

    /**
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        int count = 0;
        ListNode next = head;
        ListNode header = head;
        while (next != null){
            count ++;
            ListNode nextTemp = next.next;
            if (count % 2 != 0){
                header = next;
                ListNode countHeader = next;
                for (int i = 0 ; i < 3; i++){
                    if (countHeader.next != null){
                        countHeader = countHeader.next;
                    } else {
                        break;
                    }
                }
                if (countHeader != next && countHeader != next.next){
                    next.next = countHeader;
                } else {
                    next.next = null;
                }
            } else {
                next.next = header;
                if (count == 2){
                    head = next;
                }
            }
            next = nextTemp;
        }
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode next = head;
        while (next != null){
            if (next.next != null && next.val == next.next.val){
                next.next = next.next.next;
            } else {
                next = next.next;
            }
        }
        return head;
    }
}
