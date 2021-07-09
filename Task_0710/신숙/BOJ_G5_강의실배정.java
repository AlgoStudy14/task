import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_G5_강의실배정 {
	/*
	 * <문제>
	 * N이 주어지고 N개의 수업이 주어진다.
	 * a ~ b 까지의 수업이 주어지는데, 최소의 강의실을 사용하고자한다. 최소로 강의실을 사용할때 개수는?
	 * A~B B~C는 같은 강의실에서 강의 가능.
	 * 
	 * <문제풀이>
	 * 예제 -> 1 3 / 3 5		2개의 강의실. 
	 * 		 2 4
	 * 1. 입력 받은 강의들을 강의 시작시간을 기준으로 정렬해야함.
	 * 2. 우선순위 큐를 생성해서 강의를 하나씩 넣을 때 비교를해봄.
	 * 3. if(peek를 해서 꺼낸 강의가 끝나는 시간이 새로 넣을 강의가 시작하는 시간보다 같거나 작으면)
	 * 		 queue에서 poll.
	 * 4. 위 경우 + 아닐때에도 큐에 새로 넣을 강의가 끝나는 시간을 넣으면 됨.
	 * 5. 답은 queue 크기
	 * 
	 * <문제점>
	 * 1. 2차원 배열 정렬하는게 기억이 안나서 검색하고 했음..
	 * 2. 문제 푸는법 생각하는게 좀 어려운것 같다.
	 */
	static int N;
	static int[][] arr;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		Arrays.sort(arr, (lec1, lec2) -> {
			if(lec1[0] == lec2[0])
				return lec1[1] - lec2[1];
			return lec1[0] - lec2[0];
		});
		
		pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			if(!pq.isEmpty()) {
				if(pq.peek() <= arr[i][0])
					pq.poll();
			}
			pq.offer(arr[i][1]);
		}
		System.out.println(pq.size());
	}
}
