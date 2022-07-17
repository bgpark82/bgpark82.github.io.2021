class Solution {
    
    private List<String> ans = new ArrayList();
    
    public List<String> wordBreak(String s, List<String> dict) {
        track(s, dict, 0, new ArrayList<String>());   
        return ans;
    }
    
    private void track(String s, List<String> dict, int idx, List<String> words) {
        if(idx == s.length()) {
            ans.add(String.join(" ", words));
            return;
        }
        
        for(int i = idx; i < s.length(); i++) {
            String sub = s.substring(idx, i+1);
            if(dict.contains(sub)) {
                // 임시적으로 단어를 넣을 곳을 저장하고
                words.add(sub);
                track(s, dict, i+1, words);
                // 이후에 빼준다 (백트레킹의 핵심이며 String 대신 List를 사용할 수 있다는 것을 깨달았다)
                words.remove(words.size()-1);
            }
        }
    }
}