import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 모든 수업을 진행하는데 필요한 최소 강의실의 개수.
 * 문제 유형 : 그리디.
 * 주의 사항 : 수업이 겹치는 경우 강의실이 한 개 더 필요하다.
 * <풀이 요약>
 * 1. 우선순위 큐를 이용하여 수업이 먼저 '시작하는 순서'대로 값을 반환한다.
 * 2. 강의실의 '최종 종료 시간'을 저장할 우선순위큐를 만든다('오름 차순). 수업 배치가 가능하면 해당 수업의 종료 시간으로 값을 갱신해둔다.
 * 3. 다음 수업 시작 시간이 최종 수업 종료시간보다 크다면 해당 수업 시간으로 우선순위 큐의 값을 갱신하고 다시 저장한다.
 * -> 만일 그렇지 않다면, 우선 순위에 해당 최종 수업 종료 시간을 추가로 저장한다.
 * 4. 강의실의 최종 종료 시간을 저장한 우선순위큐의 원소의 총 개수를 출력한다.
 * <피드백>
 * 그리디.. 풀었는데 증명은 못하겠네..?
 */

public class BOJ_G5_11000_강의실배정 {
	static int N;
	static PriorityQueue<int[]> pq;
	static PriorityQueue<Integer> time;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 종료 시간 오름차순으로 정렬
		pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[0], o2[0]));
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}
		// 각 강의실 시간표 갱신
		time = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o1, o2));
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			// 모든 강의실이 비어있는 경우 그냥 넣고 스킵.
			if (time.isEmpty()) {
				time.offer(cur[1]);
				continue;
			}
			// 다음 수업 시작 시간이 현재 최소 종료 시간을 가진 강의실 보다 큰 경우
			if(time.peek() <= cur[0]) {
				// 해당 강의실의 최종 종료 시간을 갱신하고 다시 큐에 넣기
				time.poll();
				time.offer(cur[1]);
			}
			// 그렇지 않은 경우 현재 수업 종료 시간을 큐에 새로 넣기
			else {
				time.offer(cur[1]);
			}
		}
		// time 큐의 크기가 강의실의 최종 개수
		System.out.println(time.size());
	}
}