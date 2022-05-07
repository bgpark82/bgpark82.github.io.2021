class Solution {
    
    /**
     * goal : length of substring
     * iterate through the substring
     * check if it's repeated
     * if it's not repeated then check whether it's the longest substring
     * we need two indices to track of beginning of substring and end of substring
     * if value of last index is contained in the substring
     * then last index will be first index
     * so we only need container to contain the substring
     
     * 1. cache
     * caching in StringBuilder
     */
//     public int lengthOfLongestSubstring(String s) {
//         int len = s.length();
//         int res = 0;
//         for(int i = 0; i < len; i++) {
//             for(int j = i; j < len; j++) {
//                 if(checkRep(i, j, s)) {
//                     res = Math.max(res, j-i+1);
//                 }
//             }
//         }
//         return res;
//     }
    
//     public boolean checkRep(int start, int end, String s) {
        
//         // all number of the ascii characters (128)
//         int[] chars = new int[128];
        
//         for(int i = start; i <= end; i++) {
//             char c = s.charAt(i);
//             chars[c]++;
//             if(chars[c] > 1) {
//                 return false;
//             }
//         }
        
//         return true;
//     }
    
    public int lengthOfLongestSubstring(String s) {
        int[] chars = new int[128];
        int left = 0;
        int right = 0;
        int ans = 0;
        while(right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;
            
            // if duplicated
            while(chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--; // reduce until finding duplicated letter
                left++;
            }
            
            ans = Math.max(ans, right-left+1);
            right++;
        }
        
        return ans;
    }
}