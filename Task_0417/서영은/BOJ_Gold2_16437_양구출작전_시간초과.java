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
 * 문제 유형: 그래프 
 * 요구 개념: 그래프 / 깊이 우선 탐색 
 * 
 * <풀이법 요약>
 * 1. 경로 p[] 배열 만들어서 설정
 * 2. 각 위치마다 S, W 표시(객체로 만들어서)
 * 3. 지나가는거 체크
 */
public class BOJ_Gold2_16437_양구출작전_시간초과 {

	static class Seom {
		int vertex;
		Seom next;
		public Seom(int vertex, Seom next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}

	static int N, w[], s[];
	static long cnt;
	static boolean[] visited;
	static Seom[] seom, findLeaf;
	static List<Integer> leaf = new ArrayList<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		findLeaf = new Seom[N+1];
		seom = new Seom[N+1];
		visited = new boolean[N+1];
		w = new int[N+1];
		s = new int[N+1];
		
		StringTokenizer st=null;
		for (int from = 2; from <= N; from++) {
			st = new StringTokenizer(in.readLine(), " ");
			String type = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(type.equals("S")) {
				s[from] = cnt;
			} else {
				w[from] = cnt;
			}
			findLeaf[to] = new Seom(from, findLeaf[to]);
			seom[from] = new Seom(to, seom[from]);
		}
		
		visited[1] = true;
		findLeafProcess(1);
		
		for(Integer c : leaf) {
			cnt += (long)s[c];
			dfs(c);
		}
		
		System.out.println(cnt);
	}

	private static void findLeafProcess(int current) {
		int check=0;
		for(Seom temp = findLeaf[current]; temp != null; temp=temp.next) {
			if(!visited[temp.vertex]) {
				visited[temp.vertex] = true;
				++check;
				findLeafProcess(temp.vertex);
			}
		}
		if(check==0) leaf.add(current);
	}

	private static void dfs(int current) {
		for(Seom temp = seom[current]; temp != null; temp=temp.next) {
				
				if(w[temp.vertex] != 0) {
					cnt = w[temp.vertex] >= cnt ? 0 : cnt- (long) w[temp.vertex];
				} else if(s[temp.vertex] != 0) {
					cnt += (long) s[temp.vertex];
				}
				dfs(temp.vertex);
		}
	}
}
