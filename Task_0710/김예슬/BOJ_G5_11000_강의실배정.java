package algo0707;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 모든 수업을 가능하게 하는 최소한의 강의실의 개수 출력
 * 문제 유형 : 그리디
 * 주의 사항 : 
 * <풀이 요약>
 * 0. 수업의 시작>종료시간 순으로 오름차순 정렬해야하기 때문에 우선순위 큐 사용
 * 1. LR(강의실)에 방을 사용하고 있다는 의미로 수업이 종료하는 시간으로 구분
 * 	1-1. 수업이 끝난 후 사용 가능(강의실 사용 중 수업.종료 시간 <= 강의실을 배정받지 않은 수업.시작 시간)
 * 		LR(강의실)에 기존의 종료 시간 갱신
 *  1-2. 수업이 겹쳐 강의실을 같이 사용할 수 없는 경우
 *  	LR(강의실)에 종료 시간 추가
 * 2. 사용 중인 강의실의 개수 출력
 * <피드백>
 * 처음에 수업의 종료>시작시간 순으로 정렬을 해 결과가 제대로 나오지 않음 
 */

public class BOJ_G5_11000_강의실배정 {

	static int N, cnt;
	static PriorityQueue<Lesson> PQ;
	
	static class Lesson implements Comparable<Lesson> {
		int start, end;

		public Lesson(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Lesson l) {
			int diff = this.start - l.start;
			return diff != 0 ? diff : this.end - l.end;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		PQ = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			PQ.add(new Lesson(sc.nextInt(), sc.nextInt()));
		}

		System.out.println(solve());
	}
	
	static int solve() {
		PriorityQueue<Integer> LR = new PriorityQueue<>();
		LR.add(PQ.poll().end);
		
		while (!PQ.isEmpty()) {
			Lesson cur = PQ.poll();
			if (LR.peek() <= cur.start) {
				LR.poll();
			}
			LR.add(cur.end);						
		}
		
		return LR.size();
	}
}
