import java.util.Arrays;

public class Solution_프로그래머스_입국심사 {
	/*
	 * 3,5,7,8이 틀리는건 long으로 바꿔줘야 한다고 한다.
	 * 그치만 인덱스에 들어가려면 int로 해야되는데
	 * 어케 해야될지 모르겟다
	 * 
	 */

	public static void main(String[] args) {
		int n=6;
		int[] times= {7,10};
		long answer=solution(n,times);
		System.out.println(answer);
	}

	private static long solution(int n, int[] times) {
		int len = times.length;
		Arrays.sort(times);
		System.out.println(Arrays.toString(times));
		long right=times[len-1]*n;
		long answer=right;
		long sum=0,left=0,mid=0;
		while(left<=right) {
			sum=0;
			mid = (left+right)/2;
			for(int i=0;i<len;i++) {
				sum+=mid/times[i];
				System.out.println(sum);
			}
			if(sum<n) left=mid+1;
			else {
				if(mid<=answer)answer=mid;
				right=mid-1;
			}
		}
		return answer;
	}

}
