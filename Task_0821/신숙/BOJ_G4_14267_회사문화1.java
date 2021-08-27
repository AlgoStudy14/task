import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G4_14267_회사문화1 {
	/*
	 * <문제>
	 * 상사가 직속부하를 칭찬하면 또 그 부하는 직속부하에게 내리칭찬을 해야함. => 상사가 직속부하를 칭찬하면 모든 부하들이 칭찬받음.
	 * 칭찬에는 수치가 있는데, 수치또한 똑같이.
	 * 각자 얼마의 칭찬을 받았는가?
	 * 
	 * <문제풀이>
	 * bfs로 3째줄부터 큐에 넣어서 값 더해주기?
	 * int[] mem => 이걸 바꿔줘야겠다. 
	 */
	static int N, M;
	static ArrayList<ArrayList<Integer>> mem;
	static int[] ans;
	static Queue<Integer> q;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = new int[N + 1];
		M = sc.nextInt();
		mem = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++)
			mem.add(new ArrayList<Integer>());
				
		for(int i = 1; i <= N; i++) {
			int member = sc.nextInt();
			if(member == -1)
				continue;
			mem.get(member).add(i);
		}
		
		for(int i = 0; i < M; i++) {
			int num = sc.nextInt();
			int praise = sc.nextInt();
			
			ans[num] += praise;
		}
		
		bfs(1);
		
		for(int i = 1; i <= N; i++)
			System.out.print(ans[i] + " ");
	}
	
	static void bfs(int s) {
		q = new LinkedList<>();
		q.add(s);
		
		while(!q.isEmpty()) {
			int upper = q.poll();
			
			for(int n : mem.get(upper)) {
				ans[n] += ans[upper];
				q.offer(n);
			}
		}
	}
}
