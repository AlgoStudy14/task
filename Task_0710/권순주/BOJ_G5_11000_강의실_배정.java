import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 문제 정의 : 모든 수업을 가능하게 하는 최소한의 강의실 수 
 * 문제 유형 : 구현
 * 
 * <풀이법 요약>
 * 우선순위 큐를 사용하는 이유 - 가장 작은 친구를 계속 구해줘야 하기 때문에~
 * 1. 스케쥴을 시작시간으로 오름차순 정렬한다.
 * 2. 첫 강의 끝나는 시간을 우선순위 큐에 넣어준다.
 * 3. 반복문을 진행하면서 현재 강의의 시작시간과 우선 순위 큐의 peek값을 비교한다.
 * 3-1. 현재 강의 시작시간이 우선 순위 큐의 peek값보다 크거나 같으면 우선순위큐를 poll한다.
 * 4. 현재 강의 시간의 끝나는 시간을 우선순위 큐에 넣어준다.
 * 5. 최종적으로 남아있는 즉 사용준인 강의실의 크기를 출력한다.
 * 
 * <피드백>
 * 아이디어 생각하는 것이 중요한거같다
 */

public class BOJ_G5_11000_강의실_배정 {

	static class Lecture implements Comparable<Lecture> {

		int start;
		int end;

		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// start 1차 정렬, end 2차 정렬
		@Override
		public int compareTo(Lecture o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}

	}

	static int N;
	static ArrayList<Lecture> schedule = new ArrayList<>();
	static PriorityQueue<Integer> room = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			schedule.add(new Lecture(start, end));
		}

		// 입력받은 스케줄 정렬
		Collections.sort(schedule);

		// 첫 강의의 끝나는 시간
		room.add(schedule.get(0).end);

		for (int i = 1; i < N; i++) {
			// 가장 일찍 끝나는 시간이 스케쥴의 시작 시간보다 작거나 같으면
			if (room.peek() <= schedule.get(i).start) {
				// room에서 강의 빼준다
				room.poll();
			}
			// 해당 강의의 끝나는 시간 넣어주기
			room.add(schedule.get(i).end);
		}
		
		// 남아있는 = 사용중인 강의실 수 
		System.out.println(room.size());
	}

}
