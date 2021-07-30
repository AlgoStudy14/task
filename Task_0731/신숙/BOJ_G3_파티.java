import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_G3_파티 {
	/*
	 * <문제>
	 * N(마을의 수. 마을당 학생 1명), M(단방향 도로의 개수), X(파티가 열리는 장소)이 주어짐.
	 * 그 뒤 M + 1줄까지 i번째의 도로 시작점, 끝점, 이도로를 지나는데 소요한 시간이 주어짐.
	 * 각각 학생들이 파티에 참석하기 위해 걸어가서 그들의 마을로 다시 돌아와야함.
	 * 
	 * N명의 학생 중 갔다가 오는데 가ㅏㅇ 많은 시간을 소비한 학생이 걸린 시간은?
	 * 
	 * <문제풀이>
	 * 출발점이 정해진 다익스트라.
	 * 출발점(각각 학생들의 마을)부터 파티가 열리는 지점까지 다익스트라 1번			
	 * X부터 그들의 마을까지 다익스트라 1번 합쳐서 가장 time이 큰 마을 번호 출력.	
	 * 		
	 * <해결>
	 * 1. java.lang.ArrayIndexOutOfBoundsException: 5
	 * 		=> 답 출력할때 N 이 아니라 M을 했었음.
	 */
	
	static class Graph implements Comparable<Graph>{
		int i;
		int time;
		
		Graph(int i, int t){
			this.i = i;
			this.time = t;
		}
		
		public int compareTo(Graph g) {
			return this.time - g.time;
		}
	}
	
	static int N, M, X, ans;
	static ArrayList<ArrayList<Graph>> list, rev;
	static int[] time, revTime;
	static boolean[] visited;
	static int INF = Integer.MAX_VALUE;
	static PriorityQueue<Graph> pq;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		
		//초기화
		time = new int[N + 1];			//마을에서 X 가는 경우
		revTime = new int[N + 1];		//X에서 마을 가는 경우
		Arrays.fill(time, INF);
		Arrays.fill(revTime, INF);
		
		list = new ArrayList<>();
		rev = new ArrayList<>();
		
		for(int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
			rev.add(new ArrayList<>());
		}
		
		for(int i = 1; i < M + 1; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int time = sc.nextInt();
			
			list.get(start).add(new Graph(end, time));
			rev.get(end).add(new Graph(start, time));
		}
		
		dijkstra(list, time, X);
		dijkstra(rev, revTime, X);
		
		for(int i = 1; i < N + 1; i++)
			ans = Math.max(ans, time[i] + revTime[i]);
		
		System.out.println(ans);
	}
	
	static void dijkstra(ArrayList<ArrayList<Graph>> g, int[] t, int start) {
		visited = new boolean[N + 1];
		pq = new PriorityQueue<>();
		pq.add(new Graph(start, 0));
		t[start] = 0;
		
		while(!pq.isEmpty()) {
			int index = pq.poll().i;
			
			if(visited[index])
				continue;
			visited[index] = true;
			//다익스트라
			for(Graph node : g.get(index)) {
				if(t[node.i] > t[index] + node.time) {
					t[node.i] = t[index] + node.time;
					pq.add(new Graph(node.i, t[node.i]));
				}
			}
		}
	}
}
