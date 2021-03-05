package algo0305;
//subset문제
//더하기 빼기 가능한지 확인하기
public class Solution_dfs_타겟넘버 {
	static int tot;
	public static void main(String[] args) {
		int numbers[] = {1,1,1,1,1};
		int target = 3;
		subset(0,0,numbers,target);
		System.out.println(tot);
	}
	private static void subset(int cnt, int sum,int[] numbers,int target) {
		if(cnt==numbers.length) {
			if(sum==target)
				tot++;
			return;
		}
		subset(cnt+1,sum+numbers[cnt],numbers,target);
		subset(cnt+1,sum-numbers[cnt],numbers,target);
	}
}
