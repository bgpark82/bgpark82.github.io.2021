class Solution {
    private Map<Character, String> map = new HashMap();
    
    public List<String> letterCombinations(String digits) {
        
        List<String> ans = new ArrayList();
        
        if(digits.isEmpty()) return ans;
    
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        track(digits, ans, 0, "");
        return ans;
    }
    
    private void track(String digits, List<String> ans, int idx, String word) {
        if(idx == digits.length()) {
            ans.add(word);
            return;
        }
        
        char[] arr = map.get(digits.charAt(idx)).toCharArray();
        for(int i = 0; i < arr.length; i++) {
            track(digits, ans, idx + 1, word + arr[i]);
        }
    }
}