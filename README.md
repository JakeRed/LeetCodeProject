# LeetCodeProject

## 外观数列
<br>题目链接：https://leetcode-cn.com/problems/count-and-say/</br>
<br>解题思路：while 或者递归实现，主要是去对attr数组对比，得到j和j-1的值不相等的时候记录下count，得到的结果在进行下一次while循环</br>

```
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
```

## 四数之和
<br>题目链接：https://leetcode-cn.com/problems/4sum/</br>
<br>解题思路：三数之和的基础上套了个循环，算是暴力破解了</br>

```
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
```

## 下一个排列
<br>题目链接：https://leetcode-cn.com/problems/next-permutation/</br>
<br>解题思路：其实就是从数组倒着查找，找到nums[i] 比nums[i+1]小的时候，就将nums[i]跟nums[i+1]到nums[nums.length - 1]当中找到一个最小的比nums[i]大的元素交换。交换后，再把nums[i+1]到nums[nums.length-1]排序</br>

```
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
```


## 最接近的三数之和
<br>题目链接：https://leetcode-cn.com/problems/3sum-closest/</br>
<br>解题思路：参考三数之和等于0，左右判断while循环</br>

```
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
```




## 字符串匹配
<br>题目链接：https://leetcode-cn.com/problems/implement-strstr/ </br>
<br>解题思路：以haystack长度循环，用needle去匹配，有两层for循环，提交后看了下string.indexOf方法，感觉差异不算太大</br>

```
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
```

## 括号生成
<br>题目链接：https://leetcode-cn.com/problems/generate-parentheses/ </br>
<br>解题思路：首先想到的是递归，化大为小，从2开始入手，只不过是把()插入到()中去，得到()(),(()),那么3就是在2的基础上插入()，这样递归就变得很简单了</br>

```
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
```

## 有效的括号
<br>题目链接：https://leetcode-cn.com/problems/valid-parentheses/ </br>
<br>解题思路：用栈顶元素去对比for循环元素是否能对应上，对应不上就false，对应上了就继续循环，for循环结束之后看stack里面是否还有数据，另外：如果自定义char[]来实现stack的功能，效率会比直接用高</br>

```
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
```

## 电话号码的字母组合
<br>题目链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/ </br>
<br>解题思路：首先映射到char[]，其次进行list的循环扩展，通过getSubString把list<string>扩大返回在循环读下一组char[],方法还是很常规，效率有所欠缺。</br>

```
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
```


## 最长公共前缀
<br>题目链接：https://leetcode-cn.com/problems/longest-common-prefix/ </br>
<br>解题思路：定义数组最长度为length，默认给第1个str，后续while循环第一遍的时候修正length，然后去strs里面循环找相同index下的值是否相同，不同就return</br>

```
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
```


##  Z 字形变换
<br>题目链接：https://leetcode-cn.com/problems/zigzag-conversion/ </br>
<br>解题思路：目前的想法是按Z字形去循环，然后用hashmap来存rowN（第N行）的数据，然后for循环hashmap返回字符串，另外通过找规律也简化了这道题，但是运行的效果还不如循环，
公式：(i%(2n-2) == j & 2n-2-j ----> rowJ)。代码目前没删除，还想找更优解</br>
```
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
```


## 删除链表的倒数第 N 个结点
<br>题目链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/ </br>
<br>解题思路：递归去循环链表，然后循环到最后节点反加flag，这样能通过n==flag来实现去掉倒数第n个结点。</br>

```
    public static int cur = 0;
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        head.next = removeNthFromEnd(head.next,n);
        cur ++;
        if(n==cur) return head.next;
        return head;
    }
```

## 盛最多水的容器
题目链接：https://leetcode-cn.com/problems/container-with-most-water
解题思路：首页想到的其实是权重，但是花了快1个半小时没解出来，就觉得想法可能不对，还是需要循环去计算。这里也大概说下我权重的想法：  
     就是想着去找left的坐标和right的坐标，初步是然后left按x轴值heigh[left]当结果，right按height-x轴值height[right]当结果，  
     然后循环一遍把left和right找出来，但是负责在要处理这个权重，height[n]不能当结果，要受数据短板的限制，所以真要比较还需要在循环，  
     所以这个想法应该是错的，或者说会很复杂，就放弃了。

最后解题思路： 后来想到了动态规划，那就循环一遍吧，left=0；right=length-1；左右比较大小后往中间移动，保证数据没遗漏掉大的结果，然后比较返回。  
```
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
```



## 字符串转换整数 (atoi)
题目链接: https://leetcode-cn.com/problems/string-to-integer-atoi
解题思路: 去掉前导空格，然后判断正负，接着拼接字符串，介于越界的问题，加入Long型参数来防治。   
但感觉还有优化空间，目前的想法是能不能在for里面超过10的时候就不算了。   
```
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

```



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


