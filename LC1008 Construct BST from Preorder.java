class Solution {
    // 只需要一个index的位置 然后往下走就可以了
    // 不需要考虑 这段是left subtree，下一段是right subtree这些事情
    // 从第二个数开始 一直到中间某个位置 都是left substree
    // 根据recursion性质 一直都在走第一个recursion 走到头才回来一层一层弹出 走第二个recursion
    // it is a binary search tree,
    // the max at beginning is the root value, max is the right boundry of left subtree
    // and we are using the root value to determine the boundary
    // of left/right subtree.
    // base case: 1. reach the end of preorder array 2. the current value is greater than the max 
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        int[] index = new int[]{0};
        int max = Integer.MAX_VALUE;
        return helper(preorder, index, max);
    }
    
    private TreeNode helper(int[] preorder, int[] index, int max){
        if (index[0] == preorder.length || preorder[index[0]] > max) return null;
        TreeNode root = new TreeNode(preorder[index[0]]);
        index[0]++;
        root.left = helper(preorder, index, root.val);
        root.right = helper(preorder, index, max);
        return root;
    }
}