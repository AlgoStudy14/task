package algo210710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1021_장난감조립 {

	static int N, M, lastBasicIdx, relation[][], contentF[];
	static boolean isNotBasic[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		relation = new int[N+1][N+1];
		contentF = new int[N+1];
		isNotBasic = new boolean[N+1];
		
		StringTokenizer st = null;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int middleIdx = Integer.parseInt(st.nextToken());
			int basicIdx = Integer.parseInt(st.nextToken());
			int basicAmount = Integer.parseInt(st.nextToken());
			
			relation[middleIdx][basicIdx] = basicAmount;
			isNotBasic[middleIdx] = true;
		}
		
		findAmount(N, 1);
		
		for (int i = 1; i <= N; i++) {
			if(!isNotBasic[i]) {
				System.out.println(i + " " + contentF[i]);
			}
		}
	}
	
	private static void findAmount(int num, int amount) {
//		boolean last = false;
		contentF[num] += amount;
		for (int idx = 1; idx <= num; idx++) {
			if(relation[num][idx] != 0) {
//				last = true;
				findAmount(idx, relation[num][idx] * amount);
			}
		}
		
//		if(!last) contentF[num] += amount;
	}
}
