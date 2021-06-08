993. Cousins in Binary Tree
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.


class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            // 注意这里在while loop里set false
            // 每次重新进while loop的时候都会把两个都刷新成false的
            // 不需要再在for loop里去set false
            boolean A = false;
            boolean B = false;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if (cur.val == x) A = true;
                if (cur.val == y) B = true;
                if (cur.left != null && cur.right != null){
                    if (cur.left.val == x && cur.right.val == y || cur.left.val == y && cur.right.val == x){ // 如果两个数是sibling, return false
                        return false;
                    }
                }
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
                // 两个数在同一个level出现 则是cousin, sibling的情况在往queue里加的时候就排除了
                if (A&&B) return true;
            }
        }
        return false;
    }
}