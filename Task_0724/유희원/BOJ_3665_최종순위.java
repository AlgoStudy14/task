package algo0724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_3665_최종순위 {

	final static int team = 0;
	final static int degreeVal = 1;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int t=1; t<=T; t++) {

			int N = Integer.parseInt(br.readLine());

			int rank[] = new int[N+1];

			st = new StringTokenizer(br.readLine());

			for(int i=1; i<=N; i++) {
				rank[i] = Integer.parseInt(st.nextToken());
			}

			int degreeMap[][] = new int[N+1][2];

			boolean map[][] = new boolean[N+1][N+1];


			for(int i=1; i<=N; i++) {

				int teamNum = rank[i];

				degreeMap[teamNum][team] = teamNum;
				degreeMap[teamNum][degreeVal] = i-1;

				for(int j=1; j<i; j++) {

					map[teamNum][rank[j]] = true;

				}

			}
			int K = Integer.parseInt(br.readLine());

			for(int i=1; i<=K; i++) {

				st = new StringTokenizer(br.readLine());

				int team1 = Integer.parseInt(st.nextToken());
				int team2 = Integer.parseInt(st.nextToken());

				if(map[team1][team2]) {

					degreeMap[team1][degreeVal]--;
					degreeMap[team2][degreeVal]++;

				}else {

					degreeMap[team1][degreeVal]++;
					degreeMap[team2][degreeVal]--;

				}

				map[team1][team2] = !map[team1][team2];
				map[team2][team1] = !map[team2][team1];

			}
			degreeMap[0][degreeVal] = -1;  
			Arrays.sort(degreeMap, new Comparator<int[]>() {

				@Override

				public int compare(int[] o1, int[] o2) {

					return o1[degreeVal] - o2[degreeVal];

				}

			});
			String result = "";

			for(int i=1; i<=N; i++) {

				if(degreeMap[i][degreeVal] == i-1) {
					result += degreeMap[i][team] + " ";

				}else {

					result = "IMPOSSIBLE";

					break;
				}
			}

			System.out.println(result);

		}
	}
} 
