class Solution {
    
    /**
     * goal : width * height
     *
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {
            int width = right - left;
            ans = Math.max(ans, Math.min(height[left], height[right]) * width);
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}