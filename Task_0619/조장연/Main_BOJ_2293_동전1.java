import java.util.Scanner;

public class Main_BOJ_2293_동전1 {
	static int N, K;
	static int[] nums;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		nums = new int[N];
		arr = new int[K + 1];
		arr[0] = 1;

		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < K + 1; j++) {
				if (j >= nums[i]) {
					arr[j] += arr[j - nums[i]];
				}
			}
		}

		System.out.println(arr[K]);
	}

}
