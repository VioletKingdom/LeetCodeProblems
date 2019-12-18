class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // create the result to return first
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        // corner case
        if (root == null){
            return result;
        }
        
        // key: the vertical number
        // value: the TreeNode list
        // so the values() of the map would be the result we want
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        
        // use queue to store the TreeNode by level order traversal
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // use queue to store the column number of the TreeNode 
        // these two queues should have same size all the time
        Queue<Integer> columns = new LinkedList<Integer>();
        
        // the min and max vlaue of the column numbers
        int min = 0;
        int max = 0;
        
        // offer root into the queue
        queue.offer(root);
        // define the root's column number is 0
        columns.offer(0);
        
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            Integer col = columns.poll();
            
            if (!map.containsKey(col)){
                map.put(col, new LinkedList<Integer>());
            }
            map.get(col).add(cur.val);
            
            if (cur.left != null){
                queue.offer(cur.left);
                columns.offer(col-1);
                min = Math.min(min, col-1);
            }
            
            if (cur.right != null){
                queue.offer(cur.right);
                columns.offer(col+1);
                max = Math.max(max, col+1);
            }
            
        }
        
        for (int i = min; i <= max; i++){
            result.add(map.get(i));
        }
        
        return result;
    }
}
