package algo0619;
/*
 * 처음 다익스트라로 풀어야 된다고 생각했는데
 * 굳이..? 가중치가 모두 1인데 굳이 다익으로 해야되나 싶어서 안함
 * bfs로 풀음

 * 
 *  * <문제 요약>
 * 구해야 하는 것:특정 거리까지 도시들을 오름차순으로 출력하는 문제
 * 제약 사항: (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N) 
 * 문제 유형: dfs
 * 요구 개념: 다익스트라
 * 
 * <풀이법>
 * list[]로 만들어서 연결되어 있는거 다 집어넣음
 * que로 돌리면서 하나씩 빼면서 지나지 않은곳은 그 전에갔던곳 값 +1을함
 * k값을 찾아서 빼낸다.
 * 		int [] check = new int[N+1]; 이걸 쓰는게 중요한 문제

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_특정거리의도시찾기_18352 {
	static int N,M,K,X;
	static List<Integer>list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		
		list=new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			list[i]=new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			list[x].add(y);
		}
		
		int [] check = new int[N+1];
		Queue<Integer> que = new LinkedList<>();
		que.add(X);
		while(!que.isEmpty()) {
			int go = que.poll();
			for(int i=0;i<list[go].size();i++) {
				if(check[list[go].get(i)]==0) { //간곳인지 체크하는거
					check[list[go].get(i)]=check[go]+1;
					que.add(list[go].get(i));
				}
			}
		}
		boolean ok = false; // 하나도 없을때를 대비해서 하는거
		for(int i=1;i<check.length;i++) {
			if(check[i]==K && i!=X) {
				ok=true;
				System.out.println(i);
			}
		}
		if(!ok)
			System.out.println(-1);
	}
}
