import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G2_줄세우기 {
	/*
	 * <문제>
	 * N명의 학생들을 키 순으로 줄을 세움. 키를 비교하는 방법은 두 학생의 키를 비교하는데 일부학생들의 키만 비교함.
	 * N, M이 주어지는데, m은 키를 비교한 횟수. 다음 M개의 줄에 두 학생의 키를 비교한 결과가 주어짐. 앞의 수가 더 큰 학생.
	 * 
	 * <위상정렬>
	 * 위상정렬 = 그래프 정렬
	 * 이를 하려면 방향성이 있으며 사이클이 없는 그래프여야함.
	 * dfs를 사용하거나 indegree 배열을 사용하여 구현 가능.
	 * indegree : 진입차수. 나에게 들어오는 간선의 수 (예제에선 3의 indegree는 2, 1과 2는 0)
	 * 
	 * <예제풀이>
	 * 1. 그래프 입력 받으면서 Indegree[] 배열 초기화
	 * 2. for문 돌며 Indegree[] 배열이 0인 정점을 Queue에 넣는다.
     * 3. queue에서 1번이 나온다. 1번과 연결된 3번의 Indegree를 하나 줄여준다.
     * 4. 큐에서 2가 나온다. 2와 연결된 3의 indegree 차수를 줄여준다.
	 * 5. 3의 indegree가 0이되었기 때문에 다시 queue에 넣어준다. 
	 * 6. 큐에서 3을 꺼낸다. 더이상 갈 곳이 없고 queue가 비었기 때문에 탐색 종료.
 	 * 7. 큐에서 꺼내진 순서 1-2-3 으로 정렬이 된다.
	 */
	static int N, M;
	static Queue<Integer> q;
	static int[] indegree, visited;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		list = new ArrayList[N + 1];
		indegree = new int[N + 1];
		visited = new int[N + 1];
		
		for(int i = 0; i <= N; i++)
			list[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M; i++) {
			int index = sc.nextInt();
			int idgree = sc.nextInt();
			list[index].add(idgree);
			indegree[idgree]++;
		}
		//큐에 indegree가 0인 값 넣어주기.
		q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0)
				q.add(i);
		}
		sort();//위상정렬
	}
	
	static void sort() {
		while(!q.isEmpty()) {
			int ind = q.poll();
			System.out.print(ind + " ");
			int size = list[ind].size();
			for(int i = 0; i < size; i++) {
				int link = list[ind].get(i);
				if(indegree[link] == 0)
					continue;
				indegree[link]--;
				if(indegree[link] == 0)
					q.add(link);
			}
		}
	}
}
