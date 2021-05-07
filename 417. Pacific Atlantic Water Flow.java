/*
You are given an m x n integer matrix heights representing 
the height of each unit cell in a continent. 
The Pacific ocean touches the continent's left and top edges, 
and the Atlantic ocean touches the continent's right and bottom edges.

Water can only flow in four directions: up, down, left, and right. 
Water flows from a cell to an adjacent one with an equal or lower height.

Return a list of grid coordinates where water can flow to both 
the Pacific and Atlantic oceans.
*/

class Solution {
    public int[][] dir = {{0,-1},{0,1},{-1,0}, {1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        /*
        从海往陆地上走 要每个点大于等于上一个点才行
        用bfs一步一步来 queue里放满足条件可以往里走的坐标
        用两个boolean matrix来分别记录 能到达p和a的坐标
        能两个都到的就是答案 
        */
        int n = heights.length;
        int m = heights[0].length;
        //物理意义是true的位置可以到达p或a
        boolean[][] pv = new boolean[n][m];
        boolean[][] av = new boolean[n][m];
        
        // use queue to store the coordinates, need to use an int[] to store
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        
        for (int i = 0; i < n; i++){
            //第一列放进pacific queue
            pQueue.offer(new int[]{i,0});
            //最后一列放进atlantic queue
            aQueue.offer(new int[]{i, m-1});
            //第一列一定可以流进p, 最后一列一定可以流进a
            pv[i][0] = true;
            av[i][m-1] = true;
        }
        
        for (int j = 0; j < m; j++){
            pQueue.offer(new int[]{0, j});
            aQueue.offer(new int[]{n-1, j});
            pv[0][j] = true;
            av[n-1][j] = true;
        }
        
        bfs(heights, pQueue, pv);
        bfs(heights, aQueue, av);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (pv[i][j] && av[i][j]){
                    List<Integer> cur = new ArrayList<>();
                    cur.add(i);
                    cur.add(j);
                    result.add(cur);
                }
            }
        }
        return result;
    }
    
    //bfs作用就是改变pv和av当中的boolean值
    public void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited){
        int n = heights.length;
        int m = heights[0].length;
        
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int[] d : dir){
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                // check if boundry inside
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && heights[cur[0]][cur[1]] <= heights[x][y]){
                    queue.offer(new int[]{x,y});
                    visited[x][y] = true;
                }
            }
        }
    }
    
}