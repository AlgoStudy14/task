import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_G4_카드정렬하기 {
	/*
	 * <문제>
	 * 카드 묶음 N개가 잇다.
	 * N줄에 각각 덱에 몇장의 카드가 있는지 나오고, 최소 비교는 A + B이다.
	 * 최소의 몇번 비교를 해야하는지?
	 * 
	 * <문제풀이>
	 * 주어진 숫자들을 오름차순으로 정렬하여 
	 * A , B, C가 있다면 A + B + (A + B + C)
	 * 	=> A B를 더하고 ans에 더해준 뒤 다시 큐에 넣는것 반복.
	 * 
	 * <피드백>
	 * 강의실배정을 먼저 풀어서 그런가 더 쉽게 느껴지는..?
	 */
	static int N, ans;
	static PriorityQueue<Integer> pq;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			int cardNum = sc.nextInt();
			pq.offer(cardNum);
		}
		
		if(!pq.isEmpty()) {
			while(pq.size() != 1) {
				int a = pq.poll();
				int b = pq.poll();
				int add = a + b;
				ans += add;
				pq.offer(add);
			}
		}
		System.out.println(ans);
	}
}