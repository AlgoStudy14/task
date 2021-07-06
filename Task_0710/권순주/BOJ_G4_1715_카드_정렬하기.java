import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * <문제 요약>
 * 문제 정의: N개의 숫자 카드 묶음이 각각의 크기가 주어질 때, 최소한의 비교 횟수
 * 문제 유형: 구현
 * 제약 사항: N이 1이면 비교할 필요 없음 즉 답이 0이 나와야함
 * 
 * <풀이법 요약>
 * 가장 작은 값 2개를 더해줘야 함 
 * 1. 우선순위 큐에 입력받은 card값 넣어줌
 * 2. 우선순위 큐의 크기가 1일 때까지 반복 -> 1일 때는 비교대상이 없기 때문에
 * 2-1. 우선 순위 큐에서 앞에 2개 뺌
 * 2-2. 두개 더한 값을 answer에 더해주고 우선순위큐에도 넣어줌
 * 
 * <피드백>
 * 이왜골4?
 */

public class BOJ_G4_1715_카드_정렬하기 {

	static int N;
	static PriorityQueue<Integer> cardList = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(br.readLine());
			cardList.add(card);
		}

		int answer = 0;
		// 우선순위 큐의 크기가 1일때까지 반복
		while (cardList.size() != 1) {
			// 가장 작은 값
			int first = cardList.poll();
			// 두번째로 작은 값
			int second = cardList.poll();
			// 더해서 다시 우선순위 큐에 넣어주기
			cardList.add(first + second);
			// answer에 더해주기
			answer += (first + second);
		}

		System.out.println(answer);
	}

}
