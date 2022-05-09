/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = 0;
    
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        
        return ans;
    }
    
    private int[] dfs2(TreeNode root) {
        if(root == null) {
            return new int[]{0,0};
        }
        
        int[] r = dfs2(root.right);
        int[] l = dfs2(root.left);
        
        int sum = r[0] + l[0] + root.val;
        int count = r[1] + l[1] + 1;    
        
        if(root.val == (sum/count)) {
            ans++;
        }
        
        return new int[]{sum, count};
    }
    
    private Pair dfs(TreeNode root) {
        if(root == null) {
            return new Pair(0,0);
        }
        
        Pair r = dfs(root.right);
        Pair l = dfs(root.left);
        
        int sum = r.sum + l.sum + root.val;
        int count = r.count + l.count + 1;    
        
        if(root.val == sum/count) {
            ans++;
        }
        
        return new Pair(sum, count);
    };
    
    static class Pair {
        int sum;
        int count;
        
        public Pair(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
}