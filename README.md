# LeetCodeProject

## 排序加找中位数:

题目链接: https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
解题思路: 我是通过O（m+n）的复杂度来做了一个排序，然后很容易的找到了中位数。
```
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	boolean isNums1Start = true;
    	int[] nums3;
    	if(nums1 !=null && nums1.length>0){
    		if(nums2 !=null && nums2.length>0){
    			isNums1Start = nums1[0]<nums2[0];
    			nums3 = new int[nums1.length + nums2.length];
    		}else{
    			isNums1Start = true;
    			nums3 = new int[nums1.length];
    		}
    	}else{
			isNums1Start = false;
			nums3 = new int[nums2.length];
    	}
    	int index = 0;
    	int i = 0;
    	while(i<(isNums1Start?nums1.length:nums2.length)){
    		if(isNums1Start){
    			nums3[i+index] = nums1[i];
    			i++;
    			if(nums2 !=null){
    			while(index < nums2.length && ((nums1.length>i && nums2[index]<nums1[i])|| nums1.length==i)){
    				nums3[i+index] = nums2[index];
    				index++;
    			}
    			}
    		}else{
    			nums3[i+index] = nums2[i];
    			i++;
    			if(nums1 !=null){
        			while(index < nums1.length && ((nums2.length>i && nums1[index]<nums2[i])|| nums2.length==i)){
        				nums3[i+index] = nums1[index];
        				index++;
        			}
        			}
    		}
    	}
    	if(nums3.length%2==0){
    		return (float)(nums3[nums3.length/2] + nums3[nums3.length/2-1])/2;
    	}else{
    		return (float)nums3[nums3.length/2];
    	}
    }
```

## 指数上升，越界下降

题目链接: https://leetcode-cn.com/problems/sqrtx/
解题思路: 一上来对0做特殊处理，然后2的指数去遍历，超出x了就对当前指数和前一次指数间的数据做相同的处理，得到结果：
```
 public int  mySqrt(int x) {
        if (x==0) return 0;
        long sum = 0;
        long result = 1;
        while((sum + result)*(sum + result) <= x){
            if ((2*result + sum)*(2*result + sum)>Integer.MAX_VALUE
                    || (2*result + sum)*(2*result + sum)>x){
                sum = sum + result;
                result = 1;
            }else{
                result = 2*result;
            }
        }
        return (int)sum;
    }
```

## 最长回文
题目链接: https://leetcode-cn.com/problems/longest-palindromic-substring
解题思路: 特殊处理多个连续相等，如果遇到连续相等那么for循环指针前移，减少循环次数；

处理完连续相等之后就可得到left和right指针，然后left-- right++，继续扩大搜索面

如果没有连续相等，那么就可直接把循环i当中位数，left和right继续操作。

tip：发现java用substring的耗时比较长，应该是跟Arrays.copyOfRange有关，这里面在c层有while循环，故改用了记录index的方式。

```
class Solution {
 public static String longestPalindrome(String s){
        char[] res = s.toCharArray();
        int[] range = new int[2];
        for (int i = 0;i < res.length;i++){
            int left = i,right = i;
            // 特殊处理连续相等字符串
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
}

```

## 三数相加为0

题目链接: https://leetcode-cn.com/problems/3sum/
解题思路: 首页的思路是排序后找最小值和一个最大值，然后去查找中间的数有没有为相反数的，没有的话先移动最大值为次大值，直到正数都结束，这样的结果就是除排序外进行了三次循环，不管怎么优化，查找相反数使用二分查找后也就提高到99ms。
然后换了下思路，直接拿外层循环的值，定义最小值和最大值指针，然后通过计算结果正负来移动最小值和最大值指针，这样的话就减少了一层循环，99--->19了。

前期二分的代码：
```
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
                for (int j = nums.length-1; j  >= 0 && nums[j] >= 0; j--){
                    if ((j+1 > nums.length-1 || nums[j] != nums[j+1]) && i+1 < nums.length){
                        int calNum = nums[i] + nums[j];
                        // 二分查找
                        int lastNum = commonBinarySearch(nums,i+1,j-1,-calNum);
                        if (lastNum != -1){
                            result.add(Arrays.asList(nums[i],nums[j],nums[lastNum]));
                            continue;
                        } else {

                        }
                    }
                }
            }
        }
        return result;
    }

    public static int commonBinarySearch(int[] arr,int low, int high, int key){
        int middle = 0;
        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                //比关键字大则关键字在左区域
                high = middle - 1;
            }else if(arr[middle] < key){
                //比关键字小则关键字在右区域
                low = middle + 1;
            }else{
                return middle;
            }
        }
        return -1;
    }
```

后续优化后代码：
```
class Solution {
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
}
```

## 罗马转阿拉伯数字

题目链接: https://leetcode-cn.com/problems/roman-to-integer/

解题思路: 这个简单，除暴点直接在高位左边出现低位就做减法，反之做加法。
```
class Solution {
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
}
```


