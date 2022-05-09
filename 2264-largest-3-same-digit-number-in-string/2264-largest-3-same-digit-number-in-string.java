class Solution {
    public String largestGoodInteger(String num) {
        
        String max = "";
    
        for(int i = 0; i < num.length()-2; i++) {
            if(isContinues(i, num)) {
                if (num.substring(i, i + 3).compareTo(max) > 0) {
                    max = num.substring(i, i + 3);
                }
            }
        }
        
        return max;
    }
    
    private boolean isContinues(int j, String arr) {
        char head = arr.charAt(j);
        for(int i = 0; i < 3; i++) {
            if(head != arr.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }
}