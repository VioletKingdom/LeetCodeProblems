class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        int i = 0;
        int j = nums.length-1;
        int index = a > 0 ? result.length-1 : 0;
        // 巧妙的O(n)解法
        // 当a == 0时，这个逻辑依然成立 只是要注意index起始位置 即从左往右填result还是从右往左填
        // 所以index判断时这个 a > 0? 和底下 if (a > 0) 要一致 
        // 不能一个是大于 一个是大于等于
        while (i <= j){
            if (a > 0){
                if (quad(nums[i], a, b, c) >= quad(nums[j], a, b, c)){
                    result[index] = quad(nums[i], a, b, c);
                    index--;
                    i++;
                }
                else {
                    result[index] = quad(nums[j], a, b, c);
                    index--;
                    j--;
                }
            }
            else {
                if (quad(nums[i],a, b, c) <= quad(nums[j], a, b, c)){
                    result[index] = quad(nums[i], a, b,c);
                    index++;
                    i++;
                } 
                else {
                    result[index] = quad(nums[j], a, b,c);
                    index++;
                    j--;
                }
            }
        }
        return result;
    }
    
    private int quad(int x, int a, int b, int c){
        return a*x*x + b*x + c;
    }
}