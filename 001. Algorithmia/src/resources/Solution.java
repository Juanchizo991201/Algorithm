package resources;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0; // If there are no prices or only one price, there's no profit.
        }

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (currentPrice < minPrice) {
                minPrice = currentPrice; // Update the minimum price if a lower price is found.
            } else {
                int potentialProfit = currentPrice - minPrice;
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit; // Update the max profit if a higher profit is found.
                }
            }
        }
        return maxProfit;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // If we encounter an invalid character or mismatched parenthesis, return false
            }
        }
        return stack.isEmpty();
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder prefix = new StringBuilder();
        int i = 0;
        boolean isPrefix = true;
        while (isPrefix) {
            if (strs.length == 0) {
                isPrefix = false;
            } else if (strs[0].isEmpty()) {
                isPrefix = false;
            } else if (i >= strs[0].length()) {
                isPrefix = false;
            } else {
                char c = strs[0].charAt(i);
                for (int j = 1; j < strs.length; j++) {
                    if (i >= strs[j].length()) {
                        isPrefix = false;
                        break;
                    } else if (strs[j].charAt(i) != c) {
                        isPrefix = false;
                        break;
                    }
                }
                if (isPrefix) {
                    prefix.append(c);
                }
            }
            i++;
        }
        return prefix.toString();
    }

    public boolean containsDuplicate(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            int currentNum = nums[i];
            int nextNum = nums[i + 1];
            if (currentNum == nextNum) {
                return true;
            }
        }
        return false;//
    }

    public boolean isPalindrome(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "");
//        s = s.replaceAll("_", "");
        System.out.println(s);

        StringBuilder reversed = new StringBuilder(s).reverse();
        String reversedString = reversed.toString();
        System.out.println(reversedString);

        return s.equalsIgnoreCase(reversedString);
    }

    public boolean isAnagram(String s, String t) {

        char[] sCharArray = s.toCharArray();
        Arrays.sort(sCharArray);
        char[] tCharArray = t.toCharArray();
        Arrays.sort(tCharArray);

        String sString = new String(sCharArray);
        String tString = new String(tCharArray);
        return sString.equalsIgnoreCase(tString);
    }

    public int removeElement(int[] nums, int val) {

        nums = Arrays.stream(nums).filter(i -> i != val).toArray();
        System.out.println(Arrays.toString(nums));

        return nums.length;
    }

    public int search(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1; // Target not found
    }

    public void rotate(int[] nums, int k) {
        Queue<Integer> numsQueue = new LinkedList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            numsQueue.add(nums[i]);
        }

        for (int i = 0; i < k; i++) {
            numsQueue.add(numsQueue.remove());
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = numsQueue.remove();
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] mergedArray = IntStream.range(0, (nums1.length + nums2.length)).map(i -> i < nums1.length ? nums1[i] : nums2[i - nums1.length]).toArray();

        Arrays.sort(mergedArray);

        if (mergedArray.length % 2 == 0) {
            int middleIndex = mergedArray.length / 2;
            int middleIndexMinusOne = middleIndex - 1;
            return (double) (mergedArray[middleIndex] + mergedArray[middleIndexMinusOne]) / 2;
        } else {
            int middleIndex = mergedArray.length / 2;
            return mergedArray[middleIndex];
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode mergedList = new ListNode(0);
        ListNode currentNode = mergedList;

        while (true) {
            int minIndex = -1;
            int minValue = Integer.MAX_VALUE;

            for (int i = 0; i < lists.length; i++) {
                ListNode currentListNode = lists[i];
                if (currentListNode != null && currentListNode.val < minValue) {
                    minIndex = i;
                    minValue = currentListNode.val;
                }
            }

            if (minIndex == -1) {
                break;
            }

            currentNode.next = lists[minIndex];
            currentNode = currentNode.next;
            lists[minIndex] = lists[minIndex].next;
        }

        return mergedList.next;
    }

    public int removeDuplicates(int[] nums) {

        // Un set es una colección de elementos que no permite valores duplicados,
        // por ende, cuando se intenta agregar un valor que ya existe, no se agrega.

        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }

        List<Integer> sortedList = new ArrayList<>(uniqueSet);
        Collections.sort(sortedList);

        for (int i = 0; i < uniqueSet.size(); i++) {
            nums[i] = (int) sortedList.get(i);
        }

        return uniqueSet.size();
    }

    public List<Integer> majorityElement(int[] nums) {

        Map<String, String> dictionaryOfElements = new HashMap<>();

        for (int num : nums) {
            String key = String.valueOf(num);
            if (dictionaryOfElements.containsKey(key)) {
                String value = dictionaryOfElements.get(key);
                int valueInt = Integer.parseInt(value);
                valueInt++;
                dictionaryOfElements.put(key, String.valueOf(valueInt));
            } else {
                dictionaryOfElements.put(key, "1");
            }
        }

        List<Integer> majorityElements = new ArrayList<>();
        for (Map.Entry<String, String> entry : dictionaryOfElements.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int valueInt = Integer.parseInt(value);
            if (valueInt > (nums.length / 3)) {
                majorityElements.add(Integer.parseInt(key));
            }
        }
        return majorityElements;
    }

    public int divide(int dividend, int divisor) {

        if (Integer.MIN_VALUE < dividend || dividend > Integer.MAX_VALUE || Integer.MIN_VALUE < divisor || divisor > Integer.MAX_VALUE) {
            return dividend / divisor;
        }

        return 0;
    }

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int firstIndex = i;
                int lastIndex = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target) {
                        lastIndex = j;
                    }
                }
                return new int[]{firstIndex, lastIndex};
            }
        }
        return new int[]{-1, -1};
    }

    public int searchInsert(int[] nums, int target) {

        int position = Arrays.binarySearch(nums, target);

        return position >= 0 ? position : Math.abs(position) - 1;
    }

    public int climbStairs(int n) {

        ArrayList<Integer> possibilities = new ArrayList<>();

        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    public int lengthOfLastWord(String s) {

        String[] words = s.split(" ");
        if (words.length == 0) {
            return 0;
        }

        return words[words.length - 1].length();
    }

    public int[] plusOne(int[] digits) {

        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }

        int[] newDigits = new int[digits.length + 1];
        System.arraycopy(digits, 0, newDigits, 0, digits.length);
        newDigits[digits.length - 1] = 1;
        newDigits[digits.length] = 0;
        return newDigits;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int j = 0, i = m; j < n; j++, i++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k; j++) {
                if (j < nums.length && nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    public int findMaxSubarraySum(int[] intArray) {

        int maxSum = 0;

        for (int i = 0; i < intArray.length - 1; i++) {
            if (intArray[i] >= 0) {
//                System.out.println("pivote: " + intArray[i]);
                int localMaxSum = intArray[i];
                for (int j = i + 1; j < intArray.length; j++) {
                    if (intArray[j] > 0) {
//                        System.out.println("**SUMA** el siguiente es mayor a 0: " + intArray[j]);
                        localMaxSum += intArray[j];
//                        System.out.println("sumaLocal: " + localMaxSum);
                        if (localMaxSum > maxSum) {
//                            System.out.println("La suma local es mayor a la global " + localMaxSum + ">" + maxSum + " estableciendo nueva suma global...");
                            maxSum = localMaxSum;
                        }
                    } else {
                        break;
                    }

                }
            } else {
//                System.out.println("numero pivote menor a 0 " + intArray[i]);
            }
            if (intArray[i] > maxSum) {
                maxSum = intArray[i];
            }

        }
        return maxSum;
    }

    public int bitwiseComplement(int n) {
        String binary = convertDecimalToBinary(n);
        String[] array = binary.split("");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("0")) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }

        return binaryToDecimal(sb.toString());
    }

    public static String convertDecimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 2;
            binary.insert(0, remainder); // Insert remainder at the beginning
            decimal /= 2; // Divide decimal by 2
        }

        return binary.toString();
    }

    public static int binaryToDecimal(String binaryString) {
        int decimal = 0;
        int length = binaryString.length();
        for (int i = 0; i < length; i++) {
            char digit = binaryString.charAt(i);
            // Check if the character is '1', if true, add the corresponding power of 2 to the decimal
            if (digit == '1') {
                decimal += Math.pow(2, length - i - 1);
            } else if (digit != '0') {
                // Handle invalid characters
                System.err.println("Invalid binary number: " + binaryString);
                System.exit(1);
            }
        }
        return decimal;
    }

    public static int simpleArraySum(List<Integer> ar) {
        return ar.stream().mapToInt(Integer::intValue).sum();
    }

    public static void minMaxSum(List<Integer> arr) {

        List<Integer> integerList = new ArrayList<>();




        int minsum = 0;
        int maxsum = 0;

        Collections.sort(integerList);

        System.out.println(integerList);

    }

    public  static void staircase(int n) {
        for (int i = 1; i <= n; i++) {
            String spaces = " ".repeat(n - i);
            String hashes = "#".repeat(i);
            System.out.println(spaces + hashes);
        }
    }
}