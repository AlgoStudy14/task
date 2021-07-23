import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* (완)
 * <문제 요약>
 * 문제 정의 : 장난감 환제품을 조립하기 위하여 필요한 기본 부품의 종류별 개수 계산
 * 문제 유형 : 위상 정렬
 * 
 * <풀이법 요약>
 * 1. 입력받을 때 중간 부품을 체크해준다
 * 2. 위상 정렬을 진행하면서 부품의 개수를 계산해준다.
 * 2-1. 만약 현재 부품을 처음 사용한다면 -> 필요 부품에 필요 개수만 더해준다.
 * 2-2. 이미 사용한 적 있는 부품이라면 -> 필요 부품에 (현재 부품의 개수 * 필요 부품)의 개수를 더해준다.
 * 3. 1에서 구한 체크를 사용하여 기본 부품의 번호과 개수를 출력한다.
 * 
 */

public class BOJ_G2_2637_장난감_조립 {

	static class Toy {
		int index;
		int cnt;

		public Toy(int index, int cnt) {
			this.index = index;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static int[] indegree, answer;
	// 중간 부품 체크
	// true : 중간 부품
	// false : 기본 부품
	static boolean[] check;
	static ArrayList<ArrayList<Toy>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		indegree = new int[N + 1];
		answer = new int[N + 1];
		check = new boolean[N + 1];
		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			// X -> Y
			graph.get(X).add(new Toy(Y, K));
			// 진입 차수를 1 증가
			indegree[Y]++;
			check[X] = true; // 중간 부품 체크
		}

		System.out.println(solve());
	}

	private static String solve() {
		topologySort();

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			if (!check[i]) { // 기본 부품일 때
				sb.append(i).append(" ").append(answer[i]).append("\n");
			}
		}

		return sb.toString();
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();

		// 처음 시작할 때 진입차수가 0인 노드를 큐에 삽입
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			for (Toy next : graph.get(now)) {
				// 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
				indegree[next.index]--;
				// 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
				if (indegree[next.index] == 0) {
					q.offer(next.index);
				}
				if (answer[now] == 0) { // 부품으로 처음 사용한다면
					answer[next.index] += next.cnt;
				} else { // 이미 사용한 적이 있다면
					answer[next.index] += (answer[now] * next.cnt);
				}
			}
		}
	}

}
