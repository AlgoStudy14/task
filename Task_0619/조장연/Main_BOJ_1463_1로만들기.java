import java.util.Scanner;

public class Main_BOJ_1463_1로만들기 {
	static int N;
	static int[] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1];

		for (int i = 2; i < N + 1; i++) {
			int num = Integer.MAX_VALUE;
			if (i % 2 == 0)
				num = Math.min(num, arr[i / 2] + 1);
			if (i % 3 == 0)
				num = Math.min(num, arr[i / 3] + 1);
			num = Math.min(num, arr[i - 1] + 1);
			arr[i] = num;
		}

		System.out.println(arr[N]);
	}

}
