package DisjointSet.Kruskal;

/*
 * 최소 스패닝 트리의 가중치 출력
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1197_최소스패닝트리 {

	static int V,E,A,B;
	static long C;
	static class Info implements Comparable<Info>{
		int idx;
		long val;
		public Info(int idx, long val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Info o) {
			// TODO Auto-generated method stub
			return Long.compare(this.val, o.val);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//정점의 개수
		V = sc.nextInt();
		//간선의 개수
		E = sc.nextInt();
		
		boolean visit[] = new boolean[V+1];
		List<Info> li[];
		li = new List[V+1];
		for (int i = 1; i <= V; i++) {
			li[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i <E; i++) {
			A = sc.nextInt();
			B = sc.nextInt();
			C = sc.nextLong();
			//각 노드의 간선들을 li배열에 모두 담음
			li[A].add(new Info(B,C));
			li[B].add(new Info(A,C));
		}
		long result = 0;
		PriorityQueue<Info> pq = new PriorityQueue<>();
		Deque<Integer> dq = new ArrayDeque<>();
		//정점 1에서 시작, deque에 담고 시작. deque에는 항상 최대 1개의 노드만 가질 예정
		dq.offer(1);
		while(!dq.isEmpty()) {
			//dq에서 정점을 빼며 각 정점에 연결된 모든  간선들 중 방문하지 않은 노드와 연결된 간선의 경우 우선순위큐에 삽입
			int cidx = dq.poll();
			visit[cidx] = true;
			for (int i = 0; i < li[cidx].size(); i++) {
				int next = li[cidx].get(i).idx;
				long nv = li[cidx].get(i).val;
				//우선순위큐에 삽입
				if(!visit[next]) pq.offer(new Info(next,nv));
			}
			//우선순위큐가 빌때까지
			while(!pq.isEmpty()) {
				int next = pq.peek().idx;
				long nv = pq.peek().val;
				pq.poll();
				if(!visit[next]) {
					//최소의 가중치를 가지며 방문하지 않은 노드와 연결되는 경우
					visit[next] = true;
					//다음 node를 deque에 삽입하고 멈춤
					dq.add(next);
					result += nv;
					break;
				}
			}
		}
		System.out.println(result);
	}

}
