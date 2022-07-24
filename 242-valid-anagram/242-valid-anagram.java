class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        
        
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        
        Arrays.sort(sc);
        Arrays.sort(tc);
        
        boolean check = true;
        for(int i = 0; i < s.length(); i++) {
            if(sc[i] != tc[i]) {
                check = false;
                break;
            }
        }
        
        return check;
    }
}