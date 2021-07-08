import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 각 카드의 묶음을 합쳐서 정렬할 때 필요한 최소 비교 횟수.
 * 문제 유형 : 그리디, 자료 구조.
 * <풀이 요약 : 그리디, 자료 구조>
 * 1. 우선 순위큐를 이용하여 오름 차순으로 카드의 개수를 넣는다.
 * 2. 앞의 두 카드를 뽑은 뒤 합쳐서 다시 우선 순위큐에 넣는다.
 * 3. 우선 순위큐의 크기가 1이 된다면, 해당 값을 출력한다.
 * <피드백>
 * 입력 자료의 크기가 최대 100,000개 이므로 nlogn정렬과 그리디를 생각할 수 있었다.
 */

public class BOJ_G4_1715_카드정렬하기 {
	static int N;
	static PriorityQueue<Integer> pq;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<Integer>((o1, o2) -> o1.compareTo(o2));
		ans = 0;
		for(int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		while(pq.size() != 1) {
			int a = pq.poll();
			int b = pq.poll();
			ans += (a + b);
			pq.offer(a + b);
		}
		System.out.println(ans);
	}
}
