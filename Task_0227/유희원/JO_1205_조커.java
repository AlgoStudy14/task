package algo0218;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 0 6 5 10 3 0 11 의 경우 -> 일단 입력 받으면서 조커 개수 2인거 저장
 * 1. 정렬
 *    0 0 3 5 6 10 11로 만들기
 * 2. 탐색은 첫번째로 0이 아닌수, 3부터 시작 (만약에 다 0이다? 그럼 걍 N이 최대 길이인겨)
 * 3. 3, 5 2차이 나니까 조커 써봄 -> 3 0 5 , 조커는 한개남음
 * 4. 3 0 5 -> 6 1차이!! 바로 이어붙임
 * 5. 3 0 5 6 까지 됐는데 조커한개로 10을 못이어붙임
 * 6. 그럼 다시 5부터 시작해서 3~4 처럼 조커 사용해서 반복해봄. 가다가 끊기면 다음 탐색ㄱㄱ
 *    
 */

public class JO_1205_조커 {

	static int N,zeros,length,ans;
	static int[] cards;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cards = new int[N];
		for (int i = 0; i <N; i++) {
			cards[i] = sc.nextInt();
			//조커개수를 입력받으면서 저장
			if(cards[i] == 0) zeros++;
		}
		sc.close();
		//0이아닌수부터 + 연속하는거 판단하기 위해 일단 정렬부터 하고 시작
		Arrays.sort(cards);
		// 모든 수가 0인 경우 
		ans = zeros==N?zeros:0;
		// 스트레이트 체크 위치를 0이 아닌 위치부터 시작
		for (int i = zeros; i <N; i++) {
			// 조커 개수를 사용(변경)하면서 사용해야 하므로 임시 변수 사용.
			int joker = zeros;
			// 첫번째 데이터는 무조건 스트레이트에 들어가므로 length는 1부터
			length = 1;
			//비교할 위치 start변수에 담아서 이것도 밑에서 조건에 따라 변경해가면서 사용
			int start = cards[i];
			// 현재 체크 위치 다음부터 스트레이트 확인 시작하자
			for (int j = i+1; j <N; j++) {
				// 같은 숫자일 경우 continue. ex. 100 100 100
				if(cards[j]==start) continue;
				// 현재 수가 이전 수와 1 차이일 경우 카운트 증가시키고 start(이전수)변경
				if(cards[j]==start+1) {
					length++;
					start++;
					continue;
				}
				// 현재수와 이전수가 2이상 차이일 경우 조커(0)을 사용하여 이전수를 1 증가시켜야 한다.
				// 만약, 조커가 없다면 붙일 수 없는 경우 -> 종료
				if(joker==0) break;
				start++;
				length++;
				joker--;
				//조커를 사용했을때 이어질때까지 다시 붙여야되는데 그냥 보내면 위에서 j++을 만나버리므로 j--를 해줘야됨
				//ex. 100 103 105가 있는 상황에서 100에서 조커 써서 101이 된 상태면 다시 103이랑 비교해야되는데 j++만나서 105로 넘어가게됨
				j--;
			}
			// 가장 최대인 스트레이트 숫자를 기억, 조커가 남아있으면 그것도 이어붙여야 하므로 length+joker
			ans = Math.max(ans, length+joker);
		}
		System.out.println(ans);
		
		
	}

}
