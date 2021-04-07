import java.util.Scanner;

public class BOJ_Silver1_14888 {
	/*
	 * 연산자 끼워넣기
	 * 
	 * N개로 이루어진 수열이 주어짐. 
	 * 덧셈, 뺄셈, 곱셉, 나눗셈 연산자 개수 주어질때
	 * 이를 통해 만들 수 있는 최댓값, 최솟값 구하는 문제
	 * 
	 * <해결방법>
	 * 1. DFS로, 현재 합과 수열의 index, 덧셈, 뺄셈, 곱셈, 나눗셈 연산자의 남은 개수를 넘겨주어 모두 0일때까지 dfs
	 * 2. 모두 0이되면 최대값 최소값 갱신.
	 */
	static int N, max, min;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		int plus = sc.nextInt();
		int minus = sc.nextInt();
		int mul = sc.nextInt();
		int div = sc.nextInt();
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(arr[0], 1, plus, minus, mul, div);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int sum, int index, int p, int m, int mul, int div) {
		if(p == 0 && m == 0 && mul == 0 && div == 0) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		if(p > 0)
			dfs(sum + arr[index], index + 1, p-1, m, mul, div);
		if(m > 0)
			dfs(sum - arr[index], index + 1, p, m-1, mul, div);
		if(mul > 0)
			dfs(sum * arr[index], index + 1, p, m, mul-1, div);
		if(div > 0 && arr[index] != 0)
			dfs(sum / arr[index], index + 1, p, m, mul, div-1);
	}
}
