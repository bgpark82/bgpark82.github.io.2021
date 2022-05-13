class Solution {
    
    public int countTexts(String keys) {
        
        double mod = Math.pow(10, 9) + 7;
        int n = keys.length();
        keys = " "+keys;
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];
            int size = 3;
            if(keys.charAt(i) == '7' || keys.charAt(i) == '9') {
                size = 4;
            } 
            for(int j = i-2; j >= Math.max(0, i-size); j--) {
                if(keys.charAt(j+1) == keys.charAt(i)) {
                    dp[i] += dp[j];
                    dp[i] %= mod;
                } else {
                    break;
                }
            }
            
        }
        return dp[n];
    }

    
    public int countTexts2(String keys) {

        int[] arr = new int[9];
        
        for(int i = 0; i < keys.length(); i++) {
            arr[Character.getNumericValue(keys.charAt(i))]++; 
        }
        
        int count = 0;
        for(int a : arr) {
            count += permutation(a);
        }
        
        return count;
    }
    
    
    public long permutation(long n) { 
        if(n == 0) return 0;
        return factorial(n) ;
    } 
    
    public long factorial(long i) { 
        if(i <= 1) {
            return 1;
        } else { 
            long tmp = (i * factorial(i-1)) % (long)(Math.pow(10, 9) + 7);
            return tmp;
        }
    }
}