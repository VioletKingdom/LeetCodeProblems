class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++){
            root[i] = i;
        }
        int numberOfConnection = n;
        for (int[] edge : edges){
            int root1 = findRoot(root, edge[0]);
            int root2 = findRoot(root, edge[1]);
            if (root1 != root2){
                root[root2] = root1;
                numberOfConnection--;
            }
            else {
                // this step is similar to connect every part together
                // if two nodes already connected together, they will have same root
                // in this case, there is still one edge between them means there is a cycle
                return false;
            }
        }
        // this step make sure that we have enough edges to make this graph only have one connected component
        return numberOfConnection == 1;
        
    }
    
    private int findRoot(int[] root, int a){
        while (root[a] != a){
            a = root[a];
        }
        return root[a];
    }
}

// use union find to connect every node together
