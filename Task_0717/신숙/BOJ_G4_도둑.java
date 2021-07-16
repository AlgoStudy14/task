import java.util.Scanner;

public class BOJ_G4_도둑 {
	/*
	 * <문제>
	 * 테케 개수 T가 주어지고, 그다음으로 T개의 테케에 집 개수 N, 돈을 훔칠 집의 개수 M, 방범장치가 작동하는 돈 K가 주어짐.
	 * 그 다음라인에 N개의 집이 가지고 있는 돈이 주어짐
	 * 각 테케마다 집에서 돈을 훔칠 수 있는 가짓수출력.
	 * 
	 *  <문제풀이>
	 *  투포인터로 a~b 까지 m개의 집을 훔쳤을 때 < K 면 ANS++;
	 */
	static int T, N, M, K, sum, ans;
	static int[] home;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			home = new int[N];
			sum = 0;
			ans = 0;
			//입력받고 0~M 합
			for(int i = 0; i < N; i++) {
				home[i] = sc.nextInt();
				if(i < M)
					sum+= home[i];
			}
			
			if(N == M)
				ans = sum < K ? 1 : 0;
			else {
				for(int i = 0; i < N; i++) {
					if(sum < K)
						ans++;
					sum = sum + home[(i + M) % N] - home[i];
				}
			}
			System.out.println(ans);
		}
	}
}
