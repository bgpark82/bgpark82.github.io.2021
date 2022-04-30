class Solution {
    
    private static Map<Character, Integer> numMap = new HashMap();
    
    static {
        numMap.put('I', 1);
        numMap.put('V', 5);
        numMap.put('X', 10);
        numMap.put('L', 50);
        numMap.put('C', 100);
        numMap.put('D', 500);
        numMap.put('M', 1000);
    }
    
    
    /** 
     * MCMXCIV
     * 1000, (100, 1000), (10, 100), (1, 5)
     */
    public int romanToInt(String s) {
        // 1. if adjacent letter has same digit then calculate
        int sum = 0;
        // 배열 안에서 다음 인덱스를 처리하는게 아니라 loop 밖에서 캐싱해서 사용
        int prev = numMap.get(s.charAt(0)); 
        
        for(int i = 1; i < s.length(); i++) {
            
            int next = numMap.get(s.charAt(i));
                
            if(prev < next) {
                sum -= prev;
            } else {
                sum += prev;
            }
            
            prev = next;
        }
        
        return sum + prev;
    }
}