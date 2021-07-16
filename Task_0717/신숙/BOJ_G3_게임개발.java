import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G3_게임개발 {
	/*
	 * <문제>
	 * 게임 진행 시간을 조절하려고 하는데, 어떤 건물을 짓기 위해 다른 건물을 지어야한다.
	 * 입력시 처음 나오는게 그 건물을 짓는 시간. 그 뒤 -1 전까진 이 건물을 짓기 위해 필요한 건물들이다.
	 * 각 건물이 완성되기까지 걸리는 최소 시간 출력.
	 * 
	 * <문제풀이>
	 * 줄세우기랑 유사. 위상정렬.
	 * 하지만 추가로 시간을 저장할 배열이 있어야할듯.
	 * 
	 * 왜전부 0이나오지???
	 * 	=> 0부터 <=N 까지 받았네. 1부터 ㄱㄱ
	 * 
	 */
	static int N;
	static Queue<Integer> q;
	static int[] indegree, times, ans;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		list = new ArrayList[N + 1];
		indegree = new int[N + 1];
		times = new int[N + 1];
		ans = new int[N + 1];
		for(int i = 1; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i = 1; i <= N; i++) {
			times[i] = sc.nextInt();
			while(true) {
				int n = sc.nextInt();
				if(n == -1)
					break;
				list[n].add(i);
				indegree[i]++;
			}
		}
		q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
				ans[i] = times[i];
			}
		}
		sort();
		for(int i = 1; i <= N; i++)
			System.out.println(ans[i]);
	}
	
	static void sort() {
		while(!q.isEmpty()) {
			int temp = q.poll();
			int size = list[temp].size();
			for(int i = 0; i < size; i++) {
				int link = list[temp].get(i); 
				indegree[link]--;
				ans[link] = Math.max(ans[link], ans[temp] + times[link]);	//최소 시간이니까 더 큰거
				
				if(indegree[link] == 0)
					q.add(link);
			}
		}
	}
}
