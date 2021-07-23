import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G2_장난감조립 {
	/*
	 * <문제>
	 * 첫째줄 N이 주어지는데 1 ~ N-1 까지는 부품이고, N은 완제품임.
	 * 그 뒤 M이 주어지고 M개의 줄에 부품간의 관계가 나타남. X Y Z로 나타나는데 이는 중간부품이나 완제품 X를 만드는데 부품 Y가 Z개 필요하다.
	 * 완제품을 조립하는데 필요한 기본부품들의 수를 출력. ex) 1 4  2 5  3 6 ...
	 * 
	 * <문제풀이>
	 * 그래프 문제.
	 * 아직 익숙치 않은 개념이라 어려운듯.
	 */
	//		총 물건의 수 , 연결된 상위부품 수
	static int need[], conn[];
	static boolean[] basic;
	static int N, M;
	static ArrayList<Info>[] list;
	static class Info{
		int from, to;
		public Info(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		need = new int[N + 1];
		conn = new int[N + 1];
		basic = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			basic[i] = true;
		}
		
		for(int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			conn[y]++;
			list[x].add(new Info(y, z));
			basic[x] = false;
		}
		
		sort();
		
		for(int i = 1; i <= N; i++) {
			if(basic[i])
				System.out.println(i + " " + need[i]);
		}
	}
	
	static void sort() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		need[N] = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			int num = need[now];
			int size = list[now].size();
			for(int i = 0; i < size; i++) {
				int next = list[now].get(i).from;
				int next_value = list[now].get(i).to;
				conn[next]--;
				need[next] += (num * next_value);
				if(conn[next] == 0)
					q.offer(next);
			}
		}
	}
	
	
	/*
	static int N, M;
	static boolean[] basic;	//기본부품인지 확인 
	static int[] indegree, ans;
	static int[][] map; 	//map...?
	static ArrayList<int[][]>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		basic = new boolean[N + 1];
		indegree = new int[N + 1];
		map = new int[N + 1][N + 1];
		ans = new int[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<int[][]>();
			basic[i] = true;
		}
		
		for(int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			indegree[y]++;
			list[x].add(new int[y][z]);
			basic[x] = false;
		}
		
		sort();
		
	}
	
	static void sort() {
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			int size = list[now].size();
			for(int i = 0; i < size; i++) {
				int[][] link = list[now].get(i);
				if(basic[now]) 
					ans[now]++;
				else{
					//연결된 거에 따라 기본 부품 수를 늘려야하는데....흠... 5 -> 1 가는 것 관리필요. 
					
				}
				indegree[link]--;
				if(indegree[link] == 0)
					q.offer(link);
			}
		}
	}*/
}
