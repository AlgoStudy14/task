import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 일부 학생의 키 관계가 주어졌을 때, 줄을 세우는 방법을 구하여라.
 * 문제 유형 : 위상 정렬.
 * 주의 사항 : 답이 여러 가지(순서가 여러 가지)인 경우는 아무거나 출력해도 된다.
 * <풀이 요약>
 * 1. 입력 내용을 바탕으로 인접 리스트를 사용하여 그래프를 선언한다.
 * -> 그래프를 선언하는 동시에 각 노드의 진입차수를 배열에 저장해둔다.
 * 2. 먼저 초기 진입 차수가 0인 그래프를 모두 큐에 넣는다.
 * 3. 큐에서 값을 꺼내며, 해당 노드가 가리키는 노드의 진입 차수를 1씩 감소 시킨다.
 * -> 큐에서 꺼낸 값을 배열에 저장한다.
 * -> 이때, 해당 진입 차수가 0이 된다면 큐에 넣는다.
 * 4. 2, 3과정을 큐가 모두 빌 때 까지 반복한다.
 * <피드백>
 * 1. 일단 위상 정렬 개념 자체는 매우 이지한디?
 * 2. 사용 : 그래프 형태로 주어진 자료에서 선후 관계를 따지고 싶은 경우 유용!
 */
public class BOJ_G2_2252_줄세우기 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 진입 차수를 저장할 배열.
		indegree = new int[N + 1];
		// 그래프를 정의하며 진입차수도 계산.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			// 도착 노드의 진입 차수 + 1
			indegree[b]++; 
		}
		
		// 위상 정렬.
		// 진입 차수가 0인 대상을 먼저 큐에 넣는다.
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0)
				q.offer(i);
		}
		
		// 결과를 저장할 배열
		// q의 값이 빌 때 까지 위상 정렬을 반복한다.
		while(!q.isEmpty()) {
			int cur = q.poll();
			// 뽑힌 노드 순서대로 순서가 정의된다.
			System.out.print(cur + " ");
			
			// 다음 노드를 탐색하며, 해당 노드의 진입 차수를 - 1 한다.
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int nxt = graph.get(cur).get(i);
				
				// 진입 차수 - 1.
				indegree[nxt]--;
				// 만일, 해당 진입 차수가 0이라면 큐에 값을 새로 넣기.
				if(indegree[nxt] == 0)
					q.offer(nxt);
			}
		}
	}
}
