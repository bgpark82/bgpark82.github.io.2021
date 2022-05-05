class Solution {
    
    /**
     * TC : O(n^2)
     * SP : O(2)
     * 
     * in order to reduce time complexity, we can reduce lookup time by trading space 
     * 
     * 1. caching
     * 
     * 1-1. array
     * in case of using array, it will take lots of memory space which will be
     * 8,000,000,000byte
     * 8,000,000kbyte
     * 8,000mbyte
     * 8gbyte = 8 billiant
     * in the worst case, it will take up 8gbyte at maximum
     * 
     * 1-2. hash table
     * hash table can reduce lookup time from O(n) to O(1)
     * goal is to find the complement of the each value of index
     * we can iterate loop to add the value of each index as key and index as value
     * 
     * 
     */
    // public int[] twoSum(int[] nums, int target) {
    //     int[] ans = new int[2];
    //     int sum = 0;
    //     for(int i = 0; i < nums.length - 1; i++) {
    //         sum = nums[i]; 
    //         for(int j = i+1; j < nums.length; j++) {
    //             if(sum + nums[j] == target) {
    //                 ans[0] = i;
    //                 ans[1] = j;
    //             }
    //         }
    //     }
    //     return ans;
    // }
    
    public int[] twoSum(int[] nums, int target) {
        int[] ans = null;
        Map<Integer, Integer> numMap = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            numMap.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++) {
            Integer prevId = numMap.get(target-nums[i]);
            if(prevId != null && prevId != i) {
                ans = new int[]{i, prevId};
            }
        }
        return ans;
    }
}

    