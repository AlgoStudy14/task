import java.util.Scanner;

public class BOJ_S3_이친수 {
	/*
	 * <문제>
	 * N이 주어질때, N 자리 이진수 중에서 
	 * 1. 0으로 시작하지않고(시작은 무조건 1)
	 * 2. 1이 두 번 연속 나타나지 않는 수의 개수.
	 * 1 <= N <= 90
	 * 
	 * <문제풀이>
	 * 1. 시작은 1로만.
	 * 2. 앞의 수가 0으로 끝났다면 0, 1 2가지 가능
	 *    앞의 수가 1로 끝났다면 0만 가능.
	 * 3. N = 1와 N = 2는 1, N = 3은 2. 
	 * N = 4 일 때, 1000, 1001, 1010 ans = 3, 
	 * N = 5 일 때, 10000, 10001, 10010, 10100, 10101 ans = 5 , 
	 * N = 6 일 때, 100000, 100001, 100010, 100100, 100101, 101000, 101001, 101010 ans = 8
	 * 피보나치 형태를 띄고있음.
	 * 
	 * <문제점>
	 * 1. N 이 1이거나 2일때 값을 고정시켜두니까 1, 2일때 범위밖이라고 떠서 주의가 필요.
	 * 2. N <= 90인데, 90일때 답이 -158.... 뜸. INT가 아니라 long으로.
	 */
	
	static long[] arr;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new long[N];
		if(N > 0)
			arr[0] = 1;
		if(N > 1)
			arr[1] = 1;
		if(N > 2) {
			for(int i = 2; i < N; i++)
				arr[i] = arr[i-1] + arr[i-2];
		}
		
		System.out.println(arr[N-1]);
	}
}
