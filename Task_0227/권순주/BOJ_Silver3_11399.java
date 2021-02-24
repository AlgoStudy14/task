import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 돈을 인출하는데 걸리는 최소합
 * 문제 핵심 요약 : 정렬해서 구하면 된다
 * <풀이법 요약> 
 * 1. 줄 서는 시간 오름차순 정렬
 * 2. 나 포함 + 내 뒤에 있는 사람들 모두 나의 시간만큼 기다리기 때문에 (내가 걸린 시간) X (나 포함 남은 명수) 를 계산해서 더해준다
 */

public class Main_Silver3_11399 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] people = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		Arrays.sort(people); // 정렬
		for (int i = 0; i < N; i++) {
			answer += (people[i] * (N - i)); // 나 포함 + 내 뒤에 있는 사람들 모두 나의 시간만큼 기다리기 때문에 계산해서 넣어준다.
		}
		System.out.println(answer);
	}

}
