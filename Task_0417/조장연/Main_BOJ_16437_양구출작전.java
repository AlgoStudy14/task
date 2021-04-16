import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_BOJ_16437_양구출작전 {

	static int N;
	static int[] boss;
	static Island[] islandArr;
	static long answer;
	static ArrayList<Integer> arrList;
	static ArrayList<Integer> visitedList;

	static class Island {
		boolean animal; // 양은 false, 늑대는 true
		int number;

		public Island(boolean animal, int number) {
			super();
			this.animal = animal;
			this.number = number;
		}

		public Island() {
			super();
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		boss = new int[N + 1];
		islandArr = new Island[N + 1];
		islandArr[0] = new Island();
		islandArr[1] = new Island();
		arrList = new ArrayList<>();
		answer = 0;

		for (int i = 2; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			if (st.nextToken().equals("S")) {
				islandArr[i] = new Island(false, Integer.parseInt(st.nextToken()));
				boss[i] = Integer.parseInt(st.nextToken());
			} else {
				islandArr[i] = new Island(true, Integer.parseInt(st.nextToken()));
				boss[i] = Integer.parseInt(st.nextToken());
			}

			if (!arrList.contains(boss[i]))
				arrList.add(boss[i]);
		}

		for (int i = 2; i < N + 1; i++) {
			if (islandArr[i].animal == true)
				continue;

			if (arrList.contains(i))
				continue;

			long count = 0;
			int point = i;
			while (point != 1) {
				if (islandArr[point].animal == false) {
					count += islandArr[point].number;
					islandArr[point].number = 0;
					point = boss[point];
				} else {
					if (count >= islandArr[point].number) {
						count -= islandArr[point].number;
						islandArr[point].number = 0;
					} else {
						islandArr[point].number -= count;
						count = 0;
					}

					point = boss[point];
				}
			}

			if (count > 0)
				answer += count;
		}

		System.out.println(answer);
	}

}
