import java.util.Scanner;

public class Solution_1952_수영장 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {

			int oneDay = sc.nextInt();
			int oneMonth = sc.nextInt();
			int threeMonth = sc.nextInt();
			int oneYear = sc.nextInt();
			
			int[] list = new int[13];
			int[] cost = new int[13];
			for(int i = 1; i <= 12; i++) {
				list[i] = sc.nextInt();
			}

			for (int i = 1; i <= 12; i++) {
				int day = 0;

				day = oneDay * list[i];
				cost[i] = Math.min(day, oneMonth);
				if (i > 1) {
					cost[i] += cost[i-1];
					if (i >= 3) {
						cost[i] = Math.min(cost[i - 3] + threeMonth, cost[i]);
					}
				}
			}

			System.out.println("#" + t + " " + Math.min(cost[12], oneYear));
		}
	}

}
