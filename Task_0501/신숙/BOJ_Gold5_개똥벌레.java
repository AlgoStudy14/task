import java.util.Scanner;

public class BOJ_Gold5_개똥벌레 {
	/*
	 * <문제>
	 * 길이 N, 높이 H인 동굴이 주어짐.
	 * N개의 줄마다 장애물들의 크기가 주어짐.
	 * 개똥벌레는 지나가며 장애물을 피하지 않고, 만나는 장애물을 모두 파괴함.
	 * 파괴해야하는 장애물의 최소 개수와 해당하는 구간의 수.
	 * 
	 * <조건>
	 * N은 항상 짝수, 2 <= N <= 200,000   2 <= H <= 500,000

	 * <풀이>
	 * 입력받기 : X가 입력받는 수일 때, 개똥벌레 높이 h. 짝수는 h로 비교. 홀수는 H - h로 비교. 
	 * 탐색하는 문제.	범위가 매우 많음. 완전탐색은 20만 X 50만인가?
	 * 시간제한 1초. 위 아래 따로 정렬해서 이진탐색?
	 * 
	 * <이진탐색>
	 * 이진 탐색은 정렬되어 있는 자료들의 집합에서 특정 자료를 찾고자 할 때 많이 사용되며 매우 빠른 탐색 알고리즘
	 * 이진 탐색은 '퀵정렬'과 유사하게 '분할정복'의 전략
	 * 이 전략을 사용하는 알고리즘은 문제를 나누어 해결해 나가는 방법이기 때문에 실행시간은 logN
	 * 다음과 같은 상황에서 이진 탐색은 효율적인 성능을 제공한다.
	 * 1) 새로운 자료가 추가되었어도 모든 자료가 재정렬이 일어나지 않는 경우 -> 해싱, 인덱스를 이용하는 경우
	 * 2) 획기적으로 빠르고, 효율적인 자료의 재정렬이 가능한 자료 구조를 사용할 경우 -> B-트리 계열의 트리 구조 사용
	 * 
						추가/삭제	조회
				배열		느림	        빠름
				리스트	빠름	        느림
	 */
	static int N, H, ans, cnt;
	static int[] top, bot, sum_t, sum_b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		H = sc.nextInt();
		top = new int[H + 1];
		bot = new int[H + 1];
		sum_t = new int[H + 1];
		sum_b = new int[H + 1];
		ans = Integer.MAX_VALUE;
		//높이마다 높이에 해당하는 돌의 개수를 저장해둠.
		for(int i = 0; i < N/2; i++) {
			bot[sc.nextInt()]++;
			top[sc.nextInt()]++;
		}
		
		for(int i = 1; i <= H; i++) {
			sum_t[i] = top[i] + sum_t[i - 1];
			sum_b[i] = bot[i] + sum_b[i - 1];
		}
		
		for(int i = 1; i <= H; i++) {
			int c = 0;
			c += sum_b[H] - sum_b[i - 1];
			c += sum_t[H] - sum_t[H - i];
			
			if(c < ans) {
				ans = c;
				cnt = 1;
			}else if(ans == c)
				cnt++;
		}
		System.out.println(ans + " " + cnt);
	}
}
