// some swags need to be pack into boxes
// the boxes can only contain perfect square number amount swags
// find the minimum box amount needed for num swags

public packSwag(int num){
	if (int <= 0){
		return 0;
	}
	int[] dp = new int[num+1];// off by 1
	//dp[0] = 0;
	//dp[1] = 1;
	for (int i = 1; i <= num; i++){
		int sqrt = (int) Math.sqrt(i);
		if (sqrt*sqrt == i){
			dp[i] = 1;
			continue;
		}
		int min = i;
		for (int j = 1; j < i; j++){
			min = Math.min(min, dp[i-j]+dp[j]);
		}
	}
	return dp[num];
}
// TC O(n^2)
// SC O(n)