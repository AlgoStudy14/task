import java.util.Arrays;
import java.util.Scanner;

public class BOJ_S4_숫자카드 {
	/*
	 * <문제>
	 * 숫자 카드는 정수 하나가 적혀 있는 카드. 
	 * N 개 카드를 가지고 있고, M개가 주어졌을때 이 수가 적혀있는 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 구하시오.
	 * 
	 * <문제풀이>
	 * 1. 범위가 매우큰 탐색문제는 이분탐색.
	 * 2. 이분탐색위해서 정렬
	 */
	static int N, M;
	static int[] num;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		for(int i = 0; i < N; i++)
			num[i] = sc.nextInt();
		
		Arrays.sort(num);
		
		M = sc.nextInt();
		//card = new int[M];
		for(int i = 0; i < M; i++) {
			//card[i] = sc.nextInt();
			//입력받은 수가 탐색해서 있으면 1출력. 그러면 굳이 배열 필요없네.
			if(binarySearch(sc.nextInt()))
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
	}
	static boolean binarySearch(int n) {
		int right = N - 1;
		int left = 0;
		int mid;
		
		while(left <= right) {
			mid = (right + left) / 2;
			if(num[mid] == n)
				return true;
			else if(num[mid] < n)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return false;
	}
}
