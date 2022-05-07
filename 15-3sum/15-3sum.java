class Solution {
    
    /** 
     * TC : O(N^3)
     * SC : O(N^2)
     */
//     public List<List<Integer>> threeSum(int[] nums) {
//         Set<List<Integer>> ans = new HashSet();
        
//         for(int i = 0; i < nums.length-2; i++) {
//             for(int j = i+1; j < nums.length-1; j++) {
//                 for(int z = j+1; z < nums.length; z++) {
//                     if(nums[i] + nums[j] + nums[z] == 0) {
//                         List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[z]);
//                         Collections.sort(temp);
//                         ans.add(temp);
//                     }
//                 }
//             }
//         }
        
//         List<List<Integer>> list = new ArrayList();
//         for(List<Integer> l : ans) {
//             list.add(l);
//         }
        
//         return list;
//     }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ans = new HashSet();
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; i++) {
            int left = i+1;
            int right = nums.length-1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum < 0) {
                    left++;
                } else if(sum > 0) {
                    right--;
                } else {
                    List<Integer> arr = Arrays.asList(nums[i], nums[left], nums[right]);
                    Collections.sort(arr);
                    ans.add(arr);
                    left++;
                    right--;
                }
            }
        }
        
        List<List<Integer>> list = new ArrayList();
        for(List<Integer> l : ans) {
            list.add(l);
        }
        
        return list;
    }
}