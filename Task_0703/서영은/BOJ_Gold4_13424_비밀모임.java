package algo210703;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_Gold4_13424_비밀모임 {

	static int room[][], d[][], person[], T, N, M, K, minRoomNum, minDist;
	static int INF = 100001;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			room = new int[N+1][N+1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j) room[i][j] = 0;
					else room[i][j] = INF;
				}
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				room[a][b] = room[b][a] = c;
			}
			
			K = Integer.parseInt(in.readLine());
			person = new int[K];
			st = new StringTokenizer(in.readLine(), " ");
			
			for (int i = 0; i < K; i++) {
				person[i] = Integer.parseInt(st.nextToken());
			}
			
			floydWarshall();
			
			findRoom();
			
			bw.write(minRoomNum+"\n");
		}
		
		in.close();
		bw.flush();
		bw.close();
		
	}
	
	private static void findRoom() {
		minDist = 1000000;
		minRoomNum = 101;
		
		for (int i = 1; i <= N; i++) {
			int distSum = 0;
			for (int k = 0; k < K; k++) {
				distSum += room[i][person[k]];
			}
			
			if(distSum < minDist) {
				minDist = distSum;
				minRoomNum = i;
			} else if(distSum == minDist) {
				if(i < minRoomNum) {
					minRoomNum = i;
				}
			}
		}
	}

	private static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(k==i) continue;
				for (int j = 1; j <= N; j++) {
					if(k==j || i==j) continue;
					if(room[i][j] > room[i][k] + room[k][j])
						room[i][j] = room[i][k] + room[k][j];
				}
			}
		}
	}

}
