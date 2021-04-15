package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 구할 수 있는 양의 수
 * 제약 사항: 2<=N<=123456
 * 문제 유형: 트리 
 * 요구 개념: 트리 / 후위순회
 * 
 * <풀이법 요약>
 * 1. 각 위치마다 S, W 표시(객체로 만들어서)
 * 2. 지나가는거 체크
 * => 처음에 그래프 깊이우선탐색으로 풀어서 틀렸다.
 * => 트리 후위순회!!!!
 * 
 */
public class BOJ_Gold2_16437_양구출작전 {


	static int N;
	static long w[], s[];
	static long count;
	static ArrayList<Integer>[] nodes;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		w = new long[N+1];
		s = new long[N+1];
		
		nodes = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st=null;
		for (int from = 2; from <= N; from++) {
			st = new StringTokenizer(in.readLine(), " ");
			String type = st.nextToken();
			long cnt = Long.parseLong(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(type.equals("S")) {
				s[from] = cnt;
			} else {
				w[from] = cnt;
			}
			
			nodes[to].add(from);
		}
		
		System.out.println(postOrder(1));
	}
	
	private static long postOrder(int node) {
		long sum = 0;
		
		//left -> Right -> Root
		for(int next : nodes[node]) {
			sum += postOrder(next);
		}
		
		if(w[node] != 0 && s[node] == 0) {
			return w[node] >= sum ? 0 : sum-w[node]; 
		} else {
			return sum += s[node];
		}
		
	}
}
