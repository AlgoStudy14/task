import java.util.Scanner;

public class Solution {
	static int T, N, M;
	static int[][] virusPosition;

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			virusPosition = new int[N][4];
			int max = Integer.MIN_VALUE; // 전체 맵 범위 정할 때 필요
			for (int i = 0; i < N; i++) {
				virusPosition[i][0] = sc.nextInt();
				virusPosition[i][1] = sc.nextInt();
				virusPosition[i][2] = sc.nextInt();
				virusPosition[i][3] = sc.nextInt();
				max = Math.max(max, virusPosition[i][0]);
				max = Math.max(max, virusPosition[i][1]);
				max = Math.max(max, virusPosition[i][2]);
				max = Math.max(max, virusPosition[i][3]);
			}

			int start = 1;
			int end = max;
			int middle = max / 2;
			int maxCount = 0;
			int target = N - M;
			while (true) {
				for (int i = middle; i <= max; i++) {
					for (int j = middle; j <= max; j++) {
						int count = 0;
						for (int k = 0; k < N; k++) {
							if ((j - middle) <= virusPosition[k][0] && (j - middle) <= virusPosition[k][2]
									&& (i - middle) <= virusPosition[k][1] && (i - middle) <= virusPosition[k][3]
									&& j >= virusPosition[k][0] && j >= virusPosition[k][2] && i >= virusPosition[k][1]
									&& i >= virusPosition[k][3])
								count++;
						}
						maxCount = Math.max(maxCount, count);
					}
				}

				
				if (end == start) {
					break;
				}

				if (maxCount >= target) // 목표보다 많이 죽이는 경우
				{
					end = middle;
					middle = start + (end - start) / 2;
					maxCount = 0;
				} else {
					start = middle + 1;
					middle = start + (end - start) / 2;
					maxCount = 0;
				}
			}

			System.out.println("#" + t + " " + start);
		}
	}
}
