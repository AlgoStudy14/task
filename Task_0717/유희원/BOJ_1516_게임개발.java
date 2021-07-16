package algo0717;

import java.io.*;
import java.util.*;
//위상정렬
public class BOJ_1516_게임개발 {
	public static void main(String[] args) throws IOException {
		int bnum;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bnum = Integer.parseInt(br.readLine());
		int[] time = new int[bnum + 1];
		int[] dp = new int[bnum + 1];
		int[] in = new int[bnum + 1];
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		Queue<Integer> que = new LinkedList<>();
		for (int x = 0; x <= bnum; x++) {
			list.add(new ArrayList<>());
		}
		for (int x = 1; x <= bnum; x++) {
			StringTokenizer input = new StringTokenizer(br.readLine());
			int size = input.countTokens() - 1;
			time[x] = Integer.parseInt(input.nextToken());
			for (int y = 1; y < size; y++) {
				int p = Integer.parseInt(input.nextToken());
				list.get(p).add(x);
				in[x]++;
			}
		}
		for (int x = 1; x <= bnum; x++) {
			if (in[x] == 0) {
				dp[x] = time[x];
				que.add(x);
			}
		}
		while (!que.isEmpty()) {
			int now = que.poll();
			int arr_size = list.get(now).size();
			for (int y = 0; y < arr_size; y++) {
				//base
				int b = list.get(now).get(y);
				in[b]--;
				dp[b] = Math.max(dp[b], time[b] + dp[now]);
				if (in[b] == 0) {
					que.add(b);
				}
			}
		}
		for (int x = 1; x <= bnum; x++) {
			System.out.println(String.valueOf(dp[x]));
		}
	}
}
