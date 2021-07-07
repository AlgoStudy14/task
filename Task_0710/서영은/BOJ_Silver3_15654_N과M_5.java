package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Silver3_15654_N과M_5 {

	static int N, M, comb[], arr[];
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		comb = new int[M];
		visited = new boolean[N];
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int idx = 0; idx < N; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		combination(0);
		
		bw.write(sb.toString()+"\n");
		
		in.close();
		bw.flush();
		bw.close();
	}
	
	private static void combination(int idx) {
		if(idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(comb[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			comb[idx] = arr[i];
			combination(idx+1);
			visited[i] = false;
		}
	}
}
