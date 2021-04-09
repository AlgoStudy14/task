import java.util.Scanner;
/* S1
 * N개의 수, N-1개의 연산자로 만들 수 있는 최댓값 최솟값 출력
 * 수의 순서는 그대로,
 * 연산자 우선순위 무시하고 앞에서부터 계산
 */
public class BOJ_14888_연산자끼워넣기 {

	static int N,max,min;
	static int[] numbers, operators;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		operators = new int[4];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i <N; i++) {
			numbers[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			operators[i] = sc.nextInt();
		}
		dfs(numbers[0],1);
		System.out.println(max);
		System.out.println(min);

	}
	private static void dfs(int num, int cnt) {
		if(cnt == N) {
			max = Math.max(num, max);
			min = Math.min(num, min);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(operators[i]>0) { //연산자 있으면
				operators[i]--; //사용해봄
				switch(i) {
				case 0: dfs(num+numbers[cnt],cnt+1); 
				break;
				case 1: dfs(num-numbers[cnt],cnt+1); 
				break;
				case 2: dfs(num*numbers[cnt],cnt+1); 
				break;
				case 3: dfs(num/numbers[cnt],cnt+1); 
				break;
				}
				//재귀호출 종료되면 다시 연산자 개수 돌려놓음
				operators[i]++;
			}
		}
		
	}

}
